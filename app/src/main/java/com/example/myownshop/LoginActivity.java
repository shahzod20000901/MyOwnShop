package com.example.myownshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myownshop.Admins.AdminCategoryActivity;
import com.example.myownshop.Model.Users;
import com.example.myownshop.Users.HomeActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    public Button LoginBtn;
    public EditText InputNumber, InputPassword;
    public TextView AdminLink, NotAdminLink;
    public String ParentDbName="Users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        LoginBtn = findViewById(R.id.Login_button);
        InputNumber=(EditText) findViewById(R.id.input_number);
        InputPassword=(EditText) findViewById(R.id.input_password);
        AdminLink=(TextView) findViewById(R.id.Admin_link);
        NotAdminLink=(TextView) findViewById(R.id.Not_Admin_link);

        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminLink.setVisibility(View.INVISIBLE);
                NotAdminLink.setVisibility(View.VISIBLE);
                ParentDbName="Admins";
            }
        });

        NotAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotAdminLink.setVisibility(View.INVISIBLE);
                AdminLink.setVisibility(View.VISIBLE);
                ParentDbName="Users";
            }
        });

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernumber=InputNumber.getText().toString();
                String userpassword=InputPassword.getText().toString();

                if(TextUtils.isEmpty(usernumber))
                {
                    Toast.makeText(LoginActivity.this, "Введите номер", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(userpassword))
                {
                    Toast.makeText(LoginActivity.this, "Введите пароль", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Validate(usernumber, userpassword);
                }
            }
        });


    }

    private void Validate(final String usernumber, final String userpassword) {
        DatabaseReference Rotter;
        Rotter= FirebaseDatabase.getInstance().getReference();

        Rotter.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(ParentDbName).child(usernumber).exists())
                {
                    Users userdata=snapshot.child(ParentDbName).child(usernumber).getValue(Users.class);
                    if(snapshot.child(ParentDbName).child(usernumber).exists())
                    {

                        if(userdata.getUsernumber().equals(usernumber))
                        {
                            if(userdata.getUserpassword().equals(userpassword))
                            {
                                if(ParentDbName.equals("Users"))
                                {
                                    Toast.makeText(LoginActivity.this, "Успешный вход!", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                }
                                else
                                {
                                    Toast.makeText(LoginActivity.this, "Успешный вход админа!!!", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                }
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this, "Неверный пароль", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Аккаунт с номером "+usernumber+" не существует!!!", Toast.LENGTH_SHORT).show();
                            Intent intent =new Intent(LoginActivity.this, RegisterActivity.class);
                            startActivity(intent);
                        }
                    }
                }
                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}