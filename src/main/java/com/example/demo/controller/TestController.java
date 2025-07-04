package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.TestService;
import lombok.extern.log4j.Log4j2;
import com.example.demo.vo.TestVO;
import com.google.gson.Gson;

@Log4j2
@RestController
@RequestMapping("/test/*")
public class TestController {
  @Autowired
  private TestService testService = null;
  //SELECT t_no, t_title, t_content FROM test
  @GetMapping("testList") 
  public String testList(){
    log.info("testList");
    List<TestVO> list = null;
    list = testService.testList();
    Gson g = new Gson();
    String temp = g.toJson(list);
    return temp;
  }
}
/*
 * 응답페이지를 jsp로 내보낼 생각이면 @Controller사용하고
 * 응답을 json포맷이나 문자열로 내보낼 생각이면 @RestController사용함
 * 
 */