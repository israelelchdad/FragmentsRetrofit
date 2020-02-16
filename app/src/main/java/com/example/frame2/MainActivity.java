package com.example.frame2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class  MainActivity extends AppCompatActivity implements OnMovieFragmentClickListener {

    private ViewPager tabletframeLayout = null;
    private Toolbar mytoolbar;
    public static final String keyMoovey = "7b46b0b9112d8d80d4ad663849665f94";
    private List<Result> mylist;
    mooveiFragment mymooveiFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadMoveis();

        tabletframeLayout = findViewById(R.id.AM_F2_ViewPager);

    }

    private List<Fragment> fragmentList() {
        List<Fragment> myFragment = new ArrayList<Fragment>();
        for (int i = 0; i < mylist.size(); i++) {
            myFragment.add(fragment2.newIntent(mylist.get(i)));
        }
        return myFragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    private void loadMoveis(){
//       AppDatabass.getinstansce(MainActivity.this).movieDeo().DeletAll();
        List<Result> cachedMovies = AppDatabass.getinstansce(this).movieDeo().getAll();
        if(cachedMovies !=null){
            int a = cachedMovies.size();
            mylist = cachedMovies;
            mymooveiFragment = mooveiFragment.newInstant((ArrayList<Result>) mylist);
            getSupportFragmentManager().beginTransaction().add(R.id.AM_FrameLayout, mymooveiFragment).commit();

        }

        Call <ImegeSearchResult> myCall = TMDBRetrofistRest.myMooveiServich.searchMobiesByPepuler(keyMoovey,1);
        myCall.enqueue(new Callback<ImegeSearchResult>() {
            @Override
            public void onResponse(Call<ImegeSearchResult> call, Response<ImegeSearchResult> response) {
                if (response.isSuccessful()){
                    mylist = response.body().getResults();
//                    AppDatabass.getinstansce(MainActivity.this).movieDeo().DeletAll();
                    AppDatabass.getinstansce(MainActivity.this).movieDeo().insetAll((ArrayList<Result>)mylist);
                    if(mymooveiFragment ==null){
//                        mymooveiFragment = mooveiFragment.newInstant((ArrayList<Result>) mylist);
//                        getSupportFragmentManager().beginTransaction().add(R.id.AM_FrameLayout, mymooveiFragment).commit();

                    }else {
//                        mymooveiFragment.setData((ArrayList<Result>)mylist);
                    }



                    if (tabletframeLayout != null) {
                        simpelPageAdapter mysimpelPageAdapter = new simpelPageAdapter(getSupportFragmentManager(), fragmentList());
                        tabletframeLayout.setAdapter(mysimpelPageAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ImegeSearchResult> call, Throwable t) {

            }
        });

    }

    @Override
    public void OnMooveiClicked(Result moovei) {
        fragment2 f2 = fragment2.newIntent(moovei);
        if (tabletframeLayout == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.AM_FrameLayout, f2).commit();
        } else {
//            mPager.setCurrentItem(2, true);
            tabletframeLayout.setCurrentItem(mooveiFragment.corectPosishen);
//                    .layoutManager.scrollToPosition(x)
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .addToBackStack(null)
//                    .replace(R.id.AM_F2_ViewPager,f2).commit();
//        }
        }
    }
}
