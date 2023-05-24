package com.example.jukeapp.fragment.LogIn;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.jukeapp.api.GetUserSuccess;
import com.example.jukeapp.api.Song;

import java.util.List;

public class LogInViewModel extends ViewModel {
    public MutableLiveData<GetUserSuccess> mUser = new MutableLiveData<>();

    public void getUser(String user_nick, String user_password) {
        GetUserSuccess.getUser(user_nick, user_password, mUser);
    }
}
