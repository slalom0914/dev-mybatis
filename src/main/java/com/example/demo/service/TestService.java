package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TestDao;
import com.example.demo.vo.TestVO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class TestService {
  @Autowired
  private TestDao testDao = null;

  public List<TestVO> testList() {
    log.info("testList");
    List<TestVO> list = null;
    list = testDao.testList();//null이 올 수도 있습니다
    return list;
  }
}
