package com.example.labourservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;

import java.time.Instant;

public class OtpActivity extends AppCompatActivity {
    CountryCodePicker ccp;
    EditText t1;
    Button b1;



    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Toast.makeText(getApplicationContext(),"You can't go back!",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);


        t1=(EditText)findViewById(R.id.t1);
        ccp=(CountryCodePicker) findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(t1);
        b1=(Button) findViewById(R.id.b1);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (t1.getText().toString().isEmpty()){

                    Toast.makeText(OtpActivity.this, "Please Enter the mobile number",Toast.LENGTH_SHORT).show();

                }else {
                    Intent intent=new Intent(OtpActivity.this,ManageOtp.class);
                    intent.putExtra("mobile",ccp.getFullNumberWithPlus().replace(" ",""));
                    startActivity(intent);
                }
            }
        });

    }

}