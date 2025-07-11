package com.example.demo.service;

import com.example.demo.dao.NoticeDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//모델계층 일부
//의사결정 한다
//의사결정에 따라 여러 Dao클래스의 메소드와 연결될 것이다.
//스프링은 객체에 대한 제어권을 개발자가 아닌 자기가 쥔다. - 제어권을 가져갔다.
//제어역전, 역제어
//필요한 객체가 있다면 외부에서 대신 주입해준다.
@Log4j2
@Service
public class NoticeService {
    @Autowired
    private NoticeDao noticeDao;
}
