package com.example.demo.Controller;


import com.example.demo.Dtos.FileDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@Slf4j
@RequestMapping("/file")
public class FileController {
    //어디다가 저장할지??
    private String ROOT_PATH = "c:"; //LINUX OS : '/'
    private String UPLOAD_PATH = "upload";


    @GetMapping("upload")
    public void upload() {
        log.info("GET /file/upload...........");
    }
    @PostMapping("upload")
    public void upload_post(
            @RequestParam("file") MultipartFile file //업로드할 때 사용하는 클래스
            //("file")과 html 폼에 있는 file 이름이 일치하면 리퀘스트하도록.
    ) throws IOException {

        log.info("POST /file/upload........" + file);
        System.out.println("FILE NAME : " + file.getOriginalFilename());
        System.out.println("FILE SIZE : " + file.getSize() + " Byte");
        String filename = file.getOriginalFilename();
        String uploadPath = ROOT_PATH
                + File.separator// 구분자 '\' , '/'
                + UPLOAD_PATH
                + File.separator; /* 최종적으로 'c:/upload/' 까지 들어감*/
        //업로드 폴더가 없으면 새로 생성
        File dir = new File(uploadPath);
        if (!dir.exists())
            dir.mkdirs();
        //파일 업로드(단일파일)
        File fileObject = new File(uploadPath, filename);
        file.transferTo(fileObject); //서블릿JSP의 인아웃 스트림 작업을 대신해줌

    }
    @PostMapping("/uploads")
    @ResponseBody
    public void uploads_post(
            @RequestParam("files") MultipartFile[] files //복수파일은 배열로 받기
    ) throws IOException {

        log.info("POST /file/upload........"+files);

        String uploadPath = ROOT_PATH
                + File.separator// 구분자 '\' , '/'
                + UPLOAD_PATH
                + File.separator; /* 최종적으로 'c:/upload/' 까지 들어감*/
        //업로드 폴더가 없으면 새로 생성
        File dir = new File(uploadPath);
        if(!dir.exists())
            dir.mkdirs();

        for(MultipartFile file : files) {
            System.out.println("FILE NAME : " + file.getOriginalFilename());
            System.out.println("FILE SIZE : " + file.getSize() + " Byte");
            System.out.println("--------------------------------");
            String filename = file.getOriginalFilename();
            //파일 업로드(단일파일)
            File fileObject = new File(uploadPath,filename);
            file.transferTo(fileObject); //서블릿JSP의 인아웃 스트림 작업을 대신해줌
        }
    }
    @PostMapping("/upload_dto")
    @ResponseBody
    public void uploads_post_dto(FileDto dto) throws IOException {
        log.info("POST /file/upload_dto........"+dto);
        MultipartFile [] files = dto.getFiles();

        String uploadPath = ROOT_PATH
                + File.separator// 구분자 '\' , '/'
                + UPLOAD_PATH
                + File.separator; /* 최종적으로 'c:/upload/' 까지 들어감*/
        //업로드 폴더가 없으면 새로 생성
        File dir = new File(uploadPath);
        if(!dir.exists())
            dir.mkdirs();

        for(MultipartFile file : files) {
            System.out.println("FILE NAME : " + file.getOriginalFilename());
            System.out.println("FILE SIZE : " + file.getSize() + " Byte");
            System.out.println("--------------------------------");
            String filename = file.getOriginalFilename();
            //파일 업로드(단일파일)
            File fileObject = new File(uploadPath,filename);
            file.transferTo(fileObject); //서블릿JSP의 인아웃 스트림 작업을 대신해줌
        }
    }

}
