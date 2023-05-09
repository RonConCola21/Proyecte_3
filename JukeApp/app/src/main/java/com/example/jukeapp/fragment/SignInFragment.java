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
import com.example.jukeapp.databinding.FragmentSignInBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SignInFragment extends Fragment implements View.OnClickListener {
    private BottomNavigationView bottomNav;
    FragmentSignInBinding binding;
    NavController navController;
    public SignInFragment() {
        // Required empty public constructor
    }
    public static SignInFragment newInstance(String param1, String param2) {
        SignInFragment fragment = new SignInFragment();
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
        binding = FragmentSignInBinding.inflate(inflater, container, false);
        bottomNav = getActivity().findViewById(R.id.menu_nav);
        bottomNav.setVisibility(View.GONE);
        binding.txvError.setText("");
        binding.btnSignin.setOnClickListener(this);
        binding.btnLogin.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                navController = NavHostFragment.findNavController(SignInFragment.this);
                navController.navigate(R.id.action_signInFragment_to_logInFragment2);
                break;
            case R.id.btnSignin:
                if(binding.edtUser.getText().toString().isEmpty() || binding.edtEmail.getText().toString().isEmpty() || binding.edtPassword.getText().toString().isEmpty() || binding.edtPassword2.getText().toString().isEmpty()){
                    binding.txvError.setText("Todos los campos son obligatorios");
                    return;
                }
                if(!binding.edtPassword.getText().toString().equals(binding.edtPassword2.getText().toString())){
                    binding.txvError.setText("Las contraseñas no coinciden");
                    return;
                }
                navController = NavHostFragment.findNavController(SignInFragment.this);
                navController.navigate(R.id.action_signInFragment_to_whitelistFragment);
                break;
        }
    }
}