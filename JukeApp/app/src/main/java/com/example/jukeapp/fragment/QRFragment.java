package com.example.jukeapp.fragment;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.jukeapp.MainActivity;
import com.example.jukeapp.R;
import com.example.jukeapp.databinding.FragmentQRBinding;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class QRFragment extends Fragment implements View.OnClickListener {
    FragmentQRBinding binding;
    private String lectura;
    public QRFragment() {
        // Required empty public constructor
    }

    public static QRFragment newInstance(String param1, String param2) {
        QRFragment fragment = new QRFragment();
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
//        getActivity().setRequestedOrientation(
//                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        binding = FragmentQRBinding.inflate(inflater, container, false);
        binding.btnScan.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnScan:
                ScanOptions options = new ScanOptions();
                options.setOrientationLocked(false);
                barcodeLauncher.launch(options);
                break;
        }
    }

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(this.getContext(), "Cancelled", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this.getContext(), "Token scanned", Toast.LENGTH_LONG).show();
                    lectura = result.getContents();
                    Toast.makeText(this.getContext(), lectura, Toast.LENGTH_LONG).show();
                }
            });
}