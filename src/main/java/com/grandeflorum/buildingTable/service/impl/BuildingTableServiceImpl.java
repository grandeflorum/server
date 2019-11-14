package com.grandeflorum.buildingTable.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grandeflorum.buildingTable.dao.BuildingTableMapper;
import com.grandeflorum.buildingTable.domain.*;
import com.grandeflorum.buildingTable.service.BuildingTableService;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.PagingEntity;
import com.grandeflorum.common.domain.ResponseBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


@Service("buildingTableService")
public class BuildingTableServiceImpl implements BuildingTableService {
    @Autowired
    private BuildingTableMapper buildingTableMapper;

    @Override
    public ResponseBo getBuildingTableList(Page page) {

        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();

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

        if (cList != null && cList.size() > 0 && hList != null && hList.size() > 0) {

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
            int Dys = hList.stream().max(Comparator.comparingInt(H::getDyh)).map(x -> x.getDyh()).get();

            for (int i = 1; i <= Dys; i++) {
                DY d = new DY();
                d.setName(i + "单元");

                int rowSpan = 0;

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

                d.setRowSpan(rowSpan);

                result.getDyList().add(d);
            }

            //获取层
            for (int i = 0; i < cList.size(); i++) {

                C info = cList.get(i);
                int count = info.getCount();
                int index = 0;

                int[] list = new int[Dys];
                if (info.getSfqfdy().equals("0")) {
                    for (int k = 0; k < Dys; k++) {
                        int dy = k + 1;

                        int ct = 0;

                        List<H> filter = hList.stream().filter(x -> x.getCh() == info.getCh() && x.getDyh() == dy).collect(Collectors.toList());

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

                while (count > 0) {
                    C c = new C();
                    c.setId(info.getId());
                    c.setCh(info.getCh());
                    c.setCount(info.getCount());
                    c.setSfqfdy(info.getSfqfdy());
                    c.setSjc(info.getSjc());
                    c.sethList(new ArrayList<>());

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
                                                List<H> filter = hList.stream().filter(x -> x.getDyh() == dd && x.getCh() == c.getSjc() && x.getHbh() == ini).collect(Collectors.toList());

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

                                List<H> filter = hList.stream().filter(x -> x.getDyh() == dy && x.getCh() == c.getSjc() && x.getHbh() == inx).collect(Collectors.toList());

                                if (filter != null && filter.size() > 0) {
                                    h = filter.stream().findFirst().get();
                                } else {
                                    h = new H();
                                }
                                c.gethList().add(h);
                            }
                        }
                    }

                    result.getcList().add(c);

                    index++;
                    count -= 10;
                }
            }
        }
    }

}
