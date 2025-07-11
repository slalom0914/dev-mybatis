package com.example.demo.service;

import com.example.demo.dao.NoticeDao;
import com.example.demo.vo.NoticeVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    //조회
    public List<NoticeVO> noticeList(NoticeVO nvo){
        log.info("noticeList");
        List<NoticeVO> list = null;
        list = noticeDao.noticeList(nvo);
        return list;
    }
    //입력
    public int noticeInsert(NoticeVO nvo){
        log.info("noticeInsert");
        int result = 0;
        result = noticeDao.noticeInsert(nvo);
        return result;
    }
    //수정
    public int noticeUpdate(NoticeVO nvo){
        log.info("noticeUpdate");
        int result = 0;
        result = noticeDao.noticeUpdate(nvo);
        return result;
    }
    //삭제
    public int noticeDelete(int n_no){
        log.info("noticeDelete");
        int result = 0;
        result = noticeDao.noticeDelete(n_no);
        return result;
    }
}
