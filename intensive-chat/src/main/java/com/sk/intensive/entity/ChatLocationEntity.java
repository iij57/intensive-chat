package com.sk.intensive.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CHATLOCATION")
@Getter
@Setter
public class ChatLocationEntity {
	
    @Id
    @GeneratedValue
    @Column(name = "LOCATION_SEQ", nullable = false, unique = true)
    private long locationSeq;
    
    @Column(name = "CHATROOM_ID", nullable = false)
    private long chatRoomId;
    
    @Column(name = "ADDR_ID", nullable = false)
    private long addrId;
    
    @Column(name = "REGIST_DT", nullable = false)
    private LocalDateTime registDt;
    
    @PrePersist
    private void prePersist() {
        this.registDt = LocalDateTime.now();
    }

}
