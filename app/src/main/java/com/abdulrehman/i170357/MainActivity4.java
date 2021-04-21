package com.abdulrehman.i170357;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity4<TAG> extends AppCompatActivity implements MyRvAdapter.OnItemListner{
    public static final int REQUEST_CODE = 1;
    RecyclerView rv;
    FloatingActionButton addContact;
    FloatingActionButton sendMessage;
    List<Contact> contacts;
    AutoCompleteTextView search;
    ArrayList names=new ArrayList();
    MyRvAdapter adapter;
    ArrayAdapter<String> Search_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        rv = findViewById(R.id.rv);
        addContact = findViewById(R.id.addContact);
        sendMessage = findViewById(R.id.sendMessage);
        search=(AutoCompleteTextView) findViewById(R.id.search);
        contacts = new ArrayList<>();

        Search_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,names);
        Search_adapter.setNotifyOnChange(true);
        search.setThreshold(1);
        search.setAdapter(Search_adapter);

        adapter = new MyRvAdapter(contacts, this, this);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);

        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity4.this, MainActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity4.this, MainActivity2.class);
                startActivity(intent1);
            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(REQUEST_CODE == 1){
            if(resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    Toast.makeText(MainActivity4.this, "Data Received", Toast.LENGTH_SHORT).show();
                    String name = (String) data.getExtras().getString("name");
                    String address = (String) data.getExtras().getString("address");
                    String phone = (String) data.getExtras().getString("phone");
                    String email = (String) data.getExtras().getString("email");
                    String image = (String) data.getExtras().getString("image");
                    names.add(name);
                    contacts.add(new Contact(name,address,phone,email,image));
                    adapter.notifyDataSetChanged();
                    Search_adapter.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public void ONItemClick(int position){
        Log.d("TAG","onItemListner: clicked.");

        contacts.get(position);
        Intent intent2 = new Intent(this,MainActivity3.class);
        intent2.putExtra("name",contacts.get(position).getName());
        intent2.putExtra("phone",contacts.get(position).getPhno());
        intent2.putExtra("address",contacts.get(position).getAddress());
        intent2.putExtra("email",contacts.get(position).getEmail());
        intent2.putExtra("image",contacts.get(position).getImage());
        startActivity(intent2);
    }
}