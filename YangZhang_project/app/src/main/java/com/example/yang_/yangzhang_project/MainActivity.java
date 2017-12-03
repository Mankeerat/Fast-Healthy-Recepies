package com.example.yang_.yangzhang_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.yang_.yangzhang_project.database.MyHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button loginButton;
    Button signupButton;

    SQLiteDatabase recipeDataBase;
    public static final String DEFAULT = "not available";
    MyHelper helper = new MyHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);

        signupButton = (Button) findViewById(R.id.signupButton);
        signupButton.setOnClickListener(this);

        recipeDataBase = helper.getWritableDatabase();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.loginButton:
                SharedPreferences sharedPrefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                String email = sharedPrefs.getString("email", DEFAULT);
                String password = sharedPrefs.getString("password", DEFAULT);

                if (email.equals(DEFAULT) || password.equals(DEFAULT)) {
                    Toast.makeText(this, "Not registered, please sign up", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "WelcomeBack" + email + "!", Toast.LENGTH_LONG).show();
                    Intent homeIntent = new Intent(this, HomeActivity.class);
                    startActivity(homeIntent);
                }
                break;

            case R.id.signupButton:
                Intent signupIntent = new Intent(this, SignUpActivity.class);
                startActivity(signupIntent);
                break;
        }

    }
}
