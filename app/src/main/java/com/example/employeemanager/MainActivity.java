package com.example.employeemanager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.employeemanager.scheme.Account;
import com.google.firebase.database.ChildEventListener;
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

        // activity library ButterKnife
        ButterKnife.bind(MainActivity.this);
    }

    public void onButtonLogin(View view) {

        final String userName = etUser.getText().toString();
        final String passWord = etPassword.getText().toString();

        if (etUser.getText().toString().isEmpty() && etPassword.getText().toString().isEmpty()){
            Toast.makeText(MainActivity.this, "missing name, password", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (etUser.getText().toString().isEmpty()){
            Toast.makeText(MainActivity.this, "missing password", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (etPassword.getText().toString().isEmpty()){
            Toast.makeText(MainActivity.this, "missing password", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (etUser.getText().toString() != null && etPassword.getText().toString() != null){

            // wait function
            ProgressBar progressBar = new ProgressBar(MainActivity.this);
            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                    .setView(progressBar)
                    .setCancelable(false) // do not allow canceling while loading
                    .create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialog.show();

            // get data from FireBase
            databaseReference = FirebaseDatabase.getInstance().getReference();
            // get list of data in the account
            databaseReference.child("account").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    //from list of account, get the key and use it to call the value you need
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("account").child(dataSnapshot.getKey());
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Account account = dataSnapshot.getValue(Account.class);
                            if (account != null && account.getUserName().equals(userName)){
                                if (account.getPassWord().equals(passWord)){

                                    Toast.makeText(MainActivity.this, "login success", Toast.LENGTH_SHORT).show();

                                    // data send on Bundle
                                    Bundle bundle = new Bundle();
                                    bundle.putString("USER_NAME_MANAGER_EMPLOYEE_FRAGMENT", userName);

                                    // intent bridges the layouts
                                    Intent intent = new Intent(MainActivity.this, ManagerEmployeeFragment.class);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                    alertDialog.dismiss();
                                }
                                else {
                                    Toast.makeText(MainActivity.this, "incorrect password please try again", Toast.LENGTH_LONG).show();
                                    etPassword.setText("");
                                    alertDialog.dismiss();
                                    return;
                                }
                            }
                            else{
                                Toast.makeText(MainActivity.this, "your account is not registered", Toast.LENGTH_LONG).show();
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

    public void onButtonList(View view) {
        Intent intent = new Intent(MainActivity.this, EmployeeList.class);
        startActivity(intent);
    }
}
