package com.example.evitegiphydemo.di.component;

import android.app.Application;
import android.content.Context;
import com.example.evitegiphydemo.App;
import com.example.evitegiphydemo.di.module.AppModule;
import com.example.evitegiphydemo.di.module.LeakCanaryModule;
import com.example.evitegiphydemo.di.module.RxModule;
import com.example.evitegiphydemo.rx.RxBus;
import com.example.evitegiphydemo.ui.activity.MainActivity;
import com.example.evitegiphydemo.ui.fragment.MainFragment;
import com.squareup.leakcanary.RefWatcher;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {AppModule.class, RxModule.class, LeakCanaryModule.class})
public interface AppComponent {
  void inject(final App app);
  void inject(final MainActivity mainActivity);
  void inject(final MainFragment mainFragment);

  Application application();
  Context context();
  RxBus bus();
  RefWatcher refWatcher();
}
