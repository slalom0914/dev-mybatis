package com.example.demo.service;

import com.example.demo.dao.EmpDao;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class EmpService {
    @Autowired
    private EmpDao empDao;
}
