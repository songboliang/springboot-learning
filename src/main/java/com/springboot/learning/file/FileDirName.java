package com.springboot.learning.file;

import java.io.File;

public class FileDirName {

    public static void main(String[] args) {
        testFileDirOrName("D://build");
    }

    private static void testFileDirOrName(String path) {
        File dirFile = new File(path);
        if (dirFile.exists()) {
            File[] files = dirFile.listFiles();
            if (files != null) {
                for (File fileChildDir : files) {
                    //输出文件名或者文件夹名
                    System.out.println(fileChildDir.getName());
//                    if (fileChildDir.isDirectory()) {
//                        System.out.println(" :  此为目录名" +fileChildDir.getName());
//                        //通过递归的方式,可以把目录中的所有文件全部遍历出来
////                        testFileDirOrName(fileChildDir.getAbsolutePath());
//                    }
//                    if (fileChildDir.isFile()) {
//                        System.out.println(" :  此为文件名");
//                    }
                }
            }
        }else{
            System.out.println("你想查找的文件不存在");
        }
    }

}
