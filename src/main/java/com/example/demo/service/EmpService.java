package com.example.demo.service;

import com.example.demo.dao.EmpDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Log4j2
@Service
public class EmpService {
    //resources>application.yml
    @Value("${uploadPath}")
    private String uploadPath;
    @Autowired
    private EmpDao empDao;

    public Map<String, Object> empDetail(Map<String, Object> pmap) {
        Map<String, Object> rmap = null;
        rmap = empDao.empDetail(pmap);
        return rmap;
    }
    public List<Map<String, Object>> empList(Map<String, Object> pmap) {
        List<Map<String, Object>> list = null;
        list = empDao.empList(pmap);
        if(list==null){
            list=new ArrayList<>();
        }
        return list;
    }

    public int empInsert(Map<String, Object> pmap) {
        log.info("empInsert");
        int result = 0;
        result = empDao.empInsert(pmap);
        return  result;
    }

    public int empUpdate(Map<String, Object> pmap) {
        log.info("empUpdate");
        int result = 0;
        result = empDao.empUpdate(pmap);
        return  result;
    }
    //업무에 경우 선택과 결정한다 - 업무 진행 방향이 바뀐다.
    public int empDelete(Map<String, Object> pmap) {
        log.info("empDelete");
        int result = 0;
        result = empDao.empDelete(pmap);
        return  result;
    }

    public String imageUpload(MultipartFile image) {
        Map<String,Object> map = new HashMap<>();
        //이미지파일을 톰캣 서버가 바라보는 프로젝트 폴더 pds에 업로드 한다.
        String filename = null;
        String fullPath = null;
        //이미지 파일이 존재하면....
        //만일 같은 이름의 파일이 존재하면 안되니까.... 예방코드 작성
        if(image!=null && !image.isEmpty()){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            Calendar time = Calendar.getInstance();
            filename = sdf.format(time.getTime())+"-"+image.getOriginalFilename().replaceAll(" ","-");
            fullPath = uploadPath+filename;
            try {
                //File객체는 파일명을 객체로 만들어주는 클래스 이다.
                File f = new File(fullPath);
                //위에서 File객체는 파일명만 생성할 뿐 내용까지 포함되는 건 아니다.
                byte[] bytes = image.getBytes();
                //OutputStream을 생성하여 파일객체에 읽어들인 내용들을 쓴다.
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(f));
                out.write(bytes);
                //입출력 관련 클래스는 사용 후 반드시 닫아준다.
                out.close();
                //파일 처리하는 경우 - 추가 파일 정보가 필요할 때
                //파일 크기
                double size = Math.floor(filename.length()/(1024.0*1024.0)*10/10);
                log.info(size);
                map.put("filename",filename);
                map.put("filesize",size);
            }catch (Exception e){
                e.printStackTrace();
            }
        }//end of if
        return filename;
    }//imageUpload

    public List<Map<String, Object>> pagingList() {
        List<Map<String, Object>> list = null;
        return list;
    }
}
