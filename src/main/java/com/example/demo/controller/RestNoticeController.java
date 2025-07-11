package com.example.demo.controller;

import com.example.demo.service.NoticeService;
import com.example.demo.vo.NoticeVO;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.util.Map;

//나는 이제부터 req와 res없어도 모든 요청에 대해서 처리할 수 있어
@Log4j2
@RestController
@RequestMapping("/notice/*")
public class RestNoticeController{
    @Autowired
    private NoticeService noticeService;
    @GetMapping("noticeList3")
    public String noticeList3(NoticeVO nvo) throws Exception
    {
        log.info("noticeList3");
        //쿼리스트링 값 가져오기
        log.info(nvo.getN_title());
        System.out.println(nvo.getN_content());
        return "[{n_no:3, n_title:'제목3', n_content:'내용3'}]";
    }//end of noticeList3
    //파라미터 자리에 VO타입은 추가 어노테이션없이 사용이 가능하지만
    //Map타입을 사용시에는 @RequestParam생략할 수  없다
    @GetMapping("noticeList2")
    public String noticeList2(@RequestParam Map<String,Object> pmap) throws Exception
    {
        log.info("noticeList2");
        //쿼리스트링 값 가져오기
        log.info(pmap.get("n_title"));
        System.out.println(pmap.get("n_content"));
        return "[{n_no:2, n_title:'제목21', n_content:'내용21'}]";
    }//end of noticeList2
    @GetMapping("noticeList")
    public void noticeList(HttpServletRequest request, HttpServletResponse response)
    throws Exception
    {
        log.info("noticeList");
        //쿼리스트링 값 가져오기
        log.info(request.getParameter("n_title"));
        System.out.println(request.getParameter("n_content"));
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("[{n_no:1, n_title:'제목1', n_content:'내용1'}]");
        //return  "noticeList";
    }//end of noticeList
    //@GetMapping("noticeDetail")
    //@PostMapping("noticeInsert")
    //@PutMapping("noticeUpdate")
    //@DeleteMapping("noticeDelete")
}
