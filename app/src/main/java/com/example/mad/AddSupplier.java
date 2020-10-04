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

public class AddSupplier extends AppCompatActivity {
    EditText txtname,txtage,txtemail,txtphone,txtnic,txtpassword;
    Button btnsave;
    long maxid=0;
    DatabaseReference reff;
    Supplier supplier;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState );
        setContentView(R.layout.activity_add_supplier);
        txtname=(EditText)findViewById(R.id.name );
        txtage=(EditText)findViewById(R.id.age );
        txtemail=(EditText)findViewById(R.id.email );
        txtphone=(EditText)findViewById(R.id.phone );
        txtpassword=(EditText)findViewById (R.id.password ) ;
        txtnic=(EditText)findViewById(R.id.nic);
        btnsave=(Button)findViewById(R.id.btnsave);

        supplier =new Supplier (  );

        reff= FirebaseDatabase.getInstance().getReference().child("Supplier");
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

             supplier.setName( txtname.getText ().toString ().trim ());
                supplier.setAge(txtage.getText().toString().trim());
                supplier.setEmail(txtemail.getText().toString().trim());
                supplier.setNic(txtnic.getText().toString().trim());
                supplier.setPhone(txtphone.getText().toString().trim());
                supplier.setPassword (txtpassword.getText ().toString ().trim () );

                // lecturer.setAge(Age);
               reff.child(String.valueOf(maxid+1)).setValue(supplier);
                Toast.makeText(AddSupplier.this,"Data inserted Successfully",Toast.LENGTH_LONG).show();
            }
        });







    }
}







