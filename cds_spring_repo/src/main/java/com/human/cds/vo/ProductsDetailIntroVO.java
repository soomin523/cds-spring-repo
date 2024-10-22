package com.human.cds.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductsDetailIntroVO {
    private Response response;

	@Data
	@NoArgsConstructor
    public static class Response {
        private Header header;
        private Body body;
    }

    @Data
	@NoArgsConstructor
    public static class Header {
        private String resultCode;
        private String resultMsg;
    }

    @Data
	@NoArgsConstructor
    public static class Body {
        private Items items;
        private int numOfRows;
        private int pageNo;
        private int totalCount;
    }

    @Data
	@NoArgsConstructor
    public static class Items {
        private List<ProductsDetailIntro> item;
    }

	@Data
    @NoArgsConstructor
    public static class ProductsDetailIntro {
    	private String contentid;	//	콘텐츠ID
    	private String contenttypeid;	//	콘텐츠타입ID
    	
    	//관광지
    	private String accomcount;        		// 수용인원
    	private String chkbabycarriage;        // 유모차대여정보
    	private String chkcreditcard;          // 신용카드가능정보
    	private String chkpet;                 // 애완동물동반가능정보
    	private String expagerange;            // 체험가능연령
    	private String expguide;               // 체험안내
    	private String heritage1;              // 세계문화유산유무
    	private String heritage2;              // 세계자연유산유무
    	private String heritage3;              // 세계기록유산유무
    	private String infocenter;             // 문의및안내
    	private String opendate;               // 개장일
    	private String parking;                // 주차시설
    	private String restdate;               // 쉬는날
    	private String useseason;              // 이용시기
    	private String usetime;                // 이용시간

    	
    	//축제
    	private String agelimit;          	   // 관람가능연령
    	private String bookingplace;           // 예매처
    	private String discountinfofestival;   // 할인정보
    	private String eventenddate;           // 행사종료일
    	private String eventhomepage;          // 행사홈페이지
    	private String eventplace;             // 행사장소
    	private String eventstartdate;         // 행사시작일
    	private String festivalgrade;          // 축제등급
    	private String placeinfo;              // 행사장위치안내
    	private String playtime;               // 공연시간
    	private String program;                // 행사프로그램
    	private String spendtimefestival;      // 관람소요시간
    	private String sponsor1;               // 주최자정보
    	private String sponsor1tel;            // 주최자연락처
    	private String sponsor2;               // 주관사정보
    	private String sponsor2tel;            // 주관사연락처
    	private String subevent;               // 부대행사
    	private String usetimefestival;        // 이용요금

    	
    	//레포츠
		private String accomcountleports; // 수용인원
		private String chkbabycarriageleports; // 유모차대여정보
		private String chkcreditcardleports; // 신용카드가능정보
		private String chkpetleports; // 애완동물동반가능정보
		private String expagerangeleports; // 체험가능연령
		private String infocenterleports; // 문의및안내
		private String openperiod; // 개장기간
		private String parkingfeeleports; // 주차요금
		private String parkingleports; // 주차시설
		private String reservation; // 예약안내 (변경 없음)
		private String restdateleports; // 쉬는날
		private String scaleleports; // 규모
		private String usefeeleports; // 입장료
		private String usetimeleports; // 이용시간
    
    	
    }
}
