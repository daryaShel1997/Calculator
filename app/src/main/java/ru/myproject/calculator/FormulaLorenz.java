package ru.myproject.calculator;

import androidx.appcompat.app.AppCompatActivity;
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

public class FormulaLorenz extends Fragment {

    TextView tvTitle, tvLableLorenz, tvLableLorenz2;
    RadioGroup rgGender;
    RadioButton rbGender;
    EditText etHeight_value;
    TextView tvWeight_value;
    TextView tvWeight_temp;
    String result;
    Double result_double;
    Button btnCalculate;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.formula_lorenz, container, false);
        tvTitle = view.findViewById(R.id.textTitle);             //заголовок
        tvLableLorenz = view.findViewById(R.id.lable_lorenz);
        rgGender = view.findViewById(R.id.gender);
        tvLableLorenz2 = view.findViewById(R.id.lable2_lorenz);
        etHeight_value = view.findViewById(R.id.input_height);   //ввод роста
        btnCalculate = view.findViewById(R.id.calculate);        //кнопка
        tvWeight_temp = view.findViewById(R.id.weight_temp);     //подсказка, для веса
        tvWeight_value = view.findViewById(R.id.weight_value);   //вывод веса

        btnCalculate.setOnClickListener(v -> {
            int radioId = rgGender.getCheckedRadioButtonId();
            rbGender = view.findViewById(radioId);
            try {
                if (rbGender.getText().equals("Женщина")) {
                    {
                        if (etHeight_value.getText().length() == 0) {
                            Toast.makeText(getActivity(), "Введите Ваш рост!", Toast.LENGTH_LONG).show();
                        } else {
                            result = etHeight_value.getText().toString();                       //получили то, что ввел пользователь
                            //перевели в цифру и посчитали
                            if (Double.parseDouble(result) > 175) {
                                Toast.makeText(getActivity(), "Рост больше 175см!", Toast.LENGTH_LONG).show();
                            } else if (Double.parseDouble(result) < 150) {
                                Toast.makeText(getActivity(), "Слишком маленький рост!", Toast.LENGTH_LONG).show();
                            } else {
                                result_double = (Double.parseDouble(result) - 100) - (Double.parseDouble(result) - 150) / 2;
                                result_double = FormulaBrock.aroundUp(result_double, 3); //округлили

                                result = (Double.toString(result_double));                          //вернули в строку
                                result += getResources().getString(R.string.add_kg);                //дописали кг
                                tvWeight_temp.setText(" ");
                                tvWeight_value.setText(result);                                     //вывели

                            }
                        }
                    }
                }
            } catch (NullPointerException npe) {
                Toast.makeText(getActivity(), "Выберете Ваш пол!", Toast.LENGTH_LONG).show();
            }
        });
        return  view;
    }
}





//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
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
//        TextView nameView = findViewById(R.id.weight_value);
//        outState.putString("weight2", nameView.getText().toString());
//
//        TextView nameView2 = findViewById(R.id.weight_temp);
//        outState.putString("weight_temp", nameView2.getText().toString());
//    }


