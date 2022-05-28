package com.example.myownshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button RegisterBtn, LoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RegisterBtn=(Button)findViewById(R.id.Registr_button);
        LoginBtn=(Button)findViewById(R.id.Login_button);

        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent=new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(Intent);
            }
        });

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent=new Intent(MainActivity.this, LoginActivity.class);
                startActivity(Intent);
            }
        });
    }
}