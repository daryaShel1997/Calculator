package ru.myproject.calculator;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class FormulaBrock extends Fragment {

    RadioGroup rgGender;
    RadioButton rbGender;
    EditText etHeight_value;
    TextView tvWeight_value;
    TextView tvWeight_temp;
    String result;
    Double result_double;
    Button btnCalculate;
    TextView tvTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.formula_brock, container, false);


        rgGender = view.findViewById(R.id.gender);

        etHeight_value = view.findViewById(R.id.input_height);
        tvWeight_value = view.findViewById(R.id.weight_value);
        btnCalculate = view.findViewById(R.id.calculate);
        tvWeight_temp = view.findViewById(R.id.weight_temp);
        //etHeight_value.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        tvTitle = view.findViewById(R.id.textTitle);

        btnCalculate.setOnClickListener(v -> {
            int radioId = rgGender.getCheckedRadioButtonId();
            rbGender = view.findViewById(radioId);
            try {
                if (rbGender.getText().equals("Женщина")) {
                    if (etHeight_value.getText().length() == 0) {
                        Toast.makeText(getActivity(), "Введите Ваш рост!", Toast.LENGTH_LONG).show();
                    } else {
                        result = etHeight_value.getText().toString();                   //получили то, что ввел пользователь
                        if (Double.parseDouble(result) < 150) {
                            Toast.makeText(getActivity(), "Слишком маленький рост!", Toast.LENGTH_LONG).show();
                        } else if (Double.parseDouble(result) > 230) {
                            Toast.makeText(getActivity(), "Слишком большой рост!", Toast.LENGTH_LONG).show();
                        } else {
                            result_double = (Double.parseDouble(result) - 110) * (1.15);    //перевели в цифру и посчитали
                            result_double = aroundUp(result_double, 2);          //округлили
                            result = (Double.toString(result_double));                      //вернули в строку
                            result += getResources().getString(R.string.add_kg);                                                //дописали кг
                            tvWeight_temp.setText(" ");
                            tvWeight_value.setText(result);                                 //вывели
                        }

                    }
                } else if (rbGender.getText().equals("Мужчина")) {
                    if (etHeight_value.getText().length() == 0) {
                        Toast.makeText(getActivity(), "Введите Ваш рост!", Toast.LENGTH_LONG).show();
                    } else {
                        result = etHeight_value.getText().toString();                   //получили то, что ввел пользователь
                        if (Double.parseDouble(result) < 150) {
                            Toast.makeText(getActivity(), "Слишком маленький рост!", Toast.LENGTH_LONG).show();
                        } else {
                            result_double = (Double.parseDouble(result) - 100) * (1.15);    //перевели в цифру и посчитали
                            result_double = aroundUp(result_double, 2);          //округлили

                            result = (Double.toString(result_double));                      //вернули в строку
                            result += getResources().getString(R.string.add_kg);            //дописали кг
                            tvWeight_temp.setText(" ");
                            tvWeight_value.setText(result);                                 //вывели

                        }
                    }
                }
            } catch (NullPointerException npe) {
                Toast.makeText(getActivity(), "Выберете Ваш пол!", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }


    public static double aroundUp(double number, int canDecimal) {
        int cifras = (int) Math.pow(10, canDecimal);
        return Math.ceil(number * cifras) / cifras;
    }


//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        //  result = savedInstanceState.getString("weight");
//        String textViewText = savedInstanceState.getString("weight2");
//        TextView nameView = findViewById(R.id.weight_value);
//        nameView.setText(textViewText);
//
//        String textViewText2 = savedInstanceState.getString("weight_temp");
//        TextView nameView2 = findViewById(R.id.weight_temp);
//        nameView2.setText(textViewText2);
//    }


//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        //outState.putString("weight", result);
//        TextView nameView = findViewById(R.id.weight_value);
//        outState.putString("weight2", nameView.getText().toString());
//
//        TextView nameView2 = findViewById(R.id.weight_temp);
//        outState.putString("weight_temp", nameView2.getText().toString());
//    }


}


