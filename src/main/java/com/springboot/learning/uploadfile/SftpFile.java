package com.springboot.learning.uploadfile;

import com.jcraft.jsch.*;
import com.springboot.learning.file.FileToMultipartFile;
import com.springboot.learning.util.SFtpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Vector;

@Slf4j
public class SftpFile {

    public static void main(String args[]){

        Session session = null;
        ChannelSftp sftp = null;
        File file =new File("D:\\ChromeCoreDownloads\\demo-0.0.1-SNAPSHOT.jar");
        try{
            log.error("=======================upload file starting ...============================");
            session = SFtpUtil.connect("188.103.142.58", 22,
                    "root", "Wgzyc#@2017");
            System.out.println(file.getName());
            Channel channel = session.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
            String path = "/home/boco4a/jenkins/workspace/test_dev_1568081031356-down/";
            try {
                @SuppressWarnings("rawtypes")
                Vector content = sftp.ls(path);
                if (content == null) {
                    sftp.mkdir(path);
                }
            } catch (SftpException e) {
                sftp.mkdir(path);
            }
//            //创建文件夹  目录必须一级一级创建
//            sftp.mkdir("/home/boco4a/file/11/22");
//            //判断文件目录是否存在
//            SftpATTRS stat = sftp.stat("/home/boco4a/jenkins/workspace/irm-frontend-sso_qa_1566893480742-down/");
//            Vector vector = sftp.ls("/home/boco4a");
//            Iterator iterator = vector.iterator();
//            while (iterator.hasNext()){
//                System.out.println(iterator.next());
//            }
//            FileToMultipartFile fileToMultipartFile = new FileToMultipartFile();
//            MultipartFile multipartFile = fileToMultipartFile.fileToMultipartFile(file);
//           File file1 = fileToMultipartFile.multipartFileToFile(multipartFile);
//            file=File.createTempFile("tmp", null);
//
//            multipartFile.transferTo(file);
//
////　          file.deleteOnExit();
//            SFtpUtil.upload("/home/boco4a/jenkins/workspace/csf-demo_qa_1565770638972-down/",file1,sftp);
//            log.error("=======================upload file end ... ================================");
        }catch (Exception e){
            log.error(e.getMessage());
        }

    }

    public void upload(@RequestParam(value = "file") MultipartFile file) {
        if (file != null) {
            try {
                String fileRealName = file.getOriginalFilename();//获得原始文件名;
                int pointIndex =  fileRealName.lastIndexOf(".");//点号的位置
                String fileSuffix = fileRealName.substring(pointIndex);//截取文件后缀
//                String fileNewName = DateUtils.getNowTimeForUpload();//新文件名,时间戳形式yyyyMMddHHmmssSSS
//                String saveFileName = fileNewName.concat(fileSuffix);//新文件完整名（含后缀）
                String filePath  = "D:\\FileAll" ;
                File path = new File(filePath); //判断文件路径下的文件夹是否存在，不存在则创建
                if (!path.exists()) {
                    path.mkdirs();
                }
                File savedFile = new File(filePath);
                boolean isCreateSuccess = savedFile.createNewFile(); // 是否创建文件成功
                if(isCreateSuccess){      //将文件写入
                    //第一种
                    file.transferTo(savedFile);
                    //第二种
//                    savedFile = new File(filePath,saveFileName);
//                    // 使用下面的jar包
//                    FileUtils.copyInputStreamToFile(file.getInputStream(),savedFile);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("文件是空的");
        }
    }





}
