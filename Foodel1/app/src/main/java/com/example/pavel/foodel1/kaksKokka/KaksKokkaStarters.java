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
import com.example.pavel.foodel1.chedi.ChediMainCourse;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class KaksKokkaStarters extends AppCompatActivity implements View.OnClickListener, RestaurantCourse {
    CheckBox starter1,starter2,starter3,starter4,starter5;
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
        setTitle("Kaks Kokka: Starters");

        setContentView(R.layout.kaks_kokka_starters);
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
            case R.id.buttonStartersKaksKokkaPay:
                selectCourse();
                Intent profile = new Intent(this,OrderConfirmation.class);
                profile.putExtra("message1", name);
                profile.putExtra("message2",email);
                profile.putExtra("resto","kaks Kokka");
                startActivity(profile);
                break;
            case R.id.buttonStartersKaksKokkaMain:
                selectCourse();
                Intent main = new Intent(this, KaksKokkaMainCourse.class);
                main.putExtra("message1", name);
                main.putExtra("message2",email);
                startActivity(main);
                break;
            case R.id.buttonStartersKaksKokkaDessert:
                selectCourse();
                Intent starters = new Intent(this, KaksKokkaDesserts.class);
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
        starter1 = (CheckBox)findViewById(R.id.kaksKokkaStarter1);
        starter2 = (CheckBox)findViewById(R.id.kaksKokkaStarter2);
        starter3 = (CheckBox)findViewById(R.id.kaksKokkaStarter3);
        starter4 = (CheckBox)findViewById(R.id.kaksKokkaStarter4);
        starter5 = (CheckBox)findViewById(R.id.kaksKokkaStarter5);
        pay = (Button)findViewById(R.id.buttonStartersKaksKokkaPay);
        dessert = (Button)findViewById(R.id.buttonStartersKaksKokkaDessert);
        mainCourse = (Button)findViewById(R.id.buttonStartersKaksKokkaMain);


    }

    @Override
    public void addMealToArray() {
        selectedStarters.add(starter1);
        selectedStarters.add(starter2);
        selectedStarters.add(starter3);
        selectedStarters.add(starter4);
        selectedStarters.add(starter5);

    }
}
