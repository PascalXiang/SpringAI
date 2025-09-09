package com.pascal.ai.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

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

    @RequestMapping(value = "/chat", produces = "text/html;charset=utf-8")
    public Flux<String> chat(String prompt, String chatId) {
        // 关键的调试日志！
        System.out.println("Received request for chatId: " + chatId + ", with prompt: " + prompt);

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
