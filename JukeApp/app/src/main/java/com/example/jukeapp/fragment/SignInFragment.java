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

public class SignInFragment extends Fragment implements View.OnClickListener {
    private BottomNavigationView bottomNav;
    private EditText mEdtName;
    private EditText mEdtEmail;
    private EditText mEdtPassword;
    private EditText mEdtConfirmPassword;
    private Button signInButton;
    private Button loginButton;
    private TextView mtxvError;
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
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        signInButton = view.findViewById(R.id.btnLogin);
        loginButton = view.findViewById(R.id.btnSignin);
        mEdtName = view.findViewById(R.id.edtUser);
        mEdtEmail = view.findViewById(R.id.edtEmail);
        mEdtPassword = view.findViewById(R.id.edtPassword);
        bottomNav = getActivity().findViewById(R.id.menu_nav);
        bottomNav.setVisibility(View.GONE);
        mEdtConfirmPassword = view.findViewById(R.id.edtPassword2);
        mtxvError = view.findViewById(R.id.txvError);
        mtxvError.setText("");
        signInButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                navController = NavHostFragment.findNavController(SignInFragment.this);
                navController.navigate(R.id.action_signInFragment_to_logInFragment2);
                break;
            case R.id.btnSignin:
                if(mEdtName.getText().toString().isEmpty() || mEdtEmail.getText().toString().isEmpty() || mEdtPassword.getText().toString().isEmpty() || mEdtConfirmPassword.getText().toString().isEmpty()){
                    mtxvError.setText("Todos los campos son obligatorios");
                    return;
                }
                if(!mEdtPassword.getText().toString().equals(mEdtConfirmPassword.getText().toString())){
                    mtxvError.setText("Las contraseñas no coinciden");
                    return;
                }
                navController = NavHostFragment.findNavController(SignInFragment.this);
                navController.navigate(R.id.action_signInFragment_to_whitelistFragment);
                break;
        }
    }
}