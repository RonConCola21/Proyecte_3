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
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class ForgottenPasswordFragment extends Fragment implements View.OnClickListener {
    private BottomNavigationView bottomNav;
    private Button btnBack;
    private Button btnSend;
    private TextView txvError;
    private EditText edtEmail;
    private NavController navController;
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
        View view = inflater.inflate(R.layout.fragment_forgotten_password, container, false);
        btnSend = view.findViewById(R.id.btnSend);
        btnBack = view.findViewById(R.id.btnBack);
        txvError = view.findViewById(R.id.txvError);
        edtEmail = view.findViewById(R.id.edtEmail);
        bottomNav = getActivity().findViewById(R.id.menu_nav);
        bottomNav.setVisibility(View.GONE);
        btnBack.setOnClickListener(this);
        btnSend.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSend) {
            if (edtEmail.getText().toString().isEmpty()) {
                txvError.setText("Please enter your email address!");
            } else
                txvError.setText("Email sent to " + edtEmail.getText().toString() + "!");
                btnBack.setVisibility(View.VISIBLE);
        } else if (view.getId() == R.id.btnBack && btnBack.getVisibility() == View.VISIBLE) {
            navController = NavHostFragment.findNavController(ForgottenPasswordFragment.this);
            navController.navigate(R.id.action_forgottenPasswordFragment3_to_logInFragment);
        }
    }
}