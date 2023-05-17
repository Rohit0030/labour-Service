package com.example.labourservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class ManageOtp extends AppCompatActivity {


    EditText t2;
    Button b2;
    String phonenumber;
    FirebaseAuth mAuth;
    String optid;

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"Enter OTP",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_otp);


        phonenumber=getIntent().getStringExtra("mobile").toString();
        t2=(EditText) findViewById(R.id.t2);
        b2=(Button) findViewById(R.id.b2);
        mAuth=FirebaseAuth.getInstance();


        initiateopt();
      b2.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if (t2.getText().toString().isEmpty())
                  Toast.makeText(getApplicationContext(),"Enter OTP to Sign In",Toast.LENGTH_LONG).show();
              else if (t2.getText().toString().length()!=6)
                  Toast.makeText(getApplicationContext(),"Invelid OTP",Toast.LENGTH_LONG).show();
              else {
                  PhoneAuthCredential credential=PhoneAuthProvider.getCredential(optid,t2.getText().toString());
                  signInWithPhoneAuthCredential(credential);

              }
          }
      });
    }

    private void initiateopt()
    {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phonenumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // (optional) Activity for callback binding
                        // If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

     PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential credential)
        {
            signInWithPhoneAuthCredential(credential);

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e)
        {

            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();



        }

        @Override
        public void onCodeSent(@NonNull String verificationId,
                @NonNull PhoneAuthProvider.ForceResendingToken token) {

            optid=verificationId;

        }
    };


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            startActivity(new Intent(ManageOtp.this,MainActivity.class));
                            finish();
                        } else
                        {
                            Toast.makeText(getApplicationContext(),"signing Code Error",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}