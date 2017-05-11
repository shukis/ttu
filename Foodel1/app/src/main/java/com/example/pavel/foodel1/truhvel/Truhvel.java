package com.example.pavel.foodel1.truhvel;

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

public class Truhvel extends AppCompatActivity implements View.OnClickListener{
    TextView truhvelStarters;
    TextView truhvelMainCourse;
    TextView truhvelDesserts;
    String email,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("message1");
        email = bundle.getString("message2");
        super.onCreate(savedInstanceState);
        setTitle("Trühvel");

        setContentView(R.layout.truhvel);

        truhvelStarters = (TextView)findViewById(R.id.truhvelMainMenuStarters);
        truhvelMainCourse= (TextView)findViewById(R.id.truhvelMainMenuMainCourse);
        truhvelDesserts = (TextView)findViewById(R.id.truhvelMainMenuDesserts);

        truhvelStarters.setOnClickListener(this);
        truhvelMainCourse.setOnClickListener(this);
        truhvelDesserts.setOnClickListener(this);
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
            case R.id.truhvelMainMenuStarters:
                Intent starters = new Intent(this, TruhvelStarters.class);
                starters.putExtra("message1", name);
                starters.putExtra("message2",email);
                startActivity(starters);
                break;
            case R.id.truhvelMainMenuMainCourse:
                Intent mainCourse = new Intent(this, TruhvelMainCourse.class);
                mainCourse.putExtra("message1", name);
                mainCourse.putExtra("message2",email);
                startActivity(mainCourse);
                break;
            case R.id.truhvelMainMenuDesserts:
                Intent desserts = new Intent(this, TruhvelDesserts.class);
                desserts.putExtra("message1", name);
                desserts.putExtra("message2",email);
                startActivity(desserts);
                break;

        }

    }
}
