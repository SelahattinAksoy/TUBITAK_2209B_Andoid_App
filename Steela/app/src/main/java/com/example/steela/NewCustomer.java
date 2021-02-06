package com.example.steela;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class NewCustomer extends AppCompatActivity {



    Button save_newCustomer;
    TextView customer_name,customer_address,customer_tel,customer_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_new_customer);



        customer_name=findViewById(R.id.new_customer_name_id);
        customer_address=findViewById(R.id.new_customer_address_id);
        customer_tel=findViewById(R.id.new_customer_tel_id);
        customer_detail=findViewById(R.id.new_customer_detail_id);

        save_newCustomer=findViewById(R.id.new_customer_register_id);

        save_newCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save_newCustomer.setEnabled(false);

                String name_of_Customer=customer_name.getText().toString();
                String address_of_Customer=customer_address.getText().toString();
                String tel_of_Customer=customer_tel.getText().toString();
                String detail_of_Customer=customer_detail.getText().toString();

                Customer c=new Customer(name_of_Customer,address_of_Customer,tel_of_Customer,detail_of_Customer);
                SaveToFireBase s=new SaveToFireBase("customer",NewCustomer.this);

                s.Save(c);
               // Bitmap qr=getQRCode(c);
                //Bitmap br=getBarCode(c);







            }
        });



    }

    public Bitmap getQRCode(Customer c){

        Bitmap qr = null;
        try{

            qr=qrcode(c);


        }catch (Exception e){

            e.printStackTrace();
        }

        return qr;
    }

    public Bitmap getBarCode(Customer c){

        Bitmap br = null;
        try{

            br=qrcode(c);


        }catch (Exception e){

            e.printStackTrace();
        }

        return br;
    }



    MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
    public Bitmap qrcode(Customer c) throws WriterException {

        BitMatrix bitMatrix=multiFormatWriter.encode(c.customer_name, BarcodeFormat.QR_CODE,350,300 );
        BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
        Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);
        return bitmap;

    }
    public Bitmap barcode(Customer c) throws WriterException {

        BitMatrix bitMatrix=multiFormatWriter.encode(c.customer_name, BarcodeFormat.CODE_128,400,170 );
        BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
        Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);

        return bitmap;
    }
}
