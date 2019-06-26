package com.sk.intensive.repository;

import org.springframework.data.repository.CrudRepository;

import com.sk.intensive.entity.ChatLocationEntity;

public interface ChatLocationRepository extends CrudRepository<ChatLocationEntity, Long>{
	
	Iterable<ChatLocationEntity> findByChatRoomId(long chatRoomId);

}
