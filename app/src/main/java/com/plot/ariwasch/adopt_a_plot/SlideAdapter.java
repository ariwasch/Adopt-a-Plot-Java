package com.plot.ariwasch.adopt_a_plot;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class SlideAdapter extends PagerAdapter {
    private Context context;
    public String[] titles = {"one", "two", "three"};
    int[] pictures = {R.drawable.prepare, R.drawable.whattocut,
            R.drawable.nottodo, R.drawable.submit };
    LayoutInflater inflater;
    int[] viewXML = {R.layout.slider1, R.layout.slider2,
            R.layout.slider3, R.layout.slider4};
    @Override
    public int getCount() {
        return pictures.length;
    }

    public SlideAdapter(Context context){
        this.context = context;
//        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(viewXML[position],container,false);
//        LinearLayout layoutslide = (LinearLayout) view.findViewById(R.id.linearLayout);
//        TextView txttitle = (TextView) view.findViewById(R.id.textView7);
        //pictures from here
//        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
//        imageView.setImageResource(pictures[position]);
        //to here
        container.addView(view);
//        txttitle.setText(pictures[position]);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view==(LinearLayout)o);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }
}
