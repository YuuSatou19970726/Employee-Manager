package com.example.employeemanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ManagerEmployeeAdd extends Fragment {

    private ButtonFragmentListener buttonFragmentListener;
    private EditText etName;
    private EditText etBirthDay;
    private EditText etAddress;
    private EditText etEmail;
    private EditText etPhoneNumber;
    private EditText etSeniority;
    private EditText etSalary;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.employee_manager_add,
                container, false);

        etName = view.findViewById(R.id.et_hoten);
        etBirthDay = view.findViewById(R.id.et_ntns_add);
        etAddress = view.findViewById(R.id.et_email_add);

        Button buttonAdd = view.findViewById(R.id.bt_add);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonFragmentListener != null){
                    buttonFragmentListener.onAddSuccess();
                }
            }
        });

        Button buttonReturn = view.findViewById(R.id.bt_return);
        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonFragmentListener != null){
                    buttonFragmentListener.onReturnSuccess();
                }
            }
        });

        Button buttonReset = view.findViewById(R.id.bt_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonFragmentListener != null){
                    buttonFragmentListener.onResetSuccess();
                }
            }
        });
        return view;
    }

    public void setButtonFragmentListener(ButtonFragmentListener buttonFragmentListener) {
        this.buttonFragmentListener = buttonFragmentListener;
    }

    public interface ButtonFragmentListener{
        public void onAddSuccess();
        public void onReturnSuccess();
        public void onResetSuccess();
    }
}
