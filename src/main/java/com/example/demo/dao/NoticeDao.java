package com.example.demo.dao;

import com.example.demo.vo.NoticeVO;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Log4j2
@Repository
public class NoticeDao {
    @Autowired
    private SqlSessionTemplate sqlSession;

    //insert into notice values(1,'공지제목1','공지내용1');
    public int noticeInsert(NoticeVO nvo){
        log.info("noticeInsert");
        int result = 0;
        result = sqlSession.insert("noticeInsert",nvo);
        return result;
    }

    //select n_no, n_title, n_content from notice
    //where n_no=:x;
    public List<NoticeVO> noticeList(NoticeVO nvo){
        log.info("noticeList");
        List<NoticeVO> list = null;
        list = sqlSession.selectList("noticeList", nvo);
        return list;
    }


    //update notice
    //set n_title =:x
      //,n_content =:y
    //where n_no =:z;
    public int noticeUpdate(NoticeVO nvo){
        log.info("noticeUpdate");
        int result = 0;
        result = sqlSession.update("noticeUpdate",nvo);
        return result;
    }

    //delete from notice where n_no=:x;
    public int noticeDelete(int n_no){
        log.info("noticeDelete");
        int result = 0;
        result = sqlSession.delete("noticeDelete",n_no);
        return result;
    }

}
