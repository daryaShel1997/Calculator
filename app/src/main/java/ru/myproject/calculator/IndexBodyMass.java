package ru.myproject.calculator;


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

import androidx.fragment.app.Fragment;

public class IndexBodyMass extends Fragment {
    RadioGroup rgAge, rgBody;
    RadioButton rbAge;
    EditText etHeight_value, etWeight_value;
    TextView tvIndexBodyMass_value, tvIndexBodyMass_temp, twAnswer;
    Button btnCalculate;
    TextView tvTitle;
    String result_height, result_weight;
    Double result_double;
    Integer flag;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.index_body_mass, container, false);



        tvTitle = view.findViewById(R.id.textTitle);
        rgAge = view.findViewById(R.id.age);
        rgBody = view.findViewById(R.id.body);

        etHeight_value = view.findViewById(R.id.input_height);
        etWeight_value = view.findViewById(R.id.input_weight);
        btnCalculate = view.findViewById(R.id.calculate);
        tvIndexBodyMass_value = view.findViewById(R.id.index_body_mass_value);
        tvIndexBodyMass_temp = view.findViewById(R.id.index_body_mass_temp);
        twAnswer = view.findViewById(R.id.answer);



        btnCalculate.setOnClickListener(v -> {

            int radioId = rgAge.getCheckedRadioButtonId();
            rbAge = view.findViewById(radioId);


            if (etHeight_value.getText().length() == 0) {
                Toast.makeText(getActivity(), "Введите Ваш рост!", Toast.LENGTH_LONG).show();
            }
            else if (etWeight_value.getText().length() == 0) {
                Toast.makeText(getActivity(), "Введите Ваш вес!", Toast.LENGTH_LONG).show();
            } else {

                result_height = etHeight_value.getText().toString();                   //получили рост, что ввел пользователь
                result_weight = etWeight_value.getText().toString();                   //получили вес, что ввел пользователь
                if (Double.parseDouble(result_height) < 150) {
                    Toast.makeText(getActivity(), "Слишком маленький рост!", Toast.LENGTH_LONG).show();
                } else if (Double.parseDouble(result_height) > 230) {
                    Toast.makeText(getActivity(), "Слишком большой рост!", Toast.LENGTH_LONG).show();
                } else  if (Double.parseDouble(result_weight) < 10) {
                    Toast.makeText(getActivity(), "Слишком вес рост!", Toast.LENGTH_LONG).show();
                } else if (Double.parseDouble(result_weight) > 330) {
                    Toast.makeText(getActivity(), "Слишком вес рост!", Toast.LENGTH_LONG).show();
                }
                else {
                    //перевели в цифру и посчитали
                    result_double = (Double.parseDouble(result_weight)/Double.parseDouble(result_height));
                    result_double = aroundUp(result_double, 2);          //округлили
                    tvIndexBodyMass_temp.setText(" ");
                    //вернули в строку и вывели
                    tvIndexBodyMass_value.setText((Double.toString(result_double)));

                        if (rbAge.getText().equals("18-25 лет")) {
                                if (result_double <= 17.5) {
                                    flag =1;
                                } else if (result_double > 17.5 && result_double <= 19.5) {
                                    flag =2;
                                } else if (result_double > 19.5 && result_double <= 22.9) {
                                    flag =3;
                                }
                                else if (result_double > 22.9 && result_double <= 27.4) {
                                    flag =4;
                                }
                                else if (result_double > 27.4 && result_double <= 29.9) {
                                    flag =5;
                                }
                                else if (result_double > 29.9 && result_double <= 34.9) {
                                    flag =6;
                                }
                                else if (result_double > 34.9 && result_double <=  39.9) {
                                    flag =7;
                                }
                                else if (result_double > 39.9) {
                                    flag =8;
                                }
                                indexBodyMass(flag, twAnswer);
                            } else
                        if (rbAge.getText().equals("от 26 лет")) {
                            if (result_double <= 18) {
                                flag =1;
                            } else if (result_double > 18 && result_double <= 20) {
                                flag =2;
                            } else if (result_double > 20 && result_double <= 25.9) {
                                flag =3;
                            }
                            else if (result_double > 25.9 && result_double <= 27.9) {
                                flag =4;
                            }
                            else if (result_double > 27.9 && result_double <= 30.9) {
                                flag =5;
                            }
                            else if (result_double > 30.9 && result_double <= 35.9) {
                                flag =6;
                            }
                            else if (result_double > 35.9 && result_double <=  40.9) {
                                flag =7;
                            }
                            else if (result_double > 40.9) {
                                flag =8;
                            }
                            indexBodyMass(flag, twAnswer);
                        }
                    }
                }
        });


        return view;
    }

    public static double aroundUp(double number, int canDecimal) {
        int cifras = (int) Math.pow(10, canDecimal);
        return Math.ceil(number * cifras) / cifras;
    }

    public static void indexBodyMass(int flag, TextView twAnswer) {
        if (flag==1) {
            twAnswer.setText("Вес недостаточен, опасно для здоровья!");
        } else if (flag == 2) {
            twAnswer.setText("Вес снижен, неопасно для здоровья!");
        } else if (flag == 3) {
            twAnswer.setText("Вес в норме!");
        }
        else if (flag == 4) {
            twAnswer.setText("Излишний вес!");
        }
        else if (flag == 5) {
            twAnswer.setText("Ожирение 1 степени!");
        } else if (flag == 6) {
            twAnswer.setText("Ожирение 2 степени!");
        } else if (flag == 7) {
            twAnswer.setText("Ожирение 3 степени");
        } else if (flag == 8) {
            twAnswer.setText("Ожирение 4 степени!");
        }
    }
}
