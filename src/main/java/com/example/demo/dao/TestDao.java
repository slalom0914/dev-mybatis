package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.demo.vo.TestVO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository
public class TestDao {
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;
  public List<Map<String,Object>> testCommentList(Map<String,Object> pmap) {
    log.info("testCommentList");
    List<Map<String,Object>> list = null;
    list = sqlSessionTemplate.selectList("testCommentList", pmap);
    return list;
  }
  public List<Map<String,Object>> testList(Map<String,Object> pmap) {
    log.info("testList");
    List<Map<String,Object>> list = null;
    list = sqlSessionTemplate.selectList("testList", pmap);
    return list;
  }
  public int testInsert(Map<String,Object> pmap) {
    int result = 0;
    result = sqlSessionTemplate.insert("testInsert",pmap);
    return result;
  }
  public int testUpdate(TestVO tvo) {
    log.info("testUpdate");
    int result = 0;
    result = sqlSessionTemplate.update("testUpdate", tvo);
    return result;
  }
  public int testDelete(int t_no) {
    log.info("testDelete");
    int result = 0;
    result = sqlSessionTemplate.delete("testDelete", t_no);
    return result;
  }

}
