package com.example.demo.dao;

import com.example.demo.vo.DeptVO;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Repository
public class DeptDao {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate = null;

    public List<DeptVO> deptList(DeptVO dvo) {
        log.info("deptList");
        log.info(dvo);
        List<DeptVO> list = null;
        list = sqlSessionTemplate.selectList("deptList", dvo);
        return list;
    }

    public int deptDelete(DeptVO dvo) {
        int result = 0;
        result = sqlSessionTemplate.delete("deptDelete", dvo);
        log.info("result : "+result);
        return result;
    }
}
