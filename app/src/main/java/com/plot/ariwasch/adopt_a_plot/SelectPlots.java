package com.plot.ariwasch.adopt_a_plot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.PolygonOptions;
import com.mapbox.mapboxsdk.annotations.PolylineOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
//import android.support.v7.widget.CardView;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.LinearSnapHelper;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.SnapHelper;

//import com.mapbox.api.directions.v5.DirectionsCriteria;
//import com.mapbox.api.matrix.v1.MapboxMatrix;
//import com.mapbox.api.matrix.v1.models.MatrixResponse;
//import com.mapbox.mapboxandroiddemo.R;

//import java.util.m;

//import com.mapbox.mapboxandroiddemo.R;


public class SelectPlots extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Button select;
    Spinner spinner;
    String selected;
    int selectedi;
    String[] plots;
    ArrayList<Coords> coordinates;
    private MapView mapView;
    Coords coords;
//    MorePlots which;
//    WhichPlot whichPlot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1IjoiYXJpd2FzY2giLCJhIjoiY2pxMHk2bndzMHJ5aDQybno3Z2h6emQ0bSJ9.vg9q3OKDkXN8GV-HdttcYw");
        setContentView(R.layout.activity_select_plots);
        //CHANGE LATER
//        WhichPlot whichPlot = new WhichPlot();
//        CreatePlot.saving = this.getSharedPreferences("Settings", Context.MODE_PRIVATE);
//        which = new MorePlots();
        System.out.println(CreatePlot.saving.getString("morePlots", ""));
        plots = WhichPlot.getPlots();
        System.out.println("updating plot");
        coordinates = WhichPlot.getCoords();
//        plots = BayRidgeData.plots();
//        coordinates = BayRidgeData.getData();
        //CHANGE LATER
        CreatePlot.saving = this.getSharedPreferences("Settings", Context.MODE_PRIVATE);
//        int i = CreatePlot.saving.getInt("i", 0);
        selected = "1";
        int selectedi = 0;
        mapView = (MapView) findViewById(R.id.mapView2);
        mapView.onCreate(savedInstanceState);
        spinner = (Spinner) findViewById(R.id.spinner2);
        select = (Button) findViewById(R.id.button6);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFunc();
            }
        });

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, plots);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(this);
    }
    @Override
    public void onStart() {
        super.onStart();
        CreatePlot.saving = this.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        if(CreatePlot.saving.getString("morePlots", "Bay Ridge").equals("Bay Ridge")) {
            selected = CreatePlot.saving.getString("selected plot", "69");
            selectedi = CreatePlot.saving.getInt("selected plot i", 68);
        }else{
            selected = "A";
            selectedi = 0;
        }

//        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String> (
//                this,R.layout.support_simple_spinner_dropdown_item,morePlots);
//
//        spinnerArrayAdapter.setDropDownViewResource(R.s);
//        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setSelection(selectedi);
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
    public void addAnnotations(){

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
        ArrayList<LatLng> polygone = new ArrayList<>();
        ArrayList<Coords> dist = new ArrayList<>();

        System.out.println("hi123 " + coordinates.size());
//        System.out.println(coordinates.get(2).getD());
//        System.out.println(coordinates.get(69).getD());

        for(int i = 0; i < coordinates.size(); i++){
            if(coordinates.get(i).getD().contains(selected)){
                System.out.println("idk what? "  + selected);
                polygone.add(new LatLng(coordinates.get(i).getLat(), coordinates.get(i).getLong()));
                dist.add(coordinates.get(i));
//                LatLng(coordinates.get(i).getLat(), coordinates.get(i).getLong()).d
            }
        }
        //IF POLYLINE
        polygone.add(polygone.get(0));
        //IF POLYLINE
//        getSmallestPoly(dist, mapboxMap);
//        polygone.clear();
//        if(dist.size() > 0) {
////            System.out.println("123451234 " + dist.get(0).getLat());
//            for (int i = 0; i < dist.size(); i++) {
//                System.out.println("xdlmao " + dist.get(i).getLat() + "," + dist.get(i).getLong());
//                polygone.add(new LatLng(dist.get(i).getLat(), dist.get(i).getLong()));
//            }
//        }
        double totx = 0;
        double toty = 0;
//        System.out.println("bigsize " + polygone.size());
        for(int i = 0; i < dist.size(); i++){
            totx += dist.get(i).getLat();
            toty += dist.get(i).getLong();

        }
        totx = totx / dist.size();
        toty = toty / dist.size();
//        double zoomDist = 0;
//        double zoom = 0;
//        for(int i = 1; i < dist.size(); i++){
//            zoomDist+= getDistance(dist.get(i-1), dist.get(i));
//        }
//        zoom = .01/zoomDist;
//        if(zoom > 25.5){
//            zoom = 25.5;
//        }
        mapboxMap.addPolyline(new PolylineOptions()
                .addAll(polygone).color(Color.parseColor(colorChooser(selected)))).setWidth((4));
        CameraPosition position = new CameraPosition.Builder()
                .target(new LatLng(totx,toty)) // Sets the new camera position
            .zoom(16) // Sets the zoom
//                    .bearing(180) // Rotate the camera
//                    .tilt(30) // Set the camera tilt
                .build(); // Cre
        mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position));
