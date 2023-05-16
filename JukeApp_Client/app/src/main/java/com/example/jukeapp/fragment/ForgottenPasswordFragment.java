package com.example.jukeapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jukeapp.R;
import com.example.jukeapp.databinding.FragmentForgottenPasswordBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class ForgottenPasswordFragment extends Fragment implements View.OnClickListener {
    FragmentForgottenPasswordBinding binding;
    NavController navController;
    BottomNavigationView bottomNav;
    public ForgottenPasswordFragment() {
        // Required empty public constructor
    }

    public static ForgottenPasswordFragment newInstance() {
        ForgottenPasswordFragment fragment = new ForgottenPasswordFragment();
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
        binding = FragmentForgottenPasswordBinding.inflate(inflater, container, false);
        bottomNav = getActivity().findViewById(R.id.menu_nav);
        bottomNav.setVisibility(View.GONE);
        binding.btnBack.setOnClickListener(this);
        binding.btnSend.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSend) {
            if (binding.edtEmail.getText().toString().isEmpty()) {
                binding.txvError.setText("Please enter your email address!");
            } else
                binding.txvError.setText("Email sent to " + binding.edtEmail.getText().toString() + "!");
                binding.btnBack.setVisibility(View.VISIBLE);
        } else if (view.getId() == R.id.btnBack && binding.btnBack.getVisibility() == View.VISIBLE) {
            navController = NavHostFragment.findNavController(ForgottenPasswordFragment.this);
            navController.navigate(R.id.action_forgottenPasswordFragment3_to_logInFragment);
        }
    }
}