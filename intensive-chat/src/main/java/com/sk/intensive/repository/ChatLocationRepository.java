package com.sk.intensive.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sk.intensive.entity.ChatLocationEntity;

public interface ChatLocationRepository extends CrudRepository<ChatLocationEntity, Long>{
	
	List<ChatLocationEntity> findByChatRoomId(long chatRoomId);

}
