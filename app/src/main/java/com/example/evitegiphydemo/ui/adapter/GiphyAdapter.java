package com.example.evitegiphydemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.evitegiphydemo.App;
import com.example.evitegiphydemo.R;
import com.example.evitegiphydemo.rx.event.PreviewImageEvent;
import com.example.evitegiphydemo.ui.adapter.model.GiphyImageInfo;
import com.example.evitegiphydemo.ui.fragment.MainFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifDrawableBuilder;
import pl.droidsonroids.gif.GifImageView;

public final class GiphyAdapter extends RecyclerView.Adapter<GiphyAdapter.GiphyAdapterViewHolder> {
  static final String TAG = MainFragment.class.getSimpleName();
  private static final int GIF_IMAGE_HEIGHT_PIXELS = 128;
  private static final int GIF_IMAGE_WIDTH_PIXELS = GIF_IMAGE_HEIGHT_PIXELS;
  private List<GiphyImageInfo> data = new ArrayList<>();

  @Override
  public GiphyAdapterViewHolder onCreateViewHolder(final ViewGroup parent, final int position) {
    return new GiphyAdapterViewHolder(LayoutInflater.from(parent.getContext())
                                                    .inflate(R.layout.recyclerview_list_item, parent, false));
  }

  @Override
  public void onBindViewHolder(final GiphyAdapterViewHolder holder, final int position) {
    final Context context = holder.gifImageView.getContext();
    final GiphyImageInfo model = getItem(position);

    Glide.with(context)
         .load(model.getUrl())
         .asGif()
         .toBytes()
         .thumbnail(0.1f)
         .override(GIF_IMAGE_WIDTH_PIXELS, GIF_IMAGE_HEIGHT_PIXELS)
         .diskCacheStrategy(DiskCacheStrategy.ALL)
         .error(R.drawable.icon)
         .into(new SimpleTarget<byte[]>() {
           @Override
           public void onResourceReady(final byte[] resource,
                                       final GlideAnimation<? super byte[]> glideAnimation) {
             // Load gif
             final GifDrawable gifDrawable;
             try {
               gifDrawable = new GifDrawableBuilder().from(resource).build();
               holder.gifImageView.setImageDrawable(gifDrawable);
             } catch (final IOException e) {
             }
             holder.gifImageView.setVisibility(View.VISIBLE);

             // Turn off progressbar
             holder.progressBar.setVisibility(View.INVISIBLE);
             if (Log.isLoggable(TAG, Log.INFO)) {
               Log.i(TAG, "finished loading\t" + model);
             }
           }
         });

    holder.itemView.setOnClickListener(view -> App.get(context).getBus().send(new PreviewImageEvent(model)));
  }

  @Override
  public void onViewRecycled(final GiphyAdapterViewHolder holder) {
    super.onViewRecycled(holder);

    Glide.clear(holder.gifImageView);
    holder.gifImageView.setImageDrawable(null);
  }

  class GiphyAdapterViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.gif_progress)
    ProgressBar progressBar;
    @BindView(R.id.gif_image) GifImageView gifImageView;

    GiphyAdapterViewHolder(final View view) {
      super(view);

      ButterKnife.bind(this, view);
    }
  }

  @Override
  public int getItemCount() {
    return data.size();
  }

  public List<GiphyImageInfo> getList() {
    return data;
  }

  public GiphyImageInfo getItem(final int location) {
    return data.get(location);
  }

  public int getLocation(final GiphyImageInfo object) {
    return data.indexOf(object);
  }

  public void clear() {
    int size = data.size();
    if (size > 0) {
      for (int i = 0; i < size; i++) {
        data.remove(0);
      }

      notifyItemRangeRemoved(0, size);
    }
  }

  public boolean add(final GiphyImageInfo object) {
    final boolean added = data.add(object);
    notifyItemInserted(data.size() + 1);
    return added;
  }

  public boolean addAll(final List<GiphyImageInfo> collection) {
    final boolean added = data.addAll(collection);
    notifyItemRangeInserted(0, data.size() + 1);
    return added;
  }

  public void add(final int location, final GiphyImageInfo object) {
    data.add(location, object);
    notifyItemInserted(location);
  }

  public boolean remove(final int location, final GiphyImageInfo object) {
    final boolean removed = data.remove(object);
    notifyItemRangeRemoved(location, data.size());
    return removed;
  }

  public boolean remove(final GiphyImageInfo object) {
    final int location = getLocation(object);
    final boolean removed = data.remove(object);
    notifyItemRemoved(location);
    return removed;
  }

  public GiphyImageInfo remove(final int location) {
    final GiphyImageInfo removedObject = data.remove(location);
    notifyItemRemoved(location);
    notifyItemRangeChanged(location, data.size());
    return removedObject;
  }
}
