// Generated by view binder compiler. Do not edit!
package com.example.jukeapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.jukeapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentSignInBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final GridLayout Grid;

  @NonNull
  public final Button btnLogin;

  @NonNull
  public final Button btnSignin;

  @NonNull
  public final EditText edtEmail;

  @NonNull
  public final EditText edtPassword;

  @NonNull
  public final EditText edtPassword2;

  @NonNull
  public final EditText edtUser;

  @NonNull
  public final TextView txvEmail;

  @NonNull
  public final TextView txvPassword;

  @NonNull
  public final TextView txvPassword2;

  @NonNull
  public final TextView txvTitol;

  @NonNull
  public final TextView txvUser;

  private FragmentSignInBinding(@NonNull LinearLayout rootView, @NonNull GridLayout Grid,
      @NonNull Button btnLogin, @NonNull Button btnSignin, @NonNull EditText edtEmail,
      @NonNull EditText edtPassword, @NonNull EditText edtPassword2, @NonNull EditText edtUser,
      @NonNull TextView txvEmail, @NonNull TextView txvPassword, @NonNull TextView txvPassword2,
      @NonNull TextView txvTitol, @NonNull TextView txvUser) {
    this.rootView = rootView;
    this.Grid = Grid;
    this.btnLogin = btnLogin;
    this.btnSignin = btnSignin;
    this.edtEmail = edtEmail;
    this.edtPassword = edtPassword;
    this.edtPassword2 = edtPassword2;
    this.edtUser = edtUser;
    this.txvEmail = txvEmail;
    this.txvPassword = txvPassword;
    this.txvPassword2 = txvPassword2;
    this.txvTitol = txvTitol;
    this.txvUser = txvUser;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentSignInBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentSignInBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_sign_in, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentSignInBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.Grid;
      GridLayout Grid = ViewBindings.findChildViewById(rootView, id);
      if (Grid == null) {
        break missingId;
      }

      id = R.id.btnLogin;
      Button btnLogin = ViewBindings.findChildViewById(rootView, id);
      if (btnLogin == null) {
        break missingId;
      }

      id = R.id.btnSignin;
      Button btnSignin = ViewBindings.findChildViewById(rootView, id);
      if (btnSignin == null) {
        break missingId;
      }

      id = R.id.edtEmail;
      EditText edtEmail = ViewBindings.findChildViewById(rootView, id);
      if (edtEmail == null) {
        break missingId;
      }

      id = R.id.edtPassword;
      EditText edtPassword = ViewBindings.findChildViewById(rootView, id);
      if (edtPassword == null) {
        break missingId;
      }

      id = R.id.edtPassword2;
      EditText edtPassword2 = ViewBindings.findChildViewById(rootView, id);
      if (edtPassword2 == null) {
        break missingId;
      }

      id = R.id.edtUser;
      EditText edtUser = ViewBindings.findChildViewById(rootView, id);
      if (edtUser == null) {
        break missingId;
      }

      id = R.id.txvEmail;
      TextView txvEmail = ViewBindings.findChildViewById(rootView, id);
      if (txvEmail == null) {
        break missingId;
      }

      id = R.id.txvPassword;
      TextView txvPassword = ViewBindings.findChildViewById(rootView, id);
      if (txvPassword == null) {
        break missingId;
      }

      id = R.id.txvPassword2;
      TextView txvPassword2 = ViewBindings.findChildViewById(rootView, id);
      if (txvPassword2 == null) {
        break missingId;
      }

      id = R.id.txvTitol;
      TextView txvTitol = ViewBindings.findChildViewById(rootView, id);
      if (txvTitol == null) {
        break missingId;
      }

      id = R.id.txvUser;
      TextView txvUser = ViewBindings.findChildViewById(rootView, id);
      if (txvUser == null) {
        break missingId;
      }

      return new FragmentSignInBinding((LinearLayout) rootView, Grid, btnLogin, btnSignin, edtEmail,
          edtPassword, edtPassword2, edtUser, txvEmail, txvPassword, txvPassword2, txvTitol,
          txvUser);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
