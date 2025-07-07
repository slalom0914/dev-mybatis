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
        List<DeptVO> list = null;
        //list = sqlSessionTemplate.selectList("deptMapper.deptList", dvo);
        list = new ArrayList<>();
        DeptVO deptVO = new DeptVO();
        deptVO.setDeptno(50);
        deptVO.setDname("개발부");
        deptVO.setLoc("제주");
        list.add(deptVO);
        return list;
    }
}
