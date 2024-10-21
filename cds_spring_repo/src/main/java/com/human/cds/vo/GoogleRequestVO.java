package com.human.cds.vo;

public class GoogleRequestVO {

	private String clientId;    // 애플리케이션의 클라이언트 ID
    private String redirectUri; // Google 로그인 후 redirect 위치
    private String clientSecret; // 클라이언트 보안 비밀
    private String responseType; // Google OAuth 2.0 엔드포인트가 인증 코드를 반환하는지 여부
    private String scope;        // OAuth 동의 범위
    private String code;
    private String accessType;   // 사용자가 브라우저에 없을 때 애플리케이션이 액세스 토큰을 새로 고칠 수 있는지 여부
    private String grantType;
    private String state;
    private String includeGrantedScopes; // 추가 권한 부여를 사용
    private String loginHint;   // 인증할 사용자의 힌트
    private String prompt;      // 사용자에게 메시지가 표시될지 여부
    
    // private 생성자
    private GoogleRequestVO(Builder builder) {
        this.clientId = builder.clientId;
        this.redirectUri = builder.redirectUri;
        this.clientSecret = builder.clientSecret;
        this.responseType = builder.responseType;
        this.scope = builder.scope;
        this.code = builder.code;
        this.accessType = builder.accessType;
        this.grantType = builder.grantType;
        this.state = builder.state;
        this.includeGrantedScopes = builder.includeGrantedScopes;
        this.loginHint = builder.loginHint;
        this.prompt = builder.prompt;
    }
    
    // 정적 빌더 클래스
    public static class Builder {
        private String clientId;    
        private String redirectUri; 
        private String clientSecret; 
        private String responseType; 
        private String scope;        
        private String code;
        private String accessType;   
        private String grantType;
        private String state;
        private String includeGrantedScopes; 
        private String loginHint;   
        private String prompt;
        
        public Builder clientId(String clientId) {
            this.clientId = clientId;
            return this;
        }
        public Builder redirectUri(String redirectUri) {
            this.redirectUri = redirectUri;
            return this;
        }
        public Builder clientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
            return this;
        }
        public Builder responseType(String responseType) {
            this.responseType = responseType;
            return this;
        }
        public Builder scope(String scope) {
            this.scope = scope;
            return this;
        }
        public Builder code(String code) {
            this.code = code;
            return this;
        }
        public Builder accessType(String accessType) {
            this.accessType = accessType;
            return this;
        }
        public Builder grantType(String grantType) {
            this.grantType = grantType;
            return this;
        }
        public Builder state(String state) {
            this.state = state;
            return this;
        }
        public Builder includeGrantedScopes(String includeGrantedScopes) {
            this.includeGrantedScopes = includeGrantedScopes;
            return this;
        }
        public Builder loginHint(String loginHint) {
            this.loginHint = loginHint;
            return this;
        }
        public Builder prompt(String prompt) {
            this.prompt = prompt;
            return this;
        }
        public GoogleRequestVO build() {
            return new GoogleRequestVO(this);
        }
    }

	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getRedirectUri() {
		return redirectUri;
	}
	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public String getResponseType() {
		return responseType;
	}
	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAccessType() {
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	public String getGrantType() {
		return grantType;
	}
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIncludeGrantedScopes() {
		return includeGrantedScopes;
	}
	public void setIncludeGrantedScopes(String includeGrantedScopes) {
		this.includeGrantedScopes = includeGrantedScopes;
	}
	public String getLoginHint() {
		return loginHint;
	}
	public void setLoginHint(String loginHint) {
		this.loginHint = loginHint;
	}
	public String getPrompt() {
		return prompt;
	}
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
	
	@Override
	public String toString() {
		return "GoogleRequest{" +
	            "clientId='" + clientId + '\'' +
	            ", redirectUri='" + redirectUri + '\'' +
	            ", clientSecret='" + clientSecret + '\'' +
	            ", responseType='" + responseType + '\'' +
	            ", scope='" + scope + '\'' +
	            ", code='" + code + '\'' +
	            ", accessType='" + accessType + '\'' +
	            ", grantType='" + grantType + '\'' +
	            ", state='" + state + '\'' +
	            ", includeGrantedScopes='" + includeGrantedScopes + '\'' +
	            ", loginHint='" + loginHint + '\'' +
	            ", prompt='" + prompt + '\'' +
	            '}';
	}
	
}
