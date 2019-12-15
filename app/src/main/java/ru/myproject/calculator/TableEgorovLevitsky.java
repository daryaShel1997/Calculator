package ru.myproject.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TableEgorovLevitsky extends Fragment {

    RadioGroup rgGender;
    RadioButton rbGender;
    EditText etHeight_value, etAge_value;
    TextView tvWeight_value;
    TextView tvWeight_temp;
    Button btnCalculate;
    TextView tvTitle;
    Integer result_height, result_age;
    boolean flag_gender = false;

    String[] parsingArray;

    List<ObjectTable> weightArray = new ArrayList<>();


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.table_egorov_levitsky, container, false);
        tvTitle = view.findViewById(R.id.textTitle);
        rgGender = view.findViewById(R.id.gender);
        etHeight_value = view.findViewById(R.id.input_height);
        etAge_value = view.findViewById(R.id.input_age);
        btnCalculate = view.findViewById(R.id.calculate);
        tvWeight_value = view.findViewById(R.id.weight_value);
        tvWeight_temp = view.findViewById(R.id.weight_temp);


        btnCalculate.setOnClickListener(v -> {
            int radioId = rgGender.getCheckedRadioButtonId();
            rbGender = view.findViewById(radioId);

            try {

                if (etHeight_value.getText().length() == 0) {
                    Toast.makeText(getActivity(), "Введите Ваш рост!", Toast.LENGTH_LONG).show();
                }
                else if (etAge_value.getText().length() == 0) {
                    Toast.makeText(getActivity(), "Введите Ваш возраст!", Toast.LENGTH_LONG).show();
                } else {
                    result_height = Integer.parseInt(etHeight_value.getText().toString());
                    result_age = Integer.parseInt(etAge_value.getText().toString());


                    if (rbGender.getText().equals("Женщина")) {
                        flag_gender = false;
                        if (result_height < 148) {
                            Toast.makeText(getActivity(), "Слишком маленький рост!", Toast.LENGTH_LONG).show();
                        } else if (result_height > 190) {
                            Toast.makeText(getActivity(), "Слишком большой рост!", Toast.LENGTH_LONG).show();
                        } else if (result_age < 20) {
                            Toast.makeText(getActivity(), "Слишком маленький возраст!", Toast.LENGTH_LONG).show();
                        } else if (result_age > 200) {
                            Toast.makeText(getActivity(), "Слишком большой возраст!", Toast.LENGTH_LONG).show();
                        } else {
                            checkAge(result_age, result_height);

                        }
                    } else if (rbGender.getText().equals("Мужчина")) {
                        flag_gender = true;
                        if (result_height < 148) {
                            Toast.makeText(getActivity(), "Слишком маленький рост!!", Toast.LENGTH_LONG).show();
                        } else if (result_height > 190) {
                            Toast.makeText(getActivity(), "Слишком большой рост!!", Toast.LENGTH_LONG).show();
                        } else if (result_age < 20) {
                            Toast.makeText(getActivity(), "Слишком маленький возраст!", Toast.LENGTH_LONG).show();
                        } else if (result_age > 200) {
                            Toast.makeText(getActivity(), "Слишком большой возраст!", Toast.LENGTH_LONG).show();
                        } else {
                            checkAge(result_age, result_height);
                        }
                    }
                }
            } catch (NullPointerException npe) {
                Toast.makeText(getActivity(), "Выберете Ваш пол!", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }




    private void checkAge(int age, int height) {
        Log.d("TAG", "age =" + age + "  height=" + height);
        if (age > 19 && age < 30) {
            addListTables(R.array.age20_29);
            printWeight(height);
        } else if (age > 29 && age < 40) {
            addListTables(R.array.age30_39);
            printWeight(height);
        } else if (age > 39 && age < 50) {
            addListTables(R.array.age40_49);
            printWeight(height);
        } else if (age > 49 && age < 60) {
            addListTables(R.array.age50_59);
            printWeight(height);
        } else if (age > 59 ) {
            addListTables(R.array.age60_69);
            printWeight(height);
        }
    }


    private void printWeight(int height) {

        for (int i = 0; i < weightArray.size(); i++) {
            if (height!=190) {
                if (height >= Integer.parseInt(weightArray.get(i).height) &&
                        height < Integer.parseInt(weightArray.get(i + 1).height)) {
                    if (flag_gender) {
                        tvWeight_temp.setText(" ");
                        tvWeight_value.setText(weightArray.get(i).man);//вывели
                    } else {
                        tvWeight_temp.setText(" ");
                        tvWeight_value.setText(weightArray.get(i).women);
                    }
                }
            }  else if (height >= Integer.parseInt(weightArray.get(i).height)) {
                if (flag_gender) {
                    tvWeight_temp.setText(" ");
                    tvWeight_value.setText(weightArray.get(i).man);//вывели
                } else {
                    tvWeight_temp.setText(" ");
                    tvWeight_value.setText(weightArray.get(i).women);
                }
            }
        }

    }


    private void addListTables(int id_array) {

        parsingArray = getResources().getStringArray(id_array);
        ObjectTable objectTable;
        for (int i = 0; i < parsingArray.length; i++) {
            objectTable = new ObjectTable();
            String buf = "";
            int count = 0;

            for (int j = 0; j < parsingArray[i].length(); j++) {
                if (parsingArray[i].charAt(j) != '+') {
                    buf += parsingArray[i].charAt(j);
                } else {
                    if (count == 0) {
                        objectTable.height = buf;
                        buf = "";
                    } else if (count == 1) {
                        objectTable.man = buf;
                        buf = "";
                    } else if (count == 2) {
                        objectTable.women = buf;
                        buf = "";
                        weightArray.add(objectTable);
                    }
                    count++;
                }
            }

        }


        Log.d("SizeArray", weightArray.get(0).height + "\n"
                + weightArray.get(0).man + "\n" +
                weightArray.get(0).women);

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
