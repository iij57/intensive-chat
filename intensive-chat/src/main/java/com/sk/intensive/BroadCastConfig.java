package com.sk.intensive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.sk.intensive.dto.ChatMessageDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BroadCastConfig {
	
	@Autowired
	private SimpMessagingTemplate brokerMessagingTemplate;
	
	public void sendChatMessages(ChatMessageDTO messageDTO) {
		
		this.brokerMessagingTemplate.convertAndSend("/topic/messages", messageDTO);
		
	}
	
}
