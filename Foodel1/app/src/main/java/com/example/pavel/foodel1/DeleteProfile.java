package com.example.pavel.foodel1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class DeleteProfile extends AppCompatActivity implements View.OnClickListener {
    String currentEmail;
    DBHelper dbHelper;
    SQLiteDatabase db;
    Button delete;
    EditText password;
    TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_profile);
        Bundle bundle = getIntent().getExtras();
        currentEmail = bundle.getString("email");


        delete = (Button)findViewById(R.id.deleteButton);
        password = (EditText)findViewById(R.id.deletePassword);
        email = (TextView)findViewById(R.id.deleteEmail);
        email.setText(currentEmail);

        delete.setOnClickListener(this);
    }
    public boolean checkPassword(){
        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(DBHelper.TABLE_CONTACTS,null,null,null,null,null,null);
        cursor.moveToFirst();
        do {
            int emailIndex = cursor.getColumnIndex(DBHelper.KEY_EMAIL);
            int passwordIndex = cursor.getColumnIndex(DBHelper.KEY_PASSWORD);
            if(cursor.getString(emailIndex).trim().equalsIgnoreCase(currentEmail.trim())){
                String currentPassword = cursor.getString(passwordIndex);
                if(password.getText().toString().trim().equals(currentPassword.trim())){
                    return true;
                }
            }

        }while (cursor.moveToNext());
        cursor.close();


    return false;
    }

    public void deleteProfile(){
        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + DBHelper.TABLE_CONTACTS + " WHERE " + DBHelper.KEY_EMAIL + "='" + currentEmail + "'");
        Toast toast;
        toast = Toast.makeText(this, "You have successfully deleted your account!", Toast.LENGTH_SHORT);
        View view = toast.getView();
        view.setBackgroundColor(Color.argb(100, 255, 255, 255));
        view.setMinimumWidth(1300);
        toast.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.deleteButton:
                if(checkPassword()){
                    deleteProfile();
                    Intent signin = new Intent(this, SingIn.class);
                    startActivity(signin);
                }else{
                    Toast toast;
                    toast = Toast.makeText(this, "Insert your password or press back button to continue!", Toast.LENGTH_SHORT);
                    View view = toast.getView();
                    view.setBackgroundColor(Color.argb(100, 255, 255, 255));
                    view.setMinimumWidth(1300);
                    toast.show();
                }
        }

    }
}
