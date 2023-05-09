package com.example.jukeapp.fragment;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jukeapp.R;
import com.example.jukeapp.databinding.FragmentProfileBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class ProfileFragment extends Fragment implements View.OnClickListener {
    private BottomNavigationView bottomNav;
    FragmentProfileBinding binding;
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
        getActivity().setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        bottomNav = getActivity().findViewById(R.id.menu_nav);
        bottomNav.setVisibility(View.VISIBLE);
        //Buttons
        binding.btnCancel.setOnClickListener(this);
        binding.btnSave.setOnClickListener(this);
        binding.btnEdit.setOnClickListener(this);
        //Texts and Edits
        //Carregar camps
        carregarCamps();
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnEdit:
                binding.edtPass.setText("");
                backToOrigin(true, View.GONE, View.VISIBLE, "Old Password");
                break;
            case R.id.btnSave:
                backToOrigin(false, View.VISIBLE, View.GONE, "Password");
                //guardar valors previ comprovació
                if (binding.edtUser.getText().toString().equals("")) {
                    binding.txvError.setText("User can't be empty");
                } else if (binding.edtEmail.getText().toString().equals("")) {
                    binding.txvError.setText("Email can't be empty");
                }
                if (binding.edtPass.getText().toString().equals("")){
                    binding. txvError.setText("Old password doesn't match");
                }
                if (binding.edtPass.getText().toString().equals("1234")) {
                    if (binding.edtPass1.getText().toString().equals(binding.edtPass2.getText().toString())) {
                        //guardar valors
                    } else {
                        binding.txvError.setText("Passwords don't match");
                    }
                } else {
                    binding.txvError.setText("Wrong password");
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
        binding.edtUser.setEnabled(enabled);
        binding.txvPass.setText(text);
        binding.edtEmail.setEnabled(enabled);
        binding.edtPass.setEnabled(enabled);
        binding.edtPass2.setEnabled(enabled);
        binding.edtPass1.setEnabled(enabled);
        binding.txvPass1.setVisibility(gone);
        binding.txvPass2.setVisibility(gone);
        binding.edtPass1.setVisibility(gone);
        binding.edtPass2.setVisibility(gone);
        binding.btnEdit.setVisibility(visible);
        binding.txvError.setVisibility(gone);
        binding.btnSave.setVisibility(gone);
        binding.btnCancel.setVisibility(gone);
    }

    private void borrarCamps(){
        binding.edtUser.setText("");
        binding.edtEmail.setText("");
        binding.edtPass.setText("");
        binding.edtPass2.setText("");
        binding.edtPass1.setText("");
        binding.txvError.setText("");
    }

    private void carregarCamps(){
        //carregar camps amb valors de l'usuari
        binding.edtUser.setText("User");
        binding.edtEmail.setText("Email");
        binding.edtPass.setText("1234");
    }
    private void actulitzarCamps() {
    }
}