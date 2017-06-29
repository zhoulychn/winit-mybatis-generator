/**
 * Project Name:qyk-util-base
 * File Name:FileHelper.java
 * Package Name:org.qyk.base.util
 * Date:2017年2月16日下午7:03:25
 * Copyright (c) 2017, Thinkive(http://www.thinkive.com/) All Rights Reserved.
 *
*/

package com.winit.generator.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * ClassName:FileHelper <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年2月16日 下午7:03:25 <br/>
 * 
 * @author qiyongkang
 * @version
 * @since JDK 1.6
 * @see
 */
public class FileHelper {

    /**
     * 1kb
     */
    private final static int KB_1   = 1024;

    /**
     * FileHelper 日志
     */
    private static Logger    logger = LogManager.getLogger(FileHelper.class);

    /**
     * 获得文件的CRC32校验和
     *
     * @param file 要进行校验的文件
     * @return
     * @throws Exception
     */
    public static String getFileCRCCode(File file) throws Exception {
        FileInputStream is = new FileInputStream(file);
        CRC32 crc32 = new CRC32();
        CheckedInputStream cis = new CheckedInputStream(is, crc32);
        byte[] buffer = null;
        buffer = new byte[KB_1];
        while (cis.read(buffer) != -1) {
        }
        is.close();
        buffer = null;
        return Long.toHexString(crc32.getValue());
    }

    /**
     * 获得字串的CRC32校验和
     *
     * @param string 要进行校验的字串
     * @return
     * @throws Exception
     */
    public static String getStringCRCCode(String string) throws Exception {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(string.getBytes());
        CRC32 crc32 = new CRC32();
        CheckedInputStream checkedinputstream = new CheckedInputStream(inputStream, crc32);
        byte[] buffer = null;
        buffer = new byte[KB_1];
        while (checkedinputstream.read(buffer) != -1) {
        }
        inputStream.close();
        buffer = null;
        return Long.toHexString(crc32.getValue());
    }

    /**
     * 连接路径和文件名称，组成最后的包含路径的文件名
     *
     * @param basePath 文件路径
     * @param fullFilenameToAdd 文件名称
     * @return
     */
    public static String concat(String basePath, String fullFilenameToAdd) {
        return FilenameUtils.concat(basePath, fullFilenameToAdd);
    }

    /**
     * 获得不带文件扩展名的文件名称
     *
     * @param filename 文件完整路径
     * @return 不带扩展名的文件名称
     */
    public static String getBaseName(String filename) {
        return FilenameUtils.getBaseName(filename);
    }

    /**
     * 获得带扩展名的文件名称
     *
     * @param filename 文件完整路径
     * @return 文件名称
     */
    public static String getFileName(String filename) {
        return FilenameUtils.getName(filename);
    }

    /**
     * 获得文件的完整路径，包含最后的路径分隔条
     *
     * @param filename 文件完整路径
     * @return 目录结构
     */
    public static String getFullPath(String filename) {
        return FilenameUtils.getFullPath(filename);
    }

    /**
     * 获得文件的完整路径，不包含最后的路径分隔条
     *
     * @param filename 文件完整路径
     * @return
     */
    public static String getFullPathNoEndSeparator(String filename) {
        return FilenameUtils.getFullPathNoEndSeparator(filename);
    }

    /**
     * 判断文件是否有某扩展名
     *
     * @param filename 文件完整路径
     * @param extension 扩展名名称
     * @return 若是，返回true，否则返回false
     */
    public static boolean isExtension(String filename, String extension) {
        return FilenameUtils.isExtension(filename, extension);
    }

    /**
     * 判断文件的扩展名是否是扩展名数组中的一个
     *
     * @param filename 文件完整路径
     * @param extensions 扩展名名称
     * @return 若是，返回true，否则返回false
     */
    public static boolean isExtension(String filename, String[] extensions) {
        return FilenameUtils.isExtension(filename, extensions);
    }

    /**
     * 规范化路径，合并其中的多个分隔符为一个,并转化为本地系统路径格式
     *
     * @param filename 文件完整路径
     * @return
     */
    public static String normalize(String filename) {
        return FilenameUtils.normalize(filename);
    }

