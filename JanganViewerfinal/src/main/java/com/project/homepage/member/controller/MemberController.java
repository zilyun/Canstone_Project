package com.project.homepage.member.controller;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.homepage.member.db.MemberDTO;
import com.project.homepage.member.service.MemberServiceImpl;

@Controller
public class MemberController {

//	// 회원 가입 폼 이동
//	@RequestMapping(value = "/memberJoinForm.do")
//	public String memberJoinForm() throws Exception {
//		return "memberJoinForm";
//	} 폐기@@@@@@@@@@@@@@@@@@@@@@@@

	@Autowired
	private MemberServiceImpl service;

	// 아이디 중복 검사(AJAX)
	@RequestMapping(value = "/check_id.do", method = RequestMethod.POST)
	public void check_id(@RequestParam("id") String id, HttpServletResponse response) throws Exception {
		service.check_id(id, response);
	}

	// 이메일 중복 검사(AJAX)
	@RequestMapping(value = "/check_email.do", method = RequestMethod.POST)
	public void check_email(@RequestParam("email") String email, HttpServletResponse response) throws Exception {
		service.check_email(email, response);
	}

	// 회원 가입
	@RequestMapping(value = "/join_member.do", method = RequestMethod.POST)
	public String join_member(@ModelAttribute MemberDTO member, RedirectAttributes rttr, HttpServletResponse response)
			throws Exception {

		rttr.addFlashAttribute("result", service.join_member(member, response));

		return "memberJoinForm";
	}

	// 회원 인증
	@RequestMapping(value = "/approval_member.do", method = RequestMethod.POST)
	public String approval_member(@ModelAttribute MemberDTO member, HttpServletResponse response) throws Exception {
		service.approval_member(member, response);

		return "home";
	}

	// 로그인 폼 이동
	@RequestMapping(value = "/login_memberJoin.do", method = RequestMethod.GET)
	public String login_form() throws Exception {
		return "/login_memberJoin";
	}

	// 로그인
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(@ModelAttribute MemberDTO member, HttpSession session, HttpServletResponse response)
			throws Exception {
		member = service.login(member, response);
		session.setAttribute("member", member);
		return "home";
	}

	// 로그아웃
	@RequestMapping(value = "/logout.do", method = { RequestMethod.GET, RequestMethod.POST })
	public void logout(HttpSession session, ServletResponse response) throws Exception {
		session.invalidate();
		// session.removeAttribute("member");
		System.out.println("로그아웃");
		service.logout(response);
	}

	// 아이디 찾기 폼
	@RequestMapping(value = "/find_id_form.do")
	public String find_id_form() throws Exception {
		return "find_id_form";
	}

	// 아이디 찾기
	@RequestMapping(value = "/find_id.do", method = RequestMethod.POST)
	public String find_id(HttpServletResponse response, @RequestParam("email") String email, Model md)
			throws Exception {
		md.addAttribute("id", service.find_id(response, email));
		return "find_id";
	}

}