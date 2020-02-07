package com.plot.ariwasch.adopt_a_plot;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.PolylineOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import java.util.ArrayList;
import java.util.List;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;

public class FullScreenPlot extends AppCompatActivity implements OnMapReadyCallback, PermissionsListener {
    private MapView mapView;
    ArrayList lats;
    ArrayList longs;
    CreatePlot createPlot;
    String[] plots;
    Button in;
    Button out;
    Button userLoc;
    Button pin;
    ArrayList<Coords> coordinates;
    private PermissionsManager permissionsManager;
    private MapboxMap mapboxMap;
    private int theZoom;
    LatLng thePosition;
    static boolean locationP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1IjoiYXJpd2FzY2giLCJhIjoiY2pxMHk2bndzMHJ5aDQybno3Z2h6emQ0bSJ9.vg9q3OKDkXN8GV-HdttcYw");
        setContentView(R.layout.activity_full_screen_plot);
        CreatePlot.saving = this.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        mapView = (MapView) findViewById(R.id.mapView2);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        theZoom = 16;
        in = (Button) findViewById(R.id.button11);
        out =  (Button) findViewById(R.id.button13);
        userLoc = (Button) findViewById(R.id.button14);
        pin = (Button) findViewById(R.id.button15);

        lats = new ArrayList<String>();
        longs = new ArrayList<String>();
        createPlot = new CreatePlot();
        plots = WhichPlot.getPlots();
        coordinates = WhichPlot.getCoords();
        addAnnotations();
        in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theZoom++;
                CameraPosition position = new CameraPosition.Builder()
                        .target(mapboxMap.getCameraPosition().target) // Sets the new camera position
                        .zoom(theZoom) // Sets the zoom
//                    .bearing(180) // Rotate the camera
//                    .tilt(30) // Set the camera tilt
                        .build(); // Creates a CameraPosition from the builder
                mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position));
            }
        });
        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theZoom--;
                CameraPosition position = new CameraPosition.Builder()
                        .target(mapboxMap.getCameraPosition().target) // Sets the new camera position
                        .zoom(theZoom) // Sets the zoom
//                    .bearing(180) // Rotate the camera
//                    .tilt(30) // Set the camera tilt
                        .build(); // Creates a CameraPosition from the builder
                mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position));
            }
        });
        userLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LocationComponent locationComponent = mapboxMap.getLocationComponent(); //was this

