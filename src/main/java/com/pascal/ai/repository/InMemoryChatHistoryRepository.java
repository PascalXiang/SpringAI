package com.pascal.ai.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryChatHistoryRepository implements ChatHistoryRepository {
    private final Map<String, List<String>> chatIdMap = new ConcurrentHashMap<>();
    @Override
    public void save(String type, String chatId) {
        List<String> chatIds = chatIdMap.computeIfAbsent(type, v -> new ArrayList<>());
        if (chatIds.contains(chatId)) {
            return;
        }
        chatIds.add(chatId);

    }

    @Override
    public List<String> getChatIdList(String type) {
        return chatIdMap.getOrDefault(type, new ArrayList<>());
    }
}
