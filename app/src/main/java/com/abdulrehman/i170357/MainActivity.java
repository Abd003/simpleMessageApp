package com.abdulrehman.i170357;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.abdulrehman.i170357.MainActivity4.REQUEST_CODE;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    EditText name, phone, address, email;
    Button save;
    CircleImageView image;
    Bitmap photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.name);
        phone =(EditText) findViewById(R.id.phone);
        address =(EditText) findViewById(R.id.address);
        email =(EditText) findViewById(R.id.email);
        save = findViewById(R.id.save);
        image =(CircleImageView) findViewById(R.id.cont_image);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String encoded = null;
                if (photo != null) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    photo.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                }
                final String finalEncoded = encoded;
                Intent intent = new Intent(MainActivity.this, MainActivity4.class);
                intent.putExtra("name",name.getText().toString());
                intent.putExtra("phone",phone.getText().toString());
                intent.putExtra("address",address.getText().toString());
                intent.putExtra("email",email.getText().toString());
                intent.putExtra("image",finalEncoded);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,1);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");
            image.setImageBitmap(photo);
        }
    }
}