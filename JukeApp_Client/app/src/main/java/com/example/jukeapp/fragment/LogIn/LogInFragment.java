package com.example.jukeapp.fragment.LogIn;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jukeapp.R;
import com.example.jukeapp.api.GetUserSuccess;
import com.example.jukeapp.databinding.FragmentLogInBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class LogInFragment extends Fragment implements View.OnClickListener{
    private BottomNavigationView bottomNav;

    FragmentLogInBinding binding;
    LogInViewModel viewModel;

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
        viewModel = new ViewModelProvider(this).get(LogInViewModel.class);
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
                    binding.progressBar.setVisibility(View.VISIBLE);
                    viewModel.mUser.observe(getViewLifecycleOwner( ), new Observer<GetUserSuccess>( ) {
                        boolean loaded = false;
                        @Override
                        public void onChanged(GetUserSuccess getUserSuccess) {
                            if (getUserSuccess != null){
                                if (getUserSuccess.getUserId() != null){
                                    navController = NavHostFragment.findNavController(LogInFragment.this);
                                    navController.navigate(R.id.action_logInFragment_to_whitelistFragment);
                                }
                            }else{
                                binding.progressBar.setVisibility(View.GONE);
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                builder.setTitle("Error");
                                builder.setMessage("Usuari o contrasenya incorrectes");
                                builder.create().show();
                            }

                        }

                    });
                    viewModel.getUser(email, password);
//                    navController = NavHostFragment.findNavController(LogInFragment.this);
//                    navController.navigate(R.id.action_logInFragment_to_whitelistFragment);
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