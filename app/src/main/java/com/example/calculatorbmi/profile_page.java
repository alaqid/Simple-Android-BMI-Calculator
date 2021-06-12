package com.example.calculatorbmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;

public class profile_page extends AppCompatActivity implements View.OnClickListener {


    ImageView GitHub;
    ImageView facebook;
    ImageView insta;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);


        GitHub = findViewById(R.id.GitHub);
        facebook = findViewById(R.id.facebook);
        insta = findViewById(R.id.insta);
        GitHub.setOnClickListener(this);
        facebook.setOnClickListener(this);
        insta.setOnClickListener(this);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.GitHub:

                gotoURL("https://github.com/alaqid/Simple-Android-BMI-Calculator");


                break;


            case R.id.facebook:

                gotoURL("https://www.facebook.com/");


                break;



            case R.id.insta:

                gotoURL("https://www.instagram.com/");


                break;




        }

    }

    private void gotoURL(String s) {
        Uri url = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, url));
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

            case R.id.bmiCal:
                startActivity(new Intent(profile_page.this, MainActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}