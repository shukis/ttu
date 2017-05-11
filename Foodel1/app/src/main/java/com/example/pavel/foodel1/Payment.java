package com.example.pavel.foodel1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class Payment extends AppCompatActivity implements View.OnClickListener {
    ImageView swdbank, seb, nordea, danske;
    String email, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("message1");
        email = bundle.getString("message2");
        setContentView(R.layout.payment);
        setTitle("Payment");

        setId();

        swdbank.setOnClickListener(this);
        seb.setOnClickListener(this);
        danske.setOnClickListener(this);
        nordea.setOnClickListener(this);
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
            case R.id.imageButtonSwedbank:
                Intent swedbank = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.swedbank.ee"));
                startActivity(swedbank);
                break;
            case R.id.imageButtonSeb:
                Intent seb = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.seb.ee"));
                startActivity(seb);
                break;
            case R.id.imageButtonNordea:
                Intent nordea = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.nordea.ee"));
                startActivity(nordea);
                break;
            case R.id.imageButtonDanske:
                Intent danske = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.danske.ee"));
                startActivity(danske);
                break;
        }
    }

    public void setId(){
        swdbank = (ImageView)findViewById(R.id.imageButtonSwedbank);
        seb = (ImageView)findViewById(R.id.imageButtonSeb);
        danske = (ImageView)findViewById(R.id.imageButtonDanske);
        nordea = (ImageView)findViewById(R.id.imageButtonNordea);
    }
}
