package com.example.evitegiphydemo.ui.adapter.model;

public final class GiphyImageInfo {

  private String url;

  public String getUrl() {
    return url;
  }

  public void setUrl(final String url) {
    this.url = url;
  }

  public GiphyImageInfo withUrl(String url) {
    this.url = url;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    final GiphyImageInfo that = (GiphyImageInfo) o;

    return url != null ? url.equals(that.url) : that.url == null;

  }

  @Override
  public int hashCode() {
    return url != null ? url.hashCode() : 0;
  }
}
