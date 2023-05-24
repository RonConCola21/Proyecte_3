package com.example.jukeapp.fragment.SignIn;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.jukeapp.api.WSCreateUser;

public class SignInViewModel extends ViewModel {
    MutableLiveData<WSCreateUser> mUser = new MutableLiveData<>();

    public void createUser(String username, String email, String password){
        WSCreateUser.createUser(username, email, password, mUser);
    }
}
