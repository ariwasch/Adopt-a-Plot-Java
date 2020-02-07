package com.plot.ariwasch.adopt_a_plot;

import android.content.Context;
import android.content.SharedPreferences;
//import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
//import android.app.Activity;
//import android.content.ContentValues;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase; import android.os.Bundle;
//import android.widget.TextView;
//import android.preference.PreferenceManager;
//import android.content.SharedPreferences;
//import com.example.ariwasch.actual_adopt_a_plot.s\S
import java.util.ArrayList;
//import android.content.Intent;
//import android.app.Fragment;
//import android.app.Activity;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.PolylineOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.maps.MapView;
import android.graphics.Color;
//import com.mapbox.mapboxandroiddemo.R;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import java.util.List;
import com.mapbox.mapboxsdk.camera.CameraPosition;

public class CreatePlot extends AppCompatActivity{
    //    Intent intent = new Intent(this, CreatePlot.class);
// intent.putextra("key","value");
//    private GoogleMap mMap;
    private Button enter;
    private Button remove;
    private Button clear;
    private Button save;
    private TextView output;
    private EditText input;
    String text;
    ArrayList outputList;
    ArrayList lats;
    ArrayList longs;
    static ArrayList latitude;
    static ArrayList longitude;
    MapboxMap zoomMap;
    //    private static final String TAG = "CreatePlot";
//    private static final int ERROR_DIALOG_REQUEST = 9001;
//    static final LatLng HAMBURG = new LatLng(53.558, 9.927);
//    static final LatLng KIEL = new LatLng(53.551, 9.993);
    private MapView mapView;
//    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";
    public static SharedPreferences saving;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1IjoiYXJpd2FzY2giLCJhIjoiY2pxMHk2bndzMHJ5aDQybno3Z2h6emQ0bSJ9.vg9q3OKDkXN8GV-HdttcYw");

        setContentView(R.layout.activity_create_plot);
        mapView = (MapView) findViewById(R.id.mapView2);
        mapView.onCreate(savedInstanceState);



