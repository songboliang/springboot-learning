package com.springboot.learning.file;

import org.apache.commons.io.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class FileToMultipartFile {

    /**
     * MultipartFile 转 File
     * @param file
     * @throws Exception
     */
    public  File multipartFileToFile( @RequestParam MultipartFile file ) throws Exception {

        File toFile = null;
        if(file.equals("")||file.getSize()<=0){
            file = null;
        }else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;

    }

    public  void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * File 转 MultipartFile
     * @param file
     * @throws Exception
     */
    public  MultipartFile fileToMultipartFile( File file ) throws Exception {

        FileInputStream fileInput = new FileInputStream(file);
        MultipartFile toMultipartFile = new MockMultipartFile("file",file.getName(),"text/plain", IOUtils.toByteArray(fileInput));
        toMultipartFile.getInputStream();
        return toMultipartFile;
    }

    public static void main(String args[])throws Exception{

        File file=new File("D:\\ChromeCoreDownloads\\demo-0.0.1-SNAPSHOT.jar");
//        MultipartFile multipartFile = fileToMultipartFile(file);
//        multipartFileToFile(multipartFile);
    }

}
