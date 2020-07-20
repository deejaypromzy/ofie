package com.projectwork.ofie;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Cooking extends Fragment {
    private ArrayList<Model> ModelEdu;
    private CookingAdapter cookingAdapter;
    private FirebaseAuth auth;
    private FirebaseDatabase mfirebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private DatabaseReference mref;
    private ProgressBar progressBar;
    String service;
    private TabLayout tabLayout;
    private Bundle bundle;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.cooking, container, false);
    }


    private void setFragment(Fragment fragment) {
        FragmentTransaction t = getActivity().getSupportFragmentManager().beginTransaction();
        t.replace(R.id.container, fragment);
        t.commit();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayout = view.findViewById(R.id.tabLayout);
        Breakfast  breakfast = new Breakfast();
        bundle = new Bundle();
        bundle.putString("cooking","breakfast");
        breakfast.setArguments(bundle);
        setFragment(breakfast);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        Breakfast  breakfast = new Breakfast();
                        bundle.putString("cooking","breakfast");
                        breakfast.setArguments(bundle);
                        setFragment(breakfast);
                        break;
                    case 1:
                        Breakfast  lunch = new Breakfast();
                        bundle.putString("cooking","lunch");
                        lunch.setArguments(bundle);
                        setFragment(lunch);
                        break;
                    case 2:
                        Breakfast  dinner = new Breakfast();
                        bundle.putString("cooking","dinner");
                        dinner.setArguments(bundle);
                        setFragment(dinner);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }

}