package com.sk.intensive.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sk.intensive.entity.ChatRoomEntity;

public interface ChatRoomRepository extends CrudRepository<ChatRoomEntity, Long>{
	
	List<ChatRoomEntity> findAll();
	
	ChatRoomEntity findByChatRoomId(long chatRoomId);
	
}
