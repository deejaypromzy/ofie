package com.projectwork.ofie;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Breakfast extends Fragment {

    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private FirebaseDatabase mfirebaseDatabase;
    private DatabaseReference mref;
    private ArrayList<Model> ModelEdu;
    private CookingAdapter cookingAdapter;
    private String service;

    public Breakfast() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_breakfast, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Bundle bundle = getArguments();
        service = bundle.getString("cooking");
        Toast.makeText(getContext(), service, Toast.LENGTH_SHORT).show();
        progressBar= view.findViewById(R.id.progressbar);
        auth= FirebaseAuth.getInstance();
        mfirebaseDatabase = FirebaseDatabase.getInstance();



        if (service.equals("breakfast"))
        mref = mfirebaseDatabase.getReference().child("ofie").child("cooking").child("breakfast");
        if (service.equals("lunch"))
        mref = mfirebaseDatabase.getReference().child("ofie").child("cooking").child("lunch");
        if (service.equals("dinner"))
        mref = mfirebaseDatabase.getReference().child("ofie").child("cooking").child("dinner");


        mref.keepSynced(true);


        final RecyclerView mRecyclerView = view.findViewById(R.id.recyclerView);

//Set the Layout Manager
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
//Initialize the adapter and set it ot the RecyclerView

//Initialize the ArrayList that will contain the data
        ModelEdu = new ArrayList<>();
        cookingAdapter = new CookingAdapter(getActivity(), ModelEdu);
        mRecyclerView.setAdapter(cookingAdapter);
        new CountDownTimer(200,200)
        {
            public void onTick(long ms)
            {
                progressBar.setVisibility(View.VISIBLE);
            }
            public void onFinish() {
                if (mref!=null){
                    mref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            showData(dataSnapshot);
                            Toast.makeText(getActivity(), "hey", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        }.start();


    }

    private void showData(DataSnapshot dataSnapshot) {
        //Create the ArrayList of Sports objects with the titles, images
        // and information about each sport
        ModelEdu.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            Model userDatabase = new Model();
            userDatabase.setName((ds.getValue(Model.class)).getName());
            userDatabase.setTitle((ds.getValue(Model.class)).getTitle());
            userDatabase.setDesc((ds.getValue(Model.class)).getDesc());
            userDatabase.setImageResource((ds.getValue(Model.class)).getImageResource());
            userDatabase.setDate((ds.getValue(Model.class)).getDate());
            userDatabase.setYoutube((ds.getValue(Model.class)).getYoutube());

            ModelEdu.add(new Model(userDatabase.getId(),userDatabase.getName(),userDatabase.getTitle(),userDatabase.getImageResource(),userDatabase.getDesc(),userDatabase.getDate(),userDatabase.getYoutube()));

        }
        //Notify the adapter of the change
        cookingAdapter.notifyDataSetChanged();
    }

}
