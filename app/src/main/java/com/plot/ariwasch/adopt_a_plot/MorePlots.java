package com.plot.ariwasch.adopt_a_plot;
import android.content.Context;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.List;
import java.util.ArrayList;
import java.lang.String;

import android.view.View;
import android.content.SharedPreferences;
import android.widget.Button;
import android.content.Intent;

public class MorePlots extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    String plotPlace;
    static String staticPlot;
    // Create an ArrayAdapter using the string array and a default spinner layout
    List<String> morePlots = new ArrayList<String>();
    Button selectButton;
    Button createOwn;
//    public static SharedPreferences saving;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        morePlots.add("Bay Ridge");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_plots);
        spinner = (Spinner) findViewById(R.id.spinner);
        morePlots.add("BRCA Commons Area");//from roadtrip
        CreatePlot.saving = this.getSharedPreferences("Settings", Context.MODE_PRIVATE);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, morePlots);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(this);

        selectButton = (Button) findViewById(R.id.button2);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                save();
            }
        });
        createOwn = (Button) findViewById(R.id.button5);
        createOwn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createOwnPage();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        plotPlace = text;
        MorePlots.staticPlot = text;
        System.out.println("positionomg " + text);
        WhichPlot.thing = text;
        //Debug
        //Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }
    static public String thePlot(){
        System.out.println("testing of test" + MorePlots.staticPlot);

        return MorePlots.staticPlot;
    }
    public void save(){
        SharedPreferences.Editor editor = CreatePlot.saving.edit();
        editor.putString("morePlots", plotPlace);
        editor.apply();
        editor.putInt("plotNum", spinner.getSelectedItemPosition());
//        System.out.println("Search this " + spinner.getSelectedItemPosition());
        editor.apply();
//        database.execSQL(plotPlace);
        System.out.println("saved search this" + plotPlace);
//        Intent intent = new Intent(this, SelectPlots.class);
//        startActivity(intent);
//        Intent intent = new Intent(this, WorkingMap.class);
//        startActivity(intent);
        MainActivity.textActivity = false;
        Intent intent = new Intent(this, SelectPlots.class);
        startActivity(intent);
    }
    public String updatePlace(){
//        System.out.println("crashes here 1");
//        saving = this.getSharedPreferences("Settings", Context.MODE_PRIVATE);
//        String result = CreatePlot.getString("morePlots", "Bay Ridge");
//        System.out.println("crashes here 2 " + CreatePlot.getString("morePlots", "Bay Ridge"));
//        return result;
        CreatePlot.saving = this.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        String i = CreatePlot.saving.getString("morePlots", "Bay Ridge");
        return i;

    }
    protected void onStart() {
        super.onStart();
        CreatePlot.saving = this.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        plotPlace = CreatePlot.saving.getString("morePlots", "");
        MorePlots.staticPlot = plotPlace;
//        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String> (
//                this,R.layout.support_simple_spinner_dropdown_item,morePlots);
//
//        spinnerArrayAdapter.setDropDownViewResource(R.s);
//        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setSelection(CreatePlot.saving.getInt("plotNum", 0));

    }
    public void createOwnPage(){
        Intent intent = new Intent(this, CreatePlot.class);
        startActivity(intent);
        MainActivity.textActivity = true;

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

