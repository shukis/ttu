package com.example.pavel.foodel1.kolmSibulat;

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
import com.example.pavel.foodel1.chedi.ChediMainCourse;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class KolmSibulatStarters extends AppCompatActivity implements View.OnClickListener, RestaurantCourse {
    CheckBox starter1,starter2,starter3,starter4,starter5,starter6,starter7,starter8,starter9,starter10;
    ArrayList<CheckBox> selectedStarters = new ArrayList<>();
    final String FILENAME  = "order";
    String email,name;
    BufferedWriter bw;
    Button pay, dessert, mainCourse;

    @Override
    protected void onResume(){
        super.onResume();
        for(int i = 0; i<selectedStarters.size();i++){
            selectedStarters.get(i).setChecked(false);
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("message1");
        email = bundle.getString("message2");
        super.onCreate(savedInstanceState);
        setTitle("Kolm Sibulat: Starters");

        setContentView(R.layout.kolm_sibulat_starters);

        setId();
        addMealToArray();

        pay.setOnClickListener(this);
        dessert.setOnClickListener(this);
        mainCourse.setOnClickListener(this);


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
            case R.id.buttonStartersKolmSibulatPay:
                selectCourse();
                Intent profile = new Intent(this,OrderConfirmation.class);
                profile.putExtra("message1", name);
                profile.putExtra("message2",email);
                profile.putExtra("resto","kolm Sibulat");
                startActivity(profile);
                break;
            case R.id.buttonStartersKolmSibulatMain:
                selectCourse();
                Intent main = new Intent(this, KolmSibulatMainCourse.class);
                main.putExtra("message1", name);
                main.putExtra("message2",email);
                startActivity(main);
                break;
            case R.id.buttonStartersKolmSibulatDessert:
                selectCourse();
                Intent starters = new Intent(this, KolmSibulatDessert.class);
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

            for (int i=0; i<selectedStarters.size();i++){
                if(selectedStarters.get(i).isChecked()){
                    bw.write(selectedStarters.get(i).getText().toString()+"\n");
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
        starter1 = (CheckBox)findViewById(R.id.kolmSibulatStarter1);
        starter2 = (CheckBox)findViewById(R.id.kolmSibulatStarter2);
        starter3 = (CheckBox)findViewById(R.id.kolmSibulatStarter3);
        starter4 = (CheckBox)findViewById(R.id.kolmSibulatStarter4);
        starter5 = (CheckBox)findViewById(R.id.kolmSibulatStarter5);
        starter6 = (CheckBox)findViewById(R.id.kolmSibulatStarter6);
        starter7 = (CheckBox)findViewById(R.id.kolmSibulatStarter7);
        starter8 = (CheckBox)findViewById(R.id.kolmSibulatStarter8);
        starter9 = (CheckBox)findViewById(R.id.kolmSibulatStarter9);
        starter10 = (CheckBox)findViewById(R.id.kolmSibulatStarter10);

        pay = (Button)findViewById(R.id.buttonStartersKolmSibulatPay);
        dessert = (Button)findViewById(R.id.buttonStartersKolmSibulatDessert);
        mainCourse = (Button)findViewById(R.id.buttonStartersKolmSibulatMain);
    }

    @Override
    public void addMealToArray() {
        selectedStarters.add(starter1);
        selectedStarters.add(starter2);
        selectedStarters.add(starter3);
        selectedStarters.add(starter4);
        selectedStarters.add(starter5);
        selectedStarters.add(starter6);
        selectedStarters.add(starter7);
        selectedStarters.add(starter8);
        selectedStarters.add(starter9);
        selectedStarters.add(starter10);

    }
}
