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

public class ManageLecturer extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private LecturerAdapter adapter;
    private FloatingActionButton add;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_lecturer);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Lecturer> options =
                new FirebaseRecyclerOptions.Builder<Lecturer>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("lecturer"), Lecturer.class)
                        .build();

        adapter = new LecturerAdapter(options,this);
        recyclerView.setAdapter(adapter);


        add=findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(ManageLecturer.this,AddLecturer.class));
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
