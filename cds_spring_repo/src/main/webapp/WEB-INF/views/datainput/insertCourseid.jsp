<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="api.*, DTO.*, DAO.*, java.util.List" %>
<%
    try {
        // CourseInfoDAO를 통해 데이터를 데이터베이스에 저장하는 코드
        CourseInfoDAO courseDAO = new CourseInfoDAO();  // 대소문자 확인

        // ApiExplorerCourseinfo를 이용하여 API 데이터 가져오기
        String serviceKey = "y%2BM4KcA3dU54OMX03WyfG7Vgskk1N4ti1JPnqNLJgfSxfGZDGpJzCXttag92jy9eIo3XD6a89LQXwVwD%2BM9RyQ%3D%3D";
        String srcUrl = "http://apis.data.go.kr/B551011/KorService1/areaBasedList1";
        String numOfRows = "100";
        String contenttypeid ="25";
        int pageNo = 1;
        boolean hasMorePages = true;
        
        int result = 0; // 입력 실패 시 결과값

        // 페이지별로 데이터를 가져와서 DB에 저장하는 작업 반복
        while (hasMorePages) {
            CourseInfoDTO data = ApiExplorerCourseinfo.getApiJsonData(serviceKey, srcUrl, String.valueOf(pageNo), numOfRows, contenttypeid);

            
            
            if (data == null) {
                throw new NullPointerException("API 응답이 null입니다.");
            }

            if (data.getResponse().getBody() == null) {
                throw new NullPointerException("API 응답의 body가 null입니다.");
            }

            if (data.getResponse().getBody().getItems() == null) {
                throw new NullPointerException("API 응답의 items가 null입니다.");
            }
            
            // 데이터가 있으면 계속해서 처리, 없으면 루프 종료
            List<CourseInfoDTO.Item> items = data.getResponse().getBody().getItems().getItem();
            hasMorePages = items.size() > 0;

            if (!hasMorePages) {
                break;
            }

            // DAO를 통해 데이터를 DB에 삽입
            result = courseDAO.insertData(data);

            if (result == 1) {
                out.println(pageNo + " 페이지 관광 데이터 DB 입력 성공<br>");
            } else {
                out.println(pageNo + " 페이지 관광 데이터 DB 입력 실패<br>");
            }

            // 페이지 번호 증가
            pageNo++;
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
%>
