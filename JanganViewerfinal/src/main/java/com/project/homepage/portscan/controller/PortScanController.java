package com.project.homepage.portscan.controller;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.homepage.portscan.service.PortScanServiceImpl;

@RestController
public class PortScanController {

	private PortScanServiceImpl portScanService;

	@GetMapping("/portScanTest.do")
	public @ResponseBody String prtScan_test(String ip_num) throws Exception {
		System.out.println("ajax ½ÇÇà");
		
		JSONObject portList = new JSONObject();
		
		portScanService = new PortScanServiceImpl();
		portList = portScanService.port_scan(ip_num);
		System.out.println(portList);
		
		return portList.toString();
	}
}
