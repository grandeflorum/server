package com.grandeflorum.common.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.List;

public class WordHelper {

    public static String wordToHtml(String sourceFileName) throws Exception {
        if (sourceFileName.endsWith(".doc")) {
            String content = docToHtml(sourceFileName);
            return content;
        }
        if (sourceFileName.endsWith(".docx")) {
            String content = docxToHtml(sourceFileName);
            return content;
        }
        return null;
    }

    //docx转html
    //生成html文件
    //输出html标签和内容
    public static String docxToHtml(String sourceFileName) throws Exception {
        String htmlPath=sourceFileName.substring(0,sourceFileName.indexOf("."))+".html";
        XWPFDocument document = new XWPFDocument(new FileInputStream(sourceFileName));
        XHTMLOptions options = XHTMLOptions.create();
        File outFile = new File(htmlPath);
        outFile.getParentFile().mkdirs();
        OutputStream out = new FileOutputStream(outFile);
        XHTMLConverter.getInstance().convert(document,out, options);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XHTMLConverter.getInstance().convert(document, baos, options);
        baos.close();
        String content =new String(baos.toByteArray());
        //替换UEditor无法识别的转义字符
        String htmlContent1=content.replaceAll("&ldquo;","\"").replaceAll("&rdquo;","\"").replaceAll("&mdash;","-");
        return htmlContent1;
    }

    //doc 转 html
    public static String docToHtml(String sourceFileName) throws TransformerException, IOException, ParserConfigurationException {
        HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(sourceFileName));//WordToHtmlUtils.loadDoc(new FileInputStream(inputFile));
        //兼容2007 以上版本
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                DocumentBuilderFactory.newInstance().newDocumentBuilder()
                        .newDocument());
        wordToHtmlConverter.processDocument(wordDocument);
        //解析html
        Document htmlDocument = wordToHtmlConverter.getDocument();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(out);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "HTML");
        serializer.transform(domSource, streamResult);
        out.close();
        String htmlContent = new String(out.toByteArray());
        //替换UEditor无法识别的转义字符
        String htmlContent1 = htmlContent.replaceAll("&ldquo;", "\"").replaceAll("&rdquo;", "\"").replaceAll("&mdash;", "-");
        return htmlContent1;
    }


}
