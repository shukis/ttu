package com.example.pavel.foodel1.chedi;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.example.pavel.foodel1.MainActivity;
import com.example.pavel.foodel1.R;
import com.example.pavel.foodel1.SingIn;


public class Chedi extends AppCompatActivity implements View.OnClickListener  {
    TextView chediStarters;
    TextView chediMainCourse;
    TextView chediDesserts;
    String email,name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("message1");
        email = bundle.getString("message2");
        super.onCreate(savedInstanceState);
        setTitle("Chedi");
        setContentView(R.layout.chedi);
        chediStarters = (TextView)findViewById(R.id.chediMainMenuStarters);
        chediMainCourse = (TextView)findViewById(R.id.chediMainMenuMainCourse);
        chediDesserts = (TextView)findViewById(R.id.chediMainMenuDesserts);


        chediStarters.setOnClickListener(this);
        chediMainCourse.setOnClickListener(this);
        chediDesserts.setOnClickListener(this);

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
            case R.id.chediMainMenuStarters:
                Intent starters = new Intent(this, ChediStarters.class);
                starters.putExtra("message1", name);
                starters.putExtra("message2",email);
                startActivity(starters);
                break;
            case R.id.chediMainMenuMainCourse:
                Intent mainCourse = new Intent(this, ChediMainCourse.class);
                mainCourse.putExtra("message1", name);
                mainCourse.putExtra("message2",email);
                startActivity(mainCourse);
                break;
            case R.id.chediMainMenuDesserts:
                Intent desserts = new Intent(this, ChediDesserts.class);
                desserts.putExtra("message1", name);
                desserts.putExtra("message2",email);
                startActivity(desserts);
                break;

        }
    }
}
