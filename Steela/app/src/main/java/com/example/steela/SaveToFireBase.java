package com.example.steela;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;

public class SaveToFireBase {


    String child;
    Context clas;

    public SaveToFireBase(String child, Context clas){
        this.clas=clas;
        this.child=child;


    }

    public void Save(Object value){
        DatabaseReference myRef;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Log.e("----------","");

        String extensionRemoved = MainActivity.getUser().getEmail().split("\\.")[0];

        myRef = database.getReference().child("users").child(extensionRemoved).child(this.child);



        myRef.push().setValue(value).addOnSuccessListener(new OnSuccessListener<Void>() {

            @Override
            public void onSuccess(Void aVoid) {

                AlertDialog alertDialog = new AlertDialog.Builder(clas).create();
                alertDialog.setTitle("İşlem Bilgisi");
                alertDialog.setMessage("İşlem Başarıyla Gerçekleşmiştir..");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                AlertDialog alertDialog = new AlertDialog.Builder(clas).create();
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
        });


    };

    public void getFromFirebase(){

    }


    }


