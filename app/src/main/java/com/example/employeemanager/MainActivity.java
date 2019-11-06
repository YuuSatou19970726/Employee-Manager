package com.example.employeemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.employeemanager.scheme.Account;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;

    @BindView(R.id.et_user) EditText etUser;

    @BindView(R.id.et_password) EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(MainActivity.this);
    }

    public void onButtonLogin(View view) {

        String userName = etUser.getText().toString();
        final String passWord = etPassword.getText().toString();

        ProgressBar progressBar = new ProgressBar(MainActivity.this);
        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                .setView(progressBar)
                .setCancelable(false)
                .create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("account").child(userName);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Account account = dataSnapshot.getValue(Account.class);
                if (account != null && account.getPassWord().equals(passWord)){
                    // intent cau noi cac layout
                    Intent intent = new Intent(MainActivity.this, ManagerEmployeeFragment.class);
                    startActivity(intent);
                    alertDialog.dismiss();
                }
                else {
                    alertDialog.dismiss();
                    return;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                alertDialog.dismiss();
                return;
            }
        });
    }

    public void onButtonList(View view) {
        Intent intent = new Intent(MainActivity.this, EmployeeList.class);
        startActivity(intent);
    }
}
