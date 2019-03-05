package com.example.evitegiphydemo.di.module;

import android.app.Application;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class LeakCanaryModule {
  private Application application;

  public LeakCanaryModule(final Application application) {
    this.application = application;
  }

  @Provides @Singleton RefWatcher provideRefWatcher() {
    return LeakCanary.install(application);
  }
}
