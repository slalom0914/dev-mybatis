<%@ page import="com.example.demo.vo.NoticeVO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page language="java" contentType="application/json; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    List<NoticeVO> list = (List)request.getAttribute("list");
    //out.println(list);//[{n_no=1,}]
    Gson gson = new Gson();
    String temp = gson.toJson(list);
    out.println(temp);
%>