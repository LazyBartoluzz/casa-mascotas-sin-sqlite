package com.lostomates.mascotas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private MascotasPagerAdapter pages;

    @Override protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayShowTitleEnabled(false);
        ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
        pages = new MascotasPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pages);
        pager.setOffscreenPageLimit(2);
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(pager);
    }

    public void refrescarFavoritos() {
        if (pages != null) pages.refrescarFavoritos();
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_star) { startActivity(new Intent(this, UltimasCincoActivity.class)); return true; }
        if (id == R.id.action_contacto) { startActivity(new Intent(this, ContactoActivity.class)); return true; }
        if (id == R.id.action_acerca) { startActivity(new Intent(this, AcercaDeActivity.class)); return true; }
        return super.onOptionsItemSelected(item);
    }
}
