package com.sk.intensive.repository;

import org.springframework.data.repository.CrudRepository;

import com.sk.intensive.entity.ChatMemberEntity;

public interface ChatMemberRepository extends CrudRepository<ChatMemberEntity, Long>{

}
