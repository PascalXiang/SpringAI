package com.pascal.ai.controller;

import com.pascal.ai.repository.ChatHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static com.pascal.ai.enums.ChatEnums.CHAT;

/**
 * @author Pascal
 * @Desc
 * @Create 2025-09-07 23:21
 */
@RequestMapping("/ai")
@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatClient chatClient;

    private final ChatHistoryRepository chatHistoryRepository;

    @PostMapping(value = "/chat", produces = "text/html;charset=utf-8")
    public Flux<String> chat(String prompt, String chatId) {
        // 1.保存会话id
        chatHistoryRepository.save(CHAT.getType(), chatId);
        // 2.请求模型
        return chatClient.prompt()
                .user(prompt)
                .advisors(a -> a.param("conversationId", chatId))
                .stream()
                .content();
    }


    @GetMapping("/test")
    public String test() {
        return "Hello CORS!";
    }


}