//        LatLngBounds latLngBounds = new LatLngBounds.Builder()
//                .include(new LatLng(coordinates.get(0).getLat(),coordinates.get(0).getLong()))
//                .build();
//        mapboxMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 10));
//        System.out.println(getDistance(dist.get(0), dist.get(1)));
    }

    public static String colorChooser(String selected) {
        String result = "#ff0000";
        String[] orangeValues = {"one", "003", "005", "11", "13", "15", "22", "24", "28", "30",
                "40", "42", "49", "53", "55", "57", "60", "74", "76", "78", "87", "89"};
        String[] yellowValues = {"002", "004", "12", "14", "23", "29", "31", "33", "37", "39",
                "44", "46", "48", "51", "62", "64", "66", "68", "70", "80", "82", "84",
                "92", "91", "93", "94", "95", "96", "97", "98", "99", "100", "101", "102", "103", "104", "105", "106", "107", "108", "109", "110"};
        String[] blueValues = {"006", "008", "10", "16", "18", "20", "25", "27", "41", "43",
                "50", "54", "56", "58", "61", "75", "77", "79", "86", "88", "90"};
        for(int i = 0; i < orangeValues.length; i++){
            if(orangeValues[i].contains(selected)){
                result = "#ffa500";
            }
        }
        for(int i = 0; i < yellowValues.length; i++){
            if(yellowValues[i].contains(selected)){
                result = "#ffd000";
            }
        }
        for(int i = 0; i < blueValues.length; i++){
            if(blueValues[i].contains(selected)){
                result = "#0000ff";
            }
        }
        if(MainActivity.textActivity == true){
            result = "#ff0000";
        }
        return result;
    }


//    public void getSmallestPoly(ArrayList<Coords> list, MapboxMap mapboxMap){
//        double actDist = 1000000000;
//        ArrayList<Coords> polyline = new ArrayList<Coords>();
//        Coords one = new Coords(69,69,"DIDNT WORK BITCH");
//        Coords two = new Coords(69,69,"DIDNT WORK BITCH");
//        Coords shorty = new Coords(69,69,"DIDNT WORK BITCH");
//        ArrayList<Coords> result = new ArrayList<Coords>();
//        ArrayList<LatLng> polygone = new ArrayList<>();
//        ArrayList<LatLng> tester = new ArrayList<>();
//
//
//
//        for(int i = 0; i < list.size();i++){
//            double totDist = 100000000;
//            polyline.clear();
//            for(int j = 0; j < list.size(); j++){
////                if(i > 0 && j > 0){
//
//                    //ive got i and j here: lets find distance between them
////                    for(int k = 0; k < list.size()-1){
//                        Double temp = getDistance(list.get(i),list.get(j));
//                        if(temp < totDist && temp != 0){
//                            totDist = temp;
//                            shorty = list.get(j);
//                            one = list.get(i);
//                            two = list.get(j);
//                        }
////                    }
////                }
//            }
//            polyline.add(one);
//            polyline.add(two);
//            polygone.clear();
//            System.out.println("some length " + polyline.size());
//            polygone.add(new LatLng(polyline.get(0).getLat(),polyline.get(0).getLong()));
//            tester.add(new LatLng(polyline.get(0).getLat(),polyline.get(0).getLong()));
//            polygone.add(new LatLng(polyline.get(1).getLat(),polyline.get(1).getLong()));
//            tester.add(new LatLng(polyline.get(1).getLat(),polyline.get(1).getLong()));
//
////            for(int x = 0; x < polyline.size()-1;x++){
////                polygone.add(new LatLng(polyline.get(i).getLat(),polyline.get(i).getLong()));
////                System.out.println("oofg " + polyline.get(i).getLat());
////                polygone.add(new LatLng(2,2));
////            }
////            result.add(shorty);
////            mapboxMap.addPolyline(new PolylineOptions()
////                    .addAll(polyline).color(Color.parseColor("#ff0000")));
//        }
//        System.out.println("tttt " + tester.size());
//        mapboxMap.addPolygon(new PolygonOptions()
//                .addAll(tester).fillColor(Color.parseColor("#ff0000")));
////        mapboxMap.getPolylines().
////        for(int )
////        return result;
//    }

