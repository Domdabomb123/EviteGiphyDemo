package com.example.evitegiphydemo.di.module;

import com.example.evitegiphydemo.rx.RxBus;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class RxModule {
  @Provides @Singleton RxBus provideRxBus() {
    return new RxBus();
  }
}
