package com.example.evitegiphydemo.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class Images {
  @SerializedName("fixed_height") @Expose private FixedHeight fixedHeight;

  public Images() {
  }

  public Images(final FixedHeight fixedHeight) {
    this.fixedHeight = fixedHeight;
  }

  public FixedHeight getFixedHeight() {
    return fixedHeight;
  }

  public void setFixedHeight(final FixedHeight fixedHeight) {
    this.fixedHeight = fixedHeight;
  }
}
