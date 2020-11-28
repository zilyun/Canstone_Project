package com.project.homepage.ssh.service;

import java.io.IOException;
import java.io.InputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.project.homepage.ssh.db.SSH_ServiceDTO;

public class SSHService_Start {

	String username = "계정 아이디";
	String host = "서버의 아이피 주소";
	int port = 22; // 서버의 SSH 포트 번호
	String password = "계정 비밀번호";

	public String[] sshStart(SSH_ServiceDTO ssh_ServiceDTO) throws IOException {

		System.out.println("==> Connecting to 1 command" + host);
		Session session = null; // 명령을 내리기 위한
		Channel channel = null;
		Session session2 = null; // 명령 결과를 받기 위한
		Channel channel2 = null;
		String test = "";

		// 2. 세션 객체를 생성한다 (사용자 이름, 접속할 호스트, 포트를 인자로 준다.)
		try {

			// 1. JSch 객체를 생성한다.
			JSch jsch = new JSch();
			session = jsch.getSession(username, host, port);

			// 3. 패스워드를 설정한다.
			session.setPassword(password);

			// 4. 세션과 관련된 정보를 설정한다.
			java.util.Properties config = new java.util.Properties();
			// 4-1. 호스트 정보를 검사하지 않는다.
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);

			// 5. 접속한다.
			session.connect();

			// 6. sftp 채널을 연다.
			channel = session.openChannel("exec");

			// 8. 채널을 SSH용 채널 객체로 캐스팅한다
			ChannelExec channelExec = (ChannelExec) channel;
			channelExec.setPty(true);
			channelExec.setInputStream(null);
			channelExec.setErrStream(System.err);
			System.out.println("echo '권한이 있는 아이디 일 경우 비밀번호 입력' | sudo -S "+ssh_ServiceDTO.getSearch_ssh_command());
			channelExec.setCommand("echo '권한이 있는 아이디 일 경우 비밀번호 입력' | sudo -S "+ ssh_ServiceDTO.getSearch_ssh_command());

			channelExec.connect();

			// 결과를 받기 위하여 결과 용도의 세션을 하나 더 잡아주고 실행한다.
			JSch jsch2 = new JSch();
			session2 = jsch2.getSession(username, host, port);
			session2.setPassword(password);
			java.util.Properties config2 = new java.util.Properties();
			config2.put("StrictHostKeyChecking", "no");
			session2.setConfig(config);
			session2.connect();
			channel = session2.openChannel("exec");
			ChannelExec channelExec2 = (ChannelExec) channel;
			channelExec2.setPty(true);
			channelExec2.setInputStream(null);
			channelExec2.setErrStream(System.err);
			

			
			System.out.println("echo '권한이 있는 아이디 일 경우 비밀번호 입력' | sudo -S " + ssh_ServiceDTO.getSearch_ssh_command() + "출력 커맨드");
			channelExec2.setCommand("echo '권한이 있는 아이디 일 경우 비밀번호 입력' | sudo -S " + ssh_ServiceDTO.getSearch_ssh_command());

			
			System.out.println("==> Connected to 2 read" + host);
			InputStream in = channel.getInputStream();
			channelExec2.connect();
			String temp;

			// 결과를 받기 위한 temp(임시)
			byte[] tmp = new byte[2048];
			while (true) {
				while (in.available() > 0) {
					int i = in.read(tmp, 0, 2048);
					if (i < 0)
						break;
					System.out.println(new String(tmp, 0, i));
					temp = new String(tmp, 0, i, "UTF-8");
					test = test + temp;
					
					

				}

				if (channel.isClosed()) {
					if (in.available() > 0)
						continue;
					System.out.println("exit-status: " + channel.getExitStatus());
					break;
				}

				try {
					Thread.sleep(1000);
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}

		} catch (JSchException e) {
			e.printStackTrace();
		} finally {
			if (channel != null) {
				channel.disconnect();
			}
			if (session2 != null) {
				session2.disconnect();
			}
			if (channel2 != null) {
				channel.disconnect();
			}
			if (session2 != null) {
				session2.disconnect();
			}

		}
		String[] tests = test.split(System.getProperty("line.separator"));
		
		return tests;

	}
}