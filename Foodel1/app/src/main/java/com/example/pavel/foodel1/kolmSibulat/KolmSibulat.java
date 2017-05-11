package com.example.pavel.foodel1.kolmSibulat;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.pavel.foodel1.MainActivity;
import com.example.pavel.foodel1.R;
import com.example.pavel.foodel1.SingIn;

public class KolmSibulat extends AppCompatActivity implements View.OnClickListener {

    TextView kolmSibulatStarters;
    TextView kolmSibulatMainCourse;
    TextView kolmSibulatDesserts;
    String email,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("message1");
        email = bundle.getString("message2");
        super.onCreate(savedInstanceState);
        setTitle("Kolm Sibulat");

        setContentView(R.layout.kolm_sibulat);
        kolmSibulatStarters = (TextView)findViewById(R.id.kolmSibulatMainMenuStarters);
        kolmSibulatMainCourse = (TextView)findViewById(R.id.kolmSibulatMainMenuMainCourse);
        kolmSibulatDesserts = (TextView)findViewById(R.id.kolmSibulatMainMenuDesserts);


        kolmSibulatStarters.setOnClickListener(this);
        kolmSibulatMainCourse.setOnClickListener(this);
        kolmSibulatDesserts.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);
        switch(item.getItemId()) {
            case R.id.actionHome:
                Intent main = new Intent(this, MainActivity.class);
                main.putExtra("message1", name);
                main.putExtra("message2",email);
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
            case R.id.kolmSibulatMainMenuStarters:
                Intent starters = new Intent(this, KolmSibulatStarters.class);
                starters.putExtra("message1", name);
                starters.putExtra("message2",email);
                startActivity(starters);
                break;
            case R.id.kolmSibulatMainMenuMainCourse:
                Intent mainCourse = new Intent(this, KolmSibulatMainCourse.class);
                mainCourse.putExtra("message1", name);
                mainCourse.putExtra("message2",email);
                startActivity(mainCourse);
                break;
            case R.id.kolmSibulatMainMenuDesserts:
                Intent desserts = new Intent(this, KolmSibulatDessert.class);
                desserts.putExtra("message1", name);
                desserts.putExtra("message2",email);
                startActivity(desserts);
                break;

        }
    }

}
