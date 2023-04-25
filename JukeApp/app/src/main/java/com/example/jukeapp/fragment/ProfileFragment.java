package com.example.jukeapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jukeapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class ProfileFragment extends Fragment implements View.OnClickListener {
    private BottomNavigationView bottomNav;
    private Button btnEdit;
    private Button btnSave;
    private Button btnCancel;
    private EditText edtUser;
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtConfirmPassword;
    private EditText edtNewPassword;
    private TextView txvPass;
    private TextView txvConfirmPass;
    private TextView txvNewPass;
    private TextView txvError;
    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        bottomNav = getActivity().findViewById(R.id.menu_nav);
        bottomNav.setVisibility(View.VISIBLE);
        //Buttons
        btnEdit = view.findViewById(R.id.btnEdit);
        btnSave = view.findViewById(R.id.btnSave);
        btnCancel = view.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
        //Texts and Edits
        edtUser = view.findViewById(R.id.edtUser);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtPassword = view.findViewById(R.id.edtPass);
        edtConfirmPassword = view.findViewById(R.id.edtPass2);
        edtNewPassword = view.findViewById(R.id.edtPass1);
        txvPass = view.findViewById(R.id.txvPass);
        txvConfirmPass = view.findViewById(R.id.txvPass2);
        txvNewPass = view.findViewById(R.id.txvPass1);
        txvError = view.findViewById(R.id.txvError);
        //Carregar camps
        carregarCamps();
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnEdit:
                edtPassword.setText("");
                backToOrigin(true, View.GONE, View.VISIBLE, "Old Password");
                break;
            case R.id.btnSave:
                backToOrigin(false, View.VISIBLE, View.GONE, "Password");
                //guardar valors previ comprovació
                if (edtUser.getText().toString().equals("")) {
                    txvError.setText("User can't be empty");
                } else if (edtEmail.getText().toString().equals("")) {
                    txvError.setText("Email can't be empty");
                }
                if (edtPassword.getText().toString().equals("")){
                    txvError.setText("Old password doesn't match");
                }
                if (edtPassword.getText().toString().equals("1234")) {
                    if (edtNewPassword.getText().toString().equals(edtConfirmPassword.getText().toString())) {
                        //guardar valors
                    } else {
                        txvError.setText("Passwords don't match");
                    }
                } else {
                    txvError.setText("Wrong password");
                }
                borrarCamps();
                actulitzarCamps();
                carregarCamps();
                break;
            case R.id.btnCancel:
                backToOrigin(false, View.VISIBLE, View.GONE, "Password");
                //retornar valors originals als camps
                borrarCamps();
                carregarCamps();
                break;
        }
    }



    private void backToOrigin(boolean enabled, int visible, int gone, String text) {
        edtUser.setEnabled(enabled);
        txvPass.setText(text);
        edtEmail.setEnabled(enabled);
        edtPassword.setEnabled(enabled);
        edtConfirmPassword.setEnabled(enabled);
        edtNewPassword.setEnabled(enabled);
        txvConfirmPass.setVisibility(gone);
        txvNewPass.setVisibility(gone);
        edtNewPassword.setVisibility(gone);
        edtConfirmPassword.setVisibility(gone);
        btnEdit.setVisibility(visible);
        txvError.setVisibility(gone);
        btnSave.setVisibility(gone);
        btnCancel.setVisibility(gone);
    }

    private void borrarCamps(){
        edtUser.setText("");
        edtEmail.setText("");
        edtPassword.setText("");
        edtConfirmPassword.setText("");
        edtNewPassword.setText("");
        txvError.setText("");
    }

    private void carregarCamps(){
        //carregar camps amb valors de l'usuari
        edtUser.setText("User");
        edtEmail.setText("Email");
        edtPassword.setText("1234");
    }
    private void actulitzarCamps() {
    }
}