package com.project.homepage.portscan.controller;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.homepage.portscan.db.PortScanDTO;
import com.project.homepage.portscan.service.PortScanServiceImpl;

@Controller
public class IpSearchController {


	private PortScanServiceImpl portScanService;

	@Autowired
	ServletContext servletContext;
	
	// ��Ʈ ������ ��������
	@RequestMapping(value = "/portIpList.do")
	public String port_ajax_test(@ModelAttribute PortScanDTO port, HttpSession session, Model model) throws Exception {
		System.out.println(port.getSearch_ip());
		portScanService = new PortScanServiceImpl();
		System.out.println(servletContext.getRealPath("/resources"));
		
		
		// ip ����Ʈ�� �𵨿� �Է�
		model.addAttribute("result", portScanService.port_scan_ip_list(port));
		return "portScanAjaxResult";
	}
	
	

}