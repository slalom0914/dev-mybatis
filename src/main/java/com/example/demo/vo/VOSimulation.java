package com.example.demo.vo;
//디폴트 생성자는 파라미터가 없는 생성자
//디폴트 생성자는 생략이 가능함
//파라미터를 세 개나 가진 생성자
public class VOSimulation {
    public static void main(String[] args) {
        NoticeVO vo = new NoticeVO();
        //System.out.println(vo.getN_no()+vo.getN_title()+vo.getN_content());
        //System.out.println("hello".length());//5
        vo = new NoticeVO(1,"제목","내용");
        NoticeVO noticeVO = NoticeVO.builder()
                .n_no(1)
                .build();
        NoticeVO noticeVO2 = NoticeVO.builder()
                .n_no(2)
                .n_content("content2")
                .build();
        NoticeVO noticeVO3 = NoticeVO.builder()
                .n_no(10)
                .n_content("content3")
                .n_title("title3")
                .build();
        System.out.println(noticeVO.getN_no()+noticeVO.getN_title()+noticeVO.getN_content());
        System.out.println(noticeVO2.getN_no()+noticeVO2.getN_title()+noticeVO2.getN_content());
        System.out.println(noticeVO3.getN_no()+noticeVO3.getN_title()+noticeVO3.getN_content());
    }
}
