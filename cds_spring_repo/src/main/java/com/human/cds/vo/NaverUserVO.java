package com.human.cds.vo;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class NaverUserVO {
	private String id;
    private String name;
    private String email;
    private String gender;
    private String birthday;
    private String birthyear;
    private String mobile;
    
	public NaverUserVO(String jsonResponseBody){
       JsonParser jsonParser = new JsonParser();
       JsonElement element = jsonParser.parse(jsonResponseBody);
       
       this.id = element.getAsJsonObject().get("response").getAsJsonObject().get("id").getAsString();
       this.name = element.getAsJsonObject().get("response").getAsJsonObject().get("name").getAsString();
       this.email = element.getAsJsonObject().get("response").getAsJsonObject().get("email").getAsString();
       this.gender = element.getAsJsonObject().get("response").getAsJsonObject().get("gender").getAsString();
       this.birthday = element.getAsJsonObject().get("response").getAsJsonObject().get("birthday").getAsString();
       this.birthyear = element.getAsJsonObject().get("response").getAsJsonObject().get("birthyear").getAsString();
       this.mobile = element.getAsJsonObject().get("response").getAsJsonObject().get("mobile").getAsString();
    }
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getBirthyear() {
		return birthyear;
	}
	public void setBirthyear(String birthyear) {
		this.birthyear = birthyear;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
