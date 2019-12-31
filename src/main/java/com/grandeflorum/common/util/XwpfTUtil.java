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

    public   void setStyle(XWPFDocument doc,XWPFDocument tempdoc, Map<String, Object> params) {
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
    private  void styleInPara(XWPFParagraph para, XWPFParagraph para2,Map<String, Object> params) {
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
