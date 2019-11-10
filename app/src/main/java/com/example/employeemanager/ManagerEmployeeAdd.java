package com.example.employeemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ManagerEmployeeAdd extends Fragment {

    private TextView tvText;
    private ButtonFragmentListener buttonFragmentListener;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.employee_manager_add, container, false);

        return view;
    }

    public void setButtonFragmentListener(ButtonFragmentListener buttonFragmentListener) {
        this.buttonFragmentListener = buttonFragmentListener;
    }

    public interface ButtonFragmentListener{
        public void onAddSuccess();
        public void onResetSuccess();
    }
}
