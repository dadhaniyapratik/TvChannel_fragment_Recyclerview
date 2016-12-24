package com.example.pratik.fragment_retrofit;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pratik.fragment_retrofit.Model.ListOfShow;
import com.example.pratik.fragment_retrofit.Model.ProgramInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 19-12-2016.
 */


public class FragmentOne extends Fragment {

    List<ListOfShow> listOfShows;
    List<ProgramInfo> programInfos;
    ProgramInfo programInfo;
    @Bind(R.id.Recycler_view)
    android.support.v7.widget.RecyclerView RecyclerView;
    private VericalRecycleviewAdapter vericalRecycleviewAdapter;




    CustomBaseAdapter customBaseAdapter;
    Activity activity;
    String url = "http://indian-television-guide.appspot.com/";


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //Inflate the layout for this fragment
        activity = getActivity();
        View view = inflater.inflate(
                R.layout.fragment_one, container, false);
        ButterKnife.bind(this, view);


//        programInfo = new ProgramInfo();
        listOfShows = new ArrayList<>();
        programInfos = new ArrayList<>();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitObjectAPI service = retrofit.create(RetrofitObjectAPI.class);

        Call<ProgramInfo> call = service.getStudentDetails();

        call.enqueue(new Callback<ProgramInfo>() {
            @Override
            public void onResponse(Call<ProgramInfo> call, Response<ProgramInfo> response) {
                listOfShows = response.body().getListOfShows();
                programInfo = response.body();

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                RecyclerView.setLayoutManager(linearLayoutManager);
                vericalRecycleviewAdapter = new VericalRecycleviewAdapter(getActivity(), listOfShows);
                vericalRecycleviewAdapter.setRecycleViewItemClickListener(recycleViewItemClickListener);
                RecyclerView.setAdapter(vericalRecycleviewAdapter);

            }

            @Override
            public void onFailure(Call<ProgramInfo> call, Throwable t) {

            }
        });


        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private RecycleViewItemClickListener recycleViewItemClickListener = new RecycleViewItemClickListener() {
        @Override
        public void onClickItem(int position, ListOfShow worldpopulation) {
            String s1 = listOfShows.get(position).getShowTime();
            String s2 = listOfShows.get(position).getShowDetails().getWriter();
            Toast.makeText(activity, "" + s1 + s2, Toast.LENGTH_SHORT).show();
            Intent i = new Intent(activity,SeconFragActivity.class);
            i.putExtra("s1",s1);
            i.putExtra("s2",s2);
            startActivity(i);

        }
    };
}