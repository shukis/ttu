package com.example.pavel.foodel1;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegistrationForm extends Activity implements View.OnClickListener{
    Button registration;
    EditText stEmail, stName, stSurname, stCity, stAddress, stPassword, stCheckPassword;
    DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_form);
        setId();
        registration.setOnClickListener(this);
        dbHelper = new DBHelper(this);

    }

    public void setId(){
        registration = (Button)findViewById(R.id.registrationButton);
        stEmail = (EditText)findViewById(R.id.registrationEmail);
        stName = (EditText)findViewById(R.id.registrationName);
        stSurname = (EditText)findViewById(R.id.registrationSurname);
        stCity = (EditText)findViewById(R.id.registrationCity);
        stAddress = (EditText)findViewById(R.id.registrationAddress);
        stPassword = (EditText)findViewById(R.id.registrationPassword);
        stCheckPassword = (EditText)findViewById(R.id.registrationCheckPassword);
    }


    @Override
    public void onClick(View v) {
        String email = stEmail.getText().toString();
        String name = stName.getText().toString();
        String surname = stSurname.getText().toString();
        String city = stCity.getText().toString();
        String address = stAddress.getText().toString();
        String password = stPassword.getText().toString();
        String checkPassword = stCheckPassword.getText().toString();
        if (checkPasswords(password, checkPassword)) {
            if (checkEmail() &&
                    checkEntryFields()) {
                switch (v.getId()) {
                    case R.id.registrationButton:
                        writeParametersIntoDb(email, name, surname, city, address, password);
                        Intent mainMenu = new Intent(this, SingIn.class);
                        startActivity(mainMenu);
                        break;
                }
                dbHelper.close();
            }
        } else {
            showToast(RegistrationForm.this, "Check your password!");
        }
    }

    public void writeParametersIntoDb(String email, String name, String surname,
                                      String city, String address, String password){
        db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.KEY_EMAIL, email.trim());
        contentValues.put(DBHelper.KEY_NAME, name.trim());
        contentValues.put(DBHelper.KEY_SURNAME, surname.trim());
        contentValues.put(DBHelper.KEY_CITY, city);
        contentValues.put(DBHelper.KEY_ADDRESS, address);
        contentValues.put(DBHelper.KEY_PASSWORD, password.trim());

        db.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
    }

    public boolean checkEmail() {
        String email = stEmail.getText().toString();
        if (checkColumnFromDb(email, DBHelper.KEY_EMAIL)) {
            showToast(RegistrationForm.this, "This Email is in use.\n Please choose another one!");
            return false;
        }
        if (checkCorrectEmail(email)) {
            return true;
        } else {
            showToast(RegistrationForm.this, "Check your Email!");
            return false;
        }
    }

    public boolean checkCorrectEmail(String email){
        for(int i = 0;i<email.length();i++) {
            if (email.charAt(i) == '@') {
                return true;
            }
        }
        return false;
    }

    public boolean checkPasswords(String password, String checkPassword){
        if(password.equals(checkPassword) && password.length()>7){
            return true;
        }
        return false;
    }


    public boolean checkEntryFields(){
        if(getStName().length()>0 &&
                getStSurname().length()>0 &&
                getStAddress().length()>0 &&
                getStCity().length()>0){
            return true;
        }else showToast(RegistrationForm.this,"All entry fields should be filled!");
        return false;
    }



    public String getStName() {
        return stName.getText().toString();
    }

    public String getStSurname() {
        return stSurname.getText().toString();
    }

    public String getStCity() {
        return stCity.getText().toString();
    }

    public String getStAddress() {
        return stAddress.getText().toString();
    }


    public void showToast(Context context, String message){
        Toast toast;
        toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        View view = toast.getView();
        view.setBackgroundColor(Color.argb(100,255,255,255));
        view.setMinimumWidth(1000);
        toast.show();
        stPassword.setText("");
        stCheckPassword.setText("");
    }


    public boolean checkColumnFromDb(String parameter, String column){
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(DBHelper.TABLE_CONTACTS,null,null,null,null,null,null);
        if(cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex(column);
            do {
                if (parameter.trim().equalsIgnoreCase(cursor.getString(columnIndex).trim())) {
                    cursor.close();
                    db.close();
                    return true;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return false;
    }
}