//                CameraPosition position = new CameraPosition.Builder()
//                        .target(thePosition) // Sets the new camera position
//                        .zoom(theZoom) // Sets the zoom
////                    .bearing(180) // Rotate the camera
////                    .tilt(30) // Set the camera tilt
//                        .build(); // Creates a CameraPosition from the builder
//                mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position));
                locationComponent.setCameraMode(CameraMode.TRACKING);
                locationComponent.setRenderMode(RenderMode.COMPASS);
            }
        });
        pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAnnotations();
            }
        });
    }

    public void addAnnotations() {

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                drawPolygon(mapboxMap);
//                zoomMap = mapboxMap;
            }
        });
    }

    private void drawPolygon(MapboxMap mapboxMap) {
        mapboxMap.clear();
        List<LatLng> polyline = new ArrayList<>();

//        polygon.add(new LatLng(45.522585, -122.685699));
//        polygon.add(new LatLng(45.534611, -122.708873));
//        polyline.add(new LatLng(30, -70));
//        polyline.add(new LatLng(45.547115, -122.667503));
//        polyline.add(new LatLng(45.530643, -122.660121));
//        polygon.add(new LatLng(45.533529, -122.636260));
//        polygon.add(new LatLng(45.521743, -122.659091));
//        polygon.add(new LatLng(45.510677, -122.648792));
//        polygon.add(new LatLng(45.515008, -122.664070));
//        polygon.add(new LatLng(45.502496, -122.669048));
//        polygon.add(new LatLng(45.515369, -122.678489));
//        polygon.add(new LatLng(45.506346, -122.702007));
//        polygon.add(new LatLng(45.522585, -122.685699));
        System.out.println("did itttt");
        lats = updateLats();
        longs = updateLongs();
        System.out.println("workttt " + lats.size() + " " + longs.size());
        CreatePlot.saving = this.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        String selected = CreatePlot.saving.getString("selected plot", "No Plot Selected");
        String plot = CreatePlot.saving.getString("morePlots", "No Plot Selected");
        int integer = CreatePlot.saving.getInt("i", 0);
        if (integer > 0 && MainActivity.textActivity == true) {
            for (int i = 0; i < lats.size(); i++) {
//            System.out.println(Double.parseDouble((String) lats.get(i)) + "," + Double.parseDouble((String) longs.get(i)) + "ttttt");
                polyline.add(new LatLng(Double.parseDouble((String) lats.get(i)), Double.parseDouble((String) longs.get(i))));
                CameraPosition position = new CameraPosition.Builder()
                        .target(new LatLng(Double.parseDouble((String) lats.get(i)), Double.parseDouble((String) longs.get(i)))) // Sets the new camera position
                        .zoom(theZoom) // Sets the zoom
//                    .bearing(180) // Rotate the camera
//                    .tilt(30) // Set the camera tilt
                        .build(); // Creates a CameraPosition from the builder
                mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position));
                thePosition = new LatLng(Double.parseDouble((String) lats.get(i)), Double.parseDouble((String) longs.get(i)));
            }
            mapboxMap.addPolyline(new PolylineOptions()
                    .addAll(polyline).color(Color.parseColor(SelectPlots.colorChooser(selected))).width(4));
        } else if (MainActivity.textActivity == false) {
            mapboxMap.clear();
            ArrayList<LatLng> polygone = new ArrayList<>();
            ArrayList<Coords> dist = new ArrayList<>();

            System.out.println("hi123 " + coordinates.size());
//        System.out.println(coordinates.get(2).getD());
//        System.out.println(coordinates.get(69).getD());

            for (int i = 0; i < coordinates.size(); i++) {
                if (coordinates.get(i).getD().contains(selected)) {
                    System.out.println("idk what? " + selected);
                    polygone.add(new LatLng(coordinates.get(i).getLat(), coordinates.get(i).getLong()));
                    dist.add(coordinates.get(i));
//                LatLng(coordinates.get(i).getLat(), coordinates.get(i).getLong()).d
                }
            }
            //IF POLYLINE
            polygone.add(polygone.get(0));
            //IF POLYLINE
            double totx = 0;
            double toty = 0;
            for (int i = 0; i < dist.size(); i++) {
                totx += dist.get(i).getLat();
                toty += dist.get(i).getLong();

            }
            totx = totx / dist.size();
            toty = toty / dist.size();

            mapboxMap.addPolyline(new PolylineOptions()
                    .addAll(polygone).color(Color.parseColor((SelectPlots.colorChooser(selected))))).setWidth((4));
            CameraPosition position = new CameraPosition.Builder()
                    .target(new LatLng(totx, toty)) // Sets the new camera position
                    .zoom(theZoom) // Sets the zoom
//                    .bearing(180) // Rotate the camera
//                    .tilt(30) // Set the camera tilt
                    .build(); // Cre
            thePosition = new LatLng(totx, toty);
            mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position));

        }

    }

    public ArrayList<String> updateLats() {
        CreatePlot.saving = this.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        int i = CreatePlot.saving.getInt("i", 0);
        System.out.println("hi123 " + i);
        if (i > 0) {
            lats = new ArrayList<String>();
            for (int j = 0; j < i; j++) {
                String index = Integer.toString(j);
                String tempText = CreatePlot.saving.getString(index, "not working");
                String text1 = tempText.substring(0, tempText.indexOf(","));
                String text2 = tempText.substring(tempText.indexOf(",") + 1, tempText.length());
                lats.add(text1);

            }
        }
        return lats;
    }

    public ArrayList<String> updateLongs() {
        int i = CreatePlot.saving.getInt("i", 0);
        for (int j = 0; j < i; j++) {
            String index = Integer.toString(j);
            String tempText = CreatePlot.saving.getString(index, "not working");
            String text1 = tempText.substring(0, tempText.indexOf(","));
            String text2 = tempText.substring(tempText.indexOf(",") + 1, tempText.length());
            longs.add(text2);

        }
        return longs;
    }

    @Override
    @SuppressWarnings( {"MissingPermission"})
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onMapReady(MapboxMap mapboxMap) {
        this.mapboxMap = mapboxMap;
        enableLocationComponent();
    }

    private void enableLocationComponent() {
// Check if permissions are enabled and if not request
        if (PermissionsManager.areLocationPermissionsGranted(this)) {

            LocationComponentOptions options = LocationComponentOptions.builder(this)
                    .trackingGesturesManagement(true)
                    .accuracyColor(ContextCompat.getColor(this, R.color.mapbox_blue))
                    .build();

// Get an instance of the component
            LocationComponent locationComponent = mapboxMap.getLocationComponent();


// Activate with options
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
//                System.out.println("NO PERMISSION @!#");
                return;
            }
            locationComponent.activateLocationComponent(this, options);

// Enable to make component visible
            locationComponent.setLocationComponentEnabled(true);
            thePosition = new LatLng( mapboxMap.getLocationComponent().getLastKnownLocation().getLatitude(),
                    mapboxMap.getLocationComponent().getLastKnownLocation().getLongitude());
// Set the component's camera mode
            locationComponent.setCameraMode(CameraMode.TRACKING);
            locationComponent.setRenderMode(RenderMode.COMPASS);
        } else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
//        Toast.makeText(this, R.string.user_location_permission_explanation, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            enableLocationComponent();
//            locationP = true;
        } else {
//            locationP = false;
//            Toast.makeText(this, R.string.user_location_permission_not_granted, Toast.LENGTH_LONG).show();
//            System.out.println("some toast");
            Intent intent = new Intent(FullScreenPlot.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
