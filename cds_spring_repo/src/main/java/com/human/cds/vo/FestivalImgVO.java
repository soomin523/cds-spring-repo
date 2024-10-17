package com.human.cds.vo;

import java.util.List;

public class FestivalImgVO {
	
	private Response response;
	
	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}

	public static class Response{
		private Header header;
		private Body body;
		
		public Header getHeader() {
			return header;
		}
		public void setHeader(Header header) {
			this.header = header;
		}
		public Body getBody() {
			return body;
		}
		public void setBody(Body body) {
			this.body = body;
		}
	}
	
	public static class Header{
		private String  resultCode;
		private String  resultMsg;
		
		public String getResultCode() {
			return resultCode;
		}
		public void setResultCode(String resultCode) {
			this.resultCode = resultCode;
		}
		public String getResultMsg() {
			return resultMsg;
		}
		public void setResultMsg(String resultMsg) {
			this.resultMsg = resultMsg;
		}
	}

	public static class Body{
		private int numOfRows;
		private int pageNo;
		private int totalCount;
		private Items items;
		
		public int getNumOfRows() {
			return numOfRows;
		}
		public void setNumOfRows(int numOfRows) {
			this.numOfRows = numOfRows;
		}
		public int getPageNo() {
			return pageNo;
		}
		public void setPageNo(int pageNo) {
			this.pageNo = pageNo;
		}
		public int getTotalCount() {
			return totalCount;
		}
		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
		}
		public Items getItems() {
			return items;
		}
		public void setItems(Items items) {
			this.items = items;
		}
	}
	
	public static class Items{
		private List<FestivalImg> item;
		public List<FestivalImg> getItem() {
			return item;
		}
		public void setItem(List<FestivalImg> item) {
			this.item = item;
		}
	}
	
	public static class FestivalImg{
		private String contentid; 		//콘텐츠ID
		private String imgname;			//이미지명
		private String originimgurl;	//원본이미지
		private String serialnum;		//이미지일련번호
		private String smallimageurl;	//썸네일이미지
		private String cpyrhtDivCd;		//저작권 유형 (Type1:제1유형(출처표시-권장), Type3:제3유형(제1유형+변경금지)
		
		public String getContentid() {
			return contentid;
		}
		public void setContentid(String contentid) {
			this.contentid = contentid;
		}
		public String getImgname() {
			return imgname;
		}
		public void setImgname(String imgname) {
			this.imgname = imgname;
		}
		public String getOriginimgurl() {
			return originimgurl;
		}
		public void setOriginimgurl(String originimgurl) {
			this.originimgurl = originimgurl;
		}
		public String getSerialnum() {
			return serialnum;
		}
		public void setSerialnum(String serialnum) {
			this.serialnum = serialnum;
		}
		public String getSmallimageurl() {
			return smallimageurl;
		}
		public void setSmallimageurl(String smallimageurl) {
			this.smallimageurl = smallimageurl;
		}
		public String getCpyrhtDivCd() {
			return cpyrhtDivCd;
		}
		public void setCpyrhtDivCd(String cpyrhtDivCd) {
			this.cpyrhtDivCd = cpyrhtDivCd;
		}
	}

}
