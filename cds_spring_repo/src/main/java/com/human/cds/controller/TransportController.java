package com.human.cds.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.human.cds.api.BusApiExplorer;
import com.human.cds.api.FlightInfoApiExplorer;
import com.human.cds.api.TrainInfoApiExplorer;
import com.human.cds.api.TrainNodeApiExplorer;
import com.human.cds.api.TransportCodeApiExplorer;
import com.human.cds.vo.BusVO;
import com.human.cds.vo.FlightVO;
import com.human.cds.vo.TrainVO;


@Controller
@RequestMapping("/transportation")
public class TransportController {
	
	private final String serviceKey = "FuwMUFZUntQUUD1GcyAcTAoz7EihmI5dxJj5Bd8FgRyKWqVr5ESL/j/cwogqgbuliXCqklKwumwuM60AZ5MHeQ==";
	private final String expBusUrl = "http://apis.data.go.kr/1613000/ExpBusInfoService/getStrtpntAlocFndExpbusInfo";
	private final String flightUrl = "http://apis.data.go.kr/1613000/DmstcFlightNvgInfoService/getFlightOpratInfoList";
	private final String trainUrl = "http://apis.data.go.kr/1613000/TrainInfoService/getStrtpntAlocFndTrainInfo";
	private final String terminalListUrl = "http://apis.data.go.kr/1613000/ExpBusInfoService/getExpBusTrminlList";
	private final String airportListUrl = "http://apis.data.go.kr/1613000/DmstcFlightNvgInfoService/getArprtList";
	private final String stationListUrl = "http://apis.data.go.kr/1613000/TrainInfoService/getStationList";
	private final String trainNodeUrl = "http://apis.data.go.kr/1613000/TrainInfoService/getCtyCodeList";
	
	private BusVO busHubVO;
	private FlightVO flightHubVO;
	private TrainVO trainHubVO;
	
	@GetMapping("/bus.do")
	public String bus() {
		return "transportation/bus";
	}
	
	@GetMapping("/flight.do")
	public String flight() {
		return "transportation/flight";
	}
	
	@GetMapping("/train.do")
	public String train() {
		return "transportation/train";
	}
	

	 @GetMapping("/searchBus.do")
	 @ResponseBody
	 public BusVO searchBus(String depTerminalNm, String arrTerminalNm, String depPlandTime) {
	     try {
	    	 
	         System.out.println("Bus Search Input Parameters:");
	         System.out.println("Departure Terminal: " + depTerminalNm);
	         System.out.println("Arrival Terminal: " + arrTerminalNm);
	         System.out.println("Date: " + depPlandTime);

	         // 버스 터미널 데이터 초기화
	         busHubVO = TransportCodeApiExplorer.getApiJsonData(serviceKey, terminalListUrl, BusVO.class);
	         System.out.println("Terminal List Response: " + busHubVO);

	         
	         String depTerminalId = busHubVO.getResponse().getBody().getItems().getItem().stream()
	                 .filter(terminal -> terminal.getTerminalNm().equals(depTerminalNm))
	                 .findFirst()
	                 .map(terminal -> terminal.getTerminalId())
	                 .orElse(null);
	         
	         String arrTerminalId = busHubVO.getResponse().getBody().getItems().getItem().stream()
	                 .filter(terminal -> terminal.getTerminalNm().equals(arrTerminalNm))
	                 .findFirst()
	                 .map(terminal -> terminal.getTerminalId())
	                 .orElse(null);

	         System.out.println("Found Terminal IDs:");
	         System.out.println("Departure ID: " + depTerminalId);
	         System.out.println("Arrival ID: " + arrTerminalId);

	         return BusApiExplorer.getApiJsonData(serviceKey, expBusUrl, depTerminalId, arrTerminalId, depPlandTime, BusVO.class);
	     } catch(Exception e) {
	         e.printStackTrace();
	         return null;
	     }
	 }

