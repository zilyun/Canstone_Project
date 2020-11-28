package com.project.homepage.chat.service;

import java.util.List;

import com.project.homepage.chat.vo.Chat;
import com.project.homepage.chat.vo.Room;

public interface ChattingService {

	public int create_room(Room room) throws Exception;
	
	public List<Room> search_room(Room room) throws Exception;
	
	public List<Room> search_roomUser(Room room) throws Exception;
	
	public int create_chat(Chat chat) throws Exception;
	
	public List<Chat> search_chat(Chat chat) throws Exception;
}
