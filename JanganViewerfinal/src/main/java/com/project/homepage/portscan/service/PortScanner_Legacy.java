package com.project.homepage.portscan.service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.project.homepage.portscan.db.PortScanDTO;

/*----------------------------���--------------------------*/

class PortScanner_Legacy {

	public ArrayList<Integer> portSCanStart(PortScanDTO port)
			throws InterruptedException, ExecutionException ,IOException{
		final ExecutorService es = Executors.newCachedThreadPool();
		
//		Scanner inputScanner = new Scanner(System.in); // ���� ����
//		final String ip = inputScanner.nextLine();

		final String ip = port.getSearch_ip();
		System.out.println("�����ִ� ��Ʈ�� �˻� �� IP �ּҸ� �Է��Ͻʽÿ� : " + ip);
		final int timeout = 200;
		final List<Future<ScanResult>> futures = new ArrayList<>();
		for (int portNum = 1; portNum <= 65535; portNum++) {
			// for (int port = 1; port <= 80; port++) {
			futures.add(portIsOpen(es, ip, portNum, timeout));
		}
		es.awaitTermination(200L, TimeUnit.MILLISECONDS);
		int openPorts = 0;
		
		ArrayList<Integer> portNumbering = new ArrayList<Integer>();
		
		try {
			for (final Future<ScanResult> f : futures) {
				if (f.get().isOpen()) {
					openPorts++;
					System.out.println(f.get().getPort());
					portNumbering.add(f.get().getPort());
					//Thread.sleep(500);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(" ȣ��Ʈ " + ip + " ���� " + openPorts + "���� ��Ʈ�� �����ֽ��ϴ�. " + "(�ð� �ʰ�  " + timeout + "ms�� ���� ��).");
		es.shutdown();
		
		return portNumbering;
	}

	public static Future<ScanResult> portIsOpen(final ExecutorService es, final String ip, final int port,
			final int timeout) {
		return es.submit(new Callable<ScanResult>() {
			@Override
			public ScanResult call() {
				try {
					Socket socket = new Socket();
					socket.connect(new InetSocketAddress(ip, port), timeout);
					socket.close();
					return new ScanResult(port, true);
				} catch (Exception ex) {
					return new ScanResult(port, false);
				}
			}
		});
	}

	public static class ScanResult {
		private int port;

		private boolean isOpen;

		public ScanResult(int port, boolean isOpen) {
			super();
			this.port = port;
			this.isOpen = isOpen;
		}

		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}

		public boolean isOpen() {
			return isOpen;
		}

		public void setOpen(boolean isOpen) {
			this.isOpen = isOpen;
		}

	}
}