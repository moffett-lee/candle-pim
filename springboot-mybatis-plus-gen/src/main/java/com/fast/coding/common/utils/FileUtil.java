package com.fast.coding.common.utils;

import com.fast.coding.common.constant.GenConst;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 文件操作工具类
 *
 * @author Bamboo
 * @since 2020-03-19
 */
public class FileUtil {

    /**
     * 验证文件夹是否存在
     * @param filePath 文件夹绝对路径
     * @return
     */
    public static Boolean validateFileDir(String filePath) {
        Boolean flag = false;
        //判断文件路径是否存在
        File fileDir = new File(filePath);
        if (fileDir.exists()) {
            flag = true;
        }
        return flag;
    }

    /**
     * 创建文件夹
     * @param directoryPath
     * @return
     */
    public static String mkdir(String directoryPath) {
        try{
            File file = new File(directoryPath);
            if(!file.exists()) {
                file.mkdirs();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return directoryPath;
    }

    /**
     * 删除文件(夹)
     * @param filePath
     * @return
     */
    public static boolean deleteFile(String filePath) {
        try{
            File file = new File(filePath);
            if(file.isDirectory()) {
                for (File listFile : file.listFiles()) {
                    deleteFile(listFile.getAbsolutePath());
                }
            }
            return file.delete();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取文件大小
     * 返回单位:KB,保留两位小数
     * 文件不存在返回0.0
     *
     * @param filePath 文件绝对路径
     * @return
     */
    public static Double getFileSize(String filePath) {
        File file = new File(filePath);
        double fileLen = Double.valueOf(file.length()) / 1000;
        BigDecimal bigDecimal = new BigDecimal(fileLen).setScale(2, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

    /**
     * 判断文件是否存在
     * @param filePath
     * @return
     */
    public static boolean isExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    /**
     * 判断是否是文件夹
     * @param filePath
     * @return
     */
    public static boolean isDirectory(String filePath) {
        File file = new File(filePath);
        if(file.exists()){
            return file.isDirectory();
        }else{
            return false;
        }
    }

    /**
     * 文件(夹)重命名
     * @param oldFilePath 原文件(夹)绝对路径
     * @param newName 新的文件名称
     * @return
     */
    public static boolean fileRename(String oldFilePath, String newName) {
        try {
            File oldFile = new File(oldFilePath);
            // 判断文件是否存在
            if(oldFile.exists()) {
                // 判断是全路径还是文件名
                if (newName.indexOf(GenConst.SLASH) < 0 && newName.indexOf(GenConst.BACK_SLASH) < 0) {
                    // 判断是windows还是Linux系统
                    String absolutePath = oldFile.getAbsolutePath();
                    if(newName.indexOf(GenConst.SLASH) > 0) {
                        //Linux系统
                        newName = absolutePath.substring(0, absolutePath.lastIndexOf("/") + 1)  + newName;
                    } else {
                        newName = absolutePath.substring(0, absolutePath.lastIndexOf("\\") + 1)  + newName;
                    }
                }
                // 判断重命名后的文件是否存在
                File file = new File(newName);
                if(file.exists()) {
                    System.out.println(newName + "已存在,不能重命名");
                } else {
                    return oldFile.renameTo(file);
                }
            } else {
                System.out.println(oldFilePath + "文件不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 读取文本文件
     * @param filePath
     * @return
     */
    public static String readText(String filePath) {
        String text = "";
        BufferedReader bufferedReader = null;
        try {
            FileReader fileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                text += line + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != bufferedReader) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return text;
    }

    /**
     * 获取文件类型
     *
     * @param file 文件
     * @return 文件类型
     * @throws Exception Exception
     */
    private static String getFileType(File file) throws Exception {
        if (file.isDirectory()) {
            throw new Exception("file不是文件");
        }
        String fileName = file.getName();
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * 返回前台文件流
     *
     * @param filePath 待下载文件路径
     * @param fileName 下载文件名称
     * @param delete   下载后是否删除源文件
     * @param response HttpServletResponse
     * @throws Exception Exception
     */
    public static void downloadFile(String filePath, String fileName, Boolean delete, HttpServletResponse response) throws Exception {
        if (!isExists(filePath)) {
            throw new Exception("文件:"+filePath+"未找到");
        }

        // 设置响应
        response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(fileName, "utf-8"));
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");

        // 文件流操作
        File file = new File(filePath);
        try (InputStream inputStream = new FileInputStream(file); OutputStream os = response.getOutputStream()) {
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } finally {
            if(delete) {
                deleteFile(filePath);
            }
        }
    }
}
