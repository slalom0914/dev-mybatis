package com.example.demo.controller;

import com.example.demo.service.EmpService;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/emp/*")
public class RestEmpController {
    @Autowired
    private EmpService empService;
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
        log.info(pmap);
        return "empDetail";
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
