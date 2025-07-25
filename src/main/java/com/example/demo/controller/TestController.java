package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.TestService;
import lombok.extern.log4j.Log4j2;
import com.example.demo.vo.TestVO;
import com.google.gson.Gson;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@RestController
@RequestMapping("/test/*")
public class TestController {
  @Value("${uploadPath}")
  private String uploadPath;
  @Autowired
  private TestService testService = null;

  //이미지 업로드 하기
  @PostMapping("imageUpload")
  public String imageUpload(@RequestParam(value="image") MultipartFile image) {
    log.info("imageUpload");
    log.info("image : " + image);
    //리액트에서 요청된 이미지 파일을 톰캣 서버에 pds폴더에 업로드 처리함
    String filename = testService.imageUpload(image);
    return filename;
  }
  //이미지 읽어오기 - Quill Editor 이미지 미리보기용
  // http://localhost:8000/test/imageGet?imageName=man.png
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


  //SELECT t_no, t_title, t_content FROM test
  @GetMapping("testList") 
  public String testList(@RequestParam Map<String,Object> pmap){
    log.info("testList");
    List<Map<String,Object>> list = null;
    list = testService.testList(pmap);
    Gson g = new Gson();
    String temp = g.toJson(list);
    return temp;
  }
  @GetMapping("testDetail")
  public String testDetail(@RequestParam Map<String,Object> pmap){
    log.info("testDetail");
    log.info("pmap : " + pmap);//t_no가 있어야 한다.
    List<Map<String,Object>> list = null;
    //Map<String,Object> rmap = null;
    list = testService.testDetail(pmap);
    log.info("list : " + list);
    Gson g = new Gson();
    String temp = g.toJson(list);
    return temp;
  }
  //Rest API에서 post는 브라우저를 통해서 단위테스트가 불가함
  //insert into test values(1,'제목1','내용1')
  @PostMapping("testInsert")
  public String testInsert(@RequestBody Map<String,Object> pmap){
    int result = 0;//1이면 입력 성공 0이면 입력 실패
    result = testService.testInsert(pmap);
    return String.valueOf(result);
  }
  //update test set t_title=?, t_content=? where t_no=?
  @PutMapping("testUpdate")
  public String testUpdate(TestVO tvo){
    log.info("testUpdate");
    int result = 0;
    result = testService.testUpdate(tvo);
    return String.valueOf(result);
  }
  @DeleteMapping("testDelete")
  public int testDelete(@RequestParam int t_no){
    log.info("testDelete : " + t_no);
    int result = 0;
    result = testService.testDelete(t_no);
    return result;
  }
}
/*
 * 응답페이지를 jsp로 내보낼 생각이면 @Controller사용하고
 * 응답을 json포맷이나 문자열로 내보낼 생각이면 @RestController사용함
 * 
 */