package com.example.steela;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {



    private ImageView take_order;
    private ImageView new_cutomer;
    private ImageView list_order;
    private ImageView barcode_reader;
    private Button btn_logout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main2);

        mAuth = FirebaseAuth.getInstance();
        take_order=findViewById(R.id.take_order_id);
        list_order=findViewById(R.id.list_order_id);
        new_cutomer=findViewById(R.id.new_customer_id);
        barcode_reader=findViewById(R.id.read_barkode_id);














        btn_logout=findViewById(R.id.logout_id);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent i = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(i);
            }
        });


        take_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Main2Activity.this, TakeOrder.class);
                startActivity(i);
            }
        });
        list_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Main2Activity.this, OrderList.class);
                startActivity(i);
            }
        });
        new_cutomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Main2Activity.this, NewCustomer.class);
                startActivity(i);
            }
        });

        barcode_reader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Main2Activity.this, BarcodeReader.class);
                startActivity(i);
            }
        });


    }
}
