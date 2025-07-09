package com.example.demo.service;

import com.example.demo.dao.DeptDao;
import com.example.demo.vo.DeptVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class DeptService {
    @Autowired
    private DeptDao deptDao = null;
    public List<DeptVO> deptList(DeptVO dvo) {
        log.info("deptList");
        List<DeptVO> list = null;
        list = deptDao.deptList(dvo);
        return list;
    }

    public int deptDelete(DeptVO dvo) {
        log.info("deptDelete dvo : "+dvo.getDeptno());
        int result = 0;
        result = deptDao.deptDelete(dvo);
        return result;
    }

    public int deptUpdate(DeptVO dvo) {
        log.info("deptUpdate dvo : "+dvo.getDeptno());
        int result = 0;
        result = deptDao.deptUpdate(dvo);
        return result;
    }

    public int deptInsert(DeptVO dvo) {
        log.info("deptInsert");
        int result = 0;
        result = deptDao.deptInsert(dvo);
        return result;
    }
}
