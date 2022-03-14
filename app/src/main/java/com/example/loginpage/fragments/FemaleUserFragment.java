package com.example.loginpage.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginpage.database.DBHelper;
import com.example.loginpage.R;
import com.example.loginpage.adapters.UserlistAdapter;

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