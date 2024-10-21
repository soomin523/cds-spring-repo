package com.human.cds.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.human.cds.service.MemberService;
import com.human.cds.vo.GoogleInfResponseVO;
import com.human.cds.vo.GoogleRequestVO;
import com.human.cds.vo.GoogleResponseVO;
import com.human.cds.vo.KakaoUserVO;
import com.human.cds.vo.MemberVO;
import com.human.cds.vo.NaverUserVO;

@Controller
@CrossOrigin("*")
public class APILoginController {
	
	private MemberService memberServiceImpl;
	
	@Autowired
	public APILoginController(MemberService memberServiceImpl) {
		this.memberServiceImpl = memberServiceImpl;
	}
	
	
//	//네이버 회원가입/로그인을 위함
//	private String naverClientId = "4IZekkKFksLclCpboj2G";
//	private String naverClientPw = "y0PcRKsVJv";
//    private String naverUri = "http://localhost:9090/cds/member/naverLogin.do";
//    private String state = "cds_prtj";
//	
//    @RequestMapping(value="/member/naverLogin.do", method = RequestMethod.GET)
//    public String loginNaver(@RequestParam("code") String code, HttpServletRequest request){
//    	// 1. 인가 코드 받기 -> @RequestParam String code
//        
//        // 2. 접근 토큰 발급 요청
//        String accessToken = getAccessToken(code, state);
//        System.out.println("accessToken = " + accessToken);
//        
//        // 3. 사용자 정보 받기
//        NaverUserVO userInfo = getUserInfo2(accessToken);
//        
//        MemberVO member = memberServiceImpl.findMemberId(vo.getName(), vo.getEmail());
//    	
//    	
//    	return "redirect:/index.do";
//    }
//	
//	
//    public String getAccessToken(String code, String state)
//    {
//        String reqUrl = "https://nid.naver.com/oauth2.0/token";
//        RestTemplate restTemplate = new RestTemplate();
//        
//        // HttpHeader Object
//        HttpHeaders headers = new HttpHeaders();
//        
//        // HttpBody Object
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("grant_type", "authorization_code");
//        params.add("client_id", naverClientId);
//        params.add("client_secret", naverClientPw);
//        params.add("code", code);
//        params.add("state", state);
//        
//        // http body params 와 http headers 를 가진 엔티티
//        HttpEntity<MultiValueMap<String, String>> naverTokenRequest = new HttpEntity<>(params, headers);
//        
//        // reqUrl로 Http 요청, POST 방식
//        ResponseEntity<String> response = restTemplate.exchange(reqUrl,
//                                                  HttpMethod.POST,
//                                                  naverTokenRequest,
//                                                  String.class);
//        
//        String responseBody = response.getBody();
//        JsonObject asJsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
//        return asJsonObject.get("access_token").getAsString();
//    }
//	
//    public NaverUserVO getUserInfo2(String accessToken){
//        String reqUrl = "https://openapi.naver.com/v1/nid/me";
//        
//        RestTemplate restTemplate = new RestTemplate();
//        
//        // HttpHeader 오브젝트
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "Bearer " + accessToken);
//        
//        HttpEntity<MultiValueMap<String, String>> naverProfileRequest = new HttpEntity<>(headers);
//        
//        ResponseEntity<String> response = restTemplate.exchange(reqUrl,
//                                                  HttpMethod.POST,
//                                                  naverProfileRequest,
//                                                  String.class);
//        
//        System.out.println("response = " + response);
//        NaverUserVO naverProfile = new NaverUserVO(response.getBody());
//        
//        return naverProfile;
//    }

	
	//카카오 회원가입/로그인을 위함
	private String kakaoClientId = "c07530622585d316fcad9f90e1d935b5";
    
    @RequestMapping(value="/member/kakaoLogin.do", method = RequestMethod.GET)
    public String loginKakao(@RequestParam("code") String code, HttpServletRequest request){
    	// 1. 인가 코드 받기 (@RequestParam String code)

        // 2. 토큰 받기
        String accessToken = getAccessToken(code);

        // 3. 사용자 정보 받기
        MemberVO vo = getUserInfo(accessToken);
        
        HttpSession session = request.getSession();
        MemberVO member = memberServiceImpl.findMemberId(vo.getName(), vo.getEmail());
        
        if(member != null) {
        	session.setAttribute("member", member);
        	
        }else {
        	
        	int result = memberServiceImpl.kakaoLogin(vo);
        	if(result == 1) {
        		session.setAttribute("member", vo);
        	}
        }

        return "redirect:/index.do";
    }
    
    public String getAccessToken(String code) {
        String access_Token = "";

        try {
            final String requestUrl = "https://kauth.kakao.com/oauth/token";
            URL url = new URL(requestUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            // 서버로 요청 보내기
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()))) {
                StringBuilder sb = new StringBuilder();
                sb.append("grant_type=authorization_code");
                sb.append("&client_id="+kakaoClientId);
                sb.append("&redirect_uri=http://localhost:9090/cds/member/kakaoLogin.do");
                sb.append("&code=").append(code);
                bw.write(sb.toString());
                bw.flush();
            }

            // 서버의 응답 데이터 가져옴
            StringBuilder result = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    result.append(line);
                }
            }

            // JSON 파싱
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result.toString());
            access_Token = element.getAsJsonObject().get("access_token").getAsString();

        } catch (IOException e) {
            e.printStackTrace(); // 에러 메시지 출력
        }

        return access_Token;
    }
    
    public MemberVO getUserInfo(String accessToken) {
    	RestTemplate restTemplate = new RestTemplate();
    	
    	 // 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        // 사용자 정보 요청
        ResponseEntity<KakaoUserVO> response = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.GET,
                entity,
                KakaoUserVO.class
        );

        // 사용자 정보 처리
        if (response.getStatusCode() == HttpStatus.OK) {
        	KakaoUserVO userInfo = response.getBody();

            // 필요한 정보를 MemberVO에 담아서 반환
            MemberVO userVO = new MemberVO();
            
            userVO.setName(userInfo.getKakao_account().getProfile().getNickname());
            userVO.setEmail(userInfo.getKakao_account().getEmail());

            return userVO;
        }

        return null; // 실패 시 null 반환
    }
	
	
	//구글 회원가입/로그인을 위함
    private String googleClientId = "172267091290-704rp9g9evbu8na2co56nmop1i13d1ul.apps.googleusercontent.com";
    private String googleClientPw = "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";
	
    @RequestMapping(value="/member/googleLogin.do", method = RequestMethod.GET)
    public String loginUrlGoogle(){
        String reqUrl = "https://accounts.google.com/o/oauth2/v2/auth?client_id=" + googleClientId
                + "&redirect_uri=http://localhost:9090/cds/login/googleCallback.do"
                + "&response_type=code"
                + "&scope=email%20profile%20openid&access_type=offline";
        return "redirect:" + reqUrl;
    }
    
    @RequestMapping(value="/login/googleCallback.do", method = RequestMethod.GET)
    public String loginGoogle(@RequestParam(value = "code") String authCode, HttpServletRequest request) throws JsonProcessingException{
        
        //Google OAuth Token 요청
        GoogleRequestVO googleOAuthRequestParam = new GoogleRequestVO.Builder()
                .clientId(googleClientId)
                .clientSecret(googleClientPw)
                .code(authCode)
                .redirectUri("http://localhost:9090/cds/login/googleCallback.do")
                .grantType("authorization_code")
                .scope("email profile openid")
                .build();
        
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(googleOAuthRequestParam);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 요청 본문과 헤더를 함께 전달
        HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);
        
        RestTemplate restTemplate = new RestTemplate();
        
        try {
        	//액세스 토큰 요청
            ResponseEntity<GoogleResponseVO> resultEntity = restTemplate.postForEntity("https://oauth2.googleapis.com/token", 
            		requestEntity, GoogleResponseVO.class);
            
            
            // 사용자 정보 요청
            String jwtToken = resultEntity.getBody().getId_token();
            String tokenInfoUrl = "https://oauth2.googleapis.com/tokeninfo?id_token=" + jwtToken;

            ResponseEntity<GoogleInfResponseVO> resultEntity2 = restTemplate.getForEntity(
                    tokenInfoUrl, GoogleInfResponseVO.class
            );
            
            String email = resultEntity2.getBody().getEmail();     
            String name = (String) resultEntity2.getBody().getName();
            
            HttpSession session = request.getSession();
            MemberVO member = memberServiceImpl.findMemberId(name, email);
            
            if(member != null) {
            	session.setAttribute("member", member);
            	
            }else {
            	member = new MemberVO();
            	member.setName(name);
            	member.setEmail(email);
            	int result = memberServiceImpl.googleLogin(member);
            	if(result == 1) {
            		session.setAttribute("member", member);
            	}
            }
        } catch (HttpClientErrorException e) {
            System.err.println("Error response: " + e.getResponseBodyAsString());
        }
        
        return "redirect:/index.do";
    }
    
}