    /**
     * 规范化路径，合并其中的多个分隔符为一个,并转化为本地系统路径格式,若是路径，则不带最后的路径分隔符
     *
     * @param filename 文件完整路径
     * @return
     */
    public static String normalizeNoEndSeparator(String filename) {
        return FilenameUtils.normalizeNoEndSeparator(filename);
    }

    /**
     * 把文件路径中的分隔符转换为unix的格式，也就是"/"
     *
     * @param path 文件完整路径
     * @return 转换后的路径
     */
    public static String separatorsToUnix(String path) {
        return FilenameUtils.separatorsToUnix(path);
    }

    /**
     * 把文件路径中的分隔符转换为window的格式，也就是"\"
     *
     * @param path 文件完整路径
     * @return 转换后的路径
     */
    public static String separatorsToWindows(String path) {
        return FilenameUtils.separatorsToWindows(path);
    }

    /**
     * 把文件路径中的分隔符转换当前系统的分隔符
     *
     * @param path 文件完整路径
     * @return 转换后的路径
     */
    public static String separatorsToSystem(String path) {
        return FilenameUtils.separatorsToSystem(path);
    }

    /**
     * 提取文件的扩展名
     *
     * @param filename 文件名称
     * @return 文件扩展名，若没有扩展名，则返回空字符串
     */
    public static String getExtension(String filename) {
        return FilenameUtils.getExtension(filename);
    }

    /**
     * 移出文件的扩展名
     *
     * @param filename 文件名称
     * @return 若文件存在扩展名，则移出扩展名，然后返回移出后的值
     */
    public static String removeExtension(String filename) {
        return FilenameUtils.removeExtension(filename);
    }

    /**
     * 清除一个目录的内容，但不删除此目录
     *
     * @param directory 需要清除的目录
     * @return true:清除成功 false:清除失败
     */
    public static boolean cleanDirectory(File directory) {
        try {
            org.apache.commons.io.FileUtils.cleanDirectory(directory);
            return true;
        } catch (IOException ex) {
            logger.error("清除目录出错", ex);
        }
        return false;
    }

    /**
     * 拷贝一个目录的内容到另外一个目录中
     *
     * @param srcDir 源目录
     * @param destDir 目的目录
     * @return true:拷贝成功 false:拷贝失败
     */
    public static boolean copyDirectory(File srcDir, File destDir) {
        return copyDirectory(srcDir, destDir, true);
    }

    /**
     * 拷贝一个目录的内容到另外一个目录中
     *
     * @param srcDir 源目录
     * @param destDir 目的目录
     * @return true:拷贝成功 false:拷贝失败
     */
    public static boolean copyDirectory(String srcDir, String destDir) {
        return copyDirectory(new File(srcDir), new File(destDir));
    }

    /**
     * 拷贝一个目录的内容到另外一个目录中
     *
     * @param srcDir 源目录
     * @param destDir 目的目录
     * @param preserveFileDate 是否保持文件日期
     * @return true:拷贝成功 false:拷贝失败
     */
    public static boolean copyDirectory(File srcDir, File destDir, boolean preserveFileDate) {
        try {
            org.apache.commons.io.FileUtils.copyDirectory(srcDir, destDir, preserveFileDate);
            return true;
        } catch (IOException ex) {
            logger.error("复制目录出错", ex);
        }
        return false;
    }

    /**
     * 拷贝源目录的内容到目的目录中(注：是拷贝到目的目录的里面)
     *
     * @param srcDir 源目录
     * @param destDir 目的目录
     * @return true:拷贝成功 false:拷贝失败
     */
    public static boolean copyDirectoryToDirectory(File srcDir, File destDir) {
        try {
            org.apache.commons.io.FileUtils.copyDirectoryToDirectory(srcDir, destDir);
            return true;
        } catch (IOException ex) {
            logger.error("复制目录出错", ex);
        }
        return false;
    }

    /**
     * 拷贝源目录的内容到目的目录中(注：是拷贝到目的目录的里面)
     *
     * @param srcDir 源目录
     * @param destDir 目的目录
     * @return true:拷贝成功 false:拷贝失败
     */
    public static boolean copyDirectoryToDirectory(String srcDir, String destDir) {
        return copyDirectoryToDirectory(new File(srcDir), new File(destDir));
    }

