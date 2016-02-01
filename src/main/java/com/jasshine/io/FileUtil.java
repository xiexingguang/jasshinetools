package com.jasshine.io;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * Created by ecuser on 2016/2/1.
 */
public class FileUtil {

    private static String MESSAGE = "";


    /**
     * 拷贝文件，适合用小文件拷贝
     * @param soucreFile
     * @param targetFile
     * @param isOverlay
     * @return
     */
    public static boolean copyFile(String soucreFile, String targetFile, boolean isOverlay) {
        //jude the file
        File srcFile = new File(soucreFile);
        if (!srcFile.exists()) {
            System.out.println("sourceFile is not exist");
            return false;
        }else if (!srcFile.isFile()) {
            System.out.println("sourceFile is not a file");
            return false;
        }

        File targetFiles = new File(targetFile);
        if (targetFiles.exists()) {
            if (isOverlay) {
                System.out.println("新的文件会覆盖原有文件");
                new File(targetFile).delete();  //允许覆盖 就删除已经存在的目标文件
            }
        } else {
            if (!targetFiles.getParentFile().exists()) {
                if (!targetFiles.getParentFile().mkdirs()) {
                    return false;
                }
            }
        }

        //copy file
        int byteRead = 0;
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(srcFile);
            out = new FileOutputStream(targetFiles);
            byte[] buffer = new byte[1024];
            while ((byteRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, byteRead);
            }
            System.out.println("success copy [" + soucreFile + "]  to [ :" + targetFile +"]");
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("file not found" + e);
            return false;
        } catch (IOException e) {
            System.out.println("fail to copy file");
            return false;
        } finally{
            try {
                if (out != null)
                    out.close();
                if (in != null)
                    in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 复制目录
     * @param sourceDir
     * @param targetDir
     * @param isOverlay
     * @return
     */
    public static boolean copyDirectory(String sourceDir, String targetDir, boolean isOverlay) {
        File srcDir = new File(sourceDir);
        if (!srcDir.exists()) {
            System.out.println("sourceDir is not exist :" + sourceDir);
            return false;
        } else if(!srcDir.isDirectory()) {
            System.out.println("sourceDir is not a directory " + sourceDir);
            return false;
        }

        if (!sourceDir.endsWith(File.separator)) {
            sourceDir = sourceDir + File.separator;
        }

        if (!targetDir.endsWith(File.separator)) {
            targetDir = targetDir + File.separator;
        }

        File destDir = new File(targetDir);
        if (destDir.exists()) {
            if (isOverlay) {
                new File(targetDir).delete();
            } else {
                System.out.println("目标目录已存在");
                return false;
            }
        } else {
            System.out.println("目标目录不存在，准备创建");
            if (!destDir.mkdirs()) {
                System.out.println("创建目标目录失败");
                return false;
            }
        }

        boolean flag = true;
        File[] files = srcDir.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                flag = copyFile(files[i].getAbsolutePath(), targetDir + files[i].getName(), isOverlay);
                if (!flag) {
                    break;
                }
            }else if (files[i].isDirectory()) {
                flag = copyDirectory(files[i].getAbsolutePath(), targetDir + files[i].getName(), isOverlay);
                if (!flag) {
                    break;
                }
            }
        }
        return flag;
    }



    /**
     * nio方式复制
     * @param sourceFile
     * @param targetFile
     * 测试300M的文件大小复制，nio方式比不用nio方式效率还要低。。
     */

    public static void nioCopyFile(String sourceFile, String targetFile) {
        FileChannel in = null;
        FileChannel out = null;
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(sourceFile);
            outputStream = new FileOutputStream(targetFile);
            in = inputStream.getChannel();
            out = outputStream.getChannel();
            in.transferTo(0, in.size(), out);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public static void main(String[] args) {
        String srcFile = "D:\\学习视频\\13_03_Linux进程管理之一.avi";
        String de = "E:\\2.avi";
        long beginTime = System.currentTimeMillis();
        // copyFile(srcFile, de, true);
       // nioCopyFile(srcFile,de);

        String srcDir = "e:\\jblog";
        String targetDir = "e:\\zst";
        copyDirectory(srcDir, targetDir, true);
        System.out.println("use time is :" + (System.currentTimeMillis() - beginTime) + "毫秒");
    }


}
