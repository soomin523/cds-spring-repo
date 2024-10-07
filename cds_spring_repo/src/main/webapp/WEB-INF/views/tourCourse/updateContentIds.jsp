<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="DAO.*" %>

<%
    String[] selectedContentIds = request.getParameterValues("selectedContentIds");
    if (selectedContentIds != null && selectedContentIds.length > 0) {
        DetailDAO dao = new DetailDAO();
        
        for (String contentId : selectedContentIds) {
            // API에서 overview 가져오기
            String overview = dao.getOverviewFromApi(contentId);
            
            if (overview != null) {
                // DB에 overview 업데이트
                dao.updateOverview(contentId, overview);
            }
        }
        
        out.println("업데이트가 완료되었습니다.");
    } else {
        out.println("선택된 항목이 없습니다.");
    }
%>
