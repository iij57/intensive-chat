package com.sk.intensive.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sk.intensive.entity.ChatMemberEntity;

public interface ChatMemberRepository extends CrudRepository<ChatMemberEntity, Long>{
	
	List<ChatMemberEntity> findByChatRoomId(long chatRoomId);
	
	ChatMemberEntity findByChatRoomIdAndUserId(long chatRoomId, String userId);

}
