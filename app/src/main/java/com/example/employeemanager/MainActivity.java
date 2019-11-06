package com.example.employeemanager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.employeemanager.scheme.Account;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;

    private EditText etUser, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUser = findViewById(R.id.et_user);
        etPassword = findViewById(R.id.et_password);
    }

    public void onButtonLogin(View view) {

        String userName = etUser.getText().toString();
        final String passWord = etPassword.getText().toString();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("account").child(userName);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Account account = dataSnapshot.getValue(Account.class);
                if (account != null && account.getPassWord().equals(passWord)){
                    // intent cau noi cac layout
                    Intent intent = new Intent(MainActivity.this, ManagerEmployeeFragment.class);
                    startActivity(intent);
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

    public void onButtonList(View view) {
        Intent intent = new Intent(MainActivity.this, EmployeeList.class);
        startActivity(intent);
    }
}
