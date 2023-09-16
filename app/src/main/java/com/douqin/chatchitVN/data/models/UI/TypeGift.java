package com.douqin.chatchitVN.data.models.UI;

public enum TypeGift {
    TINY_MP4("tinymp4"),
    MEDIUM_GIF("mediumgif"),
    TINY_WEBM("tinywebm"),
    NANO_MP4("nanomp4"),
    GIF("gif"),
    WEBM("webm"),
    NANO_GIF_PREVIEW("nanogifpreview"),
    TINY_GIF_PREVIEW("tinygifpreview"),
    GIF_PREVIEW("gifpreview"),
    NANO_GIF("nanogif"),
    MP4("mp4"),
    LOOPED_MP4("loopedmp4"),
    NANO_WEBM("nanowebm"),
    TINY_GIF("tinygif");

    private final String value;

    TypeGift(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
