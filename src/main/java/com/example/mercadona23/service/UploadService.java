package com.example.mercadona23.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class UploadService {
    @Autowired
    CheckService checkService;

    public String uploadFileToTemp (MultipartFile file, String productLabel){
        if (file == null){
            return "";
        }
        String TEMP_DIRECTORY = System.getProperty("user.dir")+"/tempFiles";
        String fileName = productLabel+"_"
                + LocalDateTime.now(ZoneId.of("Europe/Paris")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"))
                +file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));;
        try {
            saveMultipartFile(file,TEMP_DIRECTORY,fileName);
        }catch (IOException ioe){
            return "";
        }
     return fileName;
    }

    public void saveMultipartFile (MultipartFile file,String path,String fileName) throws IOException {
        Path fileNameAndPath = Paths.get(path, fileName);
        File dir = new File(path);
        dir.mkdir();
        Files.write(fileNameAndPath, file.getBytes());
    }

    public File getTempFilesByFileName (String fileName){
        File dir = new File(System.getProperty("user.dir")+"/tempFiles");
        File tempFile = null;
        File[] filesListe = dir.listFiles();
        for (File f : filesListe){
            if (f.getName().equals(fileName)){
                tempFile = f;
            }
        }
        return tempFile;
    }
    public File getUploadsFilesByFileName (String fileName){
        File dir = new File(System.getProperty("user.dir")+"/uploadsFiles");
        File uploadsFile = null;
        File[] filesListe = dir.listFiles();
        for (File f : filesListe){
            if (f.getName().equals(fileName)){
                uploadsFile = f;
            }
        }
        return uploadsFile;
    }

    public String copyFilesToUploads (File tempFile, Long productId){
        String UPLOADS_DIRECTORY = System.getProperty("user.dir")+"/uploadsFiles";
        String destFileName =
                tempFile.getName().substring(0,tempFile.getName().lastIndexOf("_")+1)
                + productId
                + tempFile.getName().substring(tempFile.getName().lastIndexOf("."));
        try {
            File destF = new File(UPLOADS_DIRECTORY,destFileName);
            this.copyFiles(tempFile,destF);
        }catch (IOException ioe){
            return "";
        }
        this.delteFiles(tempFile);
        return destFileName;
    }

    public void copyFiles (File src, File dest) throws IOException {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dest);
        byte[] buffer = new byte[1024];
        int length;
        while((length = in.read(buffer))>0){
            out.write(buffer,0,length);
        }
        in.close();
        out.close();
    }

    public void delteFiles(File file){
        if (file.exists()){
            file.delete();
        }
    }
}
