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

/*@@@@!!!!!!!!!!!!!!!!!!!!!!!!! 꼭 NMAP 응용프로그램을 설치해주세요 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!@@@@*/
public class PortScanner_new {

	JSONObject obj = new JSONObject();

	JSONArray jArray = new JSONArray();

	List<PortScanDTO> port_info = new ArrayList<PortScanDTO>();

	CompletableFuture<JSONObject> future_port_reuslt = new CompletableFuture<>();

	CompletableFuture<List<String>> future_search_ip_result_list = new CompletableFuture<>();

	public List<String> portScanStart(PortScanDTO port) throws InterruptedException, ExecutionException {
		try {
			// Linux의 경우는 /bin/bash
			// Process process = Runtime.getRuntime().exec("/bin/bash");
			Process process = Runtime.getRuntime().exec("cmd");
			// Process의 각 stream을 받는다.
			// process의 입력 stream
			OutputStream stdin = process.getOutputStream();
			// process의 에러 stream
			InputStream stderr = process.getErrorStream();
			// process의 출력 stream
			InputStream stdout = process.getInputStream();
			// 쓰레드 풀을 이용해서 3개의 stream을 대기시킨다.
			// 쓰레드로 나누는 이유는 nmap 처리하기 전 cmd 명령을 내리는 부분에도 스레드로 나눔으로써 성능을 더 최적화시키기 위함이다.
			// 출력 stream을 BufferedReader로 받아서 라인 변경이 있을 경우 console 화면에 출력시킨다.

			ExecutorService executorService = Executors.newCachedThreadPool();

			executorService.submit(() -> {
				// 문자 깨짐이 발생할 경우 InputStreamReader(stdout)에 인코딩 타입을 넣는다. ex)
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
			// 에러 stream을 BufferedReader로 받아서 에러가 발생할 경우 console 화면에 출력시킨다.
			executorService.execute(() -> {
				// 문자 깨짐이 발생할 경우 InputStreamReader(stdout)에 인코딩 타입을 넣는다. ex)
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
			// 입력 stream을 BufferedWriter로 받아서 콘솔로부터 받은 입력을 Process 클래스로 실행시킨다.
			executorService.execute(() -> {
				try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin))) {
					// 콘솔로 디렉터리 위치 변경
					String input_cd = "cd C:\\Program Files (x86)\\Nmap";
					// 콘솔에서 \n가 포함되어야 실행된다.(엔터의 의미인듯 싶습니다.)
					input_cd += "\n";
					writer.write(input_cd);
					// Process로 명령어 입력
					writer.flush();

					// 콘솔로 부터 엔터가 포함되면 input String 변수로 값이 입력됩니다.
					String input_command = "nmap -sn " + port.getSearch_ip();
					// 콘솔에서 \n가 포함되어야 실행된다.(엔터의 의미인듯 싶습니다.)
					input_command += "\n";
					writer.write(input_command);
					// Process로 명령어 입력
					writer.flush();
					// exit 명령어가 들어올 경우에는 프로그램을 종료합니다.
					// 끝내려 할 경우 error
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
			// Linux의 경우는 /bin/bash
			// Process process = Runtime.getRuntime().exec("/bin/bash");
			Process process = Runtime.getRuntime().exec("cmd");
			// Process의 각 stream을 받는다.
			// process의 입력 stream
			OutputStream stdin = process.getOutputStream();
			// process의 에러 stream
			InputStream stderr = process.getErrorStream();
			// process의 출력 stream
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
										System.out.println(array[j] + "입니다.");
										String compareBuffer = array[j];
										if (compareBuffer.equals("http")) {
											sObject.put("port_description",
													"HTTP는 W3 상에서 정보를 주고받을 수 있는 프로토콜이다. 주로 HTML 문서를 주고받는 데에 쓰인다.");
										} else if (compareBuffer.equals("https")) {
											sObject.put("port_description",
													"HTTPS는 월드 와이드 웹 통신 프로토콜인 HTTP의 보안이 강화된 버전이다.");
										} else if (compareBuffer.equals("telnet")) {
											sObject.put("port_description",
													"텔넷은 인터넷이나 로컬 영역 네트워크 연결에 쓰이는 네트워크 프로토콜이다. \r\n");
										} else if (compareBuffer.equals("mysql")) {
											sObject.put("port_description",
													"MySQL은 세계에서 가장 많이 쓰이는 오픈 소스의 관계형 데이터베이스 관리 시스템이다.");
										} else if (compareBuffer.equals("microsoft-ds")) {
											sObject.put("port_description",
													"Microsoft - Data Share 서비스로 시스템이 여는 포트이다. 시스템 운영상에 \r\n"
															+ "꼭 필요한 포트로 중지시킬 수 없다는 이유로 웜바이러스를 통한 약용 사례가 증가 중인 포트이다.");
										} else if (compareBuffer.equals("netbios-ssn")) {
											sObject.put("port_description",
													"컴퓨터에 있는 응용 프로그램이 LAN을 통해 통신 할 수 있도록 OSI 모델의 세션 계층과 관련된 서비스를 제공합니다. \r\n"
															+ "예를 들자면, IP주소가 아닌 컴퓨터(NETBIOS)이름으로 통신할 수 있도록 합니다.");
										} else if (compareBuffer.equals("msrpc")) {
											sObject.put("port_description",
													"MSRPC 는 MS 사의 RPC 프로토콜로 원격지에 있는 컴퓨터 상의 프로그램을 \r\n"
															+ "불러낼 수 있도록 하는 Remote Procedure Call 을 의미한다. ");
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

			// 에러 stream을 BufferedReader로 받아서 에러가 발생할 경우 console 화면에 출력시킨다.
			executorService.execute(() -> {
				// 문자 깨짐이 발생할 경우 InputStreamReader(stdout)에 인코딩 타입을 넣는다. ex)
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

			// 입력 stream을 BufferedWriter로 받아서 콘솔로부터 받은 입력을 Process 클래스로 실행시킨다.
			executorService.execute(() -> {
				// 콘솔로 디렉터리 위치 변경
				try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin))) {
					String input_cd = "cd C:\\Program Files (x86)\\Nmap";
					// 콘솔에서 \n가 포함되어야 실행된다.(엔터의 의미인듯 싶습니다.)
					input_cd += "\n";
					writer.write(input_cd);
					// Process로 명령어 입력
					writer.flush();

					// 콘솔로 부터 엔터가 포함되면 input String 변수로 값이 입력됩니다.
					String input_command = "nmap -sT " + ip_num;
					// 콘솔에서 \n가 포함되어야 실행된다.(엔터의 의미인듯 싶습니다.)
					input_command += "\n";
					writer.write(input_command);
					// Process로 명령어 입력
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