	 @GetMapping("/getFlightOperationInfo")
	 @ResponseBody
	 public List<FlightVO.Flight> getFlightOperationInfo(
	         @RequestParam String depAirportNm,
	         @RequestParam String arrAirportNm, 
	         @RequestParam String depPlandTime) {
	     try {
	         // 항공 데이터 초기화
	         flightHubVO = TransportCodeApiExplorer.getApiJsonData(serviceKey, airportListUrl, FlightVO.class);
	         
	         String depAirportId = flightHubVO.getResponse().getBody().getItems().getItem().stream()
	                 .filter(airport -> airport.getAirportNm().equals(depAirportNm))
	                 .findFirst()
	                 .map(airport -> airport.getAirportId())
	                 .orElse(null);
	         
	         String arrAirportId = flightHubVO.getResponse().getBody().getItems().getItem().stream()
	                 .filter(airport -> airport.getAirportNm().equals(arrAirportNm))
	                 .findFirst()
	                 .map(airport -> airport.getAirportId())
	                 .orElse(null);

	         FlightVO flightData = FlightInfoApiExplorer.getApiJsonData(serviceKey, depAirportId, arrAirportId, depPlandTime, FlightVO.class);
	         return flightData.getResponse().getBody().getItems().getItem();
	     } catch (Exception e) {
	         e.printStackTrace();
	         return Collections.emptyList();
	     }
	 }

	 @GetMapping("/getTrainOperationInfo")
	 @ResponseBody
	 public List<TrainVO.Train> getTrainOperationInfo(
	         @RequestParam String depPlaceName,
	         @RequestParam String arrPlaceName,
	         @RequestParam String depPlandTime) {
	     try {
	         // 1. First get all city codes
	    	 
	         System.out.println("Train Search Input Parameters:");
	         System.out.println("Departure: " + depPlaceName);
	         System.out.println("Arrival: " + arrPlaceName);
	         System.out.println("Date: " + depPlandTime);
	         
	         TrainVO cityCodeData = TransportCodeApiExplorer.getApiJsonData(serviceKey, 
	                 trainNodeUrl, 
	                 TrainVO.class);
	         
	         System.out.println("City Code Data Response: " + cityCodeData);

	         // 2. Get station lists for all cities
	         List<TrainVO.Train> allStations = new ArrayList<>();
	         for (TrainVO.Train city : cityCodeData.getResponse().getBody().getItems().getItem()) {
	             TrainVO stationData = TrainNodeApiExplorer.getApiJsonData(
	                     serviceKey, 
	                     city.getCitycode(), 
	                     TrainVO.class);
	             if (stationData.getResponse().getBody().getItems() != null && 
	                 stationData.getResponse().getBody().getItems().getItem() != null) {
	                 allStations.addAll(stationData.getResponse().getBody().getItems().getItem());
	             }
	         }

	         // 3. Find departure and arrival station IDs
	         String depPlaceId = allStations.stream()
	                 .filter(station -> station.getNodename().equals(depPlaceName))
	                 .map(station -> station.getNodeid())
	                 .findFirst()
	                 .orElse(null);

	         String arrPlaceId = allStations.stream()
	                 .filter(station -> station.getNodename().equals(arrPlaceName))
	                 .map(station -> station.getNodeid())
	                 .findFirst()
	                 .orElse(null);
	         
	         System.out.println("Found Station IDs:");
	         System.out.println("Departure ID: " + depPlaceId);
	         System.out.println("Arrival ID: " + arrPlaceId);

	         if (depPlaceId == null || arrPlaceId == null) {
	        	  System.out.println("Station IDs not found - returning empty list");
	             return Collections.emptyList();
	         }

	         // 4. Get train schedule data
	         TrainVO trainData = TrainInfoApiExplorer.getApiJsonData(
	                 serviceKey, 
	                 depPlaceId, 
	                 arrPlaceId, 
	                 depPlandTime, 
	                 TrainVO.class);
	         
	         System.out.println("Final Train Data Response: " + trainData);


	         if (trainData.getResponse().getBody().getItems() != null && 
	             trainData.getResponse().getBody().getItems().getItem() != null) {
	             return trainData.getResponse().getBody().getItems().getItem();
	         }

	         return Collections.emptyList();
	     } catch (Exception e) {
	         e.printStackTrace();
	         return Collections.emptyList();
	     }
	 }


	 
	 
}