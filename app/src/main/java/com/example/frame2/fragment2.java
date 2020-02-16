package com.example.frame2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class fragment2 extends Fragment {

    private static final String moovei_bandel_string = "moovei string";
    private Result data;
    private Button bbb;
    private ImageView imageView1;
    private ImageView imageView2;
    private TextView titel;
    private TextView ttext;
    ProgressBar myprogres;
    private List<ResultsItem> resultsItems;


    public static fragment2 newIntent (Result data){
        fragment2 f2 = new fragment2();
        Bundle bundle = new Bundle();
        bundle.putParcelable(moovei_bandel_string,data);
        f2.setArguments(bundle);
        return f2;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = getArguments().getParcelable(moovei_bandel_string);

    }

    private void loadPathTrallar() {

        myprogres.setVisibility(View.VISIBLE);
        ResultsItem item = AppDatabass.getinstansce(getActivity()).trailerDeo().getTrelair(data.getId());
        if(item !=null){

            String pathYouTub = "https://www.youtube.com/watch?v=";
            String key = item.getKey();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(pathYouTub+key));
            startActivity(intent);

        }
        else {

        Call<Response> myResultItem = TMDBRetrofistRest.myMooveiServich.serchTrailerById(String.valueOf(data.getId()),MainActivity.keyMoovey);
        myResultItem.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()){

                    resultsItems  = response.body().getResults();
                    resultsItems.get(0).setMovieid(data.getId());
                    AppDatabass.getinstansce(getActivity()).trailerDeo().insert(resultsItems.get(0));
                    String pathYouTub = "https://www.youtube.com/watch?v=";
                    String key = resultsItems.get(0).getKey();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(pathYouTub+key));

                    startActivity(intent);
                    myprogres.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }}

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vveiw = inflater.inflate(R.layout.farm2, container, false);
        initveiw(vveiw);
        if (data != null){
            setdata(vveiw);
        }
        return vveiw;
    }

    private void initveiw(View vveiw) {
       imageView1 = vveiw.findViewById(R.id.imeg1);
        myprogres = vveiw.findViewById(R.id.fr2_mp);
        imageView2 = vveiw.findViewById(R.id.imeg2);
        titel = vveiw.findViewById(R.id.titel);
        ttext = vveiw.findViewById(R.id.textt);
        bbb = vveiw.findViewById(R.id.f2_butun);

    }

    private void setdata(View vveiw) {
        final String pathurl ="https://image.tmdb.org/t/p/w500_and_h282_face/";
        String imegurl = pathurl+data.getPosterPath();
        String imegurl2 = pathurl+data.getBackdropPath();
        Picasso.get().load(imegurl).into(imageView1);
        Picasso.get().load(imegurl2).into(imageView2);
//        imageView1.setImageResource(data.getImeg());
//        imageView2.setImageResource(data.getImeg());
        titel.setText(data.getTitle());
        ttext.setText(data.getOverview());

        bbb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPathTrallar();

            }
        });
    }


}
