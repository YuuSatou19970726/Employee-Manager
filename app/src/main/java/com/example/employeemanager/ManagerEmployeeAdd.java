package com.example.employeemanager;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.employeemanager.scheme.Employee;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    private int temp = 0;
    private int i , n;

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

                    // Write value to the database Employee
                    databaseReference = FirebaseDatabase.getInstance().getReference();
                    // get list of data in the employee
                    databaseReference.child("employee").addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                            n = 0;

                            //from list of employee, get the key and use it to call the value you need
                            databaseReference = FirebaseDatabase.getInstance().getReference().child("employee").child(dataSnapshot.getKey());
                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    n = n + 1;
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    // wait function
                    ProgressBar progressBar = new ProgressBar(getActivity());
                    final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                            .setView(progressBar)
                            .setCancelable(false) // do not allow canceling while loading
                            .create();
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    alertDialog.show();

                    if (name.isEmpty() || birthDay.isEmpty() || address.isEmpty() || phoneNumber.isEmpty()
                    || seniority.isEmpty() || salary.isEmpty()){
                        Toast.makeText(getActivity(), "please, fill in the information is missing!", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                        return;
                    }
                    else {
                        // Write value to the database Employee
                        databaseReference = FirebaseDatabase.getInstance().getReference();
                        // get list of data in the employee
                        databaseReference.child("employee").addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                i = 0;
                                temp = 0;
                                //from list of employee, get the key and use it to call the value you need
                                databaseReference = FirebaseDatabase.getInstance().getReference().child("employee").child(dataSnapshot.getKey());
                                databaseReference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        Employee employeeData = dataSnapshot.getValue(Employee.class);
                                        if (employeeData.getName().equalsIgnoreCase(name)
                                                && employeeData.getAddress().equalsIgnoreCase(address)){

                                                etName.setText("");
                                                etBirthDay.setText("");
                                                etAddress.setText("");
                                                etEmail.setText("");
                                                etPhoneNumber.setText("");
                                                etSeniority.setText("");
                                                etSalary.setText("");
                                                temp = 1;

                                                Toast.makeText(getActivity(), "Registered Staff", Toast.LENGTH_SHORT).show();
                                                alertDialog.dismiss();
                                                return;
                                            }
                                        else{
                                            i = i + 1;
                                            if (temp == 0 && n == i){
                                                // Write value to the database Employee
                                                databaseReference = FirebaseDatabase.getInstance().getReference();

                                                Employee employee = new Employee(
                                                        name, birthDay, address, email,
                                                        phoneNumber, seniority, salary, data);
                                                databaseReference.child("employee").push().setValue(employee);

                                                etName.setText("");
                                                etBirthDay.setText("");
                                                etAddress.setText("");
                                                etEmail.setText("");
                                                etPhoneNumber.setText("");
                                                etSeniority.setText("");
                                                etSalary.setText("");

                                                Toast.makeText(getActivity(), "Add Success", Toast.LENGTH_SHORT).show();
                                                alertDialog.dismiss();
                                            }
                                            else {
                                                alertDialog.dismiss();
                                                return;
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                        alertDialog.dismiss();
                                        return;
                                    }
                                });
                            }

                            @Override
                            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                            }

                            @Override
                            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                            }

                            @Override
                            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                    buttonFragmentListener.onAddSuccess();
                }
            }
        });

        Button buttonReturn = view.findViewById(R.id.bt_return);
        buttonReturn.setOnClickListener(new View.OnClickListener() {
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

                    if (salary != null){
                        etSalary.setText("");
                    }
                    else if (seniority != null)
                    {
                        etSeniority.setText("");
                    }
                    else if (phoneNumber != null){
                        etPhoneNumber.setText("");
                    }
                    else if (email != null){
                        etEmail.setText("");

                    }
                    else if (address != null){
                        etAddress.setText("");
                    }
                    else if (birthDay != null){
                        etBirthDay.setText("");
                    }
                    else if (name != null){
                        etName.setText("");
                    }
                    else {
                        return;
                    }

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

                    Toast.makeText(getActivity(), "Reset Success",Toast.LENGTH_LONG).show();
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
