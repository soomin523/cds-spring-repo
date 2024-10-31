package com.human.cds.vo;

import java.util.List;
 
 
public class FlightVO {
    private Response response;

	 
	 
    public Response getResponse() {
		return response;
	}



	public void setResponse(Response response) {
		this.response = response;
	}



	public static class Response {
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
		private Header header;
        private Body body;
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
        private List<Flight> item;

		public List<Flight> getItem() {
			return item;
		}

		public void setItem(List<Flight> item) {
			this.item = item;
		}
    }

	 
     
    public static class Flight {
    	private String airlineId;
    	private String airlineNm;       
    	private String airportId;         
        private String airportNm;          
       private String vihicleId;
      // private String airlineNm;
       private String depPlandTime;
       private String arrPlandTime;
       private String economyCharge;
       private String prestigeCharge;
       private String depAirportNm;
       private String arrAirportNm;
	public String getAirlineId() {
		return airlineId;
	}
	public void setAirlineId(String airlineId) {
		this.airlineId = airlineId;
	}
	public String getAirlineNm() {
		return airlineNm;
	}
	public void setAirlineNm(String airlineNm) {
		this.airlineNm = airlineNm;
	}
	public String getAirportId() {
		return airportId;
	}
	public void setAirportId(String airportId) {
		this.airportId = airportId;
	}
	public String getAirportNm() {
		return airportNm;
	}
	public void setAirportNm(String airportNm) {
		this.airportNm = airportNm;
	}
	public String getVihicleId() {
		return vihicleId;
	}
	public void setVihicleId(String vihicleId) {
		this.vihicleId = vihicleId;
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
	public String getEconomyCharge() {
		return economyCharge;
	}
	public void setEconomyCharge(String economyCharge) {
		this.economyCharge = economyCharge;
	}
	public String getPrestigeCharge() {
		return prestigeCharge;
	}
	public void setPrestigeCharge(String prestigeCharge) {
		this.prestigeCharge = prestigeCharge;
	}
	public String getDepAirportNm() {
		return depAirportNm;
	}
	public void setDepAirportNm(String depAirportNm) {
		this.depAirportNm = depAirportNm;
	}
	public String getArrAirportNm() {
		return arrAirportNm;
	}
	public void setArrAirportNm(String arrAirportNm) {
		this.arrAirportNm = arrAirportNm;
	}


        

    	
    }

	
}
