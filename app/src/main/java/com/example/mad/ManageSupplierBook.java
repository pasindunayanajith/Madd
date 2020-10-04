package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class ManageSupplierBook extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SupplierBookAdapter adapter;
    private FloatingActionButton add;



    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_supplier_book);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager (this));

        FirebaseRecyclerOptions<SupplierBook> options =
                new FirebaseRecyclerOptions.Builder<SupplierBook>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("SupplierBook"), SupplierBook.class)
                        .build();

        adapter = new SupplierBookAdapter (options,this);
        recyclerView.setAdapter(adapter);


        add=findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManageSupplierBook.this,AddSupplierBook.class));
            }
        });

    }





    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }


    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}