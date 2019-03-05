package com.example.evitegiphydemo.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public final class GiphyResponse {
  @SerializedName("data") @Expose private List<Data> data = new ArrayList<>();

  public GiphyResponse() {
  }

  public GiphyResponse(final List<Data> data) {
    this.data = data;
  }

  public List<Data> getData() {
    return data;
  }

  public void setData(final List<Data> data) {
    this.data = data;
  }
}