    /**
     * 拷贝文件
     *
     * @param srcFile 源文件
     * @param destFile 目的文件
     * @return true:拷贝成功 false:拷贝失败
     */
    public static boolean copyFile(File srcFile, File destFile) {
        return copyFile(srcFile, destFile, true);
    }

    /**
     * 拷贝文件
     *
     * @param srcFile 源文件路径
     * @param destFile 目的文件路径
     * @return true:拷贝成功 false:拷贝失败
     */
    public static boolean copyFile(String srcFile, String destFile) {
        return copyFile(new File(srcFile), new File(destFile));
    }

    /**
     * 拷贝文件
     *
     * @param srcFile 源文件
     * @param destFile 目的文件
     * @param preserveFileDate 是否保留文件日期
     * @return true:拷贝成功 false:拷贝失败
     */
    public static boolean copyFile(File srcFile, File destFile, boolean preserveFileDate) {
        try {
            org.apache.commons.io.FileUtils.copyFile(srcFile, destFile, preserveFileDate);
            return true;
        } catch (IOException ex) {
            logger.error("复制文件出错", ex);
        }
        return false;
    }

    /**
     * 拷贝文件到某目录中
     *
     * @param srcFile 源文件
     * @param destDir 目的目录
     * @return true:拷贝成功 false:拷贝失败
     */
    public static boolean copyFileToDirectory(File srcFile, File destDir) {
        try {
            org.apache.commons.io.FileUtils.copyFileToDirectory(srcFile, destDir);
            return true;
        } catch (IOException ex) {
            logger.error("复制文件出错", ex);
        }
        return false;
    }

    /**
     * 拷贝文件到某目录中
     *
     * @param srcFile 源文件
     * @param destDir 目的目录
     * @return true:拷贝成功 false:拷贝失败
     */
    public static boolean copyFileToDirectory(String srcFile, String destDir) {
        return copyFileToDirectory(new File(srcFile), new File(destDir));
    }

    /**
     * 删除一个目录和该目录下的所有内容
     *
     * @param directory 需要删除的目录
     * @return true:删除成功 false:删除失败
     */
    public static boolean deleteDirectory(String directory) {
        try {
            org.apache.commons.io.FileUtils.deleteDirectory(new File(directory));
            return true;
        } catch (IOException ex) {
            logger.error("删除目录出错", ex);
        }
        return false;
    }

    /**
     * 删除文件
     *
     * @param file 需要删除的文件路径
     * @return true:删除成功 false:删除失败
     */
    public static boolean deleteFile(String file) {
        try {
            org.apache.commons.io.FileUtils.forceDelete(new File(file));
            return true;
        } catch (IOException ex) {
            logger.error("删除文件出错", ex);
        }
        return false;
    }

    /**
     * 递归创建目录
     *
     * @param directory 目录
     * @return
     */
    public static boolean createDirectory(String directory) {
        try {
            org.apache.commons.io.FileUtils.forceMkdir(new File(directory));
            return true;
        } catch (IOException ex) {
            logger.error("创建目录出错", ex);
        }
        return false;
    }

    /**
     * 读入文件到字节数组中
     *
     * @param file 需要读取的文件路径
     * @return 读取的字节数组，若读入失败，则返回null
     */
    public static byte[] readFileToByteArray(String file) {
        try {
            byte[] bytes = org.apache.commons.io.FileUtils.readFileToByteArray(new File(file));
            return bytes;
        } catch (IOException ex) {
            logger.error("读取文件出错", ex);
        }
        return null;
    }

    /**
     * 读入文件到字串中
     *
     * @param file 需要读取的文件路径
     * @return 读取的文件内容，若读入失败，则返回空字串
     */
    public static String readFileToString(String file, String encoding) {
        try {
            if (StringHelper.isEmpty(encoding)) {
                encoding = "UTF-8";
            }
            String content = org.apache.commons.io.FileUtils.readFileToString(new File(file), encoding);
            return content;
        } catch (IOException ex) {
            logger.error("读取文件出错", ex);
        }
        return "";
    }

    /**
     * 读入文件到字串中
     *
     * @param file 需要读取的文件路径
     * @return 读取的文件内容，若读入失败，则返回空字串
     */
    public static String readFileToString(String file) {
        return readFileToString(file, "UTF-8");
    }

