package com.example.dell.DTUhack;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    Button btnlogin;

    TextView tvcreateaccount;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        btnlogin = findViewById(R.id.btn_login);

        tvcreateaccount = findViewById(R.id.tv_createAccount);

        tvcreateaccount.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);


        tvcreateaccount.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                startActivity(new Intent(LoginActivity.this, RegistrationTabbedActivity.class));

            }

        });

    }
}
