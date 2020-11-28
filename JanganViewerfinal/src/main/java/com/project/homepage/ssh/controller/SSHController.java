package com.project.homepage.ssh.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.homepage.ssh.db.SSH_ServiceDTO;
import com.project.homepage.ssh.service.SSHServiceImpl;

@Controller
public class SSHController {

	@Autowired
	private SSHServiceImpl sshServiceImpl;

	@RequestMapping(value = "/ssh_service_command.do", method = RequestMethod.POST)
	public String test(@ModelAttribute SSH_ServiceDTO ssh_ServiceDTO, HttpSession session, Model model) {

		try {
			model.addAttribute("result_cmd", sshServiceImpl.ssh_test(ssh_ServiceDTO));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "sshResult";
	}
}