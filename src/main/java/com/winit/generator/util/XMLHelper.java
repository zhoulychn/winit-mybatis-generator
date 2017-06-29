/**
 * Project Name:qyk-util-base
 * File Name:XMLHelper.java
 * Package Name:org.qyk.base.util
 * Date:2017年2月16日下午6:36:17
 * Copyright (c) 2017, Thinkive(http://www.thinkive.com/) All Rights Reserved.
 *
*/

package com.winit.generator.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.Properties;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * ClassName:XMLHelper <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年2月16日 下午6:36:17 <br/>
 * 
 * @author qiyongkang
 * @version
 * @since JDK 1.6
 * @see
 */
public class XMLHelper {

    private static Logger logger = LogManager.getLogger(XMLHelper.class);

    /**
     * 把XML按照给定的XSL进行转换，返回转换后的值
     * 
     * @param xml xml
     * @param xsl xsl
     * @return
     * @throws Exception
     */
    public static String xml2xsl(String xml, URL xsl) throws Exception {
        if (StringHelper.isEmpty(xml)) {
            throw new Exception("xml string is empty");
        }
        if (xsl == null) {
            throw new Exception("xsl string is empty");
        }

        StringWriter writer = new StringWriter();
        Source xmlSource = null;
        Source xslSource = null;
        Result result = null;

        try {
            xmlSource = new StreamSource(new StringReader(xml));
            xslSource = new StreamSource(xsl.openStream());
            result = new StreamResult(writer);

            TransformerFactory transFact = TransformerFactory.newInstance();
            Transformer trans = transFact.newTransformer(xslSource);
            trans.transform(xmlSource, result);
            return writer.toString();
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            writer.close();
            writer = null;
            xmlSource = null;
            xslSource = null;
            result = null;
        }
    }

    /**
     * 把XML按用户定义好的XSL样式进行输出
     * 
     * @param xmlFilePath XML文档
     * @param xsl XSL样式
     * @return 样式化后的字段串
     */
    public static String xml2xsl(String xmlFilePath, String xsl) throws Exception {
        if (StringHelper.isEmpty(xmlFilePath)) {
            throw new Exception("xml string is empty");
        }
        if (StringHelper.isEmpty(xsl)) {
            throw new Exception("xsl string is empty");
        }

        StringWriter writer = new StringWriter();
        Source xmlSource = new StreamSource(new File(xmlFilePath));
        Source xslSource = new StreamSource(new File(xsl));
        Result result = new StreamResult(writer);

        try {
            TransformerFactory transFact = TransformerFactory.newInstance();
            Transformer trans = transFact.newTransformer(xslSource);
            Properties properties = trans.getOutputProperties();
            properties.setProperty(OutputKeys.ENCODING, "gb2312");
            properties.put(OutputKeys.METHOD, "html");
            trans.setOutputProperties(properties);

            trans.transform(xmlSource, result);
            return writer.toString();
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            writer.close();
            writer = null;

            xmlSource = null;
            xslSource = null;
            result = null;
        }
    }

    /**
     * 读取XML文档，返回Document对象.<br>
     * 
     * @param xmlFile XML文件路径
     * @return Document 对象
     */
    public static Document getDocument(String xmlFile) throws Exception {
        if (StringHelper.isEmpty(xmlFile)) {
            return null;
        }

        File file = null;
        SAXReader saxReader = new SAXReader();

        file = new File(xmlFile);
        return saxReader.read(file);
    }

    /**
     * 读取XML文档，返回Document对象.<br>
     * 
     * @param xmlFile file对象
     * @return Document 对象
     */
    public static Document getDocument(File xmlFile) {
        try {
            SAXReader saxReader = new SAXReader();
            return saxReader.read(xmlFile);
        } catch (Exception ex) {
            logger.error("读取xml文件出错，返回null", ex);
            return null;
        }
    }

    /**
     * 描述：读取XML文档，先从指定的位置读取，没有再通过文件流读取(读jar包的配置文件) 时间：2015-5-15 下午9:13:12
     * 
     * @param cls
     * @param propFile
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Document getDocument(Class cls, String xmlFile) {
        Document document = null;
        File file = FileHelper.guessPropFile(cls, xmlFile);
        if (file != null && file.exists() && file.isFile()) {
            document = XMLHelper.getDocument(file);
        } else {
            InputStream ins = null;
            try {
                // 得到类的类装载器
                ClassLoader loader = cls.getClassLoader();
                if (loader != null) {
                    // 先从当前类所处路径的根目录中寻找属性文件
                    ins = loader.getResourceAsStream(xmlFile);
                }

                if (ins != null) {
                    SAXReader reader = new SAXReader();
                    document = reader.read(ins);
                }
            } catch (Exception ex) {
                logger.error("读取xml文件出错，返回null", ex);
            } finally {
                if (ins != null) {
                    try {
                        ins.close();
                        ins = null;
                    } catch (IOException e) {
                        logger.error("", e);
                    }
                }
            }
        }
        return document;
    }

    /**
     * 读取XML字串，返回Document对象
     * 
     * @param xmlString XML文件路径
     * @return Document 对象
     */
    public static Document getDocumentFromString(String xmlString) {
        if (StringHelper.isEmpty(xmlString)) {
            return null;
        }
        try {
            SAXReader saxReader = new SAXReader();
            return saxReader.read(new StringReader(xmlString));
        } catch (Exception ex) {
            logger.error("解析xml字符串出错，返回null", ex);
            return null;
        }
    }

