package com.example.demo.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TestDao;
import com.example.demo.vo.TestVO;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@Service
public class TestService {
  //resources>application.yml
  @Value("${uploadPath}")
  private String uploadPath;
  @Autowired
  private TestDao testDao = null;

  public List<Map<String,Object>> testList(Map<String,Object> pmap) {
    log.info("testList");
    List<Map<String,Object>> list = null;
    list = testDao.testList(pmap);//null이 올 수도 있습니다
    return list;
  }
  public List<Map<String,Object>> testDetail(Map<String,Object> pmap) {
    log.info("testDetail");
    List<Map<String,Object>> list = null;
    list = testDao.testList(pmap);//null이 올 수도 있습니다
    List<Map<String,Object>> clist = testDao.testCommentList(pmap);
    log.info(clist);
    //댓글이 존재하면
    if(clist !=null && clist.size()>0){
      Map<String,Object> cmap = new HashMap<>();
      cmap.put("comment", clist);
      list.add(1, cmap);
    }
    return list;
  }

  public int testInsert(Map<String,Object> pmap) {
    log.info("testInsert");
    int result = 0;
    result = testDao.testInsert(pmap);
    return result;
  }

  public int testUpdate(TestVO tvo) {
    log.info("testUpdate");
    int result = 0;
    result = testDao.testUpdate(tvo);
    return result;
  }

  public int testDelete(int t_no) {
    log.info("testDelete");
    int result = 0;
    result = testDao.testDelete(t_no);
    return result;
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
}
