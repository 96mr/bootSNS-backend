package com.spring.boot.reply.repository;

import java.util.List;

import com.spring.boot.entity.Reply;

public interface ReplyRepositoryCustom {
	List<Reply> list(long board);
}
