package com.lostomates.mascotas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AcercaDeActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_simple);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.acerca);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        LinearLayout body = (LinearLayout) findViewById(R.id.body);
        ImageView logo = new ImageView(this);
        logo.setImageResource(R.drawable.ic_tomato);
        body.addView(logo, new LinearLayout.LayoutParams(-1, (int) (getResources().getDisplayMetrics().density * 160)));
        TextView info = new TextView(this);
        info.setText("Casa Mascotas Los Tomates\n\nDesarrollador: Equipo Los Tomates\n\nBio: Proyecto académico de desarrollo móvil en Java y XML. Se practica RecyclerView, Adapter, ViewHolder, menús, Fragments, ViewPager y JavaMail.\n\nVersión: 1.0\nCompatible desde Android 4.0.3 (API 15).");
        info.setTextSize(16);
        info.setPadding(10, 20, 10, 10);
        body.addView(info);
    }
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) { finish(); return true; }
        return super.onOptionsItemSelected(item);
    }
}
