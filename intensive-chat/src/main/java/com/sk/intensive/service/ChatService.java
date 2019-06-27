package com.sk.intensive.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
		// RestTemplate 에 MessageConverter 세팅
	    List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
	    converters.add(new FormHttpMessageConverter());
	    converters.add(new StringHttpMessageConverter());
	 
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.setMessageConverters(converters);
	    
	    String geox = chatRoomDTO.getWgs84_x();
	    String geoy = chatRoomDTO.getWgs84_y();
	    
	    String result = restTemplate.getForObject("http://13.124.20.178:8082/v1/locations/{geo}/", String.class, geox+","+geoy);
	    
	    System.out.println(result);
	    
	    ObjectMapper mapper = new ObjectMapper();
	    List<Map<String, Object>> data;
		try {
			data = mapper.readValue(result, new TypeReference<List<Map<String, Object>>>(){});
			chatLocationEntity.setAddrId(Long.parseLong(data.get(0).get("addrId").toString()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
		
		chatLocationRepository.save(chatLocationEntity);
		
		return chatRoomEntity.getChatRoomId();
		
	}
	
	public List<ChatRoomResponseDTO> getChatRooms(){
		
		List<ChatRoomResponseDTO> returnValue = new ArrayList<>();
		
		List<ChatRoomEntity> chatrooms = chatRoomRepository.findAll();
		
		for(ChatRoomEntity chatroom : chatrooms) {
			
			Iterable<ChatLocationEntity> locations = chatLocationRepository.findByChatRoomId(chatroom.getChatRoomId());
			
			Iterable<ChatMemberEntity> members = chatMemberRepository.findByChatRoomId(chatroom.getChatRoomId());
			
			ChatRoomResponseDTO returnDto = new ChatRoomResponseDTO(chatroom.getChatRoomId(), chatroom.getChatRoomName(), chatroom.getStatus().toString(), locations, members);
			
			returnValue.add(returnDto);
			
		}
		
		return returnValue;
		
		
	}
	
	public ChatRoomResponseDTO getChatRoomsByChatRoomId(long chatRoomId){
		
		ChatRoomEntity chatrooms = chatRoomRepository.findByChatRoomId(chatRoomId);
			
		Iterable<ChatLocationEntity> locations = chatLocationRepository.findByChatRoomId(chatrooms.getChatRoomId());
			
		Iterable<ChatMemberEntity> members = chatMemberRepository.findByChatRoomId(chatrooms.getChatRoomId());
			
		
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
	
	public void deleteChatRoom(String chatRoomId) {
		
		Iterable<ChatMemberEntity> chatmember = chatMemberRepository.findByChatRoomId(Long.parseLong(chatRoomId));
		
		chatMemberRepository.deleteAll(chatmember);
		
		Iterable<ChatLocationEntity> locations = chatLocationRepository.findByChatRoomId(Long.parseLong(chatRoomId));
		
		chatLocationRepository.deleteAll(locations);
		
		chatRoomRepository.deleteById(Long.parseLong(chatRoomId));
		
	}
	
	
	private String getJSONData(String apiURL) throws Exception {
		String jsonString = new String();
		String buf;
		URL url = new URL(apiURL);
		URLConnection conn = url.openConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				conn.getInputStream(), "UTF-8"));
		while ((buf = br.readLine()) != null) {
			jsonString += buf;
		}
		return jsonString;
	}
	

}
