package com.project.homepage.remote.service;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class SendThread extends Thread {

	private Socket socket;
	private int x = 0;
	private int y = 0;
	private int click = 0;
	private int key = 0;
	private int spKey = 0;
	private int spKey2 = 0;
	
	String x_str, y_str, c_str, k_str,spKey_str,spKey2_str;
	String str_cursor = "";

	@Override
	public void run() {
		super.run();

		try {
			BufferedOutputStream bout = new BufferedOutputStream(socket.getOutputStream());
			DataOutputStream dout = new DataOutputStream(bout);

			while (true) {
				// ���� �κп� �Ʒ�ó�� ���� �ð��� ����ϰ�

				long start = System.currentTimeMillis();

				// Ŀ�� �̵� ����
				x_str = Integer.toString(x);
				y_str = Integer.toString(y);
				c_str = Integer.toString(click);
				k_str = Integer.toString(key);
				spKey_str = Integer.toString(spKey);
				spKey2_str = Integer.toString(spKey2);
				
				str_cursor = x_str + "/" + y_str;

				if (c_str != "0") {
					str_cursor = str_cursor + "/" + c_str;

				} else {
					str_cursor = str_cursor + "/" + "0";
				}

				if (k_str != "0") {
					str_cursor = str_cursor + "/" + k_str;

				} else {
					str_cursor = str_cursor + "/" + "0";
				}
				
				if (spKey_str != "0") {
					str_cursor = str_cursor + "/" + spKey_str;

				} else {
					str_cursor = str_cursor + "/" + "0";
				}
				
				if (spKey2_str != "0") {
					str_cursor = str_cursor + "/" + spKey2_str;

				} else {
					str_cursor = str_cursor + "/" + "0";
				}

				dout.writeUTF(str_cursor);
				dout.flush();
				// System.out.println(str_cursor);
				str_cursor = "";
				key = 0;
				spKey = 0;
				spKey2 = 0;
				Thread.sleep(50);		//�������� �������� �ð����� ��Ȯ�ϰ� �����ؾ��� 
										// Ŭ���̾�Ʈ 48�ʸ� �ش�.
				long end = System.currentTimeMillis();
				System.out.println( "���� �ð� : " + ( end - start )/1000.0 );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setC_str(int click) {
		this.click = click;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getSpKey() {
		return spKey;
	}

	public void setSpKey(int spKey) {
		this.spKey = spKey;
	}

	public int getSpKey2() {
		return spKey2;
	}

	public void setSpKey2(int spKey2) {
		this.spKey2 = spKey2;
	}

}
