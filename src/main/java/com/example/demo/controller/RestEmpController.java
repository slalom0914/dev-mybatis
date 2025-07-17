package com.example.demo.controller;

import com.example.demo.service.EmpService;
import com.google.gson.Gson;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/emp/*")
public class RestEmpController {
    @Value("${uploadPath}")
    private String uploadPath;
    @Autowired
    private EmpService empService;
    //페이징 처리 실습 메서드 선언 - 오라클 : paging
    @GetMapping("pagingList")
    public String pagingList(){
        List<Map<String, Object>> list = null;
        list = empService.pagingList();
        Gson gson = new Gson();
        String temp = gson.toJson(list);
        return temp;
    }

    //이미지 업로드 하기
    @PostMapping("imageUpload")
    public String imageUpload(@RequestParam(value="image") MultipartFile image) {
        log.info("imageUpload");
        log.info("image : " + image);
        //리액트에서 요청된 이미지 파일을 톰캣 서버에 pds폴더에 업로드 처리함
        String filename = empService.imageUpload(image);
        return filename;
    }
    //이미지 읽어오기 - Quill Editor 이미지 미리보기용
    // http://localhost:8000/emp/imageGet?imageName=man.png
    @GetMapping("imageGet")
    public String imageGet(HttpServletRequest req, HttpServletResponse res) {
        log.info("imageGet");
        String b_file = req.getParameter("imageName");
        log.info("b_file : " + b_file);
        String filePath = "D:\\dev_lab\\07.myBatis\\dev-mybatis\\src\\main\\webapp\\pds";
        String fname = b_file;
        File file = new File(filePath, b_file.trim());
        String mimeType = req.getServletContext().getMimeType(file.toString());
        if (mimeType == null) {
            res.setContentType("application/octet-stream");
        }
        String downName = null;
        FileInputStream fis = null;
        ServletOutputStream sos = null;
        try{
            if(req.getHeader("User-Agent").indexOf("MSIE") == -1){
                downName = new String(b_file.getBytes("UTF-8"),"8859_1");
            }else{
                downName = new String(b_file.getBytes("EUC-KR"),"8859_1");
            }
            res.setHeader("Content-Disposition", "attachment; filename=" + downName);
            fis = new FileInputStream(file);
            sos = res.getOutputStream();
            byte[] b = new byte[1024*10];
            int data = 0;
            while((data = (fis.read(b, 0, b.length))) != -1){
                sos.write(b,0,data);
            }
            sos.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(sos != null) sos.close();
                if(fis != null) fis.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
    //이미지 다운로드
    //imageName키값은 스프링에서 request.getParameter가 아니어도 사용자가 입력한 값을 읽어올 수 있다.
    //emp.ephoto값은 오라클 서버에서 select한 결과값이다.
    //http://localhost:8000/emp/imageDownload?imageName=emp.ephoto
    @GetMapping("imageDownload")
    public ResponseEntity<Resource> imageDownload(@RequestParam(value="imageName") String imageName) {
        log.info("imageDownload");
        try {
            File file = new File(uploadPath, URLDecoder.decode(imageName, "UTF-8"));
            HttpHeaders header = new HttpHeaders();
            header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + imageName);
            header.add("Cache-Control", "no-cache, no-store, must-revalidate");
            header.add("Pragma", "no-cache");
            header.add("Expires", "0");

            Path path = Paths.get(file.getAbsolutePath());
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
            return ResponseEntity.ok()
                    .headers(header)
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(resource);

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }//end of imageDownload


    //전체 조회(조검검색포함) - 리턴타입과 파라미터 처리
    @GetMapping("empList")
    public String empList(@RequestParam Map<String,Object> pmap){
        log.info("empList");
        log.info(pmap);
        List<Map<String,Object>> list = null;
        list = empService.empList(pmap);
        Gson gson = new Gson();
        String temp = gson.toJson(list);
        return temp;
    }
    //상세 조회 - 리턴과 파라미터
    @GetMapping("empDetail")
    public String empDetail(@RequestParam Map<String,Object> pmap){
        log.info("empDetail");
        log.info(pmap);//empno=7566
        Map<String,Object> rmap = null;
        rmap = empService.empDetail(pmap);
        Gson gson = new Gson();
        String temp = gson.toJson(rmap);
        return temp;
    }
    //Post나 Put은 브라우저로 부터 인터셉트를 당하지 않음
    //입력 - 리턴과 파라미터
    @PostMapping("empInsert")
    public String empInsert(@RequestBody Map<String,Object> pmap){
        log.info("empInsert");
        log.info(pmap);
        int result = 0;
        result = empService.empInsert(pmap);
        return String.valueOf(result);
    }
    //수정 - 리턴과 파라미터
    @PutMapping("empUpdate")
    public String empUpdate(@RequestBody Map<String,Object> pmap){
        log.info("empUpdate");
        log.info(pmap);
        int result = 0;
        result = empService.empUpdate(pmap);
        return String.valueOf(result);
    }
    //삭제 - 리턴과 파라미터
    @DeleteMapping("empDelete")
    public String empDelete(@RequestParam Map<String,Object> pmap){
        log.info("empDelete");
        log.info(pmap);
        int result = 0;
        result = empService.empDelete(pmap);
        return String.valueOf(result);
    }
}
