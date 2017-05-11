package com.example.pavel.foodel1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OrderConfirmation extends AppCompatActivity implements View.OnClickListener{
    String email,name, restoranName;
    final String FILENAME  = "order";
    final String ATTRIBUTE_NAME = "name";
    final String ATTRIBUTE_PRICE = "price";
    final String ATTRIBUTE_QUANTITY = "quantity";
    ListView listView;
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> prices = new ArrayList<>();
    ArrayList<String> quantities = new ArrayList<>();
    ArrayList<Map<String, String>> data;
    TextView totalSum;
    private static final int CM_DELETE_ID =1;
    SimpleAdapter sAdapter;
    Button payment, reset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("message1");
        email = bundle.getString("message2");
        restoranName = bundle.getString("resto");
        super.onCreate(savedInstanceState);
        setTitle(name);
        setContentView(R.layout.order_confirmation);
        totalSum = (TextView) findViewById(R.id.orderTotalSum);
        payment = (Button)findViewById(R.id.orderPayment);
        reset = (Button)findViewById(R.id.orderReset);
        payment.setOnClickListener(this);
        reset.setOnClickListener(this);

        createListView();


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

    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            HashMap<String, Object> itemHashMap = (HashMap <String, Object>) parent.getItemAtPosition(position);
            String name = itemHashMap.get(ATTRIBUTE_NAME).toString();
            String quantity = itemHashMap.get(ATTRIBUTE_QUANTITY).toString();
            int index = names.indexOf(name);
            int next = Integer.parseInt(quantity);
            next++;
            quantities.set(index, Integer.toString(next));
            createListView();

        }
    };

    public void fillListViewFromFile(){
        String[] splitted = splitText(readFile());

        for (int i = 0; i < splitted.length; i++) {
            if (!names.contains(separateName(splitted[i]))) {
                names.add(separateName(splitted[i]));
                prices.add(separatePrice(splitted[i]));
                quantities.add("1");
            }
        }
    }

    public int totalSum(String price, String quantity){
        int total = Integer.parseInt(price)*Integer.parseInt(quantity);
        return total;
    }

    public void createHash() {
        int totalSumma = 0;
        if (names.isEmpty()) {
            fillListViewFromFile();
        }
        if (!names.contains(null)) {

            data = new ArrayList<>(names.size());
            Map<String, String> m;
            for (int i = 0; i < names.size(); i++) {
                m = new HashMap<>();
                m.put(ATTRIBUTE_NAME, names.get(i));
                m.put(ATTRIBUTE_PRICE, "Price: " + prices.get(i) + "€");
                m.put(ATTRIBUTE_QUANTITY, quantities.get(i));
                data.add(m);
                totalSumma += totalSum(prices.get(i), quantities.get(i));
            }
        }
        totalSum.setText("Total to pay: " + Integer.toString(totalSumma)+"€");
    }


    public void createListView() {
        createHash();
        if (!names.contains(null)) {
            String[] from = {ATTRIBUTE_NAME, ATTRIBUTE_PRICE, ATTRIBUTE_QUANTITY};
            int[] to = {R.id.listViewName, R.id.listViewPrice, R.id.listViewQuantity};

            sAdapter = new SimpleAdapter(this, data, R.layout.my_list_menu, from, to);

            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(sAdapter);

            registerForContextMenu(listView);
            listView.setOnItemClickListener(itemClickListener);
        }
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);

        SpannableString span = new SpannableString("REMOVE");
        span.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.contextColor)),0,span.length(),0);
        span.setSpan(new AbsoluteSizeSpan(70),0,span.length(),0);
        menu.add(0,CM_DELETE_ID,0,span);
    }



    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getItemId()==CM_DELETE_ID){
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            String name = names.get(acmi.position);
            removeLine(name);
            names.remove(acmi.position);
            data.remove(acmi.position);
            prices.remove(acmi.position);
            quantities.remove(acmi.position);
            sAdapter.notifyDataSetChanged();
            createListView();
            return true;
        }
        return super.onContextItemSelected(item);
    }


    public String readFile(){
        String str;
        String text = "";
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(FILENAME)));
            while((str=br.readLine())!=null){
                text = text+str+"//";
            }
            Log.d("file",text);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        return text;
    }

    public String[] splitText(String text){
        String[] splitted = text.split("//");
        return splitted;
    }


    public String separatePrice(String string){
        if(string.length() != 0) {
            char[] c = string.toCharArray();
            String price = "";
            for (int i = c.length - 5; i < c.length; i++) {
                if (Character.isDigit(c[i])) {
                    price = price + c[i];
                }
            }
            return price.trim();
        }
        return null;
    }

    public String separateName(String string){
        if(string.length() != 0) {
            char[] c = string.toCharArray();
            String name = "";
            for (int i = 0; i < c.length - 1; i++) {
                if (!Character.isDigit(c[i])) {
                    name = name + c[i];
                }
            }
            return name.trim();
        }
        return null;
    }


    public void removeLine(String lineToRemove) {
        String currentLine;

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(FILENAME)));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(openFileOutput("temp",MODE_PRIVATE)));
            while((currentLine=br.readLine())!=null){
                if(!currentLine.startsWith(lineToRemove)){
                    bw.write(currentLine+"\n");
                }
            }
            br.close();
            bw.close();

            String dir = getFilesDir().getAbsolutePath();
            File f0 = new File(dir, FILENAME);
            f0.delete();
            File directory = Environment.getExternalStorageDirectory();
            if(directory.exists()){
                File from = new File(dir,"temp");
                File to = new File(dir,FILENAME);
                if(from.exists())
                    from.renameTo(to);
            }

        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.orderPayment:
                Intent payment = new Intent(this, Delivery.class);
                payment.putExtra("message1", name);
                payment.putExtra("message2", email);
                payment.putExtra("message3", totalSum.getText().toString());
                payment.putExtra("resto", restoranName);
                startActivity(payment);
                break;
            case R.id.orderReset:
                names.clear();
                prices.clear();
                quantities.clear();
                createListView();
                break;

        }
    }
}
