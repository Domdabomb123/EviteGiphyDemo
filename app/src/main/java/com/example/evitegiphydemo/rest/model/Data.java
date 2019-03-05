package com.example.evitegiphydemo.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class Data {
  @SerializedName("images") @Expose private Images images;

  public Data() {
  }

  public Data(final Images images) {
    this.images = images;
  }

  public Images getImages() {
    return images;
  }

  public void setImages(final Images images) {
    this.images = images;
  }
}
