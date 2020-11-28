package com.project.homepage.ssh.service;

import com.project.homepage.ssh.db.SSH_ServiceDTO;

public interface SSHService {

	public String[] ssh_test(SSH_ServiceDTO ssh_ServiceDTO) throws Exception;

}
