package com.example.evitegiphydemo.rx.event;

import com.example.evitegiphydemo.ui.adapter.model.GiphyImageInfo;

public final class PreviewImageEvent {
  private final GiphyImageInfo imageInfo;

  public PreviewImageEvent(final GiphyImageInfo imageInfo) {
    this.imageInfo = imageInfo;
  }

  public GiphyImageInfo getImageInfo() {
    return imageInfo;
  }
}