public void getSmallestPoly(ArrayList<Coords> list, MapboxMap mapboxMap) {
    double actDist = 1000000000;
    ArrayList<Coords> polyline = new ArrayList<Coords>();
    Coords one = new Coords(69, 69, "DIDNT WORK BITCH");
    Coords two = new Coords(69, 69, "DIDNT WORK BITCH");
    Coords shorty = new Coords(69, 69, "DIDNT WORK BITCH");
    ArrayList<Coords> result = new ArrayList<Coords>();
    ArrayList<LatLng> polygone = new ArrayList<>();
    ArrayList<LatLng> tester = new ArrayList<>();

    for (int i = 0; i < list.size(); i++) {
        list.add(list.remove(i));

        System.out.println("cmdf" + i);
        for (int j = 0; j < list.size(); j++) {
//            if (i > 0 && j > 0) {
                System.out.println("wb here " + list.size());
                Collections.swap(list, i,j);
//                for(int y = 0; y < list.size(); y++){
//                    list.add(list.remove(0));
//                }
            double totDist = 0;
            System.out.println("list same size? " + list.size());
                for(int x = 1; x < list.size();x++){
                    System.out.println(getDistance(list.get(x-1), list.get(x)));
                    totDist += getDistance(list.get(x-1), list.get(x)); //my distance func
//                    totDist += calculateDistanceBetweenPoints(list.get(x-1).getLat(),list.get(x-1).getLong(), list.get(x).getLat(),list.get(x).getLong());
                    System.out.println(x + "x");

                }
                if(totDist < actDist){
                    System.out.println("is it here" + list.size());
                    actDist = totDist;
                    result = list;
                }
//            }
            //ive got i and j here: lets find distance between them
        }

    }
    System.out.println("comparison " + list.size() + ":" + result.size());
    for(int k = 0; k < result.size(); k++){
        polygone.add(new LatLng(result.get(k).getLat(), result.get(k).getLong()));
        System.out.println("thisis" + result.get(k).getLat() + "," + result.get(k).getLong());
    }
    System.out.println("comparison 2 " + polygone.size());
    CameraPosition position = new CameraPosition.Builder()
            .target(new LatLng(list.get(0).getLat(),list.get(0).getLong())) // Sets the new camera position
//            .zoom(.1) // Sets the zoom
//                    .bearing(180) // Rotate the camera
//                    .tilt(30) // Set the camera tilt
            .build(); // Creates a CameraPosition from the builder
    mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position));
    mapboxMap.addPolygon(new PolygonOptions()
                .addAll(polygone).fillColor(Color.parseColor("#ff0000")));
}

//    var actDist: Double = 10000000
//    var testPoly = MKPolyline(coordinates: &testCoordinates, count: locations.count)
//            // this works
//            for i in 0...locations.count-1{
//        for j in 0...locations.count-1{
//            if(i > 0 && j > 0){
//                var totDist: Double = 0
//                locations.swapAt(i, j)
//                for x in 1...locations.count-1{
//                    totDist += locations[x-1].distance(from: locations[x])
//                }
//                if totDist < actDist{
//                    print(locations.count)
//                    actDist = totDist
//                    testCoordinates = locations.map({(location: CLLocation!) -> CLLocationCoordinate2D in return location.coordinate})
//                    testPoly = MKPolyline(coordinates: &testCoordinates, count: locations.count)
//                    print(totDist)
//                    print(actDist)
//                }
//            }
//        }
//    }
    public Double getDistance(Coords first, Coords second){
        return Math.sqrt(Math.pow((second.getLat() - first.getLat()),2) +  Math.pow((second.getLong() - first.getLong()),2));
    }
    public double calculateDistanceBetweenPoints(
            double x1,
            double y1,
            double x2,
            double y2) {
        x1 = Math.abs(x1);
        x2 = Math.abs(x2);
        y1 = Math.abs(y1);
        y2 = Math.abs(y2);
        System.out.println(Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1)));
        System.out.println(Math.abs(Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1))));

        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }
    public void selectFunc(){
//        addAnnotations();
        System.out.println("selected" + selected);
        SharedPreferences.Editor editor = CreatePlot.saving.edit();
        editor.putString("selected plot", selected);
        editor.apply();
        editor.putInt("selected plot i", selectedi);
        editor.apply();
        MainActivity.textActivity = false;

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        System.out.println("tester123 " + parent.getItemAtPosition(position).toString());
        selected = parent.getItemAtPosition(position).toString();
        selectedi = position;
        if(selected == "10"){
            selected = "010";
        }
        System.out.println("selected test" + selected);
        addAnnotations();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
