package com.sk.intensive.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CHATROOM")
@Getter
@Setter
public class ChatRoomEntity {

    @Id
    @GeneratedValue
    @Column(name = "CHATROOM_ID", nullable = false, unique = true)
    private long chatRoomId;
    
    @Column(name = "CHATROOM_NAME", length = 200)
    private String chatRoomName;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "CHATROOM_STATUS", length = 1, nullable = false)
    private ChatRoomStatus status;
    
}
