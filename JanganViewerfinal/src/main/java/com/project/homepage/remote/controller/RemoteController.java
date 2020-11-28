package com.project.homepage.remote.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.homepage.remote.service.RemoteServiceImpl;
import com.project.homepage.remote.service.coolSMS_ServiceImpl;

@Controller
public class RemoteController {
	
	@Autowired
	private coolSMS_ServiceImpl coolSMS_ServiceImpl;

	@RequestMapping(value = "smsTest.do")
	public String smsPageMove() throws Exception {
		return "coolSMS";
	}

	@GetMapping("/check/sendSMS")
	public @ResponseBody String sendSMS(String phoneNumber) throws Exception {

		Random rand = new Random();
		String numStr = "";
		String ran = "";
		for (int i = 0; i < 5; i++) {
			if(i==0) {
				ran = Integer.toString(rand.nextInt(5));
			}else {
				ran = Integer.toString(rand.nextInt(10));
			}	
			numStr += ran;
		}

		System.out.println("수신자 번호 : " + phoneNumber);
		System.out.println("인증번호 : " + numStr);
		
		coolSMS_ServiceImpl.certifiedPhoneNumber(phoneNumber, numStr);
		RemoteServiceImpl remoteServiceImpl = new RemoteServiceImpl();
		remoteServiceImpl.remote_start(numStr);
		
		return numStr;
	}
	
	@GetMapping("/check/sendSMS_test")
	public @ResponseBody String sendSMS_test(String phoneNumber) throws Exception {

		Random rand = new Random();
		String numStr = "";
		String ran = "";
		for (int i = 0; i < 5; i++) {
			if(i==0) {
				ran = Integer.toString(rand.nextInt(6));
			}else {
				ran = Integer.toString(rand.nextInt(10));
			}	
			numStr += ran;
		}

		System.out.println("수신자 번호 : " + phoneNumber);
		System.out.println("인증번호 : " + numStr);
		
		//coolSMS_ServiceImpl.certifiedPhoneNumber(phoneNumber, numStr);
		RemoteServiceImpl remoteServiceImpl = new RemoteServiceImpl();
		remoteServiceImpl.remote_start(numStr);
		
		return numStr;
	}
}