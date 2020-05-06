package com.elliot.android.ch20.fragments;

import android.content.Context;
import android.content.Intent;
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

import com.elliot.android.ch20.InfoActivity;
import com.elliot.android.ch20.R;


public class LoginFragment extends Fragment {
    private Button loginBtn, signUpBtn;
    private EditText nameEdt, pwdEdt;
    private String username;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initView(view);

        if (getArguments() != null) {
            username = getArguments().getString("username", "null");
            if (!username.equals("null")) nameEdt.setText(username);
        }


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //                MainActivity activity = (MainActivity) getActivity();
//                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_layout, new SignupFragment());
                transaction.commit();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "");
                String password = sharedPreferences.getString("password", "");
                if (username.equals(nameEdt.getText().toString()) && password.equals(pwdEdt.getText().toString())) {
                    Intent intent = new Intent(getActivity(), InfoActivity.class);
                    intent.putExtra("username", username);
                    getActivity().startActivity(intent);
                    Toast.makeText(getActivity(), "Welcome! ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Ops! Wrong password or username! ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    public static Fragment newInstance(String str) {
        LoginFragment loginFragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString("username", str);
        loginFragment.setArguments(args);
        return loginFragment;
    }

    public static Fragment newInstance() {
        LoginFragment loginFragment = new LoginFragment();
        return loginFragment;
    }


    private void initView(View view) {
        loginBtn = view.findViewById(R.id.btn_signup);
        signUpBtn = view.findViewById(R.id.btn_login);
        pwdEdt = view.findViewById(R.id.edt_pwd);
        nameEdt = view.findViewById(R.id.edt_username);
    }
}
