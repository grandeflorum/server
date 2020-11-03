package com.grandeflorum.common.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlCursor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute;

import java.util.ArrayList;






/**
 * Created by 13260 on 2019/11/12.
 */
public class XwpfTUtil {

    /**
     * 替换段落里面的变量
     *
     * @param doc    要替换的文档
     * @param params 参数
     */
    public void replaceInPara(XWPFDocument doc, Map<String, Object> params,String id,String path) {
        Iterator<XWPFParagraph> iterator = doc.getParagraphsIterator();
        XWPFParagraph para;
        while (iterator.hasNext()) {
            para = iterator.next();
            this.replaceInPara1(para, params,id,path);
        }
    }

    /**
     * 替换段落里面的变量
     *
     * @param para   要替换的段落
     * @param params 参数
     */
    public void replaceInPara(XWPFParagraph para, Map<String, Object> params) {
        List<XWPFRun> runs;
        Matcher matcher;
        if (this.matcher(para.getParagraphText()).find()) {
            runs = para.getRuns();

            int start = -1;
            int end = -1;
            String str = "";
            for (int i = 0; i < runs.size(); i++) {
                XWPFRun run = runs.get(i);
                String runText = run.toString();
                System.out.println("------>>>>>>>>>" + runText);
                if ('$' == runText.charAt(0)&&'{' == runText.charAt(1)) {
                    start = i;
                }
                if ((start != -1)) {
                    str += runText;
                }
                if ('}' == runText.charAt(runText.length() - 1)) {
                    if (start != -1) {
                        end = i;
                        break;
                    }
                }
            }
            System.out.println("start--->"+start);
            System.out.println("end--->"+end);

            System.out.println("str---->>>" + str);

            for (int i = start; i <= end; i++) {
                para.removeRun(i);
                i--;
                end--;
                System.out.println("remove i="+i);
            }

            for (String key : params.keySet()) {
                if (str.equals(key)) {
                        para.createRun().setText((String) params.get(key));
                    break;
                }
            }


        }
    }


    /**
     * 替换段落中的参数（word）
     * @param para
     * @param params
     */
    private  void replaceInPara1(XWPFParagraph para, Map<String, Object> params,String id,String path) {
        List<XWPFRun> runs;
        Matcher matcher;
        if (matcher(para.getParagraphText()).find()) {
            runs = para.getRuns();
            for (int i=0; i<runs.size(); i++) {
                XWPFRun run = runs.get(i);
                String runText = run.toString();
                matcher = matcher(runText);
                if (matcher.find()) {

                    if("${ewm}".equalsIgnoreCase(runText)){
                        try{
                            para.removeRun(i);
                            para.insertNewRun(i).addPicture(new FileInputStream(path),XWPFDocument.PICTURE_TYPE_PNG,id+".png",Units.toEMU(150), Units.toEMU(150));
                        }catch (Exception e){
                            e.printStackTrace();
                        }


                    }else{
                        while ((matcher = matcher(runText)).find()) {
                            runText = matcher.replaceFirst(String.valueOf(params.get(matcher.group(1))));
                        }
                        UnderlinePatterns ul = run.getUnderline();

                        CTRPr ctrPr = run.getCTR().getRPr();
//                        CTR ctr =  run.getCTR();
                        para.removeRun(i);
                        para.insertNewRun(i);
                        XWPFRun pRun = para.getRuns().get(i);
                        //重新插入run里内容格式可能与原来模板的格式不一致
                        pRun.setText(runText);
                        pRun.setBold(true);//字体是否加粗
//                        pRun.getCTR().setRPr(ctrPr);
//                        pRun.setUnderline(ul);
//                        XWPFRun pRun = para.ge
//                             tRuns().get(i);
//
//                        CTRPr pRpr = null;
//                        if (pRun.getCTR() != null) {
//                            pRpr = pRun.getCTR().getRPr();
//                            if (pRpr == null) {
//                                pRpr = pRun.getCTR().addNewRPr();
//                            }
//                        }
//
//                        CTUnderline u = pRpr.isSetU() ? pRpr.getU() : pRpr.addNewU();
//                        u.setVal(STUnderline.Enum.forInt(Math.abs(1 % 19)));
                    }

                }
            }
        }
    }





