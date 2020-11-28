package com.project.homepage.chat.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.project.homepage.chat.service.ChattingServiceImpl;
import com.project.homepage.chat.vo.Chat;

@Component
public class SocketHandler extends TextWebSocketHandler {

	List<HashMap<String, Object>> rls = new ArrayList<>(); // 웹소켓 세션을 담아둘 리스트 ---roomListSessions
	String FILE_UPLOAD_PATH = "";
	static int fileUploadIdx = 0;
	static String fileUploadSession = "";
	List<Chat> chatList;
	String userName;

	@Autowired
	ChattingServiceImpl chattingServiceImpl;

	@Autowired
	ResourceLoader resourceLoder;

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

		Resource resource = resourceLoder.getResource("classpath:/static/img");
		FILE_UPLOAD_PATH = resource.getURI().getPath();
		// 메시지 발송
		String msg = message.getPayload(); // JSON형태의 String메시지를 받는다.
		JSONObject obj = jsonToObjectParser(msg); // JSON데이터를 JSONObject로 파싱한다.

		String rN = (String) obj.get("roomNumber"); // 방의 번호를 받는다.
		String content = (String) obj.get("msg"); // 유저의 아이디를 얻는다. 세션 대신 사용한다.
		String userName = (String) obj.get("userName"); // 유저의 아이디를 얻는다.

		System.out.println(rN);
		System.out.println(userName);
		System.out.println(content);

		// 상태를 저장하기 위해 vo에 값을 넣어주고 insert
		Chat chat = new Chat();
		chat.setRoomNumber(rN);
		chat.setUserName(userName);
		chat.setContent(content);

		chattingServiceImpl.create_chat(chat);
		
