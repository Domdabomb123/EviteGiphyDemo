package com.example.evitegiphydemo;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode.VmPolicy;
import android.support.v7.app.AppCompatDelegate;

import com.squareup.leakcanary.RefWatcher;

import javax.inject.Inject;

import com.example.evitegiphydemo.di.component.AppComponent;
import com.example.evitegiphydemo.di.component.DaggerAppComponent;
import com.example.evitegiphydemo.di.module.AppModule;
import com.example.evitegiphydemo.di.module.LeakCanaryModule;
import com.example.evitegiphydemo.rx.RxBus;

public class App extends Application {
  private AppComponent appComponent;
  @Inject RxBus bus;
  @Inject RefWatcher refWatcher;

  @Override
  public void onCreate() {
    super.onCreate();

    // Let's start paying critical attention to issues via Logcat
    if (BuildConfig.DEBUG) {
      StrictMode.setThreadPolicy(new ThreadPolicy.Builder()
        .detectAll()
        .penaltyLog()
        .build());
      StrictMode.setVmPolicy(new VmPolicy.Builder()
        .detectAll()
        .penaltyLog()
        .build());
    }

    appComponent = DaggerAppComponent.builder()
                                     .appModule(new AppModule(this))
                                     .leakCanaryModule(new LeakCanaryModule(this))
                                     .build();
    appComponent.inject(this);

    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
  }

  public static App get(final Context context) {
    return (App) context.getApplicationContext();
  }

  public static RefWatcher getRefWatcher(final Context context) {
    return ((App) context.getApplicationContext()).refWatcher;
  }

  public AppComponent getAppComponent() {
    return appComponent;
  }

  public RxBus getBus() {
    return bus;
  }
}
