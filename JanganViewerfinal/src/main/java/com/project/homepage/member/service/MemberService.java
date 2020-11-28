package com.project.homepage.member.service;


import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import com.project.homepage.member.db.MemberDTO;

public interface MemberService {
	public void check_id(String id, HttpServletResponse response) throws Exception;

	public void check_email(String email, HttpServletResponse response) throws Exception;

	public int join_member(MemberDTO member, HttpServletResponse response) throws Exception;
	
	public void approval_member (MemberDTO member, HttpServletResponse response) throws Exception;
	
	public String create_key() throws Exception;
	
	public void send_mail(MemberDTO member) throws Exception;
	
	public MemberDTO login(MemberDTO member, HttpServletResponse response) throws Exception;
	
	public void logout(ServletResponse response) throws Exception;
	
	public String find_id(HttpServletResponse response, String email) throws Exception;
}