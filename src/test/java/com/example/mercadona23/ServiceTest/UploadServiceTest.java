package com.example.mercadona23.ServiceTest;

import com.example.mercadona23.service.UploadService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;

@SpringBootTest
@AutoConfigureMockMvc
public class UploadServiceTest {
    @Autowired
    private UploadService uploadServiceTest = new UploadService();

    private static String RESOURCE_DIRECTORY;
    private static String TEMP_DIRECTORY;
    private static String UPLOAD_DIRECTORY;
    private static String FILE_TEST_NAME;
    private static String TEMP_FILE_NAME_TEST;
    private static MultipartFile MULTIPART_FILE;

    @BeforeAll
    public static void beforeAll() throws IOException {
        RESOURCE_DIRECTORY = System.getProperty("user.dir") + "/src/test/java/com/example/mercadona23/testResources";
        TEMP_DIRECTORY = System.getProperty("user.dir") + "/tempFiles";
        UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploadsFiles";
        FILE_TEST_NAME = "TEST_TEST.png";
        TEMP_FILE_NAME_TEST = "TEST_TEST_2023-02-05T10-25-35.png";
        MULTIPART_FILE = new MockMultipartFile("test1.png","test1.png","IMAGE/png", new FileInputStream(new File(RESOURCE_DIRECTORY+"/testPNG1.png")));

    }
    /**************************** Test getTempFile ****************************/
    @Test
    public void getTempFilesByFileNameTest_With_FileExist() throws IOException {
        /*Création du repertoir et du fichier a récupérer*/
        Path fileNameAndPath = Paths.get(TEMP_DIRECTORY, FILE_TEST_NAME);
        Files.write(fileNameAndPath,MULTIPART_FILE.getBytes());
        Assertions.assertNotEquals(null,uploadServiceTest.getTempFilesByFileName(FILE_TEST_NAME));
        Assertions.assertEquals(FILE_TEST_NAME,uploadServiceTest.getTempFilesByFileName(FILE_TEST_NAME).getName());
        uploadServiceTest.delteFiles(uploadServiceTest.getTempFilesByFileName(FILE_TEST_NAME));
    }
    @Test
    public void getTempFilesByFileNameTest_With_FileNotExist() throws IOException {
        Assertions.assertEquals(null,uploadServiceTest.getTempFilesByFileName(FILE_TEST_NAME));
    }
    /**************************** Test getUploadsFile ****************************/
    @Test
    public void getUploadsFilesByFileNameTest_With_FileExist() throws IOException {
        /*Création du repertoir et du fichier a récupérer*/
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, FILE_TEST_NAME);
        Files.write(fileNameAndPath,MULTIPART_FILE.getBytes());
        Assertions.assertNotEquals(null,uploadServiceTest.getUploadsFilesByFileName(FILE_TEST_NAME));
        Assertions.assertEquals(FILE_TEST_NAME,uploadServiceTest.getUploadsFilesByFileName(FILE_TEST_NAME).getName());
        uploadServiceTest.delteFiles(uploadServiceTest.getUploadsFilesByFileName(FILE_TEST_NAME));
    }
    @Test
    public void getUploadsFilesByFileNameTest_With_FileNotExist(){
        Assertions.assertEquals(null,uploadServiceTest.getUploadsFilesByFileName(FILE_TEST_NAME));
    }

    /**************************** Test saveMultipartFile ****************************/
    @Test
    public void saveMultipartFileTest() throws IOException {
        uploadServiceTest.saveMultipartFile(MULTIPART_FILE,TEMP_DIRECTORY,FILE_TEST_NAME);
        boolean tempFile = false;
        File dir = new File(TEMP_DIRECTORY);
        File[] filesList = dir.listFiles();
        for (File f : filesList){
            if (f.getName().equals(FILE_TEST_NAME)){
                tempFile = true;
            }
        }
        Assertions.assertTrue(tempFile);
        uploadServiceTest.delteFiles(uploadServiceTest.getTempFilesByFileName(FILE_TEST_NAME));
    }

    /**************************** Test uploadFileToTemp ****************************/
    @Test
    public void uploadFileToTempTest_WithGoodFile(){
        String fileNameTest = uploadServiceTest.uploadFileToTemp(MULTIPART_FILE,"LABEL_TEST");
        Assertions.assertTrue(fileNameTest.contains("LABEL_TEST"+"_"+ LocalDate.now(ZoneId.of("Europe/Paris"))));

        boolean tempFile = false;
        File dir = new File(TEMP_DIRECTORY);
        File[] filesList = dir.listFiles();
        for (File f : filesList){
            if (f.getName().equals(fileNameTest)){
                tempFile = true;
            }
        }
        Assertions.assertTrue(tempFile);
        uploadServiceTest.delteFiles(uploadServiceTest.getTempFilesByFileName(fileNameTest));
    }
    @Test
    public void uploadFileToTempTest_WithWrongFile(){
        String fileNameTest = uploadServiceTest.uploadFileToTemp(null,"LABEL_TEST");
        Assertions.assertTrue(fileNameTest.equals(""));

        boolean tempFile = false;
        File dir = new File(TEMP_DIRECTORY);
        File[] filesList = dir.listFiles();
        for (File f : filesList){
            if (f.getName().equals(fileNameTest)){
                tempFile = true;
            }
        }
        Assertions.assertFalse(tempFile);
    }

    /**************************** Test copyFileToUploads ****************************/
    @Test
    public void copyFilesToUploadsTest() throws IOException {
        /*Création d'un fichier Test dans /tempFiles*/
        Path fileNameAndPath = Paths.get(TEMP_DIRECTORY, TEMP_FILE_NAME_TEST);
        Files.write(fileNameAndPath, MULTIPART_FILE.getBytes());

        /*récupération du fichier Test dans /tempFiles*/
        File tempDir = new File(TEMP_DIRECTORY);
        File tempFile = null;
        File[] filesListe = tempDir.listFiles();
        for (File f : filesListe){
            if (f.getName().equals(TEMP_FILE_NAME_TEST)){
                tempFile = f;
            }
        }

        /*Lancement fonction*/
        String destFileName = uploadServiceTest.copyFilesToUploads(tempFile,1L);

        /*Test retour fonction*/
        Assertions.assertTrue(destFileName.equals("TEST_TEST_1.png"));

        /*Récupération fichier créé dans /uploadsFiles*/
        File UploadFile=null;
        File uploadDir = new File(UPLOAD_DIRECTORY);
        File[] filesList = uploadDir.listFiles();
        for (File f : filesList){
            if (f.getName().equals(destFileName)){
                UploadFile = f;
            }
        }

        /*Test si fichier existe*/
        Assertions.assertTrue(UploadFile.exists());

        /*Suppression fichier*/
        uploadServiceTest.delteFiles(UploadFile);
    }

}
