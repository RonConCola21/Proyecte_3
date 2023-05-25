package com.example.jukeapp.fragment.SignIn;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.jukeapp.api.GetUserSuccess;
import com.example.jukeapp.api.WSCreate;

public class SignInViewModel extends ViewModel {
    MutableLiveData<GetUserSuccess> mUser = new MutableLiveData<>();

    public void createUser(String username, String email, String password){
        GetUserSuccess.createUser(username, email, password, mUser);
    }

}
