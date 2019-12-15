package ru.myproject.calculator;


import androidx.annotation.NonNull;


import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;


import android.app.Activity;
import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;


import com.google.android.material.navigation.NavigationView;


public class Main2Activity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    final static String TAG_1 = "fragment1";
    Button btnLoginToTheApplication, btnRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        mDrawerLayout = findViewById(R.id.my_drawer_layout);
        toolbar = findViewById(R.id.toolbar);


        NavigationView navigationView = findViewById(R.id.nav_view);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    Intent intent;
                    Log.d("WIII", "" + menuItem.getItemId());
                    switch (menuItem.getItemId()) {
                        case R.id.formula_brock1:
                            FormulaBrock frag1 = new FormulaBrock();
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.content_frame, frag1, TAG_1)
                                    .addToBackStack(null)
                                    .commit();
                            break;
                        case R.id.formula_lorenz1:
                            FormulaLorenz frag2 = new FormulaLorenz();
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.content_frame, frag2, TAG_1)
                                    .addToBackStack(null)
                                    .commit();
                            break;

                        case R.id.table_egorov_levitsky1:
                            TableEgorovLevitsky frag3 = new TableEgorovLevitsky();
                            getFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.content_frame, frag3, TAG_1)
                                    .addToBackStack(null)
                                    .commit();
                            break;

                        case R.id.index_ketle1:
                            IndexKetle frag4 = new IndexKetle();
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.content_frame, frag4, TAG_1)
                                    .addToBackStack(null)
                                    .commit();

                            break;
                        case R.id.index_body_mass1:
                            IndexBodyMass frag5 = new IndexBodyMass();
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.content_frame, frag5, TAG_1)
                                    .addToBackStack(null)
                                    .commit();

                            break;

                    }
                    menuItem.setChecked(true);
                    mDrawerLayout.closeDrawers();
                    return true;
                });

        btnLoginToTheApplication = findViewById(R.id.input);
        btnRegistration = findViewById(R.id.registration);

        btnLoginToTheApplication.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginToTheApplication.class);
            startActivity(intent);
        });


        btnRegistration.setOnClickListener(v -> {
            Intent intent = new Intent(this, Registration.class);
            startActivity(intent);
        });

    }

}