        saving = this.getSharedPreferences("Settings", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = saving.edit();
        lats = new ArrayList<String>();
        longs = new ArrayList<String>();
        outputList = new ArrayList<String>();

        int i = saving.getInt("i", 0);
        System.out.println("i123 " + i);

//        SupportMapFragment mapFragment =
//                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);

//        Bundle mapViewBundle = null;
//        if (savedInstanceState != null) {
//            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
//        }
//        mapView = findViewById(R.id.mapView1);
//        mapView.onCreate(savedInstanceState);
//        mapView.getMapAsync(this);


        enter = (Button) findViewById(R.id.button7);
        remove = (Button) findViewById(R.id.button8);
        clear = (Button) findViewById(R.id.button9);
        save = (Button) findViewById(R.id.button10);
        output = (TextView) findViewById(R.id.textView5);
        input = (EditText) findViewById(R.id.editText2);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterFunc();
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFunc();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFunc();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFunc();
            }
        });
        lats.clear();
        longs.clear();
        for (int j = 0; j < i; j++) {
            String index = Integer.toString(j);
            String tempText = saving.getString(index, "not working");
            String text1 = tempText.substring(0, tempText.indexOf(","));
            String text2 = tempText.substring(tempText.indexOf(",") + 1, tempText.length());
            output.append(tempText + "\n");
            lats.add(text1);
            longs.add(text2);
        }
        addAnnotations();
    }
    public void enterFunc() {
        text = input.getText().toString();
        if ((text != "" || text != " ") & text.contains(",")) {
            output.append(text + "\n");
//            System.out.println(Double.parseDouble("69,,69,..109f"));
            String text1 = text.substring(0, text.indexOf(","));
            String text2 = text.substring(text.indexOf(",") + 1, text.length());
            System.out.println(text1 + " " + text2);
//            outputList.add(new double[]{Double.parseDouble(text1), Double.parseDouble(text2)});
//        outputList.add(text1);
//        outputList.add(text2);
            lats.add(text1);
            longs.add(text2);
            

        }
        addAnnotations();

    }
    public int getLats(){
        saving = this.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        int i = saving.getInt("i", 0);
        return i;
//        latitude = new ArrayList<String>();
//        lats = new ArrayList<String>();
//        lats.add("penis");
//        latitude = lats;
//        return latitude.size();
//        return 1;
    }
    public ArrayList<String> updateLats(){
        saving = this.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        int i = saving.getInt("i", 0);
        System.out.println("hi123 " + i);
        if(i > 0) {
            lats = new ArrayList<String>();
            for (int j = 0; j < i; j++) {
                String index = Integer.toString(j);
                String tempText = saving.getString(index, "not working");
                String text1 = tempText.substring(0, tempText.indexOf(","));
                String text2 = tempText.substring(tempText.indexOf(",") + 1, tempText.length());
                output.append(tempText + "\n");
                lats.add(text1);

            }
        }
        return lats;
    }
    public ArrayList<String> updateLongs(){
        int i = saving.getInt("i", 0);
        for (int j = 0; j < i; j++) {
            String index = Integer.toString(j);
            String tempText = saving.getString(index, "not working");
            String text1 = tempText.substring(0, tempText.indexOf(","));
            String text2 = tempText.substring(tempText.indexOf(",") + 1, tempText.length());
            output.append(tempText + "\n");
            longs.add(text1);

        }
        return longs;
    }
    public void clearFunc(){
        output.setText("");
        lats.clear();
        longs.clear();
        addAnnotations();

    }
    public void removeFunc() {
//        int cookieName = saving.getInt("i", 0);
//        System.out.println(cookieName);
        ArrayList<String>tempLats = new ArrayList<String>();
        ArrayList<String>tempLongs = new ArrayList<String>();
        output.setText("");
        if(lats.size() >1) {
            for (int i = 0; i < lats.size() - 1; i++) {
                tempLats.add((String) lats.get(i));
                tempLongs.add((String) longs.get(i));
                output.append(lats.get(i) + "," + longs.get(i) + "\n");
            }
            lats = tempLats;
            longs = tempLongs;
        }else if(lats.size() <= 1){
            clearFunc();
        }
        addAnnotations();

//        for(int i = 0; i < outputList.size(); i++){
//            String[] temp = (String[]) outputList.get(i);
//                myEditor.putString(String.valueOf(i),("THIS: " + temp[0] + "," + temp[1]));
//        }
//        myEditor.putInt("i", outputList.size());
//        System.out.println("THISTHING: " + myPreferences.getInt("i",0));
////        if(outputList.size() > 0) {
////
////            output.setText("");
////            System.out.println("herelol");
////            outputList.remove(0);
////            System.out.println("herelol2");
////            for (int i = 0; i < outputList.size() - 1; i++) {
////                String[] temp = (String[]) outputList.get(i);
//////                System.out.println("THIS: " + temp[0] + "," + temp[1] + "\n");
//////                output.append(temp[0] + "," + temp[1] + "\n");
////            }
//
////        }

    }
    public void addAnnotations(){

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                drawPolygon(mapboxMap);
                zoomMap = mapboxMap;
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



//        mapboxMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 10));
        for(int i = 0; i < lats.size(); i++){
//            System.out.println(Double.parseDouble((String) lats.get(i)) + "," + Double.parseDouble((String) longs.get(i)) + "ttttt");
            polyline.add(new LatLng(Double.parseDouble((String) lats.get(i)), Double.parseDouble((String) longs.get(i))));
            CameraPosition position = new CameraPosition.Builder()
                    .target(new LatLng(Double.parseDouble((String) lats.get(i)), Double.parseDouble((String) longs.get(i)))) // Sets the new camera position
                    .zoom(1) // Sets the zoom
//                    .bearing(180) // Rotate the camera
//                    .tilt(30) // Set the camera tilt
                    .build(); // Creates a CameraPosition from the builder
            mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position));

        }


        mapboxMap.addPolyline(new PolylineOptions()
                .addAll(polyline).color(Color.parseColor("#ff0000")).width(4));


