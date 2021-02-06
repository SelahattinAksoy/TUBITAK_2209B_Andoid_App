package com.example.steela;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrderList extends AppCompatActivity {

    ListView lst;
    ArrayList<Order_Class> liste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_order_list);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String extensionRemoved = MainActivity.getUser().getEmail().split("\\.")[0];
        DatabaseReference myRef = database.getReference().child("users").child(extensionRemoved).child("order");
        lst=findViewById(R.id.listwiew_id);






        liste=new ArrayList<>();
        // Read from the database

;

        //final ArrayAdapter adapter=new ArrayAdapter(this,R.layout.order_list,R.id.selocan,liste);

        final OrderListAdapter adb=new OrderListAdapter(this,R.layout.order_list,liste);


         myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                liste.clear();


                int i=1;
                for (DataSnapshot zaza: dataSnapshot.getChildren()){
                    String name=zaza.child("customer_name").getValue().toString();
                    String name2=zaza.child("product_name").getValue().toString();
                    String name3=zaza.child("product_amount").getValue().toString();
                    liste.add(new Order_Class(name,name2,name3));
                    i++;

                }

                adb.notifyDataSetChanged();



            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });



        //lst.setAdapter(adapter);
        lst.setAdapter(adb);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                AlertDialog alertDialog = new AlertDialog.Builder(OrderList.this).create();
                alertDialog.setTitle("Sipariş Bilgisi");
                alertDialog.setMessage(liste.get(i).toString());
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();


            }
        });

    }
}
