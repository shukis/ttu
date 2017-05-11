package com.example.pavel.foodel1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pavel.foodel1.chedi.Chedi;
import com.example.pavel.foodel1.kaksKokka.KaksKokka;
import com.example.pavel.foodel1.kolmSibulat.KolmSibulat;
import com.example.pavel.foodel1.moon.Moon;
import com.example.pavel.foodel1.truhvel.Truhvel;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    String name, email;

    final String FILENAME  = "order";
    BufferedWriter bw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("message1");
        email = bundle.getString("message2");
        super.onCreate(savedInstanceState);
        setTitle(name);
        setContentView(R.layout.activity_main);
        setId();

        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);
        textView5.setOnClickListener(this);
        try {
            clearFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @Override
    protected void onResume(){
        super.onResume();
        try {
            clearFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public void onBackPressed() {

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);
        switch(item.getItemId()) {
            case R.id.actionExit:
                startActivity(new Intent(this, SingIn.class));
                return true;
            case R.id.actionProfile:
                Intent profile = new Intent(this,Profile.class);
                profile.putExtra("message1", name);
                profile.putExtra("message2", email);
                startActivity(profile);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView:
                Intent restoran1 = new Intent(this, Chedi.class);
                restoran1.putExtra("message1", name);
                restoran1.putExtra("message2",email);
                startActivity(restoran1);
                break;
            case R.id.textView2:
                Intent restoran2 = new Intent(this, KolmSibulat.class);
                restoran2.putExtra("message1", name);
                restoran2.putExtra("message2",email);
                startActivity(restoran2);
                break;
            case R.id.textView3:
                Intent restoran3 = new Intent(this, KaksKokka.class);
                restoran3.putExtra("message1", name);
                restoran3.putExtra("message2",email);
                startActivity(restoran3);
                break;
            case R.id.textView4:
                Intent restoran4 = new Intent(this, Moon.class);
                restoran4.putExtra("message1", name);
                restoran4.putExtra("message2",email);
                startActivity(restoran4);
                break;
            case R.id.textView5:
                Intent restoran5 = new Intent(this, Truhvel.class);
                restoran5.putExtra("message1", name);
                restoran5.putExtra("message2",email);
                startActivity(restoran5);
                break;

        }
    }
    public void clearFile() throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(openFileOutput(FILENAME,MODE_PRIVATE)));
        bw.close();
        Log.d("file", "o4iwen");
    }
    public void setId(){
        textView1 = (TextView)findViewById(R.id.textView);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);
        textView4 = (TextView)findViewById(R.id.textView4);
        textView5 = (TextView)findViewById(R.id.textView5);
    }

}
