package com.project.homepage.ssh.service;

import org.springframework.stereotype.Service;

import com.project.homepage.ssh.db.SSH_ServiceDTO;

@Service
public class SSHServiceImpl implements SSHService {

	@Override
	public String[] ssh_test(SSH_ServiceDTO ssh_ServiceDTO) throws Exception {
				
		String[] tests;
		
		SSHService_Start ssh_test = new SSHService_Start();
		
		tests=ssh_test.sshStart(ssh_ServiceDTO);
		
		System.out.println(tests);
		
		return tests;
	}
}
