package com.example.demo.dao;

import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
//Dao클래스 와 오라클 서버 사이에는 MyBatis Layer필요

@Log4j2
@Repository
public class EmpDao {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public Map<String, Object> empDetail(Map<String, Object> pmap) {
        Map<String, Object> rmap = null;
        rmap = sqlSessionTemplate.selectOne("empList", pmap);
        return rmap;
    }
    public List<Map<String, Object>> empList(Map<String, Object> pmap) {
        List<Map<String, Object>> list = null;
        list = sqlSessionTemplate.selectList("empList", pmap);
        return list;
    }

    public int empInsert(Map<String, Object> pmap) {
        int result = 0;
        result = sqlSessionTemplate.insert("empInsert", pmap);
        return result;
    }

    public int empUpdate(Map<String, Object> pmap) {
        int result = 0;
        result = sqlSessionTemplate.update("empUpdate", pmap);
        return result;
    }

    public int empDelete(Map<String, Object> pmap) {
        int result = 0;
        int empno = 0;
        //변수 - 배열(new예약어사용) - 객체배열 - 객체 - 자료구조(컬렉션프레임워크-읽기와쓰기)
        //Object
        if(pmap.containsKey("empno")){
            empno = Integer.parseInt(pmap.get("empno").toString());
        }
        result = sqlSessionTemplate.delete("empDelete", empno);
        return result;
    }

    public List<Map<String, Object>> pagingList(Map<String, Object> pmap) {
        log.info("pagingList");
        List<Map<String, Object>> list = null;
        list = sqlSessionTemplate.selectList("pagingList", pmap);
        return list;
    }

    public int pagingCount(Map<String, Object> pmap) {
        log.info("pagingCount");
        int total = 0;
        total = sqlSessionTemplate.selectOne("pagingCount", pmap);
        return total;
    }
}
