package com.elliot.android.ch20.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.elliot.android.ch20.R;


public class SignupFragment extends Fragment {
    private Button loginBtn, signUpBtn;
    private EditText nameEdt, pwdEdt;

    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        initView(view);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                MainActivity activity = (MainActivity) getActivity();
//                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                replaceWithLoginFragment();

            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE).edit();
                editor.putString("username", nameEdt.getText().toString());
                editor.putString("password", pwdEdt.getText().toString());
                editor.apply(); // namely commit
                Toast.makeText(getActivity(), "Hello, " + nameEdt.getText().toString(), Toast.LENGTH_SHORT).show();
                replaceWithLoginFragment(nameEdt.getText().toString());
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    private void replaceWithLoginFragment() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_layout, LoginFragment.newInstance(), "LoginFragment");
        transaction.commit();
    }

    private void replaceWithLoginFragment(String str) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_layout, LoginFragment.newInstance(str), "LoginFragment");
        transaction.commit();
    }

    private void initView(View view) {
        loginBtn = view.findViewById(R.id.btn_login);
        signUpBtn = view.findViewById(R.id.btn_signup);
        pwdEdt = view.findViewById(R.id.edt_pwd);
        nameEdt = view.findViewById(R.id.edt_username);
    }
}
