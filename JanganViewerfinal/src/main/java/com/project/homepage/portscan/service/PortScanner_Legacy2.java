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


/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 폐기 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ */
public class PortScanner_Legacy2 {

	List<String> port_info = new ArrayList<String>();

	CompletableFuture<List<String>> future_port_reuslt = new CompletableFuture<>();

	CompletableFuture<List<List>> future_port_reuslt_list = new CompletableFuture<>();

	public List<List> portScanStart(PortScanDTO port) throws InterruptedException, ExecutionException {
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
						// 무조건 이 곳에서 생성하여 메모리를 할당받은 후 진행해야지만 가능한 로직.
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

		return future_port_reuslt_list.get();
	}

	public List<String> portScan(String ip_list) throws InterruptedException, ExecutionException {
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
					String input_command = "nmap -sT " + ip_list;
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