package com.grandeflorum.buildingTable.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grandeflorum.buildingTable.dao.*;
import com.grandeflorum.buildingTable.domain.*;
import com.grandeflorum.buildingTable.service.BuildingTableService;
import com.grandeflorum.common.cache.EHCacheUtils;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.PagingEntity;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.util.GuidHelper;
import com.grandeflorum.contract.service.HouseTradeService;
import com.grandeflorum.contract.service.StockTradeService;
import com.grandeflorum.project.dao.WFAuditMapper;
import com.grandeflorum.project.domain.AuditParam;
import com.grandeflorum.project.domain.WFAudit;
import com.grandeflorum.system.domain.SystemUser;
import com.grandeflorum.system.service.SystemUserService;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


@Service("buildingTableService")
public class BuildingTableServiceImpl implements BuildingTableService {
    @Autowired
    private BuildingTableMapper buildingTableMapper;

    @Autowired
    HouseTradeService houseTradeService;

    @Autowired
    StockTradeService stockTradeService;

    @Autowired
    private ZRZMapper zrzMapper;

    @Autowired
    private LJZMapper ljzMapper;

    @Autowired
    private CMapper cMapper;

    @Autowired
    private HMapper hMapper;

    @Autowired
    SystemUserService systemUserService;

    @Autowired
    WFAuditMapper wFAuditMapper;

    @Autowired
    private CacheManager cacheManager;

    @Override
    public ResponseBo getInfoByZh(String ZH,String Type){
        if(Type.equals("1")){
            List<String> zrzId = buildingTableMapper.getZrzId(ZH);

            if(zrzId!=null&&zrzId.size()>0){
                return getZrz(zrzId.get(0));
            }
        }else if(Type.equals("2")){
            List<String> ljzId = buildingTableMapper.getLjzId(ZH);

            if(ljzId!=null&&ljzId.size()>0){
                return getLjz(ljzId.get(0));
            }
        }

        return null;
    }

