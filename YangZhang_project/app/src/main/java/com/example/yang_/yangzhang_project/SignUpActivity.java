package com.example.yang_.yangzhang_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    EditText emailEditText;
    EditText passwordEditText;

    Button submitButton;
    Button gotoLoginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailEditText = (EditText)  findViewById(R.id.emailEditText);
        passwordEditText = (EditText)  findViewById(R.id.passwordEditText);

        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);

        gotoLoginButton = (Button) findViewById(R.id.gotoLoginButton);
        gotoLoginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.submitButton:
                SharedPreferences sharedPrefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString("email", emailEditText.getText().toString());
                editor.putString("password", passwordEditText.getText().toString());
                Toast.makeText(this, "You have successfully registered!", Toast.LENGTH_LONG).show();
                editor.commit();
                break;

            case R.id.gotoLoginButton:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }

    }
}
