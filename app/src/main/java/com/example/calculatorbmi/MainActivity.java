package com.example.calculatorbmi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.security.PrivateKey;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static DecimalFormat df = new DecimalFormat("0.0");
    EditText weight, height;
    TextView bmiResult;
    Button buttonCal;

    SharedPreferences sharedPref;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        weight = findViewById(R.id.inputWeight);
        height = findViewById(R.id.inputHeight);
        buttonCal = findViewById(R.id.buttonCal);
        bmiResult = findViewById(R.id.bmiResult);
        buttonCal.setOnClickListener(this);


        sharedPref = this.getSharedPreferences("height1", Context.MODE_PRIVATE);
        sharedPref = this.getSharedPreferences("weight1", Context.MODE_PRIVATE);
        String hg = sharedPref.getString("height1", "");
        String wg = sharedPref.getString("weight1", "");
        height.setText(hg);
        weight.setText(wg);


    }






    @Override
    public void onClick(View v) {

        String getW = weight.getText().toString();
        String getH = height.getText().toString();


        switch (v.getId()) {
            case R.id.buttonCal:

                try {


                    float W = Float.parseFloat(getW);
                    float H = Float.parseFloat(getH);

                    float countH = H / 100;
                    float bmi = W / (countH * countH);

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("height1", getH);
                    editor.putString("weight1", getW);
                    editor.apply();

                    String bmi2 = df.format(bmi);


                    if (bmi < 18.49) {

                        bmiResult.setText(bmi2 + "\nUnderweight" + "\nMalnutrition risk");

                    } else if (bmi >= 18.5 && bmi <= 24.99) {

                        bmiResult.setText(bmi2 + "\nNormal Weight" + "\nLow risk");

                    } else if (bmi >= 25 && bmi <= 29.99) {

                        bmiResult.setText(bmi2 + "\nOverweight" +  "\nEnchanced risk");

                    } else if (bmi >= 30 && bmi <= 34.99) {

                        bmiResult.setText(bmi2 + "\nModerately obese" + "\nMedium Risk");

                    } else if (bmi >= 35 && bmi <= 39.99) {

                        bmiResult.setText(bmi2 + "\nSeverely Obese" + "\nHigh risk");

                    } else {

                        bmiResult.setText(bmi2 + "\n very Severely Obese " +  "\nVery high risk");

                    }


                } catch (Exception exception) {


                    Toast.makeText(this, "please enter number in text field", Toast.LENGTH_SHORT).show();
                }


                break;

            default:



        }

    }




        public void openProfile(){

            Intent intent = new Intent(this, profile_page.class);
            startActivity(intent);
        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.profile:
                startActivity(new Intent(MainActivity.this, profile_page.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



}