package com.example.jukeapp.fragment.LogIn;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jukeapp.R;
import com.example.jukeapp.databinding.FragmentLogInBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class LogInFragment extends Fragment implements View.OnClickListener{
    private BottomNavigationView bottomNav;

    FragmentLogInBinding binding;

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
        binding = FragmentLogInBinding.inflate(inflater, container, false);
        bottomNav = getActivity().findViewById(R.id.menu_nav);
        bottomNav.setVisibility(View.GONE);
        binding.btnLogin.setOnClickListener(this);
        binding.btnSignin.setOnClickListener(this);
        binding.btnForgottenPassword.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                String email = binding.edtUser.getText().toString();
                String password = binding.edtPassword.getText().toString();
                if (email.isEmpty() || password.isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Error");
                    builder.setMessage("Tots els camps són obligatoris");
                    builder.create().show();
                }else{
                    binding.edtUser.setText("");
                    binding.edtPassword.setText("");
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