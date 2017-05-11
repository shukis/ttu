package com.example.pavel.foodel1.moon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.pavel.foodel1.MainActivity;
import com.example.pavel.foodel1.R;
import com.example.pavel.foodel1.SingIn;

import static com.example.pavel.foodel1.R.id.moonMainMenuDesserts;
import static com.example.pavel.foodel1.R.id.moonMainMenuMainCourse;
import static com.example.pavel.foodel1.R.id.moonMainMenuStarters;
import static com.example.pavel.foodel1.R.id.textView;

public class Moon extends AppCompatActivity implements View.OnClickListener{
    TextView moonStarters;
    TextView moonMainCourse;
    TextView moonDesserts;
    String email,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("message1");
        email = bundle.getString("message2");
        super.onCreate(savedInstanceState);
        setTitle("Moon");

        setContentView(R.layout.moon);

        moonStarters = (TextView)findViewById(moonMainMenuStarters);
        moonMainCourse = (TextView)findViewById(moonMainMenuMainCourse);
        moonDesserts = (TextView)findViewById(moonMainMenuDesserts);

        moonStarters.setOnClickListener(this);
        moonMainCourse.setOnClickListener(this);
        moonDesserts.setOnClickListener(this);
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
            case R.id.moonMainMenuStarters:
                Intent starters = new Intent(this, MoonStarters.class);
                starters.putExtra("message1", name);
                starters.putExtra("message2",email);
                startActivity(starters);
                break;
            case R.id.moonMainMenuMainCourse:
                Intent mainCourse = new Intent(this, MoonMainCourse.class);
                mainCourse.putExtra("message1", name);
                mainCourse.putExtra("message2",email);
                startActivity(mainCourse);
                break;
            case R.id.moonMainMenuDesserts:
                Intent desserts = new Intent(this, MoonDesserts.class);
                desserts.putExtra("message1", name);
                desserts.putExtra("message2",email);
                startActivity(desserts);
                break;

        }

    }
}
