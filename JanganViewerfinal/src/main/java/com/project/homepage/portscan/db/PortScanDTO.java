package com.project.homepage.portscan.db;

public class PortScanDTO {

	private String search_ip; // �˻� ��� ip
	private String port_num; // port ��ȣ
	private String port_state; // port ����
	private String port_sevice; // port ���񽺸�

	public String getSearch_ip() {
		return search_ip;
	}

	public void setSearch_ip(String search_ip) {
		this.search_ip = search_ip;
	}

	public String getPort_num() {
		return port_num;
	}

	public void setPort_num(String port_num) {
		this.port_num = port_num;
	}

	public String getPort_state() {
		return port_state;
	}

	public void setPort_state(String port_state) {
		this.port_state = port_state;
	}

	public String getPort_sevice() {
		return port_sevice;
	}

	public void setPort_sevice(String port_sevice) {
		this.port_sevice = port_sevice;
	}

}
