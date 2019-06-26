package com.sk.intensive.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.intensive.dto.ChatRoomDTO;
import com.sk.intensive.dto.ChatRoomRequestDTO;
import com.sk.intensive.dto.ChatRoomResponseDTO;
import com.sk.intensive.entity.ChatLocationEntity;
import com.sk.intensive.entity.ChatMemberEntity;
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
			
			List<ChatMemberEntity> members = chatMemberRepository.findByChatRoomId(chatroom.getChatRoomId());
			
			ChatRoomResponseDTO returnDto = new ChatRoomResponseDTO(chatroom.getChatRoomId(), chatroom.getChatRoomName(), chatroom.getStatus().toString(), locations, members);
			
			returnValue.add(returnDto);
			
		}
		
		return returnValue;
		
		
	}
	
	public ChatRoomResponseDTO getChatRoomsByChatRoomId(long chatRoomId){
		
		ChatRoomEntity chatrooms = chatRoomRepository.findByChatRoomId(chatRoomId);
			
		List<ChatLocationEntity> locations = chatLocationRepository.findByChatRoomId(chatrooms.getChatRoomId());
			
		List<ChatMemberEntity> members = chatMemberRepository.findByChatRoomId(chatrooms.getChatRoomId());
			
		
		return new ChatRoomResponseDTO(chatrooms.getChatRoomId(), chatrooms.getChatRoomName(), chatrooms.getStatus().toString(), locations, members);
		
	}
	
	public void enterChatRoom(ChatRoomRequestDTO chatRoomRequestDTO) {
		
		ChatMemberEntity chatMemberEntity = new ChatMemberEntity();
		chatMemberEntity.setChatRoomId(chatRoomRequestDTO.getChatRoomId());
		chatMemberEntity.setUserId(chatRoomRequestDTO.getUserId());
		
		chatMemberRepository.save(chatMemberEntity);
	}
	
	public void exitChatRoom(ChatRoomRequestDTO chatRoomRequestDTO) {
		
		ChatMemberEntity chatMemberEntity = chatMemberRepository.findByChatRoomIdAndUserId(chatRoomRequestDTO.getChatRoomId(), chatRoomRequestDTO.getUserId());
		
		chatMemberRepository.delete(chatMemberEntity);
	}
	

}
