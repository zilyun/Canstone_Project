package com.project.homepage.chat.service;

import java.util.List;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.project.homepage.chat.db.ChattingDAO;
import com.project.homepage.chat.vo.Chat;
import com.project.homepage.chat.vo.Room;

@Service
public class ChattingServiceImpl implements ChattingService {

	@Inject
	ChattingDAO chat;

	List<Room> chatRoomList;
	List<Chat> chatList;

	@Override
	public int create_chat(Chat chat_vo) throws Exception {

		chat.create_chat(chat_vo);

		return 1;
	}

	@Override
	public int create_room(Room room) throws Exception {

		chat.create_chatting_room(room);

		return 1;
	}

	@Override
	public List<Chat> search_chat(Chat chat_vo) throws Exception {
		
		chatList = chat.search_chat(chat_vo);
		
		return chatList;
	}

	@Override
	public List<Room> search_room(Room room) throws Exception {

		chatRoomList = chat.search_room(room);

		return chatRoomList;
	}
	
	@Override
	public List<Room> search_roomUser(Room room) throws Exception {

		chatRoomList = chat.search_roomUser(room);

		return chatRoomList;
	}

}
