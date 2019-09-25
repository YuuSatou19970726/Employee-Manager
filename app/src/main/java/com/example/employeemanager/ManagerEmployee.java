package com.example.employeemanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ManagerEmployee extends AppCompatActivity {

    private TextView tvText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_manager);

        tvText = findViewById(R.id.tv_text);

        String user = getIntent().getExtras().getString("USER_ADMIN");
        String password = getIntent().getExtras().getString("PASSWORD_ADMIN");
        String manager = getIntent().getExtras().getString("MANAGER_ADMIN");

        if (user.equals("admin") && password.equals("admin"))
        {
            if (manager.equalsIgnoreCase("Dung Nguyen"))
            {
                tvText.setText("こんにちは " + manager);
            }
            if (manager.equalsIgnoreCase("Tai Anh"))
            {
                tvText.setText("Hi! " + manager);
            }
        }
        else
        {
            Intent intent = new Intent(ManagerEmployee.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