    public void createFooter(XWPFDocument document) throws Exception {
        /*
         * 生成页脚段落
         * 给段落设置宽度为占满一行
         * 为公司地址和公司电话左对齐，页码右对齐创造条件
         * */
        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(document, sectPr);
        XWPFFooter footer =  headerFooterPolicy.createFooter(STHdrFtr.DEFAULT);
        XWPFParagraph paragraph = footer.getParagraphArray(0);
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        paragraph.setVerticalAlignment(TextAlignment.CENTER);
        paragraph.setBorderTop(Borders.THICK);
        CTTabStop tabStop = paragraph.getCTP().getPPr().addNewTabs().addNewTab();
        tabStop.setVal(STTabJc.RIGHT);
        int twipsPerInch =  1440;
        tabStop.setPos(BigInteger.valueOf(6 * twipsPerInch));

        /*
         * 给段落创建元素
         * 设置元素字面为公司地址+公司电话
         * */
        XWPFRun run = paragraph.createRun();
        //run.setText((StringUtils.isNotEmptyOrNull(orgAddress) ? orgAddress : "") + (StringUtils.isNotEmptyOrNull(telephone) ? "  " + telephone: ""));
        setXWPFRunStyle(run,"仿宋",10);
        run.addTab();

        /*
         * 生成页码
         * 页码右对齐
         * */
        run = paragraph.createRun();
        run.setText("第");
        setXWPFRunStyle(run,"仿宋",10);

        run = paragraph.createRun();
        CTFldChar fldChar = run.getCTR().addNewFldChar();
        fldChar.setFldCharType(STFldCharType.Enum.forString("begin"));

        run = paragraph.createRun();
        CTText ctText = run.getCTR().addNewInstrText();
        ctText.setStringValue("PAGE  \\* MERGEFORMAT");
        ctText.setSpace(SpaceAttribute.Space.Enum.forString("preserve"));
        setXWPFRunStyle(run,"仿宋",10);

        fldChar = run.getCTR().addNewFldChar();
        fldChar.setFldCharType(STFldCharType.Enum.forString("end"));

        run = paragraph.createRun();
        run.setText("页 总共");
        setXWPFRunStyle(run,"仿宋",10);

        run = paragraph.createRun();
        fldChar = run.getCTR().addNewFldChar();
        fldChar.setFldCharType(STFldCharType.Enum.forString("begin"));

        run = paragraph.createRun();
        ctText = run.getCTR().addNewInstrText();
        ctText.setStringValue("NUMPAGES  \\* MERGEFORMAT ");
        ctText.setSpace(SpaceAttribute.Space.Enum.forString("preserve"));
        setXWPFRunStyle(run,"仿宋",10);

        fldChar = run.getCTR().addNewFldChar();
        fldChar.setFldCharType(STFldCharType.Enum.forString("end"));

        run = paragraph.createRun();
        run.setText("页");
        setXWPFRunStyle(run,"仿宋",10);

    }


    /**
     * 设置页脚的字体样式
     *
     * @param r1 段落元素
     */
    private void setXWPFRunStyle(XWPFRun r1,String font,int fontSize) {
        r1.setFontSize(fontSize);
        CTRPr rpr = r1.getCTR().isSetRPr() ? r1.getCTR().getRPr() : r1.getCTR().addNewRPr();
        CTFonts fonts = rpr.isSetRFonts() ? rpr.getRFonts() : rpr.addNewRFonts();
        fonts.setAscii(font);
        fonts.setEastAsia(font);
        fonts.setHAnsi(font);
    }


    /**
     * @param doc
     * @param id   id
     * @param logoFilePath   二维码图片的地址
     * @throws Exception
     */
    public void createFooter(XWPFDocument doc, String id, String logoFilePath) throws Exception {
        List<XWPFFooter> footerList=  doc.getFooterList();
        for (XWPFFooter xwpfFooter:footerList  ){
          List<XWPFParagraph> paragraphs=  xwpfFooter.getParagraphs();
            for (XWPFParagraph paragraph:paragraphs){
                if (matcher(paragraph.getParagraphText()).find()) {
                    Matcher matcher;
                    List<XWPFRun> runs=paragraph.getRuns();
                    for (int i=0; i<runs.size(); i++) {
                        XWPFRun run = runs.get(i);
                        String runText = run.toString();
                        matcher = matcher(runText);
                        if (matcher.find()) {
                            if("${ewm1}".equalsIgnoreCase(runText)){
                                try{
                                    paragraph.removeRun(i);
                                    XWPFPicture picture= paragraph.insertNewRun(i).addPicture(new FileInputStream(logoFilePath),XWPFDocument.PICTURE_TYPE_PNG,id+".png",Units.toEMU(60), Units.toEMU(60));
                                    String blipID = "";
                                    for(XWPFPictureData picturedata : xwpfFooter.getAllPackagePictures()) {    //这段必须有，不然打开的logo图片不显示
                                        blipID = xwpfFooter.getRelationId(picturedata);
                                    }
                                    picture.getCTPicture().getBlipFill().getBlip().setEmbed(blipID);
                                    paragraph.getRuns().get(i).addTab();
                                }catch (Exception e){
                                    e.printStackTrace();
                                }


                            }
                        }
                    }
                }
            }
        }
    }


