package com.project.homepage.remote.service;

import org.springframework.stereotype.Service;

@Service
public class RemoteServiceImpl implements RemoteService {
	
	@Override
	public void remote_start(String auth_str) throws Exception {
		
		RemoteStart remoteObj = new RemoteStart();
		remoteObj.remoteStart(auth_str);

	}
}
