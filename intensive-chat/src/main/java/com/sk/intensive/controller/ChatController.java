package com.sk.intensive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sk.intensive.dto.ChatRoomDTO;
import com.sk.intensive.dto.ChatRoomResponseDTO;
import com.sk.intensive.service.ChatService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	
	
    @PostMapping("/v1/chatrooms")
    public long createChatRoom(@RequestBody ChatRoomDTO chatroom) {
    	
        return chatService.createChatRoom(chatroom);
    }
    
    @GetMapping("/v1/chatrooms")
    public List<ChatRoomResponseDTO> getChatRooms(){
    	
    	return chatService.getChatRooms();
    }

}