    public void createPageFooter(String file) throws Exception {
        XWPFDocument document= new XWPFDocument();


        //添加标题
        XWPFParagraph titleParagraph = document.createParagraph();
        //设置段落居中
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun titleParagraphRun = titleParagraph.createRun();

        titleParagraphRun.setText("Java PoI");
        titleParagraphRun.setColor("000000");
        titleParagraphRun.setFontSize(20);
        //段落
        XWPFParagraph firstParagraph = document.createParagraph();
        XWPFRun run = firstParagraph.createRun();
        run.setText("Java POI 生成word文件。");
        run.setColor("696969");
        run.setFontSize(16);

        //设置段落背景颜色
        CTShd cTShd = run.getCTR().addNewRPr().addNewShd();
        cTShd.setVal(STShd.CLEAR);
        cTShd.setFill("97FFFF");

        //换行
        XWPFParagraph paragraph1 = document.createParagraph();
        XWPFRun paragraphRun1 = paragraph1.createRun();
        paragraphRun1.setText("\r");

        //基本信息表格
        XWPFTable infoTable = document.createTable();
        //去表格边框
        infoTable.getCTTbl().getTblPr().unsetTblBorders();

        //列宽自动分割
        CTTblWidth infoTableWidth = infoTable.getCTTbl().addNewTblPr().addNewTblW();
        infoTableWidth.setType(STTblWidth.DXA);
        infoTableWidth.setW(BigInteger.valueOf(9072));

        //表格第一行
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.getCell(0).setText("职位");
        infoTableRowOne.addNewTableCell().setText(": Java 开发工程师");

        //表格第二行
        XWPFTableRow infoTableRowTwo = infoTable.createRow();
        infoTableRowTwo.getCell(0).setText("姓名");
        infoTableRowTwo.getCell(1).setText(": seawater");

        //表格第三行
        XWPFTableRow infoTableRowThree = infoTable.createRow();
        infoTableRowThree.getCell(0).setText("生日");
        infoTableRowThree.getCell(1).setText(": xxx-xx-xx");

        //表格第四行
        XWPFTableRow infoTableRowFour = infoTable.createRow();
        infoTableRowFour.getCell(0).setText("性别");
        infoTableRowFour.getCell(1).setText(": 男");

        //表格第五行
        XWPFTableRow infoTableRowFive = infoTable.createRow();
        infoTableRowFive.getCell(0).setText("现居地");
        infoTableRowFive.getCell(1).setText(": xx");


        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);

        XWPFParagraph pic = document.createParagraph();
        pic.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun picRun = pic.createRun();
        List<String> filePath = new ArrayList<String>();

        for (String str:filePath) {
            picRun.setText(str);
            picRun.addPicture(
                    new FileInputStream(str),XWPFDocument.PICTURE_TYPE_JPEG,
                    str,
                    Units.toEMU(450),
                    Units.toEMU(300)
            );
        }
        //添加页眉
        CTP ctpHeader = CTP.Factory.newInstance();
        CTR ctrHeader = ctpHeader.addNewR();
        CTText ctHeader = ctrHeader.addNewT();
        String headerText = "ctpHeader";
        ctHeader.setStringValue(headerText);
        XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, document);
        //设置为右对齐
        headerParagraph.setAlignment(ParagraphAlignment.RIGHT);
        XWPFParagraph[] parsHeader = new XWPFParagraph[1];
        parsHeader[0] = headerParagraph;
        policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);

        //添加页脚
        CTP ctpFooter = CTP.Factory.newInstance();
        CTR ctrFooter = ctpFooter.addNewR();
        CTText ctFooter = ctrFooter.addNewT();
        String footerText = "ctpFooter";
        ctFooter.setStringValue(footerText);
        XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooter, document);
        headerParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFParagraph[] parsFooter = new XWPFParagraph[1];
        parsFooter[0] = footerParagraph;
        policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, parsFooter);
    }






    /**
     * 正则匹配字符串
     *
     * @param str
     * @return
     */
    private Matcher matcher(String str) {
        Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        return matcher;
    }

    public void setStyle(XWPFDocument doc,XWPFDocument tempdoc, Map<String, Object> params) {
        Iterator<XWPFParagraph> iterator = doc.getParagraphsIterator();
        Iterator<XWPFParagraph> iterator2 = tempdoc.getParagraphsIterator();
        XWPFParagraph para ;
        XWPFParagraph para2;
        while (iterator.hasNext()) {
            para = iterator.next();
            para2 = iterator2.next();
            this.styleInPara(para,para2, params);
        }
    }
    private void styleInPara(XWPFParagraph para, XWPFParagraph para2,Map<String, Object> params) {
        List<XWPFRun> runs;
        List<XWPFRun> runs2;
        Matcher matcher;
        if (matcher(para.getParagraphText()).find()) {
            runs = para.getRuns();
            runs2 = para2.getRuns();
            for (int i=0; i<runs.size(); i++) {
                XWPFRun run = runs.get(i);
                XWPFRun run2 = runs2.get(i);
                String runText = run.toString();
                matcher = matcher(runText);
                if (matcher.find()) {
                    //按模板文件格式设置格式
                    run2.getCTR().setRPr(run.getCTR().getRPr());
                }
            }
        }
    }

    /**
     * 关闭输入流
     *
     * @param is
     */
    public void close(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭输出流
     *
     * @param os
     */
    public void close(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
