package com.example.pavel.foodel1.truhvel;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.pavel.foodel1.MainActivity;
import com.example.pavel.foodel1.OrderConfirmation;
import com.example.pavel.foodel1.R;
import com.example.pavel.foodel1.RestaurantCourse;
import com.example.pavel.foodel1.SingIn;
import com.example.pavel.foodel1.chedi.ChediMainCourse;
import com.example.pavel.foodel1.chedi.ChediStarters;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class TruhvelDesserts extends AppCompatActivity implements View.OnClickListener, RestaurantCourse {
    CheckBox des1, des2, des3, des4;
    ArrayList<CheckBox> selectedDesserts = new ArrayList<>();
    String email,name;
    Button pay, mainCourse, starters;
    BufferedWriter bw;
    final String FILENAME  = "order";

    @Override
    protected void onResume(){
        super.onResume();
        for(int i = 0; i<selectedDesserts.size();i++){
            selectedDesserts.get(i).setChecked(false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("message1");
        email = bundle.getString("message2");
        super.onCreate(savedInstanceState);
        setTitle("Trühvel: Desserts");
        setContentView(R.layout.truhvel_desserts);

        setId();
        addMealToArray();

        pay.setOnClickListener(this);
        mainCourse.setOnClickListener(this);
        starters.setOnClickListener(this);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.actionHome:
                Intent main = new Intent(this, MainActivity.class);
                main.putExtra("message1", name);
                main.putExtra("message2", email);
                startActivity(main);
                break;
            case R.id.actionExit:
                startActivity(new Intent(this, SingIn.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonDessertTruhvelPay:
                selectCourse();
                Intent profile = new Intent(this,OrderConfirmation.class);
                profile.putExtra("message1", name);
                profile.putExtra("message2",email);
                profile.putExtra("resto", "Trühvel");
                startActivity(profile);
                break;
            case R.id.buttonDessertTruhvelMain:
                selectCourse();
                Intent main = new Intent(this, TruhvelMainCourse.class);
                main.putExtra("message1", name);
                main.putExtra("message2",email);
                startActivity(main);
                break;
            case R.id.buttonDessertTruhvelStarters:
                selectCourse();
                Intent starters = new Intent(this, TruhvelStarters.class);
                starters.putExtra("message1", name);
                starters.putExtra("message2",email);
                startActivity(starters);
                break;
        }

    }

    @Override
    public void selectCourse() {
        try {
            bw = new BufferedWriter(new OutputStreamWriter(openFileOutput(FILENAME,MODE_APPEND)));

            for (int i=0; i<selectedDesserts.size();i++){
                if(selectedDesserts.get(i).isChecked()){
                    bw.write(selectedDesserts.get(i).getText().toString()+"\n");
                    Log.d("file", "file zapisan");
                }
            }
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void setId() {
        des1 = (CheckBox)findViewById(R.id.truhvelDesserts1);
        des2 = (CheckBox)findViewById(R.id.truhvelDesserts2);
        des3 = (CheckBox)findViewById(R.id.truhvelDesserts3);
        des4 = (CheckBox)findViewById(R.id.truhvelDesserts4);
        pay = (Button)findViewById(R.id.buttonDessertTruhvelPay);
        mainCourse = (Button)findViewById(R.id.buttonDessertTruhvelMain);
        starters = (Button)findViewById(R.id.buttonDessertTruhvelStarters);

    }

    @Override
    public void addMealToArray() {
        selectedDesserts.add(des1);
        selectedDesserts.add(des2);
        selectedDesserts.add(des3);
        selectedDesserts.add(des4);

    }
}
