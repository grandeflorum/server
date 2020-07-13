package com.grandeflorum.statistic.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.PagingEntity;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.util.DateUtils;
import com.grandeflorum.statistic.dao.StatisticMapper;
import com.grandeflorum.statistic.domain.*;
import com.grandeflorum.statistic.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by 13260 on 2019/12/1.
 */
@Service("StatisticService")
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    StatisticMapper statisticMapper;


    @Override
    public ResponseBo getHouseRentalStatistic(Map<String,Object> map) {

        List<StatisticValue> list = statisticMapper.getHouseRentalStatisticDate(map);

        List<StatisticValue> listRegion = statisticMapper.getHouseRentalStatisticRegion(map);

        Map<String, Object> mapResult = new HashMap<>();
        mapResult.put("dateResult",list);
        mapResult.put("regionResult",listRegion);

        return ResponseBo.ok(mapResult);
    }

    @Override
    public ResponseBo getOverallSalesTrend(Map<String,Object> map) {

        Map<String, Object> result = new HashMap<>();

        List<StatisticValue> list = statisticMapper.getOverallSalesTrend(map);

        List<String> H = new ArrayList<>();
        List<Double> L = new ArrayList<>();

        for (int i = 1; i <= 12; i++) {
            String h = i > 9 ? (i + "月") : ("0" + i + "月");
            double v = 0;

            if (list != null) {
                int yf = i;

                StatisticValue s = null;

                if(list.stream().anyMatch(x->Integer.parseInt(x.getDateValue())==yf)){
                    s = list.stream().filter(x -> Integer.parseInt(x.getDateValue()) == yf).findFirst().get();
                }

                if (s != null) {
                    v = s.getTs();
                }
            }
            H.add(h);
            L.add(v);
        }

        result.put("X", H);
        result.put("Y", L);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getProjectSalesVolumeList(Page page){
        Map<String, Object> map = page.getQueryParameter();
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<SalesStatisticInfo> list = statisticMapper.getProjectSalesVolumeList(map);

        return ResponseBo.ok(list);
    }


    @Override
    public ResponseBo getSalesVolumeTotalList(Page page){
        Map<String, Object> map = page.getQueryParameter();
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<SalesStatisticInfo> list = statisticMapper.getSalesVolumeTotalList(map);
//        List<SalesStatisticInfo> list = new ArrayList<>();
//        if(map.get("lx").equals("1")){
//            list = statisticMapper.getHouseTradeTotalList(map);
//        }else if(map.get("lx").equals("2")){
//            list = statisticMapper.getStockTradeTotalList(map);
//        }else {
//            list = statisticMapper.getSalesVolumeTotalList(map);
//        }

        PageInfo<SalesStatisticInfo> pageInfo = new PageInfo<>(list);

        PagingEntity<SalesStatisticInfo> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getTimeQueryStatistics(Map<String,Object> map){
       // List<StatisticValue> list = statisticMapper.getTimeQueryStatistics(map);
        Map<String,Object> result = new HashMap<>();

        List<TimeTatolValue> list=statisticMapper.getTimeTotalValueList(map);
        Optional<TimeTatolValue> spf = list.stream().filter(s -> s.getLx().equals(1)).findFirst();
        Optional<TimeTatolValue> clf = list.stream().filter(s -> s.getLx().equals(2)).findFirst();
        boolean sflag = spf.isPresent();
        boolean cflag= clf.isPresent();

        double zj=0;
        double mj=0;
        double ts=0;
        double szj=0;
        double smj=0;
        double sts=0;
        double czj=0;
        double cmj=0;
        double cts=0;

        if(sflag){
            zj=zj+spf.get().getZj();
            mj=mj+spf.get().getMj();
            ts=ts+spf.get().getTs();
            szj=spf.get().getZj();
            smj=spf.get().getMj();
            sts=spf.get().getTs();
        }

        if(cflag){
            zj=zj+clf.get().getZj();
            mj=mj+clf.get().getMj();
            ts=ts+clf.get().getTs();
            czj=clf.get().getZj();
            cmj=clf.get().getMj();
            cts=clf.get().getTs();
        }


        result.put("zj",zj);
        result.put("mj",mj);
        result.put("ts",ts);

        result.put("czj",czj);
        result.put("szj",szj);
        result.put("cmj",cmj);
        result.put("smj",smj);
        result.put("cts",cts);
        result.put("sts",sts);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getSummarySalesPurposes(Map<String,Object> map){

        List<StatisticValue> list = statisticMapper.getSummarySalesPurposes(map);

        return  ResponseBo.ok(list);
    }

    @Override
    public ResponseBo getTransactionSummaryStatistic(Map<String,Object> map){

        String kssj = map.get("kssj")!=null?map.get("kssj").toString():"";
        String jssj = map.get("jssj")!=null?map.get("jssj").toString():"";
        String type = map.get("querytype")!=null?map.get("querytype").toString():"";

        TransactionSummaryResponse transactionSummaryResponse = new TransactionSummaryResponse();
        List<StatisticValue> list = new ArrayList<>();

        //1 当前
        TransactionSummaryInfo transactionSummaryInfoNow = statisticMapper.getTransactionSummaryInfo(map);

        double differenceCount = transactionSummaryInfoNow.getAlreadySoldCount() -transactionSummaryInfoNow.getUnsoldCount();
        double differenceArea = transactionSummaryInfoNow.getAlreadySoldArea() - transactionSummaryInfoNow.getUnsoldArea();
        transactionSummaryResponse.setDifferenceCount(Math.abs(differenceCount));
        transactionSummaryResponse.setDifferenceArea(Math.abs(differenceArea));

        //已售套数
        StatisticValue alreadySoldCount = new StatisticValue();
        alreadySoldCount.setTs(transactionSummaryInfoNow.getAlreadySoldCount());
        list.add(alreadySoldCount);

        //已售面积
        StatisticValue alreadySoldArea = new StatisticValue();
        alreadySoldArea.setMj(transactionSummaryInfoNow.getAlreadySoldArea());
        list.add(alreadySoldArea);

        //未售套数
        StatisticValue UnsoldCount = new StatisticValue();
        UnsoldCount.setTs(transactionSummaryInfoNow.getUnsoldCount());
        list.add(UnsoldCount);

        //未售面积
        StatisticValue UnsoldArea = new StatisticValue();
        UnsoldArea.setMj(transactionSummaryInfoNow.getUnsoldArea());
        list.add(UnsoldArea);

        //2 同比
        map.put("kssj", DateUtils.getUpDate(kssj,"Year"));
        map.put("jssj", DateUtils.getUpDate(jssj,"Year"));
        TransactionSummaryInfo transactionSummaryInfoTb = statisticMapper.getTransactionSummaryInfo(map);

        getRate(transactionSummaryInfoNow.getAlreadySoldCount(),transactionSummaryInfoTb.getAlreadySoldCount(),alreadySoldCount,1);
        getRate(transactionSummaryInfoNow.getAlreadySoldArea(),transactionSummaryInfoTb.getAlreadySoldArea(),alreadySoldArea,1);
        getRate(transactionSummaryInfoNow.getUnsoldCount(),transactionSummaryInfoTb.getUnsoldCount(),UnsoldCount,2);
        getRate(transactionSummaryInfoNow.getUnsoldArea(),transactionSummaryInfoTb.getUnsoldArea(),UnsoldArea,2);


        //环比
        if("Year".equalsIgnoreCase(type)){

            //年份查询时，同比环比一样
            alreadySoldCount.setHb(alreadySoldCount.getTb());
            alreadySoldCount.setHbFlag(alreadySoldCount.getTbFlag());

            alreadySoldArea.setHb(alreadySoldArea.getTb());
            alreadySoldArea.setHbFlag(alreadySoldArea.getTbFlag());

            UnsoldCount.setHb(UnsoldCount.getTb());
            UnsoldCount.setHbFlag(UnsoldCount.getTbFlag());

            UnsoldArea.setHb(UnsoldArea.getTb());
            UnsoldArea.setHbFlag(UnsoldArea.getTbFlag());

        }else{
            map.put("kssj", DateUtils.getUpDate(kssj,type));
            map.put("jssj", DateUtils.getUpDate(jssj,type));
            TransactionSummaryInfo transactionSummaryInfoHb = statisticMapper.getTransactionSummaryInfo(map);
            getRate(transactionSummaryInfoNow.getAlreadySoldCount(),transactionSummaryInfoHb.getAlreadySoldCount(),alreadySoldCount,1);
            getRate(transactionSummaryInfoNow.getAlreadySoldArea(),transactionSummaryInfoHb.getAlreadySoldArea(),alreadySoldArea,1);
            getRate(transactionSummaryInfoNow.getUnsoldCount(),transactionSummaryInfoHb.getUnsoldCount(),UnsoldCount,2);
            getRate(transactionSummaryInfoNow.getUnsoldArea(),transactionSummaryInfoHb.getUnsoldArea(),UnsoldArea,2);

        }

        transactionSummaryResponse.setList(list);
        return ResponseBo.ok(transactionSummaryResponse);
    }


    public void getRate(double num1,double num2,StatisticValue statisticValue,int type) {

        if (num2 == 0) {
            if(type ==1){
                statisticValue.setTbFlag("None");
            }else{
                statisticValue.setHbFlag("None");
            }
        }else{
            BigDecimal b1 = new BigDecimal(num1);
            BigDecimal b2 = new BigDecimal(num2);


            b1 = b1.subtract(b2,new MathContext(2));

            double rate = b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();

            if(type ==1){
                statisticValue.setTbFlag(rate>0?"Up":rate==0?"Equal":"Down");
                statisticValue.setTb(Math.abs(rate));
            }else {
                statisticValue.setHbFlag(rate>0?"Up":rate==0?"Equal":"Down");
                statisticValue.setHb(Math.abs(rate));
            }
        }


    }

}
