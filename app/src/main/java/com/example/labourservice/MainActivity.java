package com.example.labourservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.labourservice.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button Log_out;
    ViewPager2 viewPager2;

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Toast.makeText(getApplicationContext(),"Sorry, You Cannot Go Back!!",Toast.LENGTH_SHORT).show();
    }

    // implementing auto slide facility
    private Handler slideHandler =new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        viewPager2 =findViewById(R.id.viewPager);

        List<SlideIten> slideItem=new ArrayList<>();

        slideItem.add(new SlideIten(R.drawable.slider1));
        slideItem.add(new SlideIten(R.drawable.addbanner));
        slideItem.add(new SlideIten(R.drawable.add2));
        slideItem.add(new SlideIten(R.drawable.slider4));

        viewPager2.setAdapter(new SlideAdapter(slideItem,viewPager2));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(5);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositeTransform  = new CompositePageTransformer();
        compositeTransform.addTransformer(new MarginPageTransformer(30));

        compositeTransform.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {

                float r=1- Math.abs(position);
                page.setScaleY(0.85f + r*0.15f);


            }
        });
            viewPager2.setPageTransformer(compositeTransform);
            viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    slideHandler.removeCallbacks(sliderRunnable);
                    slideHandler.postDelayed(sliderRunnable,4000);
                }
            });
    }



    private Runnable sliderRunnable=new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
        }
    };
    @Override
    protected void onPause(){
        super.onPause();
        slideHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        slideHandler.postDelayed(sliderRunnable,4000);
    }
}