package com.example.evitegiphydemo.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.evitegiphydemo.R;
import com.example.evitegiphydemo.rest.service.GiphyService;
import com.example.evitegiphydemo.ui.fragment.MainFragment;

import java.security.acl.Group;
import java.util.concurrent.RejectedExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class MainActivity extends AppCompatActivity {
  @BindView(R.id.tool_bar) Toolbar toolbar;

  private DrawerLayout drawerLayout;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);

    // Bind views
    ButterKnife.bind(this);

    // Setup Toolbar
    toolbar.setTitle("Dom Giphy Demo");
    setSupportActionBar(toolbar);

    ActionBar actionbar = getSupportActionBar();
    actionbar.setDisplayHomeAsUpEnabled(true);
    actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

    // Use Fragments
    if (savedInstanceState == null) {
      getSupportFragmentManager()
          .beginTransaction()
          .replace(R.id.content_frame, new MainFragment(), MainFragment.class.getSimpleName())
          .commit();
    }

    drawerLayout = findViewById(R.id.drawer_layout);

    NavigationView navigationView = findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(
      new NavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(MenuItem menuItem) {
        if(menuItem.getTitle().toString().equals("Email Me")){
          final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
          emailIntent.setType("plain/text");
          emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"dschu215@gmail.com"});
          emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Giphy Demo App");
          startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        }else if(menuItem.getTitle().toString().equals("Call Me")){
          Intent callIntent = new Intent(Intent.ACTION_DIAL);
          callIntent.setData(Uri.parse("tel:9208196433"));
          startActivity(callIntent);
        }else if(menuItem.getTitle().toString().equals("LinkedIn")){
          Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/dominic-schumerth-36658265"));
          startActivity(browserIntent);
        }else if(menuItem.getTitle().toString().equals("GitHub")){
          Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Domdabomb123"));
          startActivity(browserIntent);
        }else if(menuItem.getTitle().toString().equals("Facebook")){
          Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/dominic.schumerth"));
          startActivity(browserIntent);
        }else if(menuItem.getTitle().toString().equals("Youtube")){
          Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UC7229mIX5ZaF_pwA3cxTWYQ"));
          startActivity(browserIntent);
        }else if(menuItem.getTitle().toString().equals("Soundcloud")){
          Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://soundcloud.com/dominic-schumerth"));
          startActivity(browserIntent);
        }
        drawerLayout.closeDrawers();
        return true;
      }
    });
  }
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        drawerLayout.openDrawer(GravityCompat.START);
        return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
