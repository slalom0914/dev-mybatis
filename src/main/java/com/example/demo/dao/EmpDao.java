package com.example.demo.dao;

import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
//Dao클래스 와 오라클 서버 사이에는 MyBatis Layer필요

@Log4j2
@Repository
public class EmpDao {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
}