    @Override
    public ResponseBo getBuildingTableList(Page page) {

        Map<String, Object> map = page.getQueryParameter();

        //获取过滤条件
        systemUserService.getSelectInfo(map);

        //需要对经纪公司特殊处理
        SystemUser user = EHCacheUtils.getCurrentUser(cacheManager);

        List<String> roles = user.getRoles();

        if(roles.contains("经纪公司")){
            map.put("needFilter",false);
        }

        PageHelper.startPage(page.getPageNo(), page.getPageSize());

        List<ResultList> list = buildingTableMapper.getBuildingTableList(map);

        PageInfo<ResultList> pageInfo = new PageInfo<>(list);

        PagingEntity<ResultList> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getLjz(String id){

        LJZ result = buildingTableMapper.getLjz(id);

        getLPB(result);

        return ResponseBo.ok(result);
    }

    @Override
    public  ResponseBo getZrz(String id){
        ZRZ zrz = buildingTableMapper.getZrz(id);

        List<LJZ> ljz = buildingTableMapper.getLjzList(zrz.getZrzh());

        if(ljz!=null &&ljz.size()>0){
            getLPB(ljz.get(0));
        }

        zrz.setLjzList(ljz);

        return ResponseBo.ok(zrz);
    }


    private void getLPB(LJZ result) {
        Map<String, Object> map = new HashMap<>();
        map.put("zrzh", result.getZrzh());
        map.put("ljzh", result.getLjzh());

        result.setcList(new ArrayList<>());
        result.setDyList(new ArrayList<>());

        List<C> cList = buildingTableMapper.getCList(map);
        List<H> hList = buildingTableMapper.getHList(map);
        Integer HDataSource=buildingTableMapper.getZrzDataSource(result.getZrzh());
        if (HDataSource == 2 ){
            for (int i = 0; i < hList.size(); i++){
                H h =hList.get(i);
                if (h.getTradeType()==0){
                    h.setTradeType(1);
                }
            }

        }

        if (cList != null && cList.size() > 0 ) {

            result.setLjzStatistical(new LJZStatistical());
            result.getLjzStatistical().setZmj(new BigDecimal(hList.stream().mapToDouble(H::getScjzmj).sum()).setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue());
            result.getLjzStatistical().setZts(hList.size());
            result.getLjzStatistical().setDyts((int)hList.stream().filter(x-> x.getStatus()==1).count());
            result.getLjzStatistical().setDymj(new BigDecimal(hList.stream().filter(x->x.getStatus()==1).mapToDouble(H::getScjzmj).sum()).setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue());
            result.getLjzStatistical().setWsmj(new BigDecimal(hList.stream().filter(x->x.getStatus()==0).mapToDouble(H::getScjzmj).sum()).setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue());
            result.getLjzStatistical().setWsts((int)hList.stream().filter(x->x.getStatus()==0).count());
            result.getLjzStatistical().setYsmj(new BigDecimal(hList.stream().filter(x->x.getStatus()!=0).mapToDouble(H::getScjzmj).sum()).setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue());
            result.getLjzStatistical().setYsts((int)hList.stream().filter(x->x.getStatus()!=0).count());


            //1.获取单元合并数
            int Dys=1;
            if(hList!=null&&hList.size()>0){
                 Dys = hList.stream().max(Comparator.comparingInt(H::getDyh)).map(x -> x.getDyh()).get();
            }
            for (int i = 1; i <= Dys; i++) {
                DY d = new DY();
                d.setName(i + "单元");

                int rowSpan = 0;

                if(hList!=null&&hList.size()>0){
                    for (C c : cList) {
                        int count = 0;
                        if (c.getSfqfdy().equals("0")) {
                            if (i == 1) {
                                int copyDyh = Dys;

                                while (copyDyh > 0) {
                                    int dy = copyDyh;

                                    List<H> filter = hList.stream().filter(x -> x.getCh() == c.getSjc() && x.getDyh() == dy).collect(Collectors.toList());

                                    if (filter != null && filter.size() > 0) {
                                        count += filter.stream().max(Comparator.comparingInt(H::getHbh)).map(x -> x.getHbh()).get();
                                    }
                                    copyDyh--;
                                }
                            } else {
                                count = 0;
                            }
                        } else {
                            int dy = i;

                            List<H> filter = hList.stream().filter(x -> x.getCh() == c.getSjc() && x.getDyh() == dy).collect(Collectors.toList());

                            if (filter != null && filter.size() > 0) {
                                count = filter.stream().max(Comparator.comparingInt(H::getHbh)).map(x -> x.getHbh()).get();
                            }
                        }
                        if (count > rowSpan) {
                            rowSpan = count > 10 ? 10 : count;
                        }
                        if (count > c.getCount()) {
                            c.setCount(count);
                        }
                    }
                }else{
                    rowSpan=1;
                }


                d.setRowSpan(rowSpan);

                result.getDyList().add(d);
            }

            //获取层
            for (int i = 0; i < cList.size(); i++) {

                C info = cList.get(i);
                int count = info.getCount();

                //暂时处理 户信息为空时层出不来
//                if(count==0&&result.getDyList().size()>0&&result.getDyList().get(0).getRowSpan()>0) {
//                    count = 1;
//                }

                int index = 0;

                int[] list = new int[Dys];
                if (info.getSfqfdy().equals("0")) {
                    for (int k = 0; k < Dys; k++) {
                        int dy = k + 1;

                        int ct = 0;

                        List<H> filter = hList.stream().filter(x -> x.getCh() == info.getSjc() && x.getDyh() == dy).collect(Collectors.toList());

                        if (filter != null && filter.size() > 0) {
                            ct = filter.stream().max(Comparator.comparingInt(H::getHbh)).map(x -> x.getHbh()).get();
                        }

                        if (k == 0) {
                            list[k] = ct;
                        } else {
                            list[k] = list[k - 1] + ct;
                        }
                    }
                }

                C c = new C();
                c.setId(info.getId());
                c.setCh(info.getCh());
                c.setCount(info.getCount());
                c.setSfqfdy(info.getSfqfdy());
                c.setSjc(info.getSjc());
                c.sethList(new ArrayList<>());

                result.getcList().add(c);


                while (count > 0) {

                    if(index != 0 ){
                        c = new C();
                        c.setId(info.getId());
                        c.setCh(info.getCh());
                        c.setCount(info.getCount());
                        c.setSfqfdy(info.getSfqfdy());
                        c.setSjc(info.getSjc());
                        c.sethList(new ArrayList<>());
                    }

                    for (int j = 0; j < result.getDyList().size(); j++) {
                        int dy = j + 1;
                        DY d = result.getDyList().get(j);

                        for (int z = 1; z <= d.getRowSpan(); z++) {

                            int inx = index * d.getRowSpan() + z;

                            if (c.getSfqfdy().equals("0")) {
                                if (j == 0) {
                                    if (list[list.length - 1] < inx) {
                                        c.gethList().add(new H());
                                    } else {
                                        for (int m = 0; m < list.length; m++) {
                                            int dd = m + 1;
                                            int inz = 0;
                                            if (list[m] >= inx) {
                                                if (m == 0) {
                                                    inz = inx;
                                                } else {
                                                    inz = inx - list[m - 1];
                                                }
                                                int ini = inz;

                                                H h;
                                                C cp = c;
                                                List<H> filter = hList.stream().filter(x -> x.getDyh() == dd && x.getCh() == cp.getSjc() && x.getHbh() == ini).collect(Collectors.toList());

                                                if (filter != null && filter.size() > 0) {
                                                    h = filter.stream().findFirst().get();
                                                } else {
                                                    h = new H();
                                                }
                                                c.gethList().add(h);
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    c.gethList().add(new H());
                                }
                            } else {

                                H h;
                                C cp = c;
                                List<H> filter = hList.stream().filter(x -> x.getDyh() == dy && x.getCh() == cp.getSjc() && x.getHbh() == inx).collect(Collectors.toList());

                                if (filter != null && filter.size() > 0) {
                                    h = filter.stream().findFirst().get();
                                } else {
                                    h = new H();
                                }
                                c.gethList().add(h);
                            }
                        }
                    }

                    if(index!=0){
                        result.getcList().add(c);
                    }

                    index++;
                    count -= 10;
                }
            }
        }
    }

    @Override
    public void printHt(String id,int type,HttpServletResponse response){

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id",id);
        map.put("type",type);

        String tradeId = buildingTableMapper.getTradeIdByHouseId(map);
        if(type==1){
            houseTradeService.previewHt(tradeId,response);
        }else{
            stockTradeService.previewHt(tradeId,response);
        }
    }

    @Override
    public ResponseBo saveOrUpdateZRZ(ZRZ zrz) {
        zrz.setGxsj(new Date());

        Example exampleZRZHRepeat = new Example(ZRZ.class);
        exampleZRZHRepeat.createCriteria().andEqualTo("zrzh",zrz.getZrzh());
        List<ZRZ> zrzhRepeatList=zrzMapper.selectByExample(exampleZRZHRepeat);

        Example exampleZRZRepeat = new Example(ZRZ.class);
        exampleZRZRepeat.createCriteria().andEqualTo("jzwmc",zrz.getJzwmc()).andEqualTo("xmmc",zrz.getXmmc());
        List<ZRZ> zrzRepeatList=zrzMapper.selectByExample(exampleZRZRepeat);


        if (zrz.getId() == null) {
            if(zrzhRepeatList!=null&&zrzhRepeatList.size()>0){
                return ResponseBo.error("自然幢号重复");
            }
            if(zrzRepeatList!=null&&zrzRepeatList.size()>0){
                return ResponseBo.error("存在相同项目名称与建筑物名称数据");
            }
            zrz.setId(GuidHelper.getGuid());
            zrz.setZt("1");
            zrz.setAuditType(0);
//            zrz.setZrzh();
            zrzMapper.insert(zrz);

        }else{
            if(zrzhRepeatList!=null&&zrzhRepeatList.size()>0){
                if(!zrzhRepeatList.get(0).getId().equals(zrz.getId()))
                    return ResponseBo.error("自然幢号重复");
            }
            if(zrzRepeatList!=null&&zrzRepeatList.size()>0){
                if(!zrzRepeatList.get(0).getId().equals(zrz.getId()))
                return ResponseBo.error("存在相同项目名称与建筑物名称数据");
            }

            zrzMapper.updateByPrimaryKey(zrz);
        }
        return ResponseBo.ok(zrz);
    }

    @Override
    public ResponseBo getZRZById(String id) {
        ZRZ zrz=buildingTableMapper.getZrz(id);
        return ResponseBo.ok(zrz);
    }

    @Override
    public ResponseBo deleteZRZ(String id){
        ZRZ zrz= zrzMapper.selectByPrimaryKey(id);
        if(zrz!=null&&zrz.getZrzh()!=null){
            Example exampleLJZ = new Example(LJZ.class);
            exampleLJZ.createCriteria().andEqualTo("zrzh", zrz.getZrzh());
            List<LJZ> ljzList=ljzMapper.selectByExample(exampleLJZ);
            if(ljzList!=null&&ljzList.size()>0){
                return ResponseBo.error("该自然幢下存在逻辑幢信息，不能删除");
            }else{
                zrzMapper.deleteByPrimaryKey(id);
            }
        }
        return ResponseBo.ok();
    }


    @Override
    public ResponseBo saveOrUpdateLJZ(LJZ ljz) {

        Example exampleLJZHRepeat = new Example(LJZ.class);
        exampleLJZHRepeat.createCriteria().andEqualTo("ljzh", ljz.getLjzh());
        List<LJZ> ljzhRepeatList=ljzMapper.selectByExample(exampleLJZHRepeat);

        Example exampleLJZRepeat = new Example(LJZ.class);
        exampleLJZRepeat.createCriteria().andEqualTo("zrzh", ljz.getZrzh()).andEqualTo("mph",ljz.getMph());
        List<LJZ> ljzRepeatList=ljzMapper.selectByExample(exampleLJZRepeat);
        if(ljz.getId()==null){
            if(ljzhRepeatList!=null&&ljzhRepeatList.size()>0){
                return ResponseBo.error("逻辑幢号重复");
            }
            if(ljzRepeatList!=null&&ljzRepeatList.size()>0){
                return ResponseBo.error("该楼幢门牌号重复");
            }
            ljz.setId(GuidHelper.getGuid());
//            ljz.setLjzh();
            ljz.setZt("1");
            ljzMapper.insert(ljz);
        }else{
            if(ljzhRepeatList!=null&&ljzhRepeatList.size()>0){
                if(!ljzhRepeatList.get(0).getId().equals(ljz.getId()))
                    return ResponseBo.error("逻辑幢号重复");
            }
            if(ljzRepeatList!=null&&ljzRepeatList.size()>0){
                if(!ljzRepeatList.get(0).getId().equals(ljz.getId()))
                    return ResponseBo.error("该楼幢门牌号重复");
            }
            ljzMapper.updateByPrimaryKey(ljz);
        }
        return ResponseBo.ok(ljz);
    }

    @Override
    public ResponseBo getLJZById(String id) {
        LJZ ljz=buildingTableMapper.getLjz(id);
        return ResponseBo.ok(ljz);
    }

    @Override
    public ResponseBo deleteLJZ(String id) {
        LJZ ljz= ljzMapper.selectByPrimaryKey(id);
        if(ljz!=null&&ljz.getZrzh()!=null&&ljz.getLjzh()!=null){
            Example exampleC = new Example(C.class);
            exampleC.createCriteria().andEqualTo("zrzh", ljz.getZrzh()).andEqualTo("ljzh",ljz.getLjzh());
            List<C> cList=cMapper.selectByExample(exampleC);
            if(cList!=null&&cList.size()>0){
                return ResponseBo.error("该逻辑幢下存在层信息，不能删除");
            }else{
                ljzMapper.deleteByPrimaryKey(id);
            }
        }else{
            return ResponseBo.error("删除失败，数据信息有误");
        }
        return ResponseBo.ok();
    }


    @Override
    public ResponseBo saveOrUpdateC(C c) {
        Example exampleCRepeat = new Example(C.class);
        exampleCRepeat.createCriteria().andEqualTo("zrzh", c.getZrzh()).andEqualTo("ljzh",c.getLjzh()).andEqualTo("sjc",c.getSjc());
        List<C> cRepeatList=cMapper.selectByExample(exampleCRepeat);

        if(c.getId()==null){
            if(cRepeatList!=null&&cRepeatList.size()>0){
                return ResponseBo.error("该楼幢存在相同的层");
            }
            c.setId(GuidHelper.getGuid());
            c.setZt("1");
            cMapper.insert(c);
        }else{
            if(cRepeatList!=null&&cRepeatList.size()>0){
                if(!cRepeatList.get(0).getId().equals(c.getId())){
                    return ResponseBo.error("该楼幢存在相同的层");
                }
            }
            cMapper.updateByPrimaryKey(c);
        }
        return ResponseBo.ok(c);
    }

    @Override
    public ResponseBo getCById(String id) {
        C c=buildingTableMapper.getC(id);
        return ResponseBo.ok(c);
    }


    @Override
    public ResponseBo deleteC(String id) {
        C c= cMapper.selectByPrimaryKey(id);
        if(c!=null&&c.getZrzh()!=null&&c.getLjzh()!=null){
            Example exampleH = new Example(H.class);
            exampleH.createCriteria().andEqualTo("zrzh", c.getZrzh()).andEqualTo("ljzh",c.getLjzh()).andEqualTo("ch",c.getSjc());
            List<H> hList=hMapper.selectByExample(exampleH);
            if(hList!=null&&hList.size()>0){
                return ResponseBo.error("该层下存在户信息，不能删除");
            }else{
                cMapper.deleteByPrimaryKey(id);
            }
        }else{
            return ResponseBo.error("删除失败，数据信息有误");
        }
        return ResponseBo.ok();
    }


    @Override
    public ResponseBo saveOrUpdateH(H h){
        Example exampleHbhRepeat = new Example(H.class);
        exampleHbhRepeat.createCriteria().andEqualTo("ljzh", h.getLjzh()).andEqualTo("dyh",h.getDyh()).andEqualTo("ch",h.getCh()).andEqualTo("hbh",h.getHbh());
        List<H> hbhRepeatlist=hMapper.selectByExample(exampleHbhRepeat);

        Example exampleSHBWRepeat = new Example(H.class);
        exampleSHBWRepeat.createCriteria().andEqualTo("ljzh", h.getLjzh()).andEqualTo("dyh",h.getDyh()).andEqualTo("shbw",h.getShbw());
        List<H> shbwRepeatlist=hMapper.selectByExample(exampleSHBWRepeat);

        if (h.getId() == null) {
            if(hbhRepeatlist!=null&&hbhRepeatlist.size()>0){
                return ResponseBo.error("同一单元同一层内户编号重复");
            }
            if(shbwRepeatlist!=null&&shbwRepeatlist.size()>0){
                return ResponseBo.error("同一单元内室号部位重复");
            }
            h.setId(GuidHelper.getGuid());
            h.setZt("1");
            h.setAuditType(0);
            hMapper.insert(h);
        }else{
            if(hbhRepeatlist!=null&&hbhRepeatlist.size()>0){
                if(!hbhRepeatlist.get(0).getId().equals(h.getId())){
                    return ResponseBo.error("同一单元同一层内户编号重复");
                }
            }
            if(shbwRepeatlist!=null&&shbwRepeatlist.size()>0){
                if(!shbwRepeatlist.get(0).getId().equals(h.getId())){
                    return ResponseBo.error("同一单元内室号部位重复");
                }
            }
            hMapper.updateByPrimaryKey(h);
        }
        return ResponseBo.ok(h);
    }

    @Override
    public ResponseBo getHById(String id) {
        H h=buildingTableMapper.getH(id);
        return ResponseBo.ok(h);
    }


    @Override
    public ResponseBo deleteH(String id){
        hMapper.deleteByPrimaryKey(id);
        return ResponseBo.ok();
    }

    @Override
    public ResponseBo getChildHList(Page page) {
        Map<String, Object> map = page.getQueryParameter();

        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<H> list = buildingTableMapper.getChildHList(map);

        PageInfo<H> pageInfo = new PageInfo<>(list);

        PagingEntity<H> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    @Transactional
    public ResponseBo saveOrUpdateZRZandLJZ(ZRZ zrz) {
        saveOrUpdateZRZ(zrz);
        if(zrz.getLjzList()!=null&&zrz.getLjzList().size()>0){
            saveOrUpdateLJZ(zrz.getLjzList().get(0));
        }
        return ResponseBo.ok(zrz);
    }

    @Override
    public ResponseBo auditZRZs(AuditParam param) {
        WFAudit wf = param.getWfAudit();
        for (String id : param.ids) {
            //更新项目表信息

            if(param.getType()==0){
                if(param.getWfAudit().getShjg()==1){
                    auditZRZById(id, 2);
                }else{
                    auditZRZById(id, 3);
                }
            }else if(param.getType() == 1){
                if(param.getWfAudit().getShjg()==1){
                    auditZRZById(id, 4);
                }else{
                    auditZRZById(id, 3);
                }
            }

            //添加或更新审核表信息
            WFAudit wfAudit = new WFAudit();
            wfAudit.setId(GuidHelper.getGuid());

            wfAudit.setShjg(wf.getShjg());
            wfAudit.setShry(wf.getShry());
            wfAudit.setShrq(wf.getShrq());
            wfAudit.setBz(wf.getBz());

            wfAudit.setProjectid(id);
            wfAudit.setSysDate(new Date());
            wfAudit.setSysUpdDate(new Date());

            wFAuditMapper.insert(wfAudit);
        }
        return ResponseBo.ok();
    }


    @Override
    public ResponseBo auditZRZById(String id, int type) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("type", type);

        buildingTableMapper.auditZRZById(map);

        return ResponseBo.ok();
    }

    @Override
    public ResponseBo getBAHistory(Page page) {
        Map<String, Object> map = page.getQueryParameter();

        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<BAHistory> list = buildingTableMapper.getBAHistory(map);

        PageInfo<BAHistory> pageInfo = new PageInfo<>(list);

        PagingEntity<BAHistory> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }
    @Override
    public ResponseBo getZrzDataSource(String zrzh) {
        Integer dataSource=buildingTableMapper.getZrzDataSource(zrzh);
        return ResponseBo.ok(dataSource);
    }

}
