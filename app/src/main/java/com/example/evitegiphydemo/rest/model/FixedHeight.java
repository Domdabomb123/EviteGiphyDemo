package com.example.evitegiphydemo.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class FixedHeight {
    @SerializedName("url") @Expose private String url;

    public FixedHeight() {
    }

    public FixedHeight(final String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
