package com.human.cds.vo;

import lombok.Data;
import java.util.List;

@Data
public class DestinationDBVO {
    private Response response;

    public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}

	@Data
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

    @Data
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

    @Data
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

    @Data
    public static class Items {
        private List<Item> item;
		public List<Item> getItem() {
			return item;
		}
		public void setItem(List<Item> item) {
			this.item = item;
		}
    }

   
    public static class Item {
       private String d_contentid;
       private String d_firstimage;
       private String d_sigungucode;
       private String d_sigunguname;
       private String d_areacode ;
       private String d_areaname;
       private String d_title;
       private String d_addr1;
       private String d_addr2; 
       private String d_mapx;
       private String d_mapy;
	public String getD_contentid() {
		return d_contentid;
	}
	public void setD_contentid(String d_contentid) {
		this.d_contentid = d_contentid;
	}
	public String getD_firstimage() {
		return d_firstimage;
	}
	public void setD_firstimage(String d_firstimage) {
		this.d_firstimage = d_firstimage;
	}
	public String getD_sigungucode() {
		return d_sigungucode;
	}
	public void setD_sigungucode(String d_sigungucode) {
		this.d_sigungucode = d_sigungucode;
	}
	public String getD_sigunguname() {
		return d_sigunguname;
	}
	public void setD_sigunguname(String d_sigunguname) {
		this.d_sigunguname = d_sigunguname;
	}
	public String getD_areacode() {
		return d_areacode;
	}
	public void setD_areacode(String d_areacode) {
		this.d_areacode = d_areacode;
	}
	public String getD_areaname() {
		return d_areaname;
	}
	public void setD_areaname(String d_areaname) {
		this.d_areaname = d_areaname;
	}
	public String getD_title() {
		return d_title;
	}
	public void setD_title(String d_title) {
		this.d_title = d_title;
	}
	public String getD_addr1() {
		return d_addr1;
	}
	public void setD_addr1(String d_addr1) {
		this.d_addr1 = d_addr1;
	}
	public String getD_addr2() {
		return d_addr2;
	}
	public void setD_addr2(String d_addr2) {
		this.d_addr2 = d_addr2;
	}
	public String getD_mapx() {
		return d_mapx;
	}
	public void setD_mapx(String d_mapx) {
		this.d_mapx = d_mapx;
	}
	public String getD_mapy() {
		return d_mapy;
	}
	public void setD_mapy(String d_mapy) {
		this.d_mapy = d_mapy;
	}
       
       
    }
}
