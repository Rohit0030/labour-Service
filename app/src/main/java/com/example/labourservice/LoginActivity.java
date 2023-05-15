package com.example.labourservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText userEdt, passEdt;
    private Button loginBtn;
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Toast.makeText(getApplicationContext(),"Sorry, You Cannot Go Back!!",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        intView();
        setVariable();
    }
    private void intView(){
        userEdt=findViewById(R.id.editTextTextPersonName2);
        passEdt=findViewById(R.id.editTextTextPassword);
        loginBtn=findViewById(R.id.loginBtn);
    }
    private void setVariable(){
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userEdt.getText().toString().isEmpty() || passEdt.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please fill the login form",Toast.LENGTH_SHORT).show();
                }else if (userEdt.getText().toString().equals("rohit")&& passEdt.getText().toString().equals("rohit")){

                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }else {
                    Toast.makeText(LoginActivity.this, "Please enter correct user name & pswd",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}