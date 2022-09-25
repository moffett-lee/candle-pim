package com.fast.coding.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 文件压缩工具类
 *
 * @author Bamboo
 * @since 2020-03-19
 */
public class CompressFileUtil {

    private final static Logger logger = LoggerFactory.getLogger(CompressFileUtil.class);

    /**
     * 缓冲器大小
     */
    private static final int BUFFER = 512;

    /**
     * 得到源文件路径的所有文件
     * @param dirFile 压缩源文件路径
     */
    public static List<File> getAllFile(File dirFile) {
        List<File> fileList = new ArrayList<>();
        File[] files = dirFile.listFiles();
        for(File file : files) {
            // 判断是否是文件
            if(file.isFile()) {
                fileList.add(file);
            } else {
                // 判断目录是否为空
                if(file.listFiles().length!=0) {
                    //把递归文件加到fileList中
                    fileList.addAll(getAllFile(file));
                } else {
                    fileList.add(file);
                }
            }
        }
        return fileList;
    }

    /**
     * 创建文件
     * @param destPath 解压目标路径
     * @param fileName 解压文件的相对路径
     */
    public static File createFile(String destPath, String fileName) {
        // 将文件名的各级目录分解
        String[] dirs = fileName.split("/");
        File file = new File(destPath);
        // 文件对应目录若不存在，则创建目录
        if (!file.exists()) {
            FileUtil.mkdir(destPath);
        }
        // 文件有上级目录
        if (dirs.length > 1) {
            for (int i = 0; i < dirs.length - 1; i++) {
                //依次创建文件对象知道文件的上一级目录
                file = new File(file, dirs[i]);
            }
            //创建文件
            file = new File(file, dirs[dirs.length - 1]);
            return file;
        } else {
            // 创建文件
            file = new File(file, dirs[0]);
            return file;
        }
    }

    /**
     * 解压
     * @param zipFilePath 压缩文件绝对路径,包括后缀
     * @param destPath 解压路径
     */
    public static void decompress(String zipFilePath,String destPath) {
        OutputStream os = null;
        ZipInputStream zis = null;
        try {
            zis = new ZipInputStream(new FileInputStream(zipFilePath));
            ZipEntry zipEntry = null;
            //缓冲器
            byte[]buffer = new byte[BUFFER];
            //每次读出来的长度
            int readLength = 0;
            while ((zipEntry = zis.getNextEntry()) != null) {
                // 若是目录
                if(zipEntry.isDirectory()) {
                    File file = new File(destPath+"/"+zipEntry.getName());
                    if(!file.exists()) {
                        file.mkdirs();
                        logger.info("创建目录: "+file.getCanonicalPath());
                        continue;
                    }
                }
                // 若是文件
                File file = createFile(destPath,zipEntry.getName());
                os = new FileOutputStream(file);
                while ((readLength=zis.read(buffer,0,BUFFER)) != -1) {
                    os.write(buffer,0,readLength);
                }
                logger.info("创建文件: " + file.getCanonicalPath());
            }
            logger.info("解压完成: " + destPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != os) {
                    os.close();
                }
                if(null != zis) {
                    zis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 压缩文件列表
     * @param srcFiles
     * @param out
     */
    public static void toZip(List<File> srcFiles , OutputStream out){
        long start = System.currentTimeMillis();
        try (ZipOutputStream zos = new ZipOutputStream(out);) {
            for (File srcFile : srcFiles) {
                byte[] buf = new byte[BUFFER];
                zos.putNextEntry(new ZipEntry(srcFile.getName()));
                int len;
                FileInputStream in = new FileInputStream(srcFile);
                while ((len = in.read(buf)) != -1){
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                in.close();
            }
            long end = System.currentTimeMillis();
            logger.info("压缩完成，耗时：" + (end - start) +" ms");
        } catch (Exception e) {
            logger.info("zip error from ZipUtils",e);
        }
    }

    /**
     * 递归压缩方法
     * @param sourceFile 源文件
     * @param zos        zip输出流
     * @param name       压缩后的名称
     * @param keepDirStructure  是否保留原来的目录结构,true:保留目录结构;
     *                          false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws Exception
     */
    private static void compress(File sourceFile, ZipOutputStream zos, String name,
                                 boolean keepDirStructure) throws Exception {
        // 文件
        if(sourceFile.isFile()) {
            byte[] buf = new byte[BUFFER];
            // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
            zos.putNextEntry(new ZipEntry(name));
            // copy文件到zip输出流中
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1) {
                zos.write(buf, 0, len);
            }
            // Complete the entry
            zos.closeEntry();
            in.close();
            return;
        }
        // 目录
        File[] listFiles = sourceFile.listFiles();
        if(listFiles == null || listFiles.length == 0) {
            // 空目录：需要保留原来的文件结构时,需要对空文件夹进行处理
            if(keepDirStructure) {
                // 空文件夹的处理
                zos.putNextEntry(new ZipEntry(name + "/"));
                // 没有文件，不需要文件的copy
                zos.closeEntry();
            }
        } else {
            // 非空目录
            for (File file : listFiles) {
                // 判断是否需要保留原来的文件结构
                if (keepDirStructure) {
                    // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                    // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                    compress(file, zos, name + "/" + file.getName(),true);
                } else {
                    compress(file, zos, file.getName(),false);
                }
            }
        }
    }

    public static void toZip(String srcDir, String fileName, OutputStream out, boolean keepDirStructure){
        long start = System.currentTimeMillis();
        try (ZipOutputStream zos = new ZipOutputStream(out)) {
            File sourceFile = new File(srcDir);
            compress(sourceFile,zos,fileName,keepDirStructure);
            long end = System.currentTimeMillis();
            logger.info("压缩完成，耗时：" + (end - start) +" ms");
        } catch (Exception e) {
            logger.info("文件压缩失败",e);
        }
    }
}
