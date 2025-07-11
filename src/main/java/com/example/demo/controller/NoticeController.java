package com.example.demo.controller;

import com.example.demo.vo.NoticeVO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Controller
@RequestMapping("/notice/*")
public class NoticeController {
    //http://localhost:8000/notice/cnoticeList
    @GetMapping("cnoticeList")
    public String cnoticeList(HttpServletRequest req)
    {
        log.info("cnoticeList");
        List<NoticeVO> list=new ArrayList<>();
        NoticeVO vo=new NoticeVO();
        vo.setN_no(1);
        vo.setN_title("공지제목1");
        vo.setN_content("공지내용1");
        list.add(vo);
        req.setAttribute("list",list);
        //RequestDispatcher rd=req.getRequestDispatcher("/notice/list.jsp");
        //rd.forward(req,res);
        //URL주소창에는 /notice/cnoticeList 인데 실제 출력된 페이지는
        //notice/noticeList.jsp가 보인다
        return "forward:./noticeList.jsp";
    }//end of cnoticeList
}