    /**
     * 读入文本文件到一个按行分开的List中
     *
     * @param file 需要读取的文件路径
     * @return 按行内容分开的List
     */
    @SuppressWarnings("rawtypes")
    public static List readLines(String file) {
        return readLines(file, "UTF-8");
    }

    /**
     * 读入文本文件到一个按行分开的List中
     *
     * @param file 需要读取的文件路径
     * @return 按行内容分开的List
     */
    @SuppressWarnings("rawtypes")
    public static List readLines(String file, String encoding) {

        try {
            if (StringHelper.isEmpty(encoding)) {
                encoding = "UTF-8";
            }
            List lineList = org.apache.commons.io.FileUtils.readLines(new File(file), encoding);
            return lineList;
        } catch (IOException ex) {
            logger.error("读取文件出错", ex);
        }
        return null;

    }

    /**
     * @描述: 读入文本文件到一个按行分开的List中
     * @作者: liwei
     * @创建日期: 2015年8月24日 下午1:47:47
     * @param url
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static List readLines(URL url) {
        return readLines(url, "UTF-8");
    }

    /**
     * @描述: 读入文本文件到一个按行分开的List中
     * @作者: liwei
     * @创建日期: 2015年8月24日 下午1:48:28
     * @param url
     * @param encoding
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static List readLines(URL url, String encoding) {
        if (url == null) {
            throw new RuntimeException("读取文件内容的url为空");
        }
        InputStream inStream = null;
        try {
            inStream = url.openStream();
            List lineList = org.apache.commons.io.IOUtils.readLines(inStream, encoding);
            return lineList;
        } catch (IOException ex) {
            logger.error("读取文件出错", ex);
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    logger.warn("关闭文件[" + url + "]失败", e);
                }
            }
        }
        return null;
    }

    /**
     * 递归求一个目录的容量大小
     *
     * @param directory 需要计算容量的目录路径
     * @return 容量的大小(字节数)
     */
    public static long sizeOfDirectory(String directory) {
        return org.apache.commons.io.FileUtils.sizeOfDirectory(new File(directory));
    }

    /**
     * 写字节数组到文件中，若文件不存在，则建立新文件
     *
     * @param file 需要写的文件的路径
     * @param data 需要写入的字节数据
     * @return true:写入成功 false:写入失败
     */
    public static boolean writeToFile(String file, byte[] data) {
        try {
            org.apache.commons.io.FileUtils.writeByteArrayToFile(new File(file), data);
            return true;
        } catch (IOException ex) {
            logger.error("写文件出错", ex);
        }
        return false;
    }

    /**
     * 写字串到文件中，若文件不存在，则建立新文件
     *
     * @param file 需要写的文件的路径
     * @param data 需要写入的字串
     * @return true:写入成功 false:写入失败
     */
    public static boolean writeToFile(String file, String data) {
        return writeToFile(file, data, "UTF-8");
    }

    /**
     * 写字串到文件中，若文件不存在，则建立新文件
     *
     * @param file 需要写的文件的路径
     * @param data 需要写入的字串
     * @param dncoding 文件编码，默认为UTF-8
     * @return true:写入成功 false:写入失败
     */
    public static boolean writeToFile(String file, String data, String encoding) {
        try {
            if (encoding == null || "".equals(encoding)) {
                encoding = "UTF-8";
            }
            org.apache.commons.io.FileUtils.writeStringToFile(new File(file), data, encoding);
            return true;
        } catch (IOException ex) {
            logger.error("写文件出错", ex);
        }
        return false;
    }

    /**
     * 建立由filePathName指定的文件，若文件路径中的目录不存在，则先建立目录
     *
     * @param filePathName 文件路径全名
     * @return
     */
    public static boolean createNewFile(String filePathName) {
        String filePath = FileHelper.getFullPath(filePathName);
        // 若目录不存在，则建立目录
        if (!FileHelper.exists(filePath)) {
            if (!createDirectory(filePath)) {
                return false;
            }
        }

        try {
            File file = new File(filePathName);
            return file.createNewFile();
        } catch (IOException ex) {
            logger.error("创建文件出错", ex);
            return false;
        }
    }

