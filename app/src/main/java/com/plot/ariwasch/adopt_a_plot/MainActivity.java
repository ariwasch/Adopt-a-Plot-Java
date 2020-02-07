package com.plot.ariwasch.adopt_a_plot;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.PendingIntent;
import android.content.Context;
import android.app.TaskStackBuilder;
import android.support.v4.app.NotificationCompat;
import android.app.AlertDialog;
import android.content.DialogInterface;
public class MainActivity extends AppCompatActivity {
    Button plot;
    Button instructions;
    Button fullScreen;
    CreatePlot createPlot;
    TextView currentPlot;
    public static boolean textActivity;
    public int integer;
//    public SharedPreferences saving;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        textActivity = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        saving = this.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        CreatePlot.saving = this.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        integer = CreatePlot.saving.getInt("i", 0);
        plot = (Button) findViewById(R.id.button3);
        instructions = (Button) findViewById(R.id.button);
        fullScreen = (Button) findViewById(R.id.button4);

        currentPlot = findViewById(R.id.textView2);
        createPlot = new CreatePlot();
        display();
        plot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plotButton();
            }
        });
        instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                instructionsButton();
            }
        });
        fullScreen.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                fullScreenButton();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        CreatePlot.saving = this.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        integer = CreatePlot.saving.getInt("i", 0);
        display();
    }

    public void plotButton(){
        Intent intent = new Intent(MainActivity.this, MorePlots.class);
        startActivity(intent);

    }
    public void instructionsButton(){
        Intent intent = new Intent(MainActivity.this, Slider.class);
        startActivity(intent);
    }

    public void display(){
//        ArrayList<String> list = createPlot.updateLats();
//        SharedPreferences.Editor editor = CreatePlot.saving.edit();
//
//        editor.putInt("i", integer);
//        editor.apply();
        CreatePlot.saving = this.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        String selected = CreatePlot.saving.getString("selected plot", "No Plot Selected");
        if(selected == "010"){
            selected = "10";
        }
        String plot = CreatePlot.saving.getString("morePlots", "No Plot Selected");
        if(integer > 0 && MainActivity.textActivity == true){
            currentPlot.setText("Custom Plot");
        }else if(MainActivity.textActivity == false){
            currentPlot.setText(plot + " #" + selected);

        }
//        currentPlot.setText("try " + integer);

//        System.out.println("hello123 " + CreatePlot.getLats());
//        if(createPlot.updateLats().size() > 0){
//            currentPlot.setText("Custom Plot");
//        }
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void fullScreenButton(){
        String selected = CreatePlot.saving.getString("selected plot", "No Plot Selected");
        System.out.println("SELECTED @@@@ " + selected.contains("No Plot Selected"));
        if(selected.contains("No Plot Selected")){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            builder.setCancelable(true);

            builder.setTitle("No Plot Selected");
            builder.setMessage("Please select a plot before continuing.");

            // Setting Positive "Yes" Button
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            builder.show();

        }
//        else if(!FullScreenPlot.locationP) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//
//            builder.setCancelable(true);
//
//            builder.setTitle("Location Not Enabled");
//            builder.setMessage("This app requires your location so you can see your position relative to your plot. " +
//                    "Please enable location services in settings.");
//            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//
//                }
//            });
//
//            builder.show();
////            finish();
        else{
            Intent intent = new Intent(MainActivity.this, FullScreenPlot.class);
            startActivity(intent);
        }
    }
}
