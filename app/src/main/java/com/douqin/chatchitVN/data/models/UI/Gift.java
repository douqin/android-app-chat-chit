package com.douqin.chatchitVN.data.models.UI;

import java.util.List;
import java.util.Map;

public class Gift {

    public String id;
    public String title;
    public Map<String, GiftFormat> media_formats;
    public double created;
    public String content_description;
    public String itemUrl;
    public String url;
    public List<String> tags;
    public List<String> flags;
    public boolean hasaudio;
}
