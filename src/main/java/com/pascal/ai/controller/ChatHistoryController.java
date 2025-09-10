package com.pascal.ai.controller;

import com.pascal.ai.entity.vo.MessageVo;
import com.pascal.ai.repository.ChatHistoryRepository;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ai/history")
@RequiredArgsConstructor
public class ChatHistoryController {

    private final ChatHistoryRepository chatHistoryRepository;

    private final ChatMemory chatMemory;

    @GetMapping("/{type}")
    @ApiResponse(responseCode = "200", description = "获取会话id列表")
    public List<String> getChatIdList(@PathVariable("type") String type) {
        return chatHistoryRepository.getChatIdList(type);
    }

    @ApiResponse(responseCode = "200", description = "查询会话历史详情")
    @GetMapping("/{type}/{chatId}")
    public List<MessageVo> getChatHistory(@PathVariable("type") String type, @PathVariable("chatId") String chatId) {
        List<Message> messages = chatMemory.get(chatId);
        if (CollectionUtils.isEmpty(messages)) {
            return new ArrayList<>();
        }
        return messages.stream().map(MessageVo::new).collect(Collectors.toList());

    }


}
