package com.douqin.chatchitVN.network.apis.RemoteData;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class GiftRemoteData {
    public String id;
    public String title;
    public Map<String, GiftFormatRemoteData> media_formats;
    public double created;
    public String content_description;
    @SerializedName("itemurl")
    public String itemUrl;
    public String url;
    public List<String> tags;
    public List<String> flags;
    public boolean hasaudio;

}
