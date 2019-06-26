package com.sk.intensive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sk.intensive.BroadCastConfig;
import com.sk.intensive.dto.ChatMessageDTO;
import com.sk.intensive.dto.ChatRoomDTO;
import com.sk.intensive.dto.ChatRoomRequestDTO;
import com.sk.intensive.dto.ChatRoomResponseDTO;
import com.sk.intensive.service.ChatService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	
    @Autowired
    private BroadCastConfig broadcast;
	
	
    @PostMapping("/v1/chatrooms")
    public long createChatRoom(@RequestBody ChatRoomDTO chatroom) {
    	
        return chatService.createChatRoom(chatroom);
    }
    
    @GetMapping("/v1/chatrooms")
    public List<ChatRoomResponseDTO> getChatRooms(){
    	
    	return chatService.getChatRooms();
    }
    
    @GetMapping("/v1/chatrooms/{chatRoomId}")
    public ChatRoomResponseDTO getChatRoomsByChatRoomId(@PathVariable("chatRoomId") String chatRoomId){
    	
    	return chatService.getChatRoomsByChatRoomId(Long.parseLong(chatRoomId));
    }
    
    @DeleteMapping("/v1/chatRooms/{chatRoomId}")
    public void deleteChatRoom(@PathVariable("chatRoomId") String chatRoomId) {
    	
        chatService.deleteChatRoom(chatRoomId);
    }
    
    @PostMapping("/v1/chat")
    public void enterChatRoom(@RequestBody ChatRoomRequestDTO chatroom) {
    	
        chatService.enterChatRoom(chatroom);
    }
    
    @DeleteMapping("/v1/chat")
    public void exitChatRoom(@RequestBody ChatRoomRequestDTO chatroom) {
    	
        chatService.exitChatRoom(chatroom);
    }
    
    @PostMapping("/v1/chatmessages")
    public void sendChatMessages(@RequestBody ChatMessageDTO chatMessage) {
    	
        broadcast.sendChatMessages(chatMessage);
    }
    
    

}
