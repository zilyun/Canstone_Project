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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.project.homepage.portscan.db.PortScanDTO;

/*@@@@!!!!!!!!!!!!!!!!!!!!!!!!! �� NMAP �������α׷��� ��ġ���ּ��� !!!!!!!!!!!!!!!!!!!!!!!!!!!!!@@@@*/
public class PortScanner_new {

	JSONObject obj = new JSONObject();

	JSONArray jArray = new JSONArray();

	List<PortScanDTO> port_info = new ArrayList<PortScanDTO>();

	CompletableFuture<JSONObject> future_port_reuslt = new CompletableFuture<>();

	CompletableFuture<List<String>> future_search_ip_result_list = new CompletableFuture<>();

	public List<String> portScanStart(PortScanDTO port) throws InterruptedException, ExecutionException {
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
					List<String> search_ip_result_list = new ArrayList<String>();

					String line;
					// String finish;

					while ((line = reader.readLine()) != null) {
						// System.out.println(line);
						line_buffer.add(line);
					}

					for (int i = 0; i < line_buffer.size(); i++) {
						if (line_buffer.get(i).contains("Nmap scan report for")) {
							// System.out.println(line_buffer.get(i).substring(line_buffer.get(i).lastIndexOf("for")
							// + 4));
							search_ip_result_list
									.add(line_buffer.get(i).substring(line_buffer.get(i).lastIndexOf("for") + 4));
						}
					}

					future_search_ip_result_list.complete(search_ip_result_list);

//					for(int j = 0 ; j < port_result_list.size() ; j++) {
//						port_one_select = port_result_list.get(j);
//						System.out.println(port_result_list.get(j));
//						for (int i = 0 ; i < port_one_select.size() ; i++) {
//							System.out.println(port_one_select.get(i));
//						}
//					}

				} catch (IOException e) {
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

		return future_search_ip_result_list.get();
	}

	public JSONObject portScan(String ip_num) throws InterruptedException, ExecutionException {
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
							System.out.println(line);

							if (line.contains(" ")) {
								line = line.replaceAll(" ", ",");
							}

							for (int i = 0; i < 10; i++) {
								String str = ",,,,,,,,,,";
								str = str.substring(i);
								line = line.replaceAll(str, ",");

							}

							line_buffer.add(line);

						}

						// System.out.println("");
						// System.out.println(ip_num);
						obj.put("search_ip", ip_num);

						for (int i = 0; i < line_buffer.size(); i++) {
							JSONObject sObject = new JSONObject();
							if (line_buffer.get(i).contains("PORT")) {
								// System.out.println(line_buffer.get(i));
							} else if (line_buffer.get(i).contains("open")) {
								String[] array = line_buffer.get(i).split(",");
								for (int j = 0; j < array.length; j++) {
									// System.out.println(array[j]);

									if (j == 0) {
										array[j] = array[j].replaceAll("/", " ");
										sObject.put("port_num", array[j]);

									} else if (j == 1) {
										sObject.put("port_state", array[j]);

									} else {
										sObject.put("port_service", array[j]);
										System.out.println(array[j] + "�Դϴ�.");
										String compareBuffer = array[j];
										if (compareBuffer.equals("http")) {
											sObject.put("port_description",
													"HTTP�� W3 �󿡼� ������ �ְ���� �� �ִ� ���������̴�. �ַ� HTML ������ �ְ�޴� ���� ���δ�.");
										} else if (compareBuffer.equals("https")) {
											sObject.put("port_description",
													"HTTPS�� ���� ���̵� �� ��� ���������� HTTP�� ������ ��ȭ�� �����̴�.");
										} else if (compareBuffer.equals("telnet")) {
											sObject.put("port_description",
													"�ڳ��� ���ͳ��̳� ���� ���� ��Ʈ��ũ ���ῡ ���̴� ��Ʈ��ũ ���������̴�. \r\n");
										} else if (compareBuffer.equals("mysql")) {
											sObject.put("port_description",
													"MySQL�� ���迡�� ���� ���� ���̴� ���� �ҽ��� ������ �����ͺ��̽� ���� �ý����̴�.");
										} else if (compareBuffer.equals("microsoft-ds")) {
											sObject.put("port_description",
													"Microsoft - Data Share ���񽺷� �ý����� ���� ��Ʈ�̴�. �ý��� ��� \r\n"
															+ "�� �ʿ��� ��Ʈ�� ������ų �� ���ٴ� ������ �����̷����� ���� ��� ��ʰ� ���� ���� ��Ʈ�̴�.");
										} else if (compareBuffer.equals("netbios-ssn")) {
											sObject.put("port_description",
													"��ǻ�Ϳ� �ִ� ���� ���α׷��� LAN�� ���� ��� �� �� �ֵ��� OSI ���� ���� ������ ���õ� ���񽺸� �����մϴ�. \r\n"
															+ "���� ���ڸ�, IP�ּҰ� �ƴ� ��ǻ��(NETBIOS)�̸����� ����� �� �ֵ��� �մϴ�.");
										} else if (compareBuffer.equals("msrpc")) {
											sObject.put("port_description",
													"MSRPC �� MS ���� RPC �������ݷ� �������� �ִ� ��ǻ�� ���� ���α׷��� \r\n"
															+ "�ҷ��� �� �ֵ��� �ϴ� Remote Procedure Call �� �ǹ��Ѵ�. ");
										} else {
											sObject.put("port_description", "unknown");
										}

									}
								}

								jArray.add(sObject);

							}
						}

						obj.put("port", jArray);

						future_port_reuslt.complete(obj);

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
					String input_command = "nmap -sT " + ip_num;
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