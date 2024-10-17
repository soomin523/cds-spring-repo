package com.human.cds.vo;

import java.util.List;


public class AccommodationVO {
	private String first_image; // 첫 번째 이미지
    private String first_image2; // 두 번째 이미지
    private String map_x; // 지도 X좌표
    private String map_y; // 지도 Y좌표
    private String m_level; // 지도 레벨
    private String addr2; // 주소2
    private String area_code; // 지역 코드
    private String modified_time; // 수정된 시간
    private String cpyrht_div_cd; // 저작권 코드
    private String book_tour; // 예약 가능 여부
    private String cat1; // 대분류 카테고리
    private String sigungu_code; // 시군구 코드
    private String tel; // 전화번호
    public String getFirst_image() {
		return first_image;
	}
	public void setFirst_image(String first_image) {
		this.first_image = first_image;
	}
	public String getFirst_image2() {
		return first_image2;
	}
	public void setFirst_image2(String first_image2) {
		this.first_image2 = first_image2;
	}
	public String getMap_x() {
		return map_x;
	}
	public void setMap_x(String map_x) {
		this.map_x = map_x;
	}
	public String getMap_y() {
		return map_y;
	}
	public void setMap_y(String map_y) {
		this.map_y = map_y;
	}
	public String getM_level() {
		return m_level;
	}
	public void setM_level(String m_level) {
		this.m_level = m_level;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getArea_code() {
		return area_code;
	}
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
	public String getModified_time() {
		return modified_time;
	}
	public void setModified_time(String modified_time) {
		this.modified_time = modified_time;
	}
	public String getCpyrht_div_cd() {
		return cpyrht_div_cd;
	}
	public void setCpyrht_div_cd(String cpyrht_div_cd) {
		this.cpyrht_div_cd = cpyrht_div_cd;
	}
	public String getBook_tour() {
		return book_tour;
	}
	public void setBook_tour(String book_tour) {
		this.book_tour = book_tour;
	}
	public String getCat1() {
		return cat1;
	}
	public void setCat1(String cat1) {
		this.cat1 = cat1;
	}
	public String getSigungu_code() {
		return sigungu_code;
	}
	public void setSigungu_code(String sigungu_code) {
		this.sigungu_code = sigungu_code;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getCat2() {
		return cat2;
	}
	public void setCat2(String cat2) {
		this.cat2 = cat2;
	}
	public String getCat3() {
		return cat3;
	}
	public void setCat3(String cat3) {
		this.cat3 = cat3;
	}
	public String getContent_id() {
		return content_id;
	}
	public void setContent_id(String content_id) {
		this.content_id = content_id;
	}
	public String getContent_type_id() {
		return content_type_id;
	}
	public void setContent_type_id(String content_type_id) {
		this.content_type_id = content_type_id;
	}
	public String getCreated_time() {
		return created_time;
	}
	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public List<AccommodationRoomVO> getRooms() {
		return rooms;
	}
	public void setRooms(List<AccommodationRoomVO> rooms) {
		this.rooms = rooms;
	}
	private String title; // 숙소 이름
    private String addr1; // 주소1
    private String cat2; // 중분류 카테고리
    private String cat3; // 소분류 카테고리
    private String content_id; // 콘텐츠 ID
    private String content_type_id; // 콘텐츠 유형 ID
    private String created_time; // 생성된 시간
    private String zipcode; // 우편번호
    private String overview; // 개요
    private List<AccommodationRoomVO> rooms; // 숙소의 방 목록
}

