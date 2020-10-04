package com.example.mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ManageStudentManager extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StudentManagerAdapter adapter;
    private FloatingActionButton add;
    FirebaseDatabase database=FirebaseDatabase.getInstance ();
    DatabaseReference reference;

    TextView tv;
    int sum=0;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_student_manager);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager (this));


        reference=database.getReference("studentmanager");
        reference.keepSynced ( true );
        tv=findViewById ( R.id.main_text3 );

        reference.addValueEventListener ( new ValueEventListener ( ) {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapsoht) {

                if(dataSnapsoht.exists () ){
                    sum=(int) dataSnapsoht.getChildrenCount ();
                    tv.setText ( "All student Managers:"+Integer.toString ( sum ));
                }else {

                    tv.setText ( "All Student 0 " );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );


        FirebaseRecyclerOptions<StudentManager> options =
                new FirebaseRecyclerOptions.Builder<StudentManager>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("studentmanager"),StudentManager.class)
                        .build();

        adapter = new StudentManagerAdapter(options,this);
        recyclerView.setAdapter(adapter);





        add=findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent (ManageStudentManager.this,AddStudentManager.class));
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
