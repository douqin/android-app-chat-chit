package com.douqin.chatchitVN.ui.message.enums;

public enum MessageType {
    TEXT(0),
    IMAGE(1),
    VIDEO(2),
    GIF(4);
    private final int value;

    MessageType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
