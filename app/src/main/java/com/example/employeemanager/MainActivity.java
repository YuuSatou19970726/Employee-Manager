package com.example.employeemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText etUser, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUser = findViewById(R.id.et_user);
        etPassword = findViewById(R.id.et_password);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
    }

    public void onButtonLogin(View view) {

        // intent cau noi cac layout
        Intent intent = new Intent(MainActivity.this, ManagerEmployeeFragment.class);
        startActivity(intent);
    }

    public void onButtonList(View view) {
        Intent intent = new Intent(MainActivity.this, EmployeeList.class);
        startActivity(intent);
    }
}
