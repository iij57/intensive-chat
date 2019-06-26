package com.sk.intensive.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.intensive.dto.ChatRoomDTO;
import com.sk.intensive.dto.ChatRoomResponseDTO;
import com.sk.intensive.entity.ChatLocationEntity;
import com.sk.intensive.entity.ChatRoomEntity;
import com.sk.intensive.entity.ChatRoomStatus;
import com.sk.intensive.repository.ChatLocationRepository;
import com.sk.intensive.repository.ChatMemberRepository;
import com.sk.intensive.repository.ChatRoomRepository;

@Service
public class ChatService {
	
	@Autowired
	private ChatRoomRepository chatRoomRepository;
	
	@Autowired
	private ChatMemberRepository chatMemberRepository;
	
	@Autowired
	private ChatLocationRepository chatLocationRepository;
	
	public long createChatRoom(ChatRoomDTO chatRoomDTO) {
		
		ChatRoomEntity chatRoomEntity = new ChatRoomEntity();
		
		chatRoomEntity.setChatRoomName(chatRoomDTO.getChatRoomName());
		chatRoomEntity.setStatus(ChatRoomStatus.Y);
		
		chatRoomRepository.save(chatRoomEntity);
		
		ChatLocationEntity chatLocationEntity = new ChatLocationEntity();
		chatLocationEntity.setChatRoomId(chatRoomEntity.getChatRoomId());
		//서비스 완성 되면 호출해서 가져옴 값을 지정
		chatLocationEntity.setAddrId(1);
		
		chatLocationRepository.save(chatLocationEntity);
		
		return chatRoomEntity.getChatRoomId();
		
	}
	
	public List<ChatRoomResponseDTO> getChatRooms(){
		
		List<ChatRoomResponseDTO> returnValue = new ArrayList<>();
		
		List<ChatRoomEntity> chatrooms = chatRoomRepository.findAll();
		
		for(ChatRoomEntity chatroom : chatrooms) {
			
			List<ChatLocationEntity> locations = chatLocationRepository.findByChatRoomId(chatroom.getChatRoomId());
			
			
			ChatRoomResponseDTO returnDto = new ChatRoomResponseDTO(chatroom.getChatRoomId(), chatroom.getChatRoomName(), chatroom.getStatus().toString(), locations);
			
			returnValue.add(returnDto);
			
		}
		
		return returnValue;
		
		
	}

}