    /**
     * 判断文件和目录是否已存在
     *
     * @param filePath 文件和目录完整路径
     * @return tru:存在 false：不存在
     */
    public static boolean exists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    /**
     * 判断特定的路径是否为文件
     *
     * @param filePath 文件完整的路径
     * @return 若是文件，则返回true，否则返回false
     */
    public static boolean isFile(String filePath) {
        File file = new File(filePath);
        return file.isFile();
    }

    /**
     * 判断特定的路径是否为目录
     *
     * @param filePath 文件完整的路径
     * @return 若是目录，则返回true，否则返回false
     */
    public static boolean isDirectory(String filePath) {
        File file = new File(filePath);
        return file.isDirectory();
    }

    /**
     * 更改文件的名称，若不在同一个目录下,则系统会移动文件
     *
     * @param srcFile 源文件路径名称
     * @param destFile 目的文件路径名称
     * @return
     */
    public static boolean renameTo(String srcFile, String destFile) {
        File file = new File(srcFile);
        return file.renameTo(new File(destFile));
    }

    /**
     * 描述：根据document生成Xml文件 作者：刘宝 时间：Jun 9, 2010 3:16:11 PM
     * 
     * @param fileName 生成文件的路径
     * @param document
     * @param encoding 编码格式
     * @return
     */
    public static boolean WriteToXMLFile(String fileName, Document document, String encoding) {
        createNewFile(fileName);
        boolean success = false;
        /** 格式化输出,类型IE浏览一样 */
        OutputFormat format = OutputFormat.createPrettyPrint();
        /** 指定XML编码 */
        format.setEncoding(encoding);
        XMLWriter writer = null;
        try {
            /** 将document中的内容写入文件中 */
            writer = new XMLWriter(new FileOutputStream(new File(fileName)), format);
            writer.write(document);
            writer.flush();
            success = true;
            /** 执行成功,需返回true */
        } catch (Exception ex) {
            logger.error("写文件出错", ex);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                    writer = null;
                } catch (IOException e) {
                    logger.error("Convert code Error:" + e.getMessage(), e);
                }
            }
        }
        return success;
    }

    /**
     * guessPropFile:
     * 
     * @param cls :和要寻找的属性文件处于相同的包中的任意的类
     * @param propFile :要寻找的属性文件名
     */
    @SuppressWarnings("rawtypes")
    public static File guessPropFile(Class cls, String propFile) {
        File f = null;
        try {
            // 得到类的类装载器
            ClassLoader loader = cls.getClassLoader();
            // 先从当前类所处路径的根目录中寻找属性文件
            URL url = loader.getResource(propFile);
            if (url != null) {
                f = new File(url.getPath());
                if (f != null && f.exists() && f.isFile()) {
                    return f;
                }
            }
            // 没有找到，就从该类所处的包目录中查找属性文件
            Package pack = cls.getPackage();
            if (pack != null) {
                String packName = pack.getName();
                String path = "";
                if (packName.indexOf(".") < 0) {
                    path = packName + "/";
                } else {
                    int start = 0, end = 0;
                    end = packName.indexOf(".");
                    while (end != -1) {
                        path = path + packName.substring(start, end) + "/";
                        start = end + 1;
                        end = packName.indexOf(".", start);
                    }
                    path = path + packName.substring(start) + "/";
                }
                url = loader.getResource(path + propFile);
                if (url != null) {
                    f = new File(url.getPath());
                    if (f != null && f.exists() && f.isFile()) {
                        return f;
                    }
                }
            }
            // 如果没有找到，再从当前系统的用户目录中进行查找
            String curDir = System.getProperty("user.dir");
            f = new File(curDir, propFile);
            if (f != null && f.exists() && f.isFile()) {
                return f;
            }
            // 如果还是没有找到，则从系统所有的类路径中查找
            String classpath = System.getProperty("java.class.path");
            String[] cps = classpath.split(System.getProperty("path.separator"));
            for (int i = 0; i < cps.length; i++) {
                f = new java.io.File(cps[i], propFile);
                if (f != null && f.exists() && f.isFile()) {
                    break;
                }
                f = null;
            }

            // 如果还是没有找到，从jar包中查找
        } catch (Exception e) {
            logger.error("获取文件出错，return null", e);
            f = null;
        }
        return f;
    }
}
