package com.pascal.ai.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatHistoryRepository {

    /**
     * 保存会话记录
     * @param type 业务类型 chat、service、pdf等
     * @param chatId 会话id
     */
    void save(String type, String chatId);

    /**
     * 获取会话id列表
     * @param type 业务类型
     * @return 会话id列表
     */
    List<String> getChatIdList(String type);
}
