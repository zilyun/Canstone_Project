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

	String username = "���� ���̵�";
	String host = "������ ������ �ּ�";
	int port = 22; // ������ SSH ��Ʈ ��ȣ
	String password = "���� ��й�ȣ";

	public String[] sshStart(SSH_ServiceDTO ssh_ServiceDTO) throws IOException {

		System.out.println("==> Connecting to 1 command" + host);
		Session session = null; // ����� ������ ����
		Channel channel = null;
		Session session2 = null; // ��� ����� �ޱ� ����
		Channel channel2 = null;
		String test = "";

		// 2. ���� ��ü�� �����Ѵ� (����� �̸�, ������ ȣ��Ʈ, ��Ʈ�� ���ڷ� �ش�.)
		try {

			// 1. JSch ��ü�� �����Ѵ�.
			JSch jsch = new JSch();
			session = jsch.getSession(username, host, port);

			// 3. �н����带 �����Ѵ�.
			session.setPassword(password);

			// 4. ���ǰ� ���õ� ������ �����Ѵ�.
			java.util.Properties config = new java.util.Properties();
			// 4-1. ȣ��Ʈ ������ �˻����� �ʴ´�.
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);

			// 5. �����Ѵ�.
			session.connect();

			// 6. sftp ä���� ����.
			channel = session.openChannel("exec");

			// 8. ä���� SSH�� ä�� ��ü�� ĳ�����Ѵ�
			ChannelExec channelExec = (ChannelExec) channel;
			channelExec.setPty(true);
			channelExec.setInputStream(null);
			channelExec.setErrStream(System.err);
			System.out.println("echo '������ �ִ� ���̵� �� ��� ��й�ȣ �Է�' | sudo -S "+ssh_ServiceDTO.getSearch_ssh_command());
			channelExec.setCommand("echo '������ �ִ� ���̵� �� ��� ��й�ȣ �Է�' | sudo -S "+ ssh_ServiceDTO.getSearch_ssh_command());

			channelExec.connect();

			// ����� �ޱ� ���Ͽ� ��� �뵵�� ������ �ϳ� �� ����ְ� �����Ѵ�.
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
			

			
			System.out.println("echo '������ �ִ� ���̵� �� ��� ��й�ȣ �Է�' | sudo -S " + ssh_ServiceDTO.getSearch_ssh_command() + "��� Ŀ�ǵ�");
			channelExec2.setCommand("echo '������ �ִ� ���̵� �� ��� ��й�ȣ �Է�' | sudo -S " + ssh_ServiceDTO.getSearch_ssh_command());

			
			System.out.println("==> Connected to 2 read" + host);
			InputStream in = channel.getInputStream();
			channelExec2.connect();
			String temp;

			// ����� �ޱ� ���� temp(�ӽ�)
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