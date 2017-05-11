package com.example.pavel.foodel1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Delivery extends AppCompatActivity implements View.OnClickListener{
    String name,email,restoranName,totalSum;
    TextView orderTotalSumma, deliveryWeBring, deliverySelfPickUp, restaurantName, restaurantAddress, restaurantCity, orSeparator;
    DBHelper dbHelper;
    SQLiteDatabase db;
    EditText stAddress, stCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("message1");
        email = bundle.getString("message2");
        totalSum = bundle.getString("message3");
        restoranName = bundle.getString("resto");
        setTitle("Delivery");
        setContentView(R.layout.delivery);

        setId();
        hideDeliveryButtons();
        getCityAndAddress();

        deliveryWeBring.setOnClickListener(this);
        deliverySelfPickUp.setOnClickListener(this);
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
            case R.id.deliveryPickUp:
                Intent profile = new Intent(this, Payment.class);
                profile.putExtra("message1", name);
                profile.putExtra("message2", email);
                startActivity(profile);
                break;
            case R.id.deliveryWeBring:
                Intent payment = new Intent(this, Payment.class);
                payment.putExtra("message1", name);
                payment.putExtra("message2", email);
                startActivity(payment);
                break;
        }
    }
    public void getCityAndAddress(){
        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(DBHelper.TABLE_CONTACTS,null,null,null,null,null,null);
        cursor.moveToFirst();
        do{
            int emailIndex = cursor.getColumnIndex(DBHelper.KEY_EMAIL);
            int cityIndex = cursor.getColumnIndex(DBHelper.KEY_CITY);
            int addressIndex = cursor.getColumnIndex(DBHelper.KEY_ADDRESS);
            if(cursor.getString(emailIndex).trim().equalsIgnoreCase(email.trim())){
                stCity.setText(cursor.getString(cityIndex));
                stAddress.setText(cursor.getString(addressIndex));

            }
        }while (cursor.moveToNext());
        cursor.close();
    }

    public String[] getRestaurantAddress(){
        String[] address = new String[3];
        if(restoranName.equalsIgnoreCase("chedi")){
            return chedi(address);
        }else if(restoranName.equalsIgnoreCase("kaks kokka")){
            return kaksKokka(address);
        }else if(restoranName.equalsIgnoreCase("trühvel")){
            return truhvel(address);
        }else if(restoranName.equalsIgnoreCase("kolm sibulat")){
            return kolmSibulat(address);
        }else if(restoranName.equalsIgnoreCase("moon")){
            return moon(address);
        }

        return null;
    }
    public String[] chedi(String[] address){
        address[0] = "Chedi";
        address[1] = "Tallinn";
        address[2] = "Sulevimägi 1";
        return address;
    }
    public String[] moon(String[] address){
        address[0] = "Moon";
        address[1] = "Tallinn";
        address[2] = "Võrgu 3";
        return address;
    }
    public String[] kaksKokka(String[] address){
        address[0] = "Kaks Kokka";
        address[1] = "Tallinn";
        address[2] = "Mere puiestee 6E";
        return address;
    }
    public String[] truhvel(String[] address){
        address[0] = "Trühvel";
        address[1] = "Tallinn";
        address[2] = "Telliskivi 60";
        return address;
    }
    public String[] kolmSibulat(String[] address){
        address[0] = "Kolm Sibulat";
        address[1] = "Tallinn";
        address[2] = "Telliskivi 2";
        return address;
    }

    public void hideDeliveryButtons(){
        if(totalSum.equalsIgnoreCase("Total to pay: 0€")){
            deliverySelfPickUp.setVisibility(View.INVISIBLE);
            deliverySelfPickUp.setClickable(false);
            deliveryWeBring.setVisibility(View.INVISIBLE);
            deliveryWeBring.setClickable(false);
            orSeparator.setVisibility(View.INVISIBLE);

        }
    }

    public void setId(){
        orSeparator = (TextView)findViewById(R.id.textView8);
        orderTotalSumma = (TextView)findViewById(R.id.deliveryTotalSumOfOrder);
        deliverySelfPickUp = (TextView)findViewById(R.id.deliveryPickUp);
        deliveryWeBring = (TextView)findViewById(R.id.deliveryWeBring);
        restaurantName = (TextView)findViewById(R.id.deliveryRestoranName);
        restaurantCity = (TextView)findViewById(R.id.deliveryRestoranCity);
        restaurantAddress = (TextView)findViewById(R.id.deliveryRestoranAddress);
        stAddress = (EditText)findViewById(R.id.deliveryAddress);
        stCity = (EditText)findViewById(R.id.deliveryCity);
        restaurantName.setText(getRestaurantAddress()[0]+" address:");
        restaurantCity.setText(getRestaurantAddress()[1]);
        restaurantAddress.setText(getRestaurantAddress()[2]);
        orderTotalSumma.setText(totalSum);
    }
}
