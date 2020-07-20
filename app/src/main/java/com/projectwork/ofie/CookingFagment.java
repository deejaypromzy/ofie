package com.projectwork.ofie;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class CookingFagment extends Fragment {
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cooking, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().findViewById(R.id.fab).setVisibility(View.GONE);

       view.findViewById(R.id.banku).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),DetailsActivity.class);
                startActivity(intent);
            }
        });

       view.findViewById(R.id.fufu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),DetailsActivity.class);
                startActivity(intent);
            }
        });
//        // FloatingActionButton fab =view.findViewById(R.id.fab);
//        // fab.setVisibility(View.VISIBLE);
//        view.findViewById(R.id.handwashing).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(CookingFagment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });
//
//
//        view.findViewById(R.id.handwashing).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(CookingFagment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });
//
//
//        view.findViewById(R.id.handwashing).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(CookingFagment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });
    }
}