    /**
     * 描述：把xml输出成为html 作者： 时间：Oct 29, 2008 4:57:56 PM
     * 
     * @param xmlDoc xmlDoc
     * @param xslFile xslFile
     * @param encoding 编码
     * @return
     * @throws Exception
     */
    public static String xml2html(String xmlDoc, String xslFile, String encoding) throws Exception {
        if (StringHelper.isEmpty(xmlDoc)) {
            throw new Exception("xml string is empty");
        }
        if (StringHelper.isEmpty(xslFile)) {
            throw new Exception("xslt file is empty");
        }

        StringWriter writer = new StringWriter();
        Source xmlSource = null;
        Source xslSource = null;
        Result result = null;
        String html = null;
        try {
            xmlSource = new StreamSource(new StringReader(xmlDoc));
            xslSource = new StreamSource(new File(xslFile));

            result = new StreamResult(writer);

            TransformerFactory transFact = TransformerFactory.newInstance();
            Transformer trans = transFact.newTransformer(xslSource);
            Properties properties = trans.getOutputProperties();
            properties.put(OutputKeys.METHOD, "html");
            properties.setProperty(OutputKeys.ENCODING, encoding);
            trans.setOutputProperties(properties);

            trans.transform(xmlSource, result);

            html = writer.toString();
            writer.close();

            return html;
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            writer = null;

            xmlSource = null;
            xslSource = null;
            result = null;
        }
    }

    /**
     * 描述：把xml输出成为html 作者： 时间：Oct 29, 2008 4:58:48 PM
     * 
     * @param xmlFile xmlFile
     * @param xslFile xslFile
     * @param encoding 编码
     * @return
     * @throws Exception
     */
    public static String xmlFile2html(String xmlFile, String xslFile, String encoding) throws Exception {
        if (StringHelper.isEmpty(xmlFile)) {
            throw new Exception("xml string is empty");
        }
        if (StringHelper.isEmpty(xslFile)) {
            throw new Exception("xslt file is empty");
        }

        StringWriter writer = new StringWriter();
        Source xmlSource = null;
        Source xslSource = null;
        Result result = null;
        String html = null;
        try {
            xmlSource = new StreamSource(new File(xmlFile));
            xslSource = new StreamSource(new File(xslFile));

            result = new StreamResult(writer);

            TransformerFactory transFact = TransformerFactory.newInstance();
            Transformer trans = transFact.newTransformer(xslSource);
            Properties properties = trans.getOutputProperties();
            properties.put(OutputKeys.METHOD, "html");
            properties.setProperty(OutputKeys.ENCODING, encoding);
            trans.setOutputProperties(properties);

            trans.transform(xmlSource, result);

            html = writer.toString();
            writer.close();

            return html;
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            writer = null;

            xmlSource = null;
            xslSource = null;
            result = null;
        }
    }

    /**
     * 描述： 作者： 时间：Oct 29, 2008 5:00:10 PM
     * 
     * @param name 名
     * @param element 元素
     * @return
     */
    public static String getString(String name, Element element) {
        return (element.valueOf(name) == null) ? "" : element.valueOf(name);
    }

    /**
     * 将一个XML文档保存至文件中.
     * 
     * @param doc 要保存的XML文档对象.
     * @param filePath 要保存到的文档路径.
     * @param format 要保存的输出格式
     * @return true代表保存成功，否则代表不成功.
     */
    public static boolean savaToFile(Document doc, String filePathName, OutputFormat format) {
        XMLWriter writer;
        try {
            String filePath = FileHelper.getFullPath(filePathName);
            // 若目录不存在，则建立目录
            if (!FileHelper.exists(filePath)) {
                if (!FileHelper.createDirectory(filePath)) {
                    return false;
                }
            }

            writer = new XMLWriter(new FileWriter(new File(filePathName)), format);
            writer.write(doc);
            writer.close();
            return true;
        } catch (IOException ex) {
            logger.error("写文件出错", ex);
        }

        return false;
    }

    /**
     * 将一个XML文档保存至文件中.
     * 
     * @param filePath 要保存到的文档路径.
     * @param doc 要保存的XML文档对象.
     * @return true代表保存成功，否则代表不成功.
     */
    public static boolean writeToXml(String filePathName, Document doc) {
        OutputFormat format = OutputFormat.createCompactFormat();
        format.setEncoding("GBK");
        return savaToFile(doc, filePathName, format);
    }
}
