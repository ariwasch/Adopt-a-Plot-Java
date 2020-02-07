package com.plot.ariwasch.adopt_a_plot;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Slider extends AppCompatActivity {
    private ViewPager viewPager;
    private SlideAdapter myadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);
        myadapter = new SlideAdapter(this);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(myadapter);
    }
}
