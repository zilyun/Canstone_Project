package com.project.homepage.chat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.homepage.chat.service.ChattingServiceImpl;
import com.project.homepage.chat.vo.Room;

@Controller
public class ChatController {
	
	@Autowired
	ChattingServiceImpl chattingServiceImpl;

	List<Room> roomList;
	static int roomNumber = 0;

	@RequestMapping("/chat")
	public ModelAndView chat() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("chat");

		return mv;
	}
	
	

	/**
	 * 방 페이지
	 * 
	 * @return
	 */
	@RequestMapping("/room")
	public ModelAndView room() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("room");
		return mv;
	}
	
	/**
	 * 방 생성하기
	 * 
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/createRoom")
	public @ResponseBody List<Room> createRoom(@RequestParam HashMap<Object, Object> params) throws Exception {
		String roomName = (String) params.get("roomName");
		String sessionId = (String) params.get("sessionId");
		String description = (String) params.get("description");
		
		Room room_empty = new Room();
		roomList=chattingServiceImpl.search_room(room_empty);
		
		if (roomName != null && !roomName.trim().equals("")) {
			Room room = new Room();
			room.setRoomNumber(++roomNumber);
			room.setRoomName(roomName);
			room.setSessionId(sessionId);
			room.setDescription(description);
			// 인설트 로직
			chattingServiceImpl.create_room(room);
			// 기존에 등록된 방을 SELECT 문으로 불러  LIST를 채움.
			roomList=chattingServiceImpl.search_room(room);
		}
		
		return roomList;
	}
	
	/**
	 * 방 정보가져오기
	 * 
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/getRoom")
	public @ResponseBody List<Room> getRoom(@RequestParam HashMap<Object, Object> params) throws Exception {
		Room room_empty = new Room();
		roomList=chattingServiceImpl.search_room(room_empty);
		return roomList;
	}
	
	@RequestMapping("/getRoomUser")
	public @ResponseBody List<Room> getRoomUser(@RequestParam HashMap<String, String> params) throws Exception {

		String sessionId = params.get("ID");

		Room room_user = new Room();
		room_user.setSessionId(sessionId);
		roomList=chattingServiceImpl.search_roomUser(room_user);
		return roomList;
	}

	/*
	 * 
	 * 채팅방
	 * 
	 * @return
	 */
	@RequestMapping("/moveChating")
	public ModelAndView chating(@RequestParam HashMap<Object, Object> params) {
		ModelAndView mv = new ModelAndView();
		int roomNumber = Integer.parseInt((String) params.get("roomNumber"));

		List<Room> new_list = roomList.stream().filter(o -> o.getRoomNumber() == roomNumber)
				.collect(Collectors.toList());
		if (new_list != null && new_list.size() > 0) {
			mv.addObject("roomName", params.get("roomName"));
			mv.addObject("roomNumber", params.get("roomNumber"));
			mv.setViewName("chat");
		} else {
			mv.setViewName("room");
		}
		return mv;
	}
}