package com.abdulrehman.i170357;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity3 extends AppCompatActivity {

    TextView name, phone, address, email;
    CircleImageView image;
    Bitmap photo;
    Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.number);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        image = findViewById(R.id.image);
        ok = findViewById(R.id.ok);

        String nm=getIntent().getStringExtra("name");
        name.setText(nm);
        String ph=getIntent().getStringExtra("phone");
        phone.setText(ph);
        String ad=getIntent().getStringExtra("address");
        address.setText(ad);
        String em=getIntent().getStringExtra("email");
        email.setText(em);
        String ig=getIntent().getStringExtra("image");
        if(ig!=null){
            byte[] decodedString = Base64.decode(ig, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            image.setImageBitmap(decodedByte);
        }
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}