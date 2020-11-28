package com.project.homepage.portscan.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.project.homepage.portscan.db.PortScanDTO;


/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ ��� @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ */
public class PortScanner_Legacy2 {

	List<String> port_info = new ArrayList<String>();

	CompletableFuture<List<String>> future_port_reuslt = new CompletableFuture<>();

	CompletableFuture<List<List>> future_port_reuslt_list = new CompletableFuture<>();

	public List<List> portScanStart(PortScanDTO port) throws InterruptedException, ExecutionException {
		try {
			// Linux�� ���� /bin/bash
			// Process process = Runtime.getRuntime().exec("/bin/bash");
			Process process = Runtime.getRuntime().exec("cmd");
			// Process�� �� stream�� �޴´�.
			// process�� �Է� stream
			OutputStream stdin = process.getOutputStream();
			// process�� ���� stream
			InputStream stderr = process.getErrorStream();
			// process�� ��� stream
			InputStream stdout = process.getInputStream();
			// ������ Ǯ�� �̿��ؼ� 3���� stream�� ����Ų��.
			// ������� ������ ������ nmap ó���ϱ� �� cmd ����� ������ �κп��� ������� �������ν� ������ �� ����ȭ��Ű�� �����̴�.
			// ��� stream�� BufferedReader�� �޾Ƽ� ���� ������ ���� ��� console ȭ�鿡 ��½�Ų��.

			ExecutorService executorService = Executors.newCachedThreadPool();

			executorService.submit(() -> {
				// ���� ������ �߻��� ��� InputStreamReader(stdout)�� ���ڵ� Ÿ���� �ִ´�. ex)
				// InputStreamReader(stdout, "euc-kr")
				// try (BufferedReader reader = new BufferedReader(new
				// InputStreamReader(stdout,"euc-kr"))) {
				try (BufferedReader reader = new BufferedReader(new InputStreamReader(stdout))) {
					ArrayList<String> line_buffer = new ArrayList<String>();
					ArrayList<String> search_ip_result_list = new ArrayList<String>();
					List<String> port_result = new ArrayList<String>();
					List<List> port_reuslt_list = new ArrayList<List>();

					String line;
					// String finish;

					while ((line = reader.readLine()) != null) {
						// System.out.println(line);
						line_buffer.add(line);
					}

					for (int i = 0; i < line_buffer.size(); i++) {
						if (line_buffer.get(i).contains("Nmap scan report for")) {
							System.out.println(line_buffer.get(i).substring(line_buffer.get(i).lastIndexOf("for") + 4));
							search_ip_result_list
									.add(line_buffer.get(i).substring(line_buffer.get(i).lastIndexOf("for") + 4));
						}
					}

					for (int i = 0; i < search_ip_result_list.size(); i++) {
						// ������ �� ������ �����Ͽ� �޸𸮸� �Ҵ���� �� �����ؾ����� ������ ����.
						PortScanner_Legacy2 portScanner_new = new PortScanner_Legacy2();
						port_result = portScanner_new.portScan(search_ip_result_list.get(i));
						port_reuslt_list.add(port_result);
					}

					future_port_reuslt_list.complete(port_reuslt_list);

//					for(int j = 0 ; j < port_result_list.size() ; j++) {
//						port_one_select = port_result_list.get(j);
//						System.out.println(port_result_list.get(j));
//						for (int i = 0 ; i < port_one_select.size() ; i++) {
//							System.out.println(port_one_select.get(i));
//						}
//					}

				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			// ���� stream�� BufferedReader�� �޾Ƽ� ������ �߻��� ��� console ȭ�鿡 ��½�Ų��.
			executorService.execute(() -> {
				// ���� ������ �߻��� ��� InputStreamReader(stdout)�� ���ڵ� Ÿ���� �ִ´�. ex)
				// InputStreamReader(stdout, "euc-kr")
				// try (BufferedReader reader = new BufferedReader(new InputStreamReader(stderr,
				// "euc-kr"))) {
				try (BufferedReader reader = new BufferedReader(new InputStreamReader(stderr))) {
					String line;
					while ((line = reader.readLine()) != null) {
						System.out.println("err " + line);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			// �Է� stream�� BufferedWriter�� �޾Ƽ� �ַܼκ��� ���� �Է��� Process Ŭ������ �����Ų��.
			executorService.execute(() -> {
				try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin))) {
					// �ַܼ� ���͸� ��ġ ����
					String input_cd = "cd C:\\Program Files (x86)\\Nmap";
					// �ֿܼ��� \n�� ���ԵǾ�� ����ȴ�.(������ �ǹ��ε� �ͽ��ϴ�.)
					input_cd += "\n";
					writer.write(input_cd);
					// Process�� ��ɾ� �Է�
					writer.flush();

					// �ַܼ� ���� ���Ͱ� ���ԵǸ� input String ������ ���� �Էµ˴ϴ�.
					String input_command = "nmap -sn " + port.getSearch_ip();
					// �ֿܼ��� \n�� ���ԵǾ�� ����ȴ�.(������ �ǹ��ε� �ͽ��ϴ�.)
					input_command += "\n";
					writer.write(input_command);
					// Process�� ��ɾ� �Է�
					writer.flush();
					// exit ��ɾ ���� ��쿡�� ���α׷��� �����մϴ�.
					// ������ �� ��� error
					/*
					 * if ("exit\n".equals(input1)) { System.exit(0); }
					 */

					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} catch (Throwable e) {
			e.printStackTrace();
		}

		return future_port_reuslt_list.get();
	}

	public List<String> portScan(String ip_list) throws InterruptedException, ExecutionException {
		try {
			// Linux�� ���� /bin/bash
			// Process process = Runtime.getRuntime().exec("/bin/bash");
			Process process = Runtime.getRuntime().exec("cmd");
			// Process�� �� stream�� �޴´�.
			// process�� �Է� stream
			OutputStream stdin = process.getOutputStream();
			// process�� ���� stream
			InputStream stderr = process.getErrorStream();
			// process�� ��� stream
			InputStream stdout = process.getInputStream();

			ExecutorService executorService = Executors.newCachedThreadPool();

			Runnable runnable = new Runnable() {

				@Override
				public void run() {
					try (BufferedReader reader = new BufferedReader(new InputStreamReader(stdout))) {
						ArrayList<String> line_buffer = new ArrayList<String>();
						String line;

						while ((line = reader.readLine()) != null) {
							// System.out.println(line);
							line_buffer.add(line);
						}

						System.out.println("");
						System.out.println(ip_list);
						port_info.add(ip_list);

						for (int i = 0; i < line_buffer.size(); i++) {

							if (line_buffer.get(i).contains("PORT")) {
								port_info.add(line_buffer.get(i));
								System.out.println(line_buffer.get(i));
							} else if (line_buffer.get(i).contains("open")) {
								port_info.add(line_buffer.get(i));
								System.out.println(line_buffer.get(i));
							}
						}

						future_port_reuslt.complete(port_info);

					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			};

			executorService.submit(runnable);

			// ���� stream�� BufferedReader�� �޾Ƽ� ������ �߻��� ��� console ȭ�鿡 ��½�Ų��.
			executorService.execute(() -> {
				// ���� ������ �߻��� ��� InputStreamReader(stdout)�� ���ڵ� Ÿ���� �ִ´�. ex)
				// InputStreamReader(stdout, "euc-kr")
				// try (BufferedReader reader = new BufferedReader(new InputStreamReader(stderr,
				// "euc-kr"))) {
				try (BufferedReader reader = new BufferedReader(new InputStreamReader(stderr))) {
					String line;
					while ((line = reader.readLine()) != null) {
						System.out.println("err " + line);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

			// �Է� stream�� BufferedWriter�� �޾Ƽ� �ַܼκ��� ���� �Է��� Process Ŭ������ �����Ų��.
			executorService.execute(() -> {
				// �ַܼ� ���͸� ��ġ ����
				try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin))) {
					String input_cd = "cd C:\\Program Files (x86)\\Nmap";
					// �ֿܼ��� \n�� ���ԵǾ�� ����ȴ�.(������ �ǹ��ε� �ͽ��ϴ�.)
					input_cd += "\n";
					writer.write(input_cd);
					// Process�� ��ɾ� �Է�
					writer.flush();

					// �ַܼ� ���� ���Ͱ� ���ԵǸ� input String ������ ���� �Էµ˴ϴ�.
					String input_command = "nmap -sT " + ip_list;
					// �ֿܼ��� \n�� ���ԵǾ�� ����ȴ�.(������ �ǹ��ε� �ͽ��ϴ�.)
					input_command += "\n";
					writer.write(input_command);
					// Process�� ��ɾ� �Է�
					writer.flush();

					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

		} catch (Throwable e) {
			e.printStackTrace();
		}

		return future_port_reuslt.get();
	}
}