package com.example.jukeapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jukeapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class LogInFragment extends Fragment implements View.OnClickListener{
    private BottomNavigationView bottomNav;
    private Button btnLogin;
    private Button btnSignin;
    private Button btnForgotPassword;
    private EditText mEdtEmail;
    private EditText mEdtPassword;
    private TextView mTxvError;
    private NavController navController;
    public LogInFragment() {
        // Required empty public constructor
    }

    public static LogInFragment newInstance(String param1, String param2) {
        LogInFragment fragment = new LogInFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_log_in, container, false);
        btnLogin = view.findViewById(R.id.btnLogin);
        btnSignin = view.findViewById(R.id.btnSignin);
        btnForgotPassword = view.findViewById(R.id.btnForgottenPassword);
        mEdtEmail = view.findViewById(R.id.edtUser);
        mEdtPassword = view.findViewById(R.id.edtPassword);
        mTxvError = view.findViewById(R.id.txvError);
        bottomNav = getActivity().findViewById(R.id.menu_nav);
        bottomNav.setVisibility(View.GONE);
        btnLogin.setOnClickListener(this);
        btnSignin.setOnClickListener(this);
        btnForgotPassword.setOnClickListener(this);
        mTxvError.setText("");
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                String email = mEdtEmail.getText().toString();
                String password = mEdtPassword.getText().toString();
                if (email.isEmpty() || password.isEmpty()){
                    mTxvError.setBackgroundColor(getResources().getColor(R.color.purple_500));
                    mTxvError.setText("Please fill all the fields");
                }else{
                    mTxvError.setText("");
                    mEdtEmail.setText("");
                    mEdtPassword.setText("");
                    navController = NavHostFragment.findNavController(LogInFragment.this);
                    navController.navigate(R.id.action_logInFragment_to_whitelistFragment);
                }
                break;
            case R.id.btnSignin:
                navController = NavHostFragment.findNavController(LogInFragment.this);
                navController.navigate(R.id.action_logInFragment_to_signInFragment);
                break;
            case R.id.btnForgottenPassword:
                navController = NavHostFragment.findNavController(LogInFragment.this);
                navController.navigate(R.id.action_logInFragment_to_forgottenPasswordFragment3);
                break;
        }
    }
}