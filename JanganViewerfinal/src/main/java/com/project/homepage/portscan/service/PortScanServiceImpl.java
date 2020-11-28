package com.project.homepage.portscan.service;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.project.homepage.portscan.db.PortScanDTO;

@Service
public class PortScanServiceImpl implements PortScanService {

	PortScanner_new portscan_test = new PortScanner_new();
	
	@Override
	public List<String> port_scan_ip_list(PortScanDTO port) throws Exception {
		// 포트스캔 ip 검색
		List<String> portList;
		portList = portscan_test.portScanStart(port);
		return portList;
	}
	
	@Override
	public JSONObject port_scan(String ip_num) throws Exception {
		// 포트스캔 실행		
		JSONObject portList = new JSONObject();
		portList = portscan_test.portScan(ip_num);
		return portList;
	}
}


