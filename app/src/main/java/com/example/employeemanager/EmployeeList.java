package com.example.employeemanager;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.employeemanager.scheme.Employee;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EmployeeList extends AppCompatActivity {

    private ListView listView;
    private List<Employee> employeeList;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_list);

        employeeList = new ArrayList<>();

        // get data from FireBase
        databaseReference = FirebaseDatabase.getInstance().getReference();
        // get list of data in the employee
        databaseReference.child("employee").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //from list of account, get the key and use it to call the value you need
                databaseReference = FirebaseDatabase.getInstance().getReference().child("employee").child(dataSnapshot.getKey());
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Employee employee = dataSnapshot.getValue(Employee.class);
                        employeeList.add(new Employee(employee.getName(), employee.getAddress(), employee.getPhoneNumber(), employee.getEmail()));
                        listView = findViewById(R.id.lv_list);
                        LineListAdapter lineListAdapter = new LineListAdapter(EmployeeList.this, (ArrayList<Employee>) employeeList);
                        listView.setAdapter(lineListAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
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
}
