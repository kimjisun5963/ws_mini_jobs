package com.project.jobs.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.jobs.dao.ICom_community_dao92;
import com.project.jobs.dto.Com_community;
import com.project.jobs.dto.Com_community_category;
import com.project.jobs.dto.Com_reply;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/community")
public class Contoller_community_company92 {

	
	@Autowired
	ICom_community_dao92 dao;
	
	@RequestMapping("/")
	public String root() {
		return "community/ex";
	}
	
	@RequestMapping("/company/list")
	public String com_list(Model model) {
		List<Com_community> list =dao.list92();
		for(Com_community dto : list) {
			dto.setCom_id(dao.com_id(dto.getCom_no()));
		}
		model.addAttribute("list", list);
		return "community/com/list";
	}
	
	@RequestMapping("/company/write_form")
	public String com_write_form(Model model) {
		List<Com_community_category> clist =dao.clist();
		model.addAttribute("clist", clist);
		
		return "community/com/write_form";
	}
	
	@RequestMapping("/company/write")
	public String com_write(Com_community com_community , HttpSession session) {
		session.setAttribute("com_no", 1L);
		com_community.setCom_no((Long)session.getAttribute("com_no"));
		dao.write92(com_community);
		
		return "redirect:list";
	}
	
	@RequestMapping("/company/modify_form")
	public String com_write_form(@RequestParam("com_community_no") String no, Model model) {
		List<Com_community_category> clist =dao.clist();
		model.addAttribute("clist", clist);
		Com_community dto = dao.detail92(no);
		model.addAttribute("dto", dto);
		return "community/com/modify_form";
	}
	
	
	@RequestMapping("/company/detail")
	public String com_detail(@RequestParam("no") String no,Model model) {
		Com_community dto = dao.detail92(no);
		 // 포맷 지정
	     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	     // 포맷을 적용하여 날짜 문자열로 변환
	     dto.setS_reg_date(sdf.format(dto.getReg_date()));
		dto.setCom_id(dao.com_id(dto.getCom_no()));
		model.addAttribute("dto", dto);
		
		return "community/com/detail";
	}
	
	@RequestMapping("/company/reply")
	public String com_reply(Com_reply com_reply) {
		
		return "redirect:detail";
	}
	
}
