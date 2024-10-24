package com.human.cds.vo;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

				//반복정보조회
 
 
public class DetailInfoVO {
    private Response response;

	 
	 
    public Response getResponse() {
		return response;
	}



	public void setResponse(Response response) {
		this.response = response;
	}



	public static class Response {
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

     
	 
    public static class Header {
        private String resultCode;
        private String resultMsg;
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

     
	 
    public static class Body {
        private Items items;
        private int numOfRows;
        private int pageNo;
        private int totalCount;
		public Items getItems() {
			return items;
		}
		public void setItems(Items items) {
			this.items = items;
		}
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
    }

     
	 
    public static class Items {
        private List<DetailInfo> item;

		public List<DetailInfo> getItem() {
			return item;
		}

		public void setItem(List<DetailInfo> item) {
			this.item = item;
		}
    }

	 
     
    public static class DetailInfo {
    	private String contentid; //	콘텐츠ID
    	private String contenttypeid; //	콘텐츠타입ID
    	private String fldgubun; // 일련번호 
    	private String infoname; // 제목 
    	private String infotext; // 내용 
    	private String serialnum; // 반복일련번호 
		public String getContentid() {
			return contentid;
		}
		public void setContentid(String contentid) {
			this.contentid = contentid;
		}
		public String getContenttypeid() {
			return contenttypeid;
		}
		public void setContenttypeid(String contenttypeid) {
			this.contenttypeid = contenttypeid;
		}
		public String getFldgubun() {
			return fldgubun;
		}
		public void setFldgubun(String fldgubun) {
			this.fldgubun = fldgubun;
		}
		public String getInfoname() {
			return infoname;
		}
		public void setInfoname(String infoname) {
			this.infoname = infoname;
		}
		public String getInfotext() {
			return infotext;
		}
		public void setInfotext(String infotext) {
			this.infotext = infotext;
		}
		public String getSerialnum() {
			return serialnum;
		}
		public void setSerialnum(String serialnum) {
			this.serialnum = serialnum;
		}

    
    	
    }
}
