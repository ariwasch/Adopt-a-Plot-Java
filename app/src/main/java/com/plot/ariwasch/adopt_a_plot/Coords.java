package com.plot.ariwasch.adopt_a_plot;

import java.util.ArrayList;

import java.io.BufferedReader;

public class Coords {
//    static String csvFile = /Users/ariwasch/Library/Preferences/AndroidStudio3.2/scratches/BayRidgeCoordinates.txt";
    static BufferedReader br = null;
    static String line = "";
    static String cvsSplitBy = ",";
    MorePlots morePlots = new MorePlots();
    public double latitud;
    public double longitud;
    public String descriptio;
    public double point;
    public Coords(double lat, double lon, String D) {
        latitud = lat;
        longitud = lon;
        descriptio = D;
    }


    public double getLat() {
        return latitud;
    }

    public double getLong() {
        return longitud;
    }

    public String getD() {
        return descriptio;
    }

    public ArrayList<Coords> getCoords() {
        ArrayList<Coords> result =  new ArrayList<Coords>();
        if(morePlots.updatePlace().equals("Bay Ridge")) {
            result = BayRidgeData.getData();
        }else if(morePlots.updatePlace().equals("Commons")){
            result = CommonsData.getData();
        }else{
            result = null;
        }
        return result;
    }
    public String[] getPlots(){
        String[] result;
        if(morePlots.updatePlace().equals("Bay Ridge")) {
            result = BayRidgeData.plots();
        }else if(morePlots.updatePlace().equals("Commons")){
            result = CommonsData.plots();
        }else{
            result = null;
        }
        return result;
    }

}

