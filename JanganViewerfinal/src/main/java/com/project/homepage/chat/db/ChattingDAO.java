package com.project.homepage.chat.db;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.homepage.chat.vo.Chat;
import com.project.homepage.chat.vo.Room;

@Repository
public class ChattingDAO {
	
	@Autowired
	SqlSession sqlSession = null;
	
	public int create_chat(Chat chat) throws Exception {
		return sqlSession.insert("chat_content.chat_create",chat);
	}
	
	public int create_chatting_room(Room room) throws Exception {
		return sqlSession.insert("chat.room_create",room);
	}
	
	public List<Room> search_room(Room room) throws Exception {
		return sqlSession.selectList("chat.search_room");	
	}
	
	public List<Room> search_roomUser(Room room) throws Exception {
		return sqlSession.selectList("chat.search_roomUser",room);	
	}
	
	public List<Chat> search_chat(Chat chat) throws Exception {
		return sqlSession.selectList("chat_content.search_chat",chat);
	}
}
