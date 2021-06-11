package com.example.finaltest;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private DrawerLayout drawerLayout;
    private View drawerView;
    private Button button_home, button_gallery, button_halla, button_close, button_university, button_ice, button_map;
    private FragmentManager fragmentManager;
    private MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerView = (View)findViewById(R.id.drawer);

        drawerLayout.setDrawerListener(listener);
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        fragmentManager = getFragmentManager();
        mapFragment = (MapFragment)fragmentManager.findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);

        inputButton();
    }

    public void onMapReady(GoogleMap googleMap) {
        LatLng location = new LatLng(37.302919, 127.908169);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title("한라대학교");
        markerOptions.snippet("대학교");
        markerOptions.position(location);
        googleMap.addMarker(markerOptions);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 16));
    }

    OnClickListener click = new OnClickListener() {
        public void onClick(View v) {
            if(v == button_home) {
                Intent intent = new Intent(MapsActivity.this, MainActivity.class);
                startActivity(intent);
            }
            else if(v == button_gallery) {
                Intent intent = new Intent(MapsActivity.this, GalleryActivity.class);
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
                Intent intent = new Intent(MapsActivity.this, MapsActivity.class);
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

    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
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