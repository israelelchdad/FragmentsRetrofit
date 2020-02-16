package com.example.frame2;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class mooveiFragment extends Fragment implements OnMooveiClickLisener {

    public OnMovieFragmentClickListener myonMovieFragmentClickListener ;

    private RecyclerView myRecyclerView;
    private RecyclerView.LayoutManager mylayoutManager;
    private RecyclerView.Adapter myAdapter;
    Button buttonAddMOvieLst;
    Button removedatabase;
    int counter =1;
    static int corectPosishen;
    static final String key = "key";
    private ArrayList <Result> myresults = new ArrayList<>();
    private List <Result> newMyResults ;



    static mooveiFragment newInstant (ArrayList<Result> mylist){
        mooveiFragment myMooveiFragment = new mooveiFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(key,mylist);
        myMooveiFragment.setArguments(bundle);
        return myMooveiFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMovieFragmentClickListener){
            myonMovieFragmentClickListener = (OnMovieFragmentClickListener) context;
        }else {
            throw new RuntimeException(context.toString() + "must by implements OnMovieFragmentClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        myonMovieFragmentClickListener = null;
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vveiw = inflater.inflate(R.layout.ferm1,container,false);
        myresults = getArguments().getParcelableArrayList(key);
        myRecyclerView = vveiw.findViewById(R.id.FM_rv);
        intimyRecyclerView();
        buttonAddMOvieLst = vveiw.findViewById(R.id.f2_butu);
        removedatabase = vveiw.findViewById(R.id.f2_butu_remove);
        removedatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabass.getinstansce(getActivity()).movieDeo().DeletAll();
            }
        });
        buttonAddMOvieLst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter ++;

                    Call<ImegeSearchResult> myCall = TMDBRetrofistRest.myMooveiServich.searchMobiesByPepuler(MainActivity.keyMoovey,counter);
                    myCall.enqueue(new Callback<ImegeSearchResult>() {
                        @Override
                        public void onResponse(Call<ImegeSearchResult> call, Response<ImegeSearchResult> response) {
                            if (response.isSuccessful()){
                                ArrayList<Result> myNewResulttt=new ArrayList<>();
                                myNewResulttt.addAll(myresults);
                                ArrayList<Result> myNewResult = (ArrayList<Result>) response.body().getResults();;
                                myNewResulttt.addAll(myNewResult);
                                myresults.clear();
                                myresults.addAll(myNewResulttt);
                                myAdapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onFailure(Call<ImegeSearchResult> call, Throwable t) {

                        }
                    });
            }
        });


        return vveiw;
    }

    private void intimyRecyclerView() {
        if (getContext() != null) {
            mylayoutManager = new LinearLayoutManager(getContext());
            myRecyclerView.setLayoutManager(mylayoutManager);
            myAdapter = new mooveyVeiwAdapter(getContext(), this, myresults);
            myRecyclerView.setAdapter(myAdapter);
        }
    }

    @Override
    public void OnMooveiClicedb(int ItemPositiom) {
        corectPosishen = ItemPositiom;
        if (myonMovieFragmentClickListener != null) {
            myonMovieFragmentClickListener.OnMooveiClicked((myresults.get(ItemPositiom)));
        }
    }
    public void  setData(ArrayList<Result> results){
        myresults.clear();
        myresults.addAll(results);
        myAdapter.notifyDataSetChanged();
    }
}
