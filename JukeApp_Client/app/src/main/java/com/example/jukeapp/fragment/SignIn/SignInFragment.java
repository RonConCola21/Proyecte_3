package com.example.jukeapp.fragment.SignIn;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jukeapp.R;
import com.example.jukeapp.api.WSCreateUser;
import com.example.jukeapp.databinding.FragmentSignInBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SignInFragment extends Fragment implements View.OnClickListener {
    private BottomNavigationView bottomNav;
    FragmentSignInBinding binding;
    NavController navController;
    SignInViewModel viewModel;
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
        binding.btnSignin.setOnClickListener(this);
        binding.btnLogin.setOnClickListener(this);
        viewModel = new ViewModelProvider(this).get(SignInViewModel.class);
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Error");
                    builder.setMessage("Tots els camps són obligatoris");
                    builder.create().show();
                    break;
                }
                if(!binding.edtPassword.getText().toString().equals(binding.edtPassword2.getText().toString())){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Error");
                    builder.setMessage("Les contrasenyes no coincideixen");
                    builder.create().show();
                    break;
                }

                viewModel.mUser.observe(getViewLifecycleOwner( ), new Observer<WSCreateUser>( ) {
                    @Override
                    public void onChanged(WSCreateUser wsCreateUser) {
                        Log.d("TAG", "Valor success: " + wsCreateUser.getSuccess());
                        if (wsCreateUser.getSuccess().equals("ok")){
                            navController = NavHostFragment.findNavController(SignInFragment.this);
                            navController.navigate(R.id.action_signInFragment_to_whitelistFragment);
                        }
                        else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("Error");
                            builder.setMessage("Error al crear l'usuari");
                            builder.create().show();
                        }
                    }
                });
                viewModel.createUser(binding.edtUser.getText().toString(), binding.edtEmail.getText().toString(), binding.edtPassword.getText().toString());

                break;
        }
    }
}