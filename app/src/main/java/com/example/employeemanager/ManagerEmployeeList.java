package com.example.employeemanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.employeemanager.scheme.Employee;

import java.util.List;

public class ManagerEmployeeList extends Fragment {

    private String data;
    private ListView listViewManager;
    private List<Employee> employeeList;

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
