package com.example.finaltest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private View drawerView;
    private Button button_home, button_gallery, button_halla, button_close, button_university, button_ice, button_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerView = (View)findViewById(R.id.drawer);

        drawerLayout.setDrawerListener(listener);
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        VideoView videoview = (VideoView) this.findViewById(R.id.videoview);
        videoview.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.qq));
        videoview.start();
        MediaController mc = new MediaController(this);
        mc.setAnchorView(videoview);
        videoview.setMediaController(mc);

        inputButton();
    }

    OnClickListener click = new OnClickListener() {
        public void onClick(View v) {
            if(v == button_home) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
            else if(v == button_gallery) {
                Intent intent = new Intent(MainActivity.this, GalleryActivity.class);
                startActivity(intent);
            }
            else if(v == button_halla) {
                if (button_university.getVisibility() == View.GONE) {
                    button_university.setVisibility(View.VISIBLE);
                    button_ice.setVisibility(View.VISIBLE);
                    button_map.setVisibility(View.VISIBLE);
                }
                else if (button_university.getVisibility() == View.VISIBLE) {
                    button_university.setVisibility(View.GONE);
                    button_ice.setVisibility(View.GONE);
                    button_map.setVisibility(View.GONE);
                }
            }
            else if(v == button_map) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
            else if(v == button_close) {
                drawerLayout.closeDrawers();
            }
        }
    };

    public void inputButton() {
        button_home = (Button)findViewById(R.id.btn_home);
        button_gallery = (Button)findViewById(R.id.btn_gallery);
        button_halla = (Button)findViewById(R.id.btn_halla);
        button_close = (Button)findViewById(R.id.btn_close);
        button_university = (Button)findViewById(R.id.btn_university);
        button_ice = (Button)findViewById(R.id.btn_ice);
        button_map = (Button)findViewById(R.id.btn_map);

        button_home.setOnClickListener(click);
        button_gallery.setOnClickListener(click);
        button_halla.setOnClickListener(click);
        button_close.setOnClickListener(click);
        button_university.setOnClickListener(click);
        button_ice.setOnClickListener(click);
        button_map.setOnClickListener(click);
    }

    public DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };

}