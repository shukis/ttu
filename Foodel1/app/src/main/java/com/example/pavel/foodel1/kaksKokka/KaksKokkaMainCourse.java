package com.example.pavel.foodel1.kaksKokka;

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
import com.example.pavel.foodel1.chedi.ChediDesserts;
import com.example.pavel.foodel1.chedi.ChediStarters;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class KaksKokkaMainCourse extends AppCompatActivity implements View.OnClickListener, RestaurantCourse {
    CheckBox main1, main2, main3, main4, main5, main6, main7;
    ArrayList<CheckBox> selectedMains = new ArrayList<>();
    final String FILENAME  = "order";
    Button pay, dessert, starters;
    String email,name;
    BufferedWriter bw;

    @Override
    protected void onResume(){
        super.onResume();
        for(int i = 0; i<selectedMains.size();i++){
            selectedMains.get(i).setChecked(false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("message1");
        email = bundle.getString("message2");
        super.onCreate(savedInstanceState);
        setTitle("Kaks Kokka: Main menu");


        setContentView(R.layout.kaks_kokka_main_course);
        setId();
        addMealToArray();

        pay.setOnClickListener(this);
        dessert.setOnClickListener(this);
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
            case R.id.buttonMainKaksKokkaPay:
                selectCourse();
                Intent profile = new Intent(this,OrderConfirmation.class);
                profile.putExtra("message1", name);
                profile.putExtra("message2",email);
                profile.putExtra("resto", "kaks Kokka");
                startActivity(profile);
                break;
            case R.id.buttonMainKaksKokkaDesserts:
                selectCourse();
                Intent main = new Intent(this, KaksKokkaDesserts.class);
                main.putExtra("message1", name);
                main.putExtra("message2",email);
                startActivity(main);
                break;
            case R.id.buttonMainKaksKokkaStarters:
                selectCourse();
                Intent starters = new Intent(this, KaksKokkaStarters.class);
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

            for (int i=0; i<selectedMains.size();i++){
                if(selectedMains.get(i).isChecked()){
                    bw.write(selectedMains.get(i).getText().toString()+"\n");
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
        main1 = (CheckBox)findViewById(R.id.kaksKokkaMainCourse1);
        main2 = (CheckBox)findViewById(R.id.kaskKokkaMainCourse2);
        main3 = (CheckBox)findViewById(R.id.kaskKokkaMainCourse3);
        main4 = (CheckBox)findViewById(R.id.kaskKokkaMainCourse4);
        main5 = (CheckBox)findViewById(R.id.kaskKokkaMainCourse5);
        main6 = (CheckBox)findViewById(R.id.kaskKokkaMainCourse6);
        main7 = (CheckBox)findViewById(R.id.kaskKokkaMainCourse7);
        pay = (Button)findViewById(R.id.buttonMainKaksKokkaPay);
        dessert = (Button)findViewById(R.id.buttonMainKaksKokkaDesserts);
        starters = (Button)findViewById(R.id.buttonMainKaksKokkaStarters);

    }

    @Override
    public void addMealToArray() {
        selectedMains.add(main1);
        selectedMains.add(main2);
        selectedMains.add(main3);
        selectedMains.add(main4);
        selectedMains.add(main5);
        selectedMains.add(main6);
        selectedMains.add(main7);
    }
}
