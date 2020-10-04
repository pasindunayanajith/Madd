package com.example.mad;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddRefBook extends AppCompatActivity {

    EditText txtmodulekey,txtbookname,txtrbc;
    Button btnsave;
    long maxid=0;
    DatabaseReference reff;
    RefBook refbook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_ref_book);
        txtmodulekey=(EditText)findViewById(R.id.txtmodulekey );
        txtbookname=(EditText)findViewById(R.id.txtname );
        txtrbc=(EditText)findViewById(R.id.txtrbc);
        btnsave=(Button)findViewById(R.id.btnsave);
        refbook=new RefBook();
        reff= FirebaseDatabase.getInstance().getReference().child("RefBook");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists())
                    maxid=(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int Age=Integer.parseInt(txtage.getText().toString().trim());
                refbook.setModulekey (txtmodulekey.getText().toString().trim());
                refbook.setBookname(txtbookname.getText().toString().trim());
                refbook.setRcb(txtrbc.getText().toString().trim());

                // lecturer.setAge(Age);
                reff.child(String.valueOf(maxid+1)).setValue(refbook);
                Toast.makeText(AddRefBook.this,"Data inserted Successfully",Toast.LENGTH_LONG).show();
            }
        });
    }
}