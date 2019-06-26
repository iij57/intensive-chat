package com.sk.intensive.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "CHATMEMBER")
@Getter
public class ChatMemberEntity {
	
    @Id
    @GeneratedValue
    @Column(name = "CHATMEMBER_SEQ", nullable = false, unique = true)
    private long chatMemberSeq;

	
    @Column(name = "CHATROOM_ID", nullable = false)
    private long chatRoomId;
    
	@Column(name = "USER_ID", length = 100 , nullable = false)
	private String userId;
    
}
