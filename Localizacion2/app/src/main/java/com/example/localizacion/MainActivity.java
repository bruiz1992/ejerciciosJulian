package com.example.localizacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity
        implements OnMapReadyCallback {
    private UiSettings mUiSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// Obtenemos el mapa de forma asíncrona (notificará cuando esté listo)
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);

    }
    @Override public void onMapReady(GoogleMap googleMap) {

        GoogleMap mapa = googleMap;
        LatLng IES = new LatLng(41.6730986, -0.8294622);
        mapa.addMarker(new MarkerOptions().position(IES).title("Marker IES "));
        mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(IES, 13.0f));
        mUiSettings = mapa.getUiSettings();
        mapa.setMyLocationEnabled(true);
        mapa.setOnMyLocationButtonClickListener(this::onMyLocationButtonClick);
        mapa.setOnMyLocationClickListener(this::onMyLocationClick);
        mUiSettings.setZoomControlsEnabled(true);
    }
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG)
                .show();
    }

    //@Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT)
                .show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }


}