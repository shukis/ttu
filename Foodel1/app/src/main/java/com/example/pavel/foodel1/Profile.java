package com.example.pavel.foodel1;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    Button confirm;
    EditText stName, stSurname, stCity, stAddress, stCheckPassword;
    TextView stEmail;
    DBHelper dbHelper;
    SQLiteDatabase db;
    String password;

    String email, name, surname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Profile");
        setContentView(R.layout.profile);
        Bundle bundle = getIntent().getExtras();
        email = bundle.getString("message2");

        setId();
        confirm.setOnClickListener(this);
        getDataFromDb();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.delete_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);
        switch(item.getItemId()) {
            case R.id.actionDelete:
                Intent delete = new Intent(this, DeleteProfile.class);
                delete.putExtra("email", email);
                startActivity(delete);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void getDataFromDb() {

        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(DBHelper.TABLE_CONTACTS,null,null,null,null,null,null);
        cursor.moveToFirst();
        Log.d("logg","0");
        do{
            Log.d("logg","1");
            int emailIndex = cursor.getColumnIndex(DBHelper.KEY_EMAIL);
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
            int surnameIndex = cursor.getColumnIndex(DBHelper.KEY_SURNAME);
            int cityIndex = cursor.getColumnIndex(DBHelper.KEY_CITY);
            int addressIndex = cursor.getColumnIndex(DBHelper.KEY_ADDRESS);
            int passwordIndex = cursor.getColumnIndex(DBHelper.KEY_PASSWORD);
            if(cursor.getString(emailIndex).trim().equalsIgnoreCase(email.trim())){
                Log.d("logg","2");
                Log.d("logg", cursor.getString(emailIndex)+" "+ cursor.getString(passwordIndex));
                stEmail.setText(email);
                stName.setText(cursor.getString(nameIndex));
                stSurname.setText(cursor.getString(surnameIndex));
                stCity.setText(cursor.getString(cityIndex));
                stAddress.setText(cursor.getString(addressIndex));
                password = cursor.getString(passwordIndex);
            }
        }while (cursor.moveToNext());
        cursor.close();
    }

    private void setId() {
        confirm = (Button)findViewById(R.id.profileButton);
        stEmail = (TextView)findViewById(R.id.profileEmail);
        stName = (EditText)findViewById(R.id.profileName);
        stSurname = (EditText)findViewById(R.id.profileSurname);
        stCity = (EditText)findViewById(R.id.profileCity);
        stAddress = (EditText)findViewById(R.id.profileAddress);
        stCheckPassword = (EditText)findViewById(R.id.profileCheckPassword);
    }

    @Override
    public void onClick(View v) {
        ContentValues contentvalues = new ContentValues();

        switch (v.getId()) {
            case R.id.profileButton:
                fillTextFields(contentvalues);
                if(checkEntryFields()) {
                    if (stCheckPassword.getText().toString().trim().equalsIgnoreCase(password)) {
                        db.update(DBHelper.TABLE_CONTACTS, contentvalues, DBHelper.KEY_PASSWORD + "= ?", new String[]{password});
                        Intent signIn = new Intent(this, MainActivity.class);
                        signIn.putExtra("message1", name+" "+surname);
                        signIn.putExtra("message2", email);
                        startActivity(signIn);
                        dbHelper.close();
                        db.close();
                    } else {
                        Toast toast;
                        toast = Toast.makeText(this, "Check your password", Toast.LENGTH_SHORT);
                        View view = toast.getView();
                        view.setBackgroundColor(Color.argb(100, 255, 255, 255));
                        view.setMinimumWidth(1000);
                        toast.show();
                        stCheckPassword.setText("");
                    }
                    break;
                }
        }
    }

    private void fillTextFields(ContentValues contentvalues) {
        name = stName.getText().toString();
        surname = stSurname.getText().toString();
        contentvalues.put(DBHelper.KEY_EMAIL, stEmail.getText().toString());
        contentvalues.put(DBHelper.KEY_NAME, name);
        contentvalues.put(DBHelper.KEY_SURNAME, surname);
        contentvalues.put(DBHelper.KEY_CITY, stCity.getText().toString());
        contentvalues.put(DBHelper.KEY_ADDRESS, stAddress.getText().toString());
    }

    public boolean checkEntryFields(){
            if(stName.getText().toString().length()>0 &&
                    stSurname.getText().toString().length()>0 &&
                    stCity.getText().toString().length()>0 &&
                    stAddress.getText().toString().length()>0){
                return true;
            }else{
                Toast toast;
                toast = Toast.makeText(this, "All entry fields should be filled!", Toast.LENGTH_SHORT);
                View view = toast.getView();
                view.setBackgroundColor(Color.argb(100, 255, 255, 255));
                view.setMinimumWidth(1000);
                toast.show();
            }
            return false;
        }


}
