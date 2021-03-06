package com.example.evitegiphydemo.di.module;


import android.app.Application;
import android.content.Context;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class AppModule {
  private Application application;

  public AppModule(final Application application) {
    this.application = application;
  }

  @Provides @Singleton
  Application provideApplication() {
    return application;
  }

  @Provides @Singleton
  Context provideApplicationContext() {
    return application.getApplicationContext();
  }
}
