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

	List<HashMap<String, Object>> rls = new ArrayList<>(); // ������ ������ ��Ƶ� ����Ʈ ---roomListSessions
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
		// �޽��� �߼�
		String msg = message.getPayload(); // JSON������ String�޽����� �޴´�.
		JSONObject obj = jsonToObjectParser(msg); // JSON�����͸� JSONObject�� �Ľ��Ѵ�.

		String rN = (String) obj.get("roomNumber"); // ���� ��ȣ�� �޴´�.
		String content = (String) obj.get("msg"); // ������ ���̵� ��´�. ���� ��� ����Ѵ�.
		String userName = (String) obj.get("userName"); // ������ ���̵� ��´�.

		System.out.println(rN);
		System.out.println(userName);
		System.out.println(content);

		// ���¸� �����ϱ� ���� vo�� ���� �־��ְ� insert
		Chat chat = new Chat();
		chat.setRoomNumber(rN);
		chat.setUserName(userName);
		chat.setContent(content);

		chattingServiceImpl.create_chat(chat);
		
		HashMap<String, Object> temp = new HashMap<String, Object>();
		if (rls.size() > 0) {
			for (int i = 0; i < rls.size(); i++) {
				String roomNumber = (String) rls.get(i).get("roomNumber"); // ���Ǹ���Ʈ�� ����� ���ȣ�� �����ͼ�
				if (roomNumber.equals(rN)) { // �������� ���� �����Ѵٸ�
					temp = rls.get(i); // �ش� ���ȣ�� ���Ǹ���Ʈ�� �����ϴ� ��� object���� �����´�.
					break;
				}
			}

			// �ش� ���� ���ǵ鸸 ã�Ƽ� �޽����� �߼����ش�.
			for (String k : temp.keySet()) {
				if (k.equals("roomNumber")) { // �ٸ� ���ȣ�� ��쿡�� �ǳʶڴ�.
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
	 * BinaryMessage message) { // ���̳ʸ� �޽��� �߼� ByteBuffer byteBuffer =
	 * message.getPayload(); String fileName = "temp.jpg"; File dir = new
	 * File(FILE_UPLOAD_PATH); if (!dir.exists()) { dir.mkdirs(); }
	 * 
	 * File file = new File(FILE_UPLOAD_PATH, fileName); FileOutputStream out =
	 * null; FileChannel outChannel = null; try { byteBuffer.flip(); // byteBuffer��
	 * �б� ���� ���� out = new FileOutputStream(file, true); // ������ ���� OutputStream�� ����.
	 * outChannel = out.getChannel(); // ä���� ���� byteBuffer.compact(); // ������ �����Ѵ�.
	 * outChannel.write(byteBuffer); // ������ ����. } catch (Exception e) {
	 * e.printStackTrace(); } finally { try { if (out != null) { out.close(); } if
	 * (outChannel != null) { outChannel.close(); } } catch (IOException e) {
	 * e.printStackTrace(); } }
	 * 
	 * byteBuffer.position(0); // ������ �����ϸ鼭 position���� ����Ǿ����Ƿ� 0���� �ʱ�ȭ�Ѵ�. // ���Ͼ��Ⱑ
	 * ������ �̹����� �߼��Ѵ�. HashMap<String, Object> temp = rls.get(fileUploadIdx); for
	 * (String k : temp.keySet()) { if (k.equals("roomNumber")) { continue; }
	 * WebSocketSession wss = (WebSocketSession) temp.get(k); try {
	 * wss.sendMessage(new BinaryMessage(byteBuffer)); // �ʱ�ȭ�� ���۸� �߼��Ѵ�. } catch
	 * (IOException e) { e.printStackTrace(); } } }
	 */

	@SuppressWarnings("unchecked")
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {

		System.out.println("���� ����");

		// ���� ����
		super.afterConnectionEstablished(session);
		boolean flag = false;
		String url = session.getUri().toString();
		String buffer = url.split("/chating/")[1];
		String roomNumber = buffer.split("&")[0];
		userName = buffer.split("&")[1];

		System.out.println(roomNumber + ": ���ȣ");
		System.out.println(userName + ": �����̸�");

		// ���ȣ�� �������� �� �޾ƿ´�.
		Chat chat = new Chat();
		chat.setRoomNumber(roomNumber);
		System.out.println(chat);
		System.out.println(chat.getRoomNumber());
		chatList = chattingServiceImpl.search_chat(chat);
		System.out.println(chatList + ": �Դϴ�.");

		int idx = rls.size(); // ���� ����� �����Ѵ�.
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

		if (flag) { // �����ϴ� ���̶�� ���Ǹ� �߰��Ѵ�.
			HashMap<String, Object> map = rls.get(idx);
			map.put(session.getId(), session);
		} else { // ���� �����ϴ� ���̶�� ���ȣ�� ������ �߰��Ѵ�.
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("roomNumber", roomNumber);
			map.put(session.getId(), session);
			rls.add(map);
		}

		// �������� ���� �޽����� ������. list ũ�� ��ŭ ������.
		for (int i = 0; i < chatList.size(); i++) {

			String content = chatList.get(i).getContent();
			String userDBName = chatList.get(i).getUserName();
			System.out.println(i);
			// ���ǵ���� ������ �߱޹��� ����ID���� �޽����� �߼��Ѵ�.
			JSONObject obj = new JSONObject();
			System.out.println(session.getId());
			System.out.println(session);
			obj.put("type", "getId");
			//obj.put(session.getId(),session); // Ȱ��ȭ�ϸ� ���ΰ�ħ �� ������ ����Ǹ鼭 ���� ���� ���� ������ �ִ� obj���� ���ŵǾ�����.
			obj.put("sessionId", userName); // ���� ���̵���. // ���� Ȱ��ȭ�ϸ� ���ǰ� ���õ� obj���� ���ŵǸ鼭 �� �÷��� js ���ǹ��� ������ ���ǿ��� �ǵ�ġ ���� ����� ���Ծ���.
			obj.put("userName", userDBName);
			obj.put("msg", content);
			session.sendMessage(new TextMessage(obj.toJSONString()));
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("��������");
		
		
		// ���� ����
		if (rls.size() > 0) { // ������ ����Ǹ� �ش� ���ǰ����� ã�Ƽ� �����.
			for (int i = 0; i < rls.size(); i++) {
				rls.get(i).remove(session.getId()); // ���ǰ��� ���õ� obj���� ������.
													// ���� ������ ���ǰ��� ���� �����شٸ� ���� ���ǰ��� ����� �� ������ ���� �� ���ο� �������� ����.
													// obj.put(session.getId(),session);�� session.getId() �Ӽ�����
													// map�� session.getId() �Ӽ�����  ���Ͽ����� ���� ��ġ�ϴ� ���ǰ��� ���� obj�� ������.
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