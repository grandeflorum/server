package com.grandeflorum.statistic.controller;

import com.grandeflorum.common.domain.Condition;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.PagingEntity;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.util.DateUtils;
import com.grandeflorum.statistic.domain.SalesStatisticInfo;
import com.grandeflorum.statistic.service.StatisticService;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 13260 on 2019/12/1.
 */
@RestController
@RequestMapping("Statistic")
public class StatisticController {

    @Autowired
    StatisticService statisticService;

    @PostMapping("/getHouseRentalStatistic")
    public ResponseBo getHouseRentalStatistic(@RequestBody Map<String,Object> map){
        return statisticService.getHouseRentalStatistic(map);
    }


    /**
     * 获取总销售量趋势
     * @param map
     * @return
     */
    @PostMapping("/getOverallSalesTrend")
    public ResponseBo getOverallSalesTrend(@RequestBody Map<String,Object> map){
        return statisticService.getOverallSalesTrend(map);
    }

    /**
     * 获取项目销售量列表
     * @param
     * @return
     */
    @PostMapping("/getProjectSalesVolumeList")
    public ResponseBo getProjectSalesVolumeList(@RequestBody Page page){
        return statisticService.getProjectSalesVolumeList(page);
    }

    /**
     * 获取时间查询统计分析统计值
     * @param map
     * @return
     */
    @PostMapping("/getTimeQueryStatistics")
    public ResponseBo getTimeQueryStatistics(@RequestBody Map<String,Object> map){
        return statisticService.getTimeQueryStatistics(map);
    }

    /**
     * 获取销售用途汇总
     * @param map
     * @return
     */
    @PostMapping("/getSummarySalesPurposes")
    public ResponseBo getSummarySalesPurposes(@RequestBody Map<String,Object> map){
        return statisticService.getSummarySalesPurposes(map);
    }

    /**
     * 导出
     * @param response
     * @param kssj
     * @param jssj
     * @param mc
     * @param yt
     * @param exportType
     * @throws IOException
     */
    @RequestMapping("/excelDownload")
    public void excelDownload(HttpServletResponse response, @Param("kssj") String kssj,@Param("jssj") String jssj,@Param("mc") String mc,@Param("yt")String yt,@Param("exportType") String exportType)throws IOException {
        Page page = new Page();

        page.setPageNo(1);
        page.setPageSize(Integer.MAX_VALUE);

        List<Condition> list = new ArrayList<>();

        list.add(new Condition("kssj", kssj));
        list.add(new Condition("jssj",jssj));
        list.add(new Condition("mc",mc));
        list.add(new Condition("yt",yt));

        page.setConditions(list);

        ResponseBo bo  = statisticService.getProjectSalesVolumeList(page);

        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        //生成一个表格，设置表格名称为"学生表"
        HSSFSheet sheet = workbook.createSheet("导出结果");

        //设置表格列宽度为10个字节
        sheet.setDefaultColumnWidth(10);

        //创建第一行表头
        HSSFRow headrow = sheet.createRow(0);

        String[] header =null;

        if(exportType.equals("sales")){
            header = new String[]{"项目名称","开发企业名称","销售量(套)","销售均价(元/平方米)","销售面积(平方米)"};
        }else{
            header = new String[]{"项目名称","开发企业名称","用途","均价(元/平方米)","销售面积(平方米)","销售量(套)","总价(万元)"};
        }

        //遍历添加表头
        for (int i = 0; i < header.length; i++) {
            //创建一个单元格
            HSSFCell cell = headrow.createCell(i);

            //创建一个内容对象
//            HSSFRichTextString text = new HSSFRichTextString(header[i]);

            //将内容对象的文字内容写入到单元格中
            cell.setCellValue(header[i]);
        }

        if(bo.get("code").toString().equals("200")){
            PagingEntity<SalesStatisticInfo> pe = (PagingEntity<SalesStatisticInfo>) bo.get("msg");

            List<SalesStatisticInfo> res = pe.getCurrentList();

            for(int i = 0;i<res.size();i++){

                SalesStatisticInfo ssi = res.get(i);

                HSSFRow row = sheet.createRow(i+1);

                row.createCell(0).setCellValue(ssi.getXmmc());
                row.createCell(1).setCellValue(ssi.getQymc());

                if(exportType.equals("sales")){
                    row.createCell(2).setCellValue(ssi.getXsl());
                    row.createCell(3).setCellValue(ssi.getJj());
                    row.createCell(4).setCellValue(ssi.getXsmj());
                }else{
                    row.createCell(2).setCellValue(ssi.getYt());
                    row.createCell(3).setCellValue(ssi.getJj());
                    row.createCell(4).setCellValue(ssi.getXsmj());
                    row.createCell(5).setCellValue(ssi.getXsl());
                    row.createCell(6).setCellValue(ssi.getZj());
                }

            }
        }

        //准备将Excel的输出流通过response输出到页面下载
        //八进制输出流
        response.setContentType("application/octet-stream");

        //这后面可以设置导出Excel的名称，此例中名为student.xls
        response.setHeader("Content-disposition", "attachment;filename=Statistic.xls");

        //刷新缓冲
        response.flushBuffer();

        //workbook将Excel写入到response的输出流中，供页面下载
        workbook.write(response.getOutputStream());
    }

    /**
     * 获取交易汇总统计
     * @param map
     * @return
     */
    @RequestMapping("/getTransactionSummaryStatistic")
    public ResponseBo getTransactionSummaryStatistic(@RequestBody Map<String,Object> map) {
        return statisticService.getTransactionSummaryStatistic(map);
    }
}
