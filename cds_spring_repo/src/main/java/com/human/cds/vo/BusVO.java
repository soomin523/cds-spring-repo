package com.human.cds.vo;

import java.util.List;

public class BusVO {
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
        private List<Bus> item;
        // getters and setters

		public List<Bus> getItem() {
			return item;
		}

		public void setItem(List<Bus> item) {
			this.item = item;
		}
    }

    public static class Bus {
        private String routeId;        // 노선ID
        private String gradeId;        // 버스등급
        private String gradeNm;        // 버스등급
        private String cityCode;
        private String cityName;       
        private String terminalId;
        private String terminalNm;       
        private String depPlandTime;   // 출발시간
        private String arrPlandTime;   // 도착시간
        private String depPlaceNm;     // 출발지
        private String arrPlaceNm;     // 도착지
        private String charge;         // 운임
        private String busType;        // 버스유형(exp: 고속버스, sub: 시외버스)
		public String getRouteId() {
			return routeId;
		}
		public void setRouteId(String routeId) {
			this.routeId = routeId;
		}
		public String getGradeNm() {
			return gradeNm;
		}
		public void setGradeNm(String gradeNm) {
			this.gradeNm = gradeNm;
		}
		public String getDepPlandTime() {
			return depPlandTime;
		}
		public void setDepPlandTime(String depPlandTime) {
			this.depPlandTime = depPlandTime;
		}
		public String getArrPlandTime() {
			return arrPlandTime;
		}
		public void setArrPlandTime(String arrPlandTime) {
			this.arrPlandTime = arrPlandTime;
		}
		public String getDepPlaceNm() {
			return depPlaceNm;
		}
		public void setDepPlaceNm(String depPlaceNm) {
			this.depPlaceNm = depPlaceNm;
		}
		public String getArrPlaceNm() {
			return arrPlaceNm;
		}
		public void setArrPlaceNm(String arrPlaceNm) {
			this.arrPlaceNm = arrPlaceNm;
		}
		public String getCharge() {
			return charge;
		}
		public void setCharge(String charge) {
			this.charge = charge;
		}
		public String getBusType() {
			return busType;
		}
		public void setBusType(String busType) {
			this.busType = busType;
		}
		public String getGradeId() {
			return gradeId;
		}
		public void setGradeId(String gradeId) {
			this.gradeId = gradeId;
		}
		public String getCityCode() {
			return cityCode;
		}
		public void setCityCode(String cityCode) {
			this.cityCode = cityCode;
		}
		public String getCityName() {
			return cityName;
		}
		public void setCityName(String cityName) {
			this.cityName = cityName;
		}
		public String getTerminalId() {
			return terminalId;
		}
		public void setTerminalId(String terminalId) {
			this.terminalId = terminalId;
		}
		public String getTerminalNm() {
			return terminalNm;
		}
		public void setTerminalNm(String terminalNm) {
			this.terminalNm = terminalNm;
		}
		
        
    }
}
