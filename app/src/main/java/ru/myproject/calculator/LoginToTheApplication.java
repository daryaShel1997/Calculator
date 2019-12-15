package ru.myproject.calculator;

import androidx.annotation.CheckResult;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginToTheApplication extends AppCompatActivity {
    TextView twLogin;
    EditText etLogin, etPassword;
    Button btnEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_to_the_application);

        twLogin = findViewById(R.id.textTitle);
        etLogin = findViewById(R.id.input_login);
        etPassword = findViewById(R.id.input_password);


        btnEnter = findViewById(R.id.enter);

            btnEnter.setOnClickListener(v -> {
                if (etLogin.getText().toString().equals("")||etPassword.getText().toString().equals(""))
                {
                    Toast.makeText(this, "Введите логин или пароль!", Toast.LENGTH_LONG).show();
                }
                else {
                    if (etLogin.getText().toString().equals("log") && etPassword.getText().toString().equals("pas")) {
                        Toast.makeText(this, "Ок:)!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "Введен неверный логин или пароль!", Toast.LENGTH_LONG).show();
                    }
                }
            });

    }


}
