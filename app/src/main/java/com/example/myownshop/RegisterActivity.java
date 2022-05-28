package com.example.myownshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private Button RegisterButton;
    private EditText InputName, InputNumber, InputAdress, InputPassword;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Init();
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccaunt();
            }
        });
    }


    private void CreateAccaunt() {
        String username=InputName.getText().toString();
        String usernumber=InputNumber.getText().toString();
        String useraddress=InputAdress.getText().toString();
        String userpassword=InputPassword.getText().toString();

        if(TextUtils.isEmpty(username))
        {
            Toast.makeText(this, "Введите имя", Toast.LENGTH_SHORT).show();
        }
        else if(usernumber.isEmpty())
        {
            Toast.makeText(this, "Введите номер теефона", Toast.LENGTH_SHORT).show();
        }
        else if(useraddress.isEmpty())
        {
            Toast.makeText(this, "Введите адрес", Toast.LENGTH_SHORT).show();
        }
        else if(userpassword.isEmpty())
        {
            Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Создание аккаунта...");
            loadingBar.setMessage("Пожалуйста подождите!");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            ValidatePhone(username, usernumber, useraddress, userpassword);
        }

    }

    private void ValidatePhone(final String username, final String usernumber, final String useraddress, final String userpassword) {
        DatabaseReference Rotref = FirebaseDatabase.getInstance().getReference();

        Rotref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.child("Users").child(usernumber).exists()))
                {
                    HashMap<String, Object> userDataMap=new HashMap<>();
                    userDataMap.put("username", username);
                    userDataMap.put("usernumber", usernumber);
                    userDataMap.put("useraddress", useraddress);
                    userDataMap.put("userpassword", userpassword);


                    Rotref.child("Users").child(usernumber).updateChildren(userDataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        loadingBar.dismiss();
                                        Toast.makeText(RegisterActivity.this, "Регистрация прошла успешно!", Toast.LENGTH_SHORT).show();

                                    }
                                    else
                                    {
                                        loadingBar.dismiss();
                                        Toast.makeText(RegisterActivity.this, "Ошибка", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else
                {
                    loadingBar.dismiss();
                    Toast.makeText(RegisterActivity.this, "Номер "+usernumber +" уже зарегистрирован!", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void Init() {
        RegisterButton=(Button) findViewById(R.id.Register_input);
        loadingBar=new ProgressDialog(this);
        InputName=(EditText) findViewById(R.id.input_name);
        InputNumber=(EditText) findViewById(R.id.input_number);
        InputAdress=(EditText) findViewById(R.id.input_address);
        InputPassword=(EditText) findViewById(R.id.input_password);
    }

}