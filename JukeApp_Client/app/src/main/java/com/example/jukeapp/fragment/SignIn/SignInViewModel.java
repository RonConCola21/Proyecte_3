package com.example.jukeapp.fragment.SignIn;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.jukeapp.api.WSCreate;

public class SignInViewModel extends ViewModel {
    MutableLiveData<WSCreate> mUser = new MutableLiveData<>();

    public void createUser(String username, String email, String password){
        WSCreate.createUser(username, email, password, mUser);
    }
}
