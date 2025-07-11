package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//아래 클래스는 오라클 서버에 notice테이블을 대신할 클래스이다
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeVO {
    //원시형 타입은 호출하면 값이 출력됨 - NullPointerException대상이 아님
    private int n_no;//0
    private String n_title;//null
    private String n_content;//null
}
/*
1. 롬복은 디폴트 생성자도 제공안됨  - @NoArgsConstructor
2. 파라미터가 있는 생성자 경우 모든 전역변수에 대한 초기화는 @AllArgsConstructor
3. 생성자의 파라미터의 위치나 갯수를 제한 받지 않고 자유롭게 하려면 @Builder사용함

스프링 시큐리티 - 시큐리티 설정에서 사용함

@Data => @getter + @setter

 */