//        if(lats.size() > 0){
//            LatLngBounds latLngBounds = new LatLngBounds.Builder().include(new LatLng(Double.parseDouble((String) lats.get(0)), Double.parseDouble((String) longs.get(0)))).build();
//            mapboxMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 10));}
//        }else if(lats.size() > 1){
//
//            LatLngBounds latLngBounds = new LatLngBounds.Builder().include(new LatLng(Double.parseDouble((String) lats.get(0)), Double.parseDouble((String) longs.get(0))))
//                    .include(new LatLng(Double.parseDouble((String) lats.get(1)), Double.parseDouble((String) longs.get(1)))).build();
//            mapboxMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 10));
//        }else if(lats.size() > 2){
//
//            LatLngBounds latLngBounds = new LatLngBounds.Builder().include(new LatLng(Double.parseDouble((String) lats.get(0)), Double.parseDouble((String) longs.get(0))))
//                    .include(new LatLng(Double.parseDouble((String) lats.get(1)), Double.parseDouble((String) longs.get(1))))
//                    .include(new LatLng(Double.parseDouble((String) lats.get(2)), Double.parseDouble((String) longs.get(2)))).build();
//            mapboxMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 10));
//        }else if(lats.size() > 3){
//            LatLngBounds latLngBounds = new LatLngBounds.Builder().include(new LatLng(Double.parseDouble((String) lats.get(0)), Double.parseDouble((String) longs.get(0))))
//                    .include(new LatLng(Double.parseDouble((String) lats.get(1)), Double.parseDouble((String) longs.get(1))))
//                    .include(new LatLng(Double.parseDouble((String) lats.get(2)), Double.parseDouble((String) longs.get(2))))
//                    .include(new LatLng(Double.parseDouble((String) lats.get(3)), Double.parseDouble((String) longs.get(3)))).build();
//            mapboxMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 10));
//        }else if(lats.size() > 4){
//            LatLngBounds latLngBounds = new LatLngBounds.Builder().include(new LatLng(Double.parseDouble((String) lats.get(0)), Double.parseDouble((String) longs.get(0))))
//                    .include(new LatLng(Double.parseDouble((String) lats.get(1)), Double.parseDouble((String) longs.get(1))))
//                    .include(new LatLng(Double.parseDouble((String) lats.get(2)), Double.parseDouble((String) longs.get(2))))
//                    .include(new LatLng(Double.parseDouble((String) lats.get(3)), Double.parseDouble((String) longs.get(3))))
//                    .include(new LatLng(Double.parseDouble((String) lats.get(lats.size()-1)), Double.parseDouble((String) longs.get(longs.size()-1)))).build();
//            mapboxMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 10));
//        }
    }

    public void saveFunc(){
        SharedPreferences.Editor editor = saving.edit();
        MainActivity.textActivity = true;
//        editor.putInt("i", 3);
//        if (outputList.size() > 0) {
//            System.out.println("finder1 " + outputList.size());
//            for (int i = 0; i < outputList.size() - 1; i++) {
//                String[] temp = (String[]) outputList.get(i);
//                System.out.println("copypastes" + temp[0] + "," + temp[1]);
//
////                editor.putString(String.valueOf(i), temp[0] + "," + temp[1]);
////                editor.apply();
//            }
//        }
        if (lats.size() > 0) {
            System.out.println("finder1 " + lats.size());
            for (int j = 0; j < lats.size(); j++) {
                String index = Integer.toString(j);
                String temp = lats.get(j) + ", " + longs.get(j);
                System.out.println("copypastes" + temp);

                editor.putString(index, temp);
                editor.apply();
            }
        }
//        MainActivity.integer = lats.size();
        editor.putInt("i", lats.size());
        editor.apply();
    }
    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }
    
//    public void addAnnotations(){
//        mapView.add
//        mapView.addPolyline(new PolylineOptions()
//                .addAll(points)
//                .color(Color.parseColor("#3bb2d0"))
//                .width(2));
//    }
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

//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
////        //seattle coordinates
////        LatLng seattle = new LatLng(47.6062095, -122.3320708);
////        mMap.addMarker(new MarkerOptions().position(seattle).title("Seattle"));
////        mMap.moveCamera(CameraUpdateFactory.newLatLng(seattle));
//    }

//    public void onMapReady(GoogleMap map) {
//        map.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
//    }

//
}
