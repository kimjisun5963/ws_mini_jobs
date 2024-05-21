package com.project.jobs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.jobs.dao.ICS_Dao99;
import com.project.jobs.dao.INotice_Dao99;
import com.project.jobs.dto.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin99")
public class Admin_controller99 {
	
	@Autowired
	private ICS_Dao99 cs_Dao;
	//문의사항
	@RequestMapping("/cs_Request_99")
	public String csRequest(HttpServletRequest request) {
		String cs_no = request.getParameter("cs_no");
		String content = request.getParameter("content");
		String title = request.getParameter("title");
		
		cs_Dao.writeRequestDao_99(cs_no, content, title);
		return "redirect:/cs_detail_99?cs_no="+cs_no;
	}
	
	@RequestMapping("/cs_list_99")
	public String list(Model model, HttpServletRequest request) {
		String cs_no = request.getParameter("cs_no");
		Object result = cs_Dao.getRequestDao_99(cs_no);
				
		if(result == null) {
			model.addAttribute("result","미답변");
		} else {
			model.addAttribute("result","답변완료");
		}
		
		model.addAttribute("list", cs_Dao.getList_99());

		return "/admin/cs_list";
	}
	
	@RequestMapping("/cs_detail_99")
	public String csDetail(Model model, HttpServletRequest request) {
		String cs_no = request.getParameter("cs_no");
		
		model.addAttribute("dto",cs_Dao.getlistDetail_99(cs_no));
		return "/admin/cs_detail";
	}
	
	@Autowired
	private INotice_Dao99 noticeDao;
	//공지사항
	@RequestMapping("/notice_write_form_99")
	public String noticeWriteForm() {
		
		return "/admin/notice_write_form";
	}
	
	@RequestMapping("/notice_write_99")
	public String noticeWrite(Model model, HttpServletRequest request) {
		String mem_no = request.getParameter("mem_no");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String category = request.getParameter("category");
		noticeDao.noticeWrite_99(mem_no, title, content, category);
		
		return "redirect:/notice_list_99";
	}
	
	@RequestMapping("/noticeDelete_99")
	public String noticeDelete(HttpServletRequest request) {
		String notice_no = request.getParameter("notice_no");
		noticeDao.noticeDeleteDao_99(notice_no);
		return "redirect:/notice_list_99";
	}
	
	@RequestMapping("/notice_modify_form")
	public String noticeModifyForm(HttpServletRequest request, Model model) {
		String notice_no = request.getParameter("notice_no");
		model.addAttribute("dto", noticeDao.getnoticeDetail_99(notice_no));
		
		return "/common/notice_modify_form";
	}
	
	@RequestMapping("/notice_modify_99")
	public String noticeModify(HttpServletRequest request) {
		String notice_no = request.getParameter("notice_no");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String category = request.getParameter("category");
		
		noticeDao.noticeModify_99(title, content, category, notice_no);
		return "redirect:/noticeDetail_99?notice_no=" + notice_no;
	}
	
	@RequestMapping("/notice_list_99")
	public String noticeList(Model model, HttpSession session) {
		
		model.addAttribute("list", noticeDao.getnoticeList_99());
		return "/admin/notice_list";
	}
	
	@RequestMapping("/admin_dashboard_99")
	public String dashboard() {
		
		return "/admin/dashboard";
	}
	
	
}
