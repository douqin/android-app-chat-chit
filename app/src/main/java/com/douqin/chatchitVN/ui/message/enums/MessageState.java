package com.douqin.chatchitVN.ui.message.enums;

public enum MessageState {

    DEFAULT(0),
    DEL_BY_ADMIN(1),
    DEL_BY_OWNER(2),
    SENT(-1),
    SENDING(-2),
    HAS_NOT_BEEN_SENT(-3);

    private final int value;

    MessageState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
