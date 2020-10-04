package com.example.mad;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class ManageRefBook extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private RefBookAdapter adapter;
    private FloatingActionButton add;
private RefBookAdapterDisplay disapter;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_ref_book);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<RefBook> options =
                new FirebaseRecyclerOptions.Builder<RefBook>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RefBook"), RefBook.class)
                        .build();

        adapter = new RefBookAdapter(options,this);
        recyclerView.setAdapter(adapter);


        add=findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManageRefBook.this,AddRefBook.class));
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
