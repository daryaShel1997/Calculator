package ru.myproject.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    TextView twRegistration, twGender, twBody;
    RadioGroup rgGender, rgBody;
    RadioButton rbGender, rbBody;
    EditText etHeight, etWeight, erAge, etLogin, etPassword, etRepeatPassword;
    Button btnEnter;
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        twRegistration = findViewById(R.id.textTitle);
        twGender = findViewById(R.id.text_gender);
        twBody = findViewById(R.id.body_text);

        rgGender = findViewById(R.id.gender);
        rgBody= findViewById(R.id.body);

        etHeight  = findViewById(R.id.input_height);
        etWeight = findViewById(R.id.input_weight);
        erAge = findViewById(R.id.input_age);
        etLogin = findViewById(R.id.input_login);
        etPassword = findViewById(R.id.input_password);
        etRepeatPassword= findViewById(R.id.input_repeat_password);

        btnEnter = findViewById(R.id.enter);

        btnEnter.setOnClickListener(v -> {
            if (etHeight.getText().toString().equals("")||etWeight.getText().toString().equals("")||
                    erAge.getText().toString().equals("")||etLogin.getText().toString().equals("")||
                    etPassword.getText().toString().equals("")||etRepeatPassword.getText().toString().equals(""))
            {
                Toast.makeText(this, "Заполнены не все поля!", Toast.LENGTH_LONG).show();
            }
            else {
                result = etHeight.getText().toString();
                if (Double.parseDouble(result) < 150) {
                    Toast.makeText(this, "Слишком маленький рост!", Toast.LENGTH_LONG).show();
                } else if (Double.parseDouble(result) > 230) {
                    Toast.makeText(this, "Слишком большой рост!", Toast.LENGTH_LONG).show();
                } else
                {
                    result = etWeight.getText().toString();
                    if (Double.parseDouble(result) < 40) {
                        Toast.makeText(this, "Слишком маленький вес!", Toast.LENGTH_LONG).show();
                    } else if (Double.parseDouble(result) > 400) {
                        Toast.makeText(this, "Слишком большой вес!", Toast.LENGTH_LONG).show();
                    } else
                    {
                        result = erAge.getText().toString();
                        if (Double.parseDouble(result) < 18) {
                            Toast.makeText(this, "Слишком маленький возраст!", Toast.LENGTH_LONG).show();
                        } else if (Double.parseDouble(result) > 200) {
                            Toast.makeText(this, "Слишком большой возраст!", Toast.LENGTH_LONG).show();
                        } else
                        {
                            result = etPassword.getText().toString();
                            if (etRepeatPassword.getText().toString().equals(result)) {
                                Toast.makeText(this, "Ок:)!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(this, "Пароли не совпадают!", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
            }
        });
    }
}
