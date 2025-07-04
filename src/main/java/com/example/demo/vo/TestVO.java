package com.example.demo.vo;
//컬럼의 수만큼 선언됨 - List<TestVO>, List<Map<>>
import lombok.Data;
//롬복 라이브러리를 추가하면 getter/setter역할을 하는 클래스에
//getter/setter 메서드를 추가하지 않아도 됩니다.
@Data
public class TestVO {
  private int t_no = 0;
  private String t_title = null;
  private String t_content = null;
}
