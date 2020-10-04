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

public class AddSupplierBook extends AppCompatActivity {

    EditText txtmodulekey,txtname,txtouthor,txtprice,txtshop;
    Button btnsave;
    long maxid=0;
    DatabaseReference reff;
   SupplierBook supplierbook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_supplier_book );
        txtmodulekey=(EditText)findViewById(R.id.modulekey );
        txtname=(EditText)findViewById(R.id.name );
        txtouthor=(EditText)findViewById(R.id.outhor );
        txtprice=(EditText)findViewById(R.id.price );
        txtshop=(EditText)findViewById(R.id.shop);


        btnsave=(Button)findViewById(R.id.btnsave);
        supplierbook=new SupplierBook ();
        reff= FirebaseDatabase.getInstance().getReference().child("SupplierBook");
        reff.addValueEventListener(new ValueEventListener () {
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

                supplierbook.setModulekey (txtmodulekey.getText().toString().trim());
                supplierbook.setName(txtname.getText().toString().trim());
               supplierbook.setOuthor(txtouthor.getText().toString().trim());
                supplierbook.setPrice (txtprice.getText().toString().trim());
               supplierbook.setShop (txtshop.getText().toString().trim());


                // lecturer.setAge(Age);
                reff.child(String.valueOf(maxid+1)).setValue(supplierbook);
                Toast.makeText(AddSupplierBook.this,"Data inserted Successfully",Toast.LENGTH_LONG).show();
            }
        });
    }
}