package com.sk.intensive.dto;

import java.util.List;

import com.sk.intensive.entity.ChatLocationEntity;
import com.sk.intensive.entity.ChatMemberEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ChatRoomResponseDTO {
	
	private long chatRoomId;
	private String chatRoomName;
	private String chatRoomStatus;
	private List<ChatLocationEntity> chatLocationEntity;
	private List<ChatMemberEntity> chatMember;
	
}
