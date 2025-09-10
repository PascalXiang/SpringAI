package com.pascal.ai.enums;

import lombok.Getter;

@Getter
public enum ChatEnums {
    CHAT("chat"),
    ;
    private final String type;

    ChatEnums(String type) {
        this.type = type;
    }

    public static ChatEnums getByType(String type) {
        for (ChatEnums chatEnums : ChatEnums.values()) {
            if (chatEnums.getType().equals(type)) {
                return chatEnums;
            }
        }
        return null;
    }

    public static boolean containsType(String type) {
        return getByType(type) != null;
    }
}
