package com.example.mad;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ManageStudent extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    private FloatingActionButton add;
   FirebaseDatabase database=FirebaseDatabase.getInstance ();
    DatabaseReference reference;
//1
    TextView tv;
    int sum=0;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_student);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        reference=database.getReference("student");
       reference.keepSynced ( true );
        tv=findViewById ( R.id.main_text3 );

        reference.addValueEventListener ( new ValueEventListener ( ) {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapsoht) {

                if(dataSnapsoht.exists () ){
                        sum=(int) dataSnapsoht.getChildrenCount ();
                        tv.setText ( "All student:"+Integer.toString ( sum ));
                }else {

                    tv.setText ( "All Student 0 " );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );


        FirebaseRecyclerOptions<Student> options =
                new FirebaseRecyclerOptions.Builder<Student>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("student"),Student.class)
                        .build();

        adapter = new StudentAdapter(options,this);
        recyclerView.setAdapter(adapter);





        add=findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManageStudent.this,AddStudent.class));
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

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ( ).inflate ( R.menu.searchmenustudent, menu );

        MenuItem item=menu.findItem ( R.id.search );
        SearchView searchView=(SearchView)item.getActionView ();

        searchView.setOnQueryTextListener ( new SearchView.OnQueryTextListener ( ) {
            @Override
            public boolean onQueryTextSubmit(String s) {

                processsearch(s);


                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                processsearch(s);
                return false;
            }
        } );


        return  super.onCreateOptionsMenu ( menu );


    }
    private void processsearch(String s){
        FirebaseRecyclerOptions<Student> options =
                new FirebaseRecyclerOptions.Builder<Student>()
                        .setQuery ( FirebaseDatabase.getInstance ( ).getReference ( ).child ( "student" ).orderByChild ( "emails" ).startAt ( s ).endAt ( s+"\uf8ff" ), Student.class )
                        .build();


       adapter=new StudentAdapter(options);
       adapter.startListening ();
        recyclerView.setAdapter ( adapter);


    }
}
