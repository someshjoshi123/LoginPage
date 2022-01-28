package com.example.loginpage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FemaleUserFragment extends Fragment {

    RecyclerView femaleUser_RecyclerView;
    UserlistAdapter adapter;

    public FemaleUserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_female_user, container, false);
        femaleUser_RecyclerView = v.findViewById(R.id.femaleUser_RecyclerView);

        DBHelper dbHelper = new DBHelper(getContext());
        adapter = new UserlistAdapter(dbHelper.getUserData("Female"), getContext());

        femaleUser_RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        femaleUser_RecyclerView.setAdapter(adapter);

        return v;
    }
}