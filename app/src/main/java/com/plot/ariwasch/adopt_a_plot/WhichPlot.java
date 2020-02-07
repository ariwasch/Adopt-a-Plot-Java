package com.plot.ariwasch.adopt_a_plot;

import java.util.ArrayList;

public class WhichPlot {
//    CreatePlot.saving = this.getSharedPreferences("Settings",Context.MODE_PRIVATE);
    static MorePlots thing2 = new MorePlots();
    static public String thing = MorePlots.thePlot();//MorePlots.staticPlot;//thing2.updatePlace();
//static String thing = CreatePlot.saving.getString("morePlots", "");

    //    public static String stringPlot = thing;
    public static ArrayList<Coords> getCoords() {
        System.out.println("aha1 " + thing);
        ArrayList<Coords> result =  new ArrayList<Coords>();
        if(thing.equals("Bay Ridge")){
            result = BayRidgeData.getData();
            System.out.println("true");
        }else if(thing.equals("BRCA Commons Area")){
            result = CommonsData.getData();
        }else{
            result = null;
        }
        return result;
//        return BayRidgeData.getData();
    }
    public static String[] getPlots(){
        String[] result;
        System.out.println("aha2 " + thing);

        if(thing.equals("Bay Ridge")) {
            result = BayRidgeData.plots();
            System.out.println("true");
        }else if(thing.equals("BRCA Commons Area")){
            result = CommonsData.plots();
            System.out.println("aha2 commons stuff");
        }else{
            result = null;
            System.out.println("saved search this NULL");
        }
        return result;
//       return BayRidgeData.plots();
    }
}
