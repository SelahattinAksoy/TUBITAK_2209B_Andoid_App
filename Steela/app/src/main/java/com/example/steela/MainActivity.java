package com.example.steela;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static FirebaseUser currentUser;
    private Button btn;
    private FirebaseAuth mAuth;
    private EditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        btn=findViewById(R.id.button_login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email= (EditText) findViewById(R.id.email_id);
                password=findViewById(R.id.password_id);
                String em=email.getText().toString().trim();
                String psw=password.getText().toString().trim();
                if(em.length()<1 || psw.length()<1){
                    Intent zaza = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(zaza);
                }else {
                    mAuth.signInWithEmailAndPassword(em, psw)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        currentUser = mAuth.getCurrentUser();

                                        Intent zaza = new Intent(MainActivity.this, Main2Activity.class);
                                        startActivity(zaza);

                                    } else {
                                        Intent zaza = new Intent(MainActivity.this, MainActivity.class);
                                        startActivity(zaza);
                                    }

                                    // ...
                                }
                            });

                }

            }
        });

    }
    public static FirebaseUser getUser(){

        return currentUser;
    }

}
