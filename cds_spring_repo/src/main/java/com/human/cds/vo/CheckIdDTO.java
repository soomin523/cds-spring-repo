package com.human.cds.vo;

import lombok.Data;

@Data
public class CheckIdDTO {
    private String member_id;

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
}
