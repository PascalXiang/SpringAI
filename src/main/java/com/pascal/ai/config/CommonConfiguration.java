package com.pascal.ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Pascal
 * @Desc
 * @Create 2025-09-07 23:10
 */
@Configuration
public class CommonConfiguration {

    @Bean
    public ChatClient chatClient(OllamaChatModel model) {
        return ChatClient
                .builder(model)
                .defaultSystem("你是一个来自异世界的魅魔，名字叫宇多田光")
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }
}
