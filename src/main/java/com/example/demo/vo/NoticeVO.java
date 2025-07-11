package com.example.demo.vo;

import lombok.Data;
//아래 클래스는 오라클 서버에 notice테이블을 대신할 클래스이다
@Data
public class NoticeVO {
    private int n_no;
    private String n_title;
    private String n_content;
}
