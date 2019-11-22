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

import com.example.employeemanager.scheme.Employee;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ManagerEmployeeAdd extends Fragment {

    private ButtonFragmentListener buttonFragmentListener;
    private EditText etName;
    private EditText etBirthDay;
    private EditText etAddress;
    private EditText etEmail;
    private EditText etPhoneNumber;
    private EditText etSeniority;
    private EditText etSalary;
    private DatabaseReference databaseReference;
    private String name, birthDay, address, email, phoneNumber, seniority, salary, data;

    public ManagerEmployeeAdd (){
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = getArguments().getString("MANAGER_EMPLOYEE_ADD");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.employee_manager_add,
                container, false);

        etName = view.findViewById(R.id.et_hoten);
        etBirthDay = view.findViewById(R.id.et_ntns_add);
        etAddress = view.findViewById(R.id.et_diachi);
        etEmail = view.findViewById(R.id.et_email_add);
        etPhoneNumber = view.findViewById(R.id.et_sdt_add);
        etSeniority = view.findViewById(R.id.et_thamnien_add);
        etSalary = view.findViewById(R.id.et_mucluong_add);

        Button buttonAdd = view.findViewById(R.id.bt_add);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etName.getText().toString();
                birthDay = etBirthDay.getText().toString();
                address = etAddress.getText().toString();
                email = etEmail.getText().toString();
                phoneNumber = etPhoneNumber.getText().toString();
                seniority = etSeniority.getText().toString();
                salary = etSalary.getText().toString();

                if (buttonFragmentListener != null){

                    Employee employee = new Employee(
                            name, birthDay, address, email,
                            phoneNumber, seniority, salary, data);

                    // Write value to the database Employee
                    databaseReference = FirebaseDatabase.getInstance().getReference();
                    databaseReference.child("employee").push().setValue(employee);

                    etName.setText("");
                    etBirthDay.setText("");
                    etAddress.setText("");
                    etEmail.setText("");
                    etPhoneNumber.setText("");
                    etSeniority.setText("");
                    etSalary.setText("");

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

                    etName.setText("");
                    etBirthDay.setText("");
                    etAddress.setText("");
                    etEmail.setText("");
                    etPhoneNumber.setText("");
                    etSeniority.setText("");
                    etSalary.setText("");

                    buttonFragmentListener.onResetSuccess();
                }
            }
        });
        return view;
    }

    public void setButtonFragmentListener(ButtonFragmentListener buttonFragmentListener) {
        this.buttonFragmentListener = buttonFragmentListener;
    }

    public static  ManagerEmployeeAdd newInstance(String data){
        Bundle bundle = new Bundle();
        bundle.putString("MANAGER_EMPLOYEE_ADD", data);
        ManagerEmployeeAdd managerEmployeeAdd = new ManagerEmployeeAdd();
        managerEmployeeAdd.setArguments(bundle);
        return managerEmployeeAdd;
    }

    public interface ButtonFragmentListener{
        public void onAddSuccess();
        public void onReturnSuccess();
        public void onResetSuccess();
    }
}
