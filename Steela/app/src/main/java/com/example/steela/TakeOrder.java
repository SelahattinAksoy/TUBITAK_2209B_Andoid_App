package com.example.steela;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TakeOrder extends AppCompatActivity {


    Double lon,lat;
    DatabaseReference myRef;
    Button btn,product_name_btn,customer_name_btn,produt_amount_btn;
    String[] customer_name_list,product_name_list,product_amount_list;
    EditText product_names,product_amounts,customer_names,customer_addresss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_take_order);


        btn=findViewById(R.id.take_order_button_id);
        product_name_btn=findViewById(R.id.select_product_name);
        produt_amount_btn=findViewById(R.id.select_product_amount);
        customer_name_btn=findViewById(R.id.select_customer_name);

        product_names=findViewById(R.id.ordered_product_name_id);
        product_amounts=findViewById(R.id.ordered_product_amaount_id);
        customer_names=findViewById(R.id.ordered_product_customer_id);
        customer_addresss=findViewById(R.id.customer_address_id);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String extensionRemoved = MainActivity.getUser().getEmail().split("\\.")[0];

        //Log.e("----------",extensionRemoved);
        DatabaseReference myRef = database.getReference().child("users").child(extensionRemoved).child("product");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int size = (int) dataSnapshot.getChildrenCount();
                product_name_list = new ArrayList<String>().toArray(new String[size]);
                //customer_name_list = new ArrayList<String>().toArray(new String[size]);
                //product_amount_list = new ArrayList<String>().toArray(new String[size]);

                int i=0;
                for (DataSnapshot zaza: dataSnapshot.getChildren()){
                    String name=zaza.child("product_name").getValue().toString();

                    product_name_list[i]=name;
                    Log.e("----------",name);
                    i++;
                }


                createSelectionBox(product_name_btn,product_name_list,product_names);
                //createSelectionBox(customer_name_btn,product_name_list,customer_names);
                //createSelectionBox(produt_amount_btn,product_amount_list,product_amounts);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });



//---------------------------------------------------------------------------siiii-------tek metoda koy bunları

        //Log.e("----------",extensionRemoved);
        DatabaseReference myRef2 = database.getReference().child("users").child(extensionRemoved).child("customer");

        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int size = (int) dataSnapshot.getChildrenCount();

                customer_name_list = new ArrayList<String>().toArray(new String[size]);
        ;

                int i=0;
                for (DataSnapshot zaza: dataSnapshot.getChildren()){
                    String name=zaza.child("customer_name").getValue().toString();

                    customer_name_list[i]=name;
                    Log.e("----------",name);
                    i++;
                }



                createSelectionBox(customer_name_btn,customer_name_list,customer_names);



            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });



//---------------------------------------------------------------------------siiii-------tek metoda koy bunları


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();


                String product_name=product_names.getText().toString();
                String product_amount=product_amounts.getText().toString();
                String customer_name=customer_names.getText().toString();
                String customer_address=customer_addresss.getText().toString();
                String product_date=formatter.format(date);
                String order_taker=MainActivity.getUser().getEmail();









                Order_Class order=new Order_Class(product_name,product_date,product_amount,customer_name,customer_address,order_taker,
                        "sipariş alındı","sipariş alındı",lat,lon);




                if(ActivityCompat.checkSelfPermission(TakeOrder.this,
                        Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                        getLocation(order);


                }else{
                    ActivityCompat.requestPermissions(TakeOrder.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
                    Toast.makeText(TakeOrder.this, "olmadı", Toast.LENGTH_SHORT).show();
                }


                //latitude=address.get(0).getLatitude();
                //langitude =address.get(0).getLatitude();


            }
        });

    }

    public void getData(){


    }

    public  void getLocation(final Order_Class c){


        FusedLocationProviderClient  fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(TakeOrder.this);
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Log.e("----------","geldi ama");
                Location location=task.getResult();
                if(location!=null){
                    Geocoder geocoder=new Geocoder(TakeOrder.this, Locale.getDefault());

                    try {
                        List<Address> address=geocoder.getFromLocation(
                                location.getLatitude(),location.getLongitude(),1
                        );


                        c.setLatitude(address.get(0).getLatitude());
                        c.setLongitude(address.get(0).getLatitude());
                        SaveToFireBase s=new SaveToFireBase("order",TakeOrder.this);
                        s.Save(c);
                        Log.e("----------","sas");


                    } catch (IOException e) {
                        Log.e("----------","sikinti var");

                        e.printStackTrace();
                        AlertDialog alertDialog = new AlertDialog.Builder(TakeOrder.this).create();
                        alertDialog.setTitle("İşlem Bilgisi");
                        alertDialog.setMessage("İşlem Gerçekleştirilemedi, \n Lütfen Tekrar Deneyiniz.");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();


                    }
                }
                else{
                    AlertDialog alertDialog = new AlertDialog.Builder(TakeOrder.this).create();
                    alertDialog.setTitle("İşlem Bilgisi");
                    alertDialog.setMessage("Lokasyon alınırken skıntı yaşandı, \n Sipariş Lokasyonsuz eklendi.");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();

                    SaveToFireBase s=new SaveToFireBase("order",TakeOrder.this);
                    s.Save(c);
                    Log.e("----------","sas");
                }

            }


        });




    }

    public void createSelectionBox(Button button, final String[] liste, final EditText show){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(TakeOrder.this);
                alertDialog.setTitle("Ürün Seç");
                alertDialog.setSingleChoiceItems( liste, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        show.setText(liste [i]);
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.setNeutralButton("İptal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog myDialog=alertDialog.create();
                myDialog.show();
            }
        });


    }
}
