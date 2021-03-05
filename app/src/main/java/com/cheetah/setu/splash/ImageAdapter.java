package com.cheetah.setu.splash;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.cheetah.setu.R;

import java.util.ArrayList;

public class ImageAdapter extends PagerAdapter {
    Context context;
    ArrayList<String> stringList;
    LayoutInflater layoutInflater;

    public ImageAdapter(Context context, ArrayList<String> stringList) {
        this.context = context;
        this.stringList = stringList;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((ImageView) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        final int x11 = position;
        View itemView = layoutInflater.inflate(R.layout.item_imageview, container, false);
        ImageView imageView = itemView.findViewById(R.id.item_imageview);
        //Glide.with(context).load(stringList.get(position)).into(imageView);
        //Log.e("INIT ", stringList.get(0));
        Glide.with(context).load(Uri.parse("file:///android_asset/slider/"+(position+1)+".png")).into(imageView);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }
}
