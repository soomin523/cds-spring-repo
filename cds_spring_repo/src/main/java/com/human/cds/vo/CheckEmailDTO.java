package com.human.cds.vo;

import lombok.Data;

@Data
public class CheckEmailDTO {

    private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