		HashMap<String, Object> temp = new HashMap<String, Object>();
		if (rls.size() > 0) {
			for (int i = 0; i < rls.size(); i++) {
				String roomNumber = (String) rls.get(i).get("roomNumber"); // 세션리스트의 저장된 방번호를 가져와서
				if (roomNumber.equals(rN)) { // 같은값의 방이 존재한다면
					temp = rls.get(i); // 해당 방번호의 세션리스트의 존재하는 모든 object값을 가져온다.
					break;
				}
			}

			// 해당 방의 세션들만 찾아서 메시지를 발송해준다.
			for (String k : temp.keySet()) {
				if (k.equals("roomNumber")) { // 다만 방번호일 경우에는 건너뛴다.
					continue;
				}

				WebSocketSession wss = (WebSocketSession) temp.get(k);
				if (wss != null) {
					try {
						wss.sendMessage(new TextMessage(obj.toJSONString()));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/*
	 * @Override public void handleBinaryMessage(WebSocketSession session,
	 * BinaryMessage message) { // 바이너리 메시지 발송 ByteBuffer byteBuffer =
	 * message.getPayload(); String fileName = "temp.jpg"; File dir = new
	 * File(FILE_UPLOAD_PATH); if (!dir.exists()) { dir.mkdirs(); }
	 * 
	 * File file = new File(FILE_UPLOAD_PATH, fileName); FileOutputStream out =
	 * null; FileChannel outChannel = null; try { byteBuffer.flip(); // byteBuffer를
	 * 읽기 위해 세팅 out = new FileOutputStream(file, true); // 생성을 위해 OutputStream을 연다.
	 * outChannel = out.getChannel(); // 채널을 열고 byteBuffer.compact(); // 파일을 복사한다.
	 * outChannel.write(byteBuffer); // 파일을 쓴다. } catch (Exception e) {
	 * e.printStackTrace(); } finally { try { if (out != null) { out.close(); } if
	 * (outChannel != null) { outChannel.close(); } } catch (IOException e) {
	 * e.printStackTrace(); } }
	 * 
	 * byteBuffer.position(0); // 파일을 저장하면서 position값이 변경되었으므로 0으로 초기화한다. // 파일쓰기가
	 * 끝나면 이미지를 발송한다. HashMap<String, Object> temp = rls.get(fileUploadIdx); for
	 * (String k : temp.keySet()) { if (k.equals("roomNumber")) { continue; }
	 * WebSocketSession wss = (WebSocketSession) temp.get(k); try {
	 * wss.sendMessage(new BinaryMessage(byteBuffer)); // 초기화된 버퍼를 발송한다. } catch
	 * (IOException e) { e.printStackTrace(); } } }
	 */

	@SuppressWarnings("unchecked")
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {

		System.out.println("소켓 설립");

		// 소켓 연결
		super.afterConnectionEstablished(session);
		boolean flag = false;
		String url = session.getUri().toString();
		String buffer = url.split("/chating/")[1];
		String roomNumber = buffer.split("&")[0];
		userName = buffer.split("&")[1];

		System.out.println(roomNumber + ": 방번호");
		System.out.println(userName + ": 유저이름");

		// 방번호를 기준으로 다 받아온다.
		Chat chat = new Chat();
		chat.setRoomNumber(roomNumber);
		System.out.println(chat);
		System.out.println(chat.getRoomNumber());
		chatList = chattingServiceImpl.search_chat(chat);
		System.out.println(chatList + ": 입니다.");

		int idx = rls.size(); // 방의 사이즈를 조사한다.
		if (rls.size() > 0) {
			for (int i = 0; i < rls.size(); i++) {
				String rN = (String) rls.get(i).get("roomNumber");
				if (rN.equals(roomNumber)) {
					flag = true;
					idx = i;
					break;
				}
			}
		}

		if (flag) { // 존재하는 방이라면 세션만 추가한다.
			HashMap<String, Object> map = rls.get(idx);
			map.put(session.getId(), session);
		} else { // 최초 생성하는 방이라면 방번호와 세션을 추가한다.
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("roomNumber", roomNumber);
			map.put(session.getId(), session);
			rls.add(map);
		}

		// 포문으로 연속 메시지를 보낸다. list 크기 만큼 돌린다.
		for (int i = 0; i < chatList.size(); i++) {

			String content = chatList.get(i).getContent();
			String userDBName = chatList.get(i).getUserName();
			System.out.println(i);
			// 세션등록이 끝나면 발급받은 세션ID값의 메시지를 발송한다.
			JSONObject obj = new JSONObject();
			System.out.println(session.getId());
			System.out.println(session);
			obj.put("type", "getId");
			//obj.put(session.getId(),session); // 활성화하면 새로고침 시 소켓이 종료되면서 같은 세션 값을 가지고 있던 obj들이 제거되었었음.
			obj.put("sessionId", userName); // 유저 아이디임. // 위를 활성화하면 세션과 관련된 obj들이 제거되면서 이 컬럼과 js 조건문이 만나는 조건에서 의도치 않은 결과가 나왔었음.
			obj.put("userName", userDBName);
			obj.put("msg", content);
			session.sendMessage(new TextMessage(obj.toJSONString()));
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("소켓종료");
		
		
		// 소켓 종료
		if (rls.size() > 0) { // 소켓이 종료되면 해당 세션값들을 찾아서 지운다.
			for (int i = 0; i < rls.size(); i++) {
				rls.get(i).remove(session.getId()); // 세션값과 관련된 obj들을 제거함.
													// 만약 위에서 세션값을 설정 안해준다면 기존 세션값을 지우고 새 소켓을 만들 시 새로운 세션으로 시작.
													// obj.put(session.getId(),session);의 session.getId() 속성값을
													// map의 session.getId() 속성값과  비교하였을때 서로 일치하는 세션값을 가진 obj를 제거함.
			}
		}
		super.afterConnectionClosed(session, status);
	}

	private static JSONObject jsonToObjectParser(String jsonStr) {
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		try {
			obj = (JSONObject) parser.parse(jsonStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return obj;
	}
}