package com.example.demo.controller;

import com.example.demo.service.DeptService;
import com.example.demo.vo.DeptVO;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/dept/*")
public class RestDeptController {
    @Autowired
    private DeptService deptService = null;

    @PostMapping
    public String deptInsert(@RequestBody DeptVO dvo) {
        int result = 0;
        result = deptService.deptInsert(dvo);
        return String.valueOf(result);
    }

    @GetMapping("deptList")
    public String deptList(DeptVO dvo){
        log.info("deptList");
        log.info(dvo);//null, 0,null, null
        List<DeptVO> list = null;
        list = deptService.deptList(dvo);
        Gson gson = new Gson();
        String temp = gson.toJson(list);
        return temp;
    }
    @GetMapping("deptDetail")
    public String deptDetail(DeptVO dvo){
        log.info("deptDetail");
        log.info(dvo);//10 or 20 or 30 or 40,  null, null
        List<DeptVO> list = null;
        list = deptService.deptList(dvo);
        Gson gson = new Gson();
        String temp = gson.toJson(list);
        return temp;
    }
    @DeleteMapping("deptDelete")
    public String deptDelete(DeptVO dvo){
        log.info("deptDetail");
        log.info(dvo);//10 or 20 or 30 or 40,  null, null
        log.info(dvo.getDeptno());//0보다 커야 삭제 처리가 된다.
        int result=0;
        result = deptService.deptDelete(dvo);
        return  String.valueOf(result);
    }
    //리액트에서 객체 리터럴로 파라미터를 받아 올 때는 @RequestParam으로는
    //못 받아 온다. 반드시 @RequestBody할 것.
    @PutMapping("deptUpdate")
    public String deptUpdate(@RequestBody DeptVO dvo){
        log.info("deptUpdate");
        log.info(dvo.getDeptno()+","+dvo.getDname()+", "+dvo.getLoc());//10 or 20 or 30 or 40,  null, null
        int result=0;
        result = deptService.deptUpdate(dvo);
        return  String.valueOf(result);
    }
}
