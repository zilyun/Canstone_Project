package com.project.homepage.portscan.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.project.homepage.portscan.db.PortScanDTO;

public interface PortScanService {

	public List<String> port_scan_ip_list(PortScanDTO port) throws Exception;
	
	public JSONObject port_scan(String ip_num) throws Exception;
	
}
