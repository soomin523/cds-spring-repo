package com.human.cds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.human.cds.service.ManagerService;
import com.human.cds.vo.MemberVO;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	
	private ManagerService managerServiceImpl;
    
    @Autowired//생성자를 이용해서 필드에 의존 자동 주입함
    public ManagerController(ManagerService managerServiceImpl) {
        this.managerServiceImpl = managerServiceImpl;
    }
	
	@GetMapping("/manager.do")
	public String manager(String select, Model model) {
		model.addAttribute("select", select);
		
		List<MemberVO> memberList = managerServiceImpl.getMemberList();
		model.addAttribute("memberList", memberList);
		
		return "/manager/manager";
	}
	
	@GetMapping("deleteMember.do")
	@ResponseBody
	public String deleteMember(@RequestParam String m_id) {
		String result = "f";
		
		if(managerServiceImpl.deleteMember(m_id) == 1) {
			result = "ok";
		}
		
		return result;
	}

}
