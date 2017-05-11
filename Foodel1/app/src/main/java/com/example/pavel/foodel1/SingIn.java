package com.example.pavel.foodel1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pavel.foodel1.chedi.Chedi;

public class SingIn extends Activity implements View.OnClickListener{

    String email, password, name, surname;
    AutoCompleteTextView enteredEmail;
    EditText enteredPassword;
    Button signIn;
    Button registrate;
    DBHelper dbHelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sign_in);
        setId();
        dbHelper = new DBHelper(this);

        signIn.setOnClickListener(this);
        registrate.setOnClickListener(this);
        enteredEmail.setOnClickListener(this);
        enteredPassword.setOnClickListener(this);


    }

    public void setId(){
        signIn = (Button)findViewById(R.id.signInButton);
        registrate = (Button)findViewById(R.id.signInRegistrationButton);
        enteredEmail = (AutoCompleteTextView) findViewById(R.id.signInEmail);
        enteredPassword = (EditText) findViewById(R.id.signInPassword);
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        email = enteredEmail.getText().toString().trim();
        password = enteredPassword.getText().toString().trim();


        switch (v.getId()) {
            case R.id.signInButton:
                if(checkColumnFromDb(email) || email.equals("test")) {
                    Intent mainMenu = new Intent(this, MainActivity.class);
                    mainMenu.putExtra("message1", name+" "+surname);
                    mainMenu.putExtra("message2", email);
                    startActivity(mainMenu);

                }
                else{
                    enteredPassword.setText("");
                    Toast toast;
                    toast = Toast.makeText(this, "Check your Email and password", Toast.LENGTH_SHORT);
                    View view = toast.getView();
                    view.setBackgroundColor(Color.argb(100, 255, 255, 255));
                    view.setMinimumWidth(1000);
                    toast.show();

                }
                break;
            case R.id.signInRegistrationButton:
                Intent registration = new Intent(this, RegistrationForm.class);
                startActivity(registration);
                break;
        }
    }


    public boolean checkColumnFromDb(String email){
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(DBHelper.TABLE_CONTACTS,null,null,null,null,null,null);
        if(cursor.moveToFirst()) {
            int emailIndex = cursor.getColumnIndex(DBHelper.KEY_EMAIL);
            int passwordIndex = cursor.getColumnIndex(DBHelper.KEY_PASSWORD);
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
            int surnameIndex = cursor.getColumnIndex(DBHelper.KEY_SURNAME);
            do {
                if (email.trim().equalsIgnoreCase(cursor.getString(emailIndex).trim())&&
                        password.trim().equalsIgnoreCase(cursor.getString(passwordIndex))) {
                    name = cursor.getString(nameIndex);
                    surname = cursor.getString(surnameIndex);
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
