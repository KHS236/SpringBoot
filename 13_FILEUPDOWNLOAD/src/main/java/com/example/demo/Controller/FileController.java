package com.example.demo.Controller;


import com.example.demo.Dtos.FileDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/file")
public class FileController {
    //어디다가 저장할지??
    private String ROOT_PATH = "c:"; //LINUX OS : '/'
    private String UPLOAD_PATH = "upload";


    @GetMapping("/upload")
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

    @PostMapping("/upload_dto_async")
    @ResponseBody
    public ResponseEntity<String> uploads_post_dto_async(FileDto dto) throws IOException {
        log.info("POST /file/upload_dto_async..."+dto);
        MultipartFile [] files = dto.getFiles();

        String uploadPath = ROOT_PATH
                + File.separator// 구분자 '\' , '/'
                + UPLOAD_PATH
                + File.separator; // 'c:\\upload\\'
        //업로드 폴더 부재시 생성
        File dir = new File(uploadPath);
        if(!dir.exists())
            dir.mkdirs();

        for(MultipartFile file : files)
        {
            System.out.println("FILE NAME : " + file.getOriginalFilename());
            System.out.println("FILE SIZE : " + file.getSize()+" Byte");
            System.out.println("-----------------------");
            String filename = file.getOriginalFilename();
            //파일 업로드(단일파일)
            File fileObject = new File(uploadPath,filename);
            file.transferTo(fileObject);
        }

        return new ResponseEntity<>("Success..!", HttpStatus.OK);
    }


    @GetMapping("/upload2")
    public void upload2() {
        log.info("GET /file/upload2.......");
    }

    @GetMapping("/list")
    public void list(Model model) {
        log.info("GET /file/list.....");
        String UploadPath = ROOT_PATH
                            +File.separator
                            + UPLOAD_PATH
                            + File.separator;
        File baseDir = new File(UploadPath);
        File [] lists = baseDir.listFiles();

        List<String> fileList = new ArrayList<>();
        for(File item : lists){
            System.out.println("item : " + item);
            //업로드 된 파일을 리스트로 확인
            System.out.println("item : " + item.getName());
            fileList.add(item.getName());

        }

        model.addAttribute("fileList",fileList);

    }

    //produces = 응답 헤더 / consume = 요청 헤더 /consumes = MediaType.APPLICATION_JSON_VALUE == JSON으로 요청하겠따
    @GetMapping(value="/download" ,produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public ResponseEntity<Resource> download(
            @RequestParam("filename") String filename
    ) throws UnsupportedEncodingException {
        log.info("GET /file/download..." + filename);

        String path = ROOT_PATH
                + File.separator
                + UPLOAD_PATH
                + File.separator
                + filename;
        //FileSystemResource : 파일시스템의 특정 파일로부터 정보를 가져오는데 사용
        Resource resource = new FileSystemResource(path);
        //헤더 정보 추가
        HttpHeaders headers = new HttpHeaders();
        //ISO-8859-1 : 라틴어(특수문자등 깨짐 방지)
        headers.add("Content-Disposition", "attachment; filename=" + new String(filename.getBytes("UTF-8"), "ISO-8859-1"));
        //리소스,파일정보가 포함된 헤더,상태정보를 전달
        return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);

    }
}
