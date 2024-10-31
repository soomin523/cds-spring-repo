package com.human.cds.vo;

import java.util.List;

public class TrainVO {
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
        // getters and setters
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
        // getters and setters
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
        // getters and setters
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
        private List<Train> item;
        // getters and setters

		public List<Train> getItem() {
			return item;
		}

		public void setItem(List<Train> item) {
			this.item = item;
		}
    }

    public static class Train {
        private String traingradename;
        private String depplandtime;
        private String arrplandtime;
        private String depplacename;
        private String arrplacename;
        private String adultcharge;
        private String trainno;
        
        private String citycode;
        private String cityname;
        
        private String nodeid;
        private String nodename;
        
        // getters and setters
		public String getTraingradename() {
			return traingradename;
		}
		public void setTraingradename(String traingradename) {
			this.traingradename = traingradename;
		}
		public String getDepplandtime() {
			return depplandtime;
		}
		public void setDepplandtime(String depplandtime) {
			this.depplandtime = depplandtime;
		}
		public String getArrplandtime() {
			return arrplandtime;
		}
		public void setArrplandtime(String arrplandtime) {
			this.arrplandtime = arrplandtime;
		}
		public String getDepplacename() {
			return depplacename;
		}
		public void setDepplacename(String depplacename) {
			this.depplacename = depplacename;
		}
		public String getArrplacename() {
			return arrplacename;
		}
		public void setArrplacename(String arrplacename) {
			this.arrplacename = arrplacename;
		}
		public String getAdultcharge() {
			return adultcharge;
		}
		public void setAdultcharge(String adultcharge) {
			this.adultcharge = adultcharge;
		}
		public String getTrainno() {
			return trainno;
		}
		public void setTrainno(String trainno) {
			this.trainno = trainno;
		}
		public String getCitycode() {
			return citycode;
		}
		public void setCitycode(String citycode) {
			this.citycode = citycode;
		}
		public String getCityname() {
			return cityname;
		}
		public void setCityname(String cityname) {
			this.cityname = cityname;
		}
		public String getNodeid() {
			return nodeid;
		}
		public void setNodeid(String nodeid) {
			this.nodeid = nodeid;
		}
		public String getNodename() {
			return nodename;
		}
		public void setNodename(String nodename) {
			this.nodename = nodename;
		}
    }
}
