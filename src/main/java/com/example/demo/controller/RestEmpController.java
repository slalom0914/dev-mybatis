package com.example.demo.controller;

import com.example.demo.service.EmpService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return "empList";
    }
    //상세 조회 - 리턴과 파라미터
    @GetMapping("empDetail")
    public String empDetail(@RequestParam Map<String,Object> pmap){
        log.info("empDetail");
        return "empDetail";
    }
    //입력 - 리턴과 파라미터
    @PostMapping("empInsert")
    public String empInsert(@RequestBody Map<String,Object> pmap){
        log.info("empInsert");
        return "empInsert";
    }
    //수정 - 리턴과 파라미터
    @PutMapping("empUpdate")
    public String empUpdate(@RequestBody Map<String,Object> pmap){
        log.info("empUpdate");
        return "empUpdate";
    }
    //삭제 - 리턴과 파라미터
    @DeleteMapping("empDelete")
    public String empDelete(@RequestParam Map<String,Object> pmap){
        log.info("empDelete");
        return "empDelete";
    }
}
