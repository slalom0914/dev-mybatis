package com.example.demo.controller;

import com.example.demo.service.DeptService;
import com.example.demo.vo.DeptVO;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/dept/*")
public class RestDeptController {
    @Autowired
    private DeptService deptService = null;
    @GetMapping("deptList")
    public String deptList(DeptVO dvo){
        log.info("deptList");
        List<DeptVO> list = null;
        list = deptService.deptList(dvo);
        Gson gson = new Gson();
        String temp = gson.toJson(list);
        return temp;
    }
}
