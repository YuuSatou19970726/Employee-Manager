package com.example.employeemanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.employeemanager.scheme.Employee;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ManagerEmployeeList extends Fragment {

    private String data;
    private ListView listViewManager;
    private List<Employee> employeeListManager;
    private DatabaseReference databaseReference;

    public ManagerEmployeeList(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = getArguments().getString("MANAGER_EMPLOYEE_LIST");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.employee_manager_list, container, false);
        employeeListManager = new ArrayList<>();


        listViewManager = view.findViewById(R.id.lv_manager_list);

        // get data from FireBase
        databaseReference = FirebaseDatabase.getInstance().getReference();
        // get list of data in the employee
        databaseReference.child("employee").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                databaseReference = FirebaseDatabase.getInstance().getReference().child("employee").child(dataSnapshot.getKey());
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Employee employee = dataSnapshot.getValue(Employee.class);
                        if (employee.getManager().equals(data)){
                            employeeListManager.add(new Employee(employee.getName(), employee.getAddress(), employee.getEmail(), employee.getPhoneNumber(), employee.getSeniority(), employee.getSalary()));
                            EmployeeAdapter employeeAdapter = new EmployeeAdapter(getActivity(), (ArrayList<Employee>) employeeListManager);
                            listViewManager.setAdapter(employeeAdapter);
                        }
                        else {
                            return;
                        }
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
        return view;
    }

    public static ManagerEmployeeList newInstance(String data){
        Bundle bundle = new Bundle();
        bundle.putString("MANAGER_EMPLOYEE_LIST", data);
        ManagerEmployeeList managerEmployeeList = new ManagerEmployeeList();
        managerEmployeeList.setArguments(bundle);
        return managerEmployeeList;
    }
}
