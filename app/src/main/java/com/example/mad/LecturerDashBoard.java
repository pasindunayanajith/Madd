package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LecturerDashBoard extends AppCompatActivity {

    Button addRef;
    Button manage;


    private Button button1,button2;
    private FirebaseAuth firebaseAuth;
    private RecyclerView recyclerView;
    private StudentAdapter adapter;

    private FloatingActionButton add;
    FirebaseDatabase database=FirebaseDatabase.getInstance ();
    DatabaseReference reference;
    TextView tv;
    int sum=0;








    private Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lecturer_dash_board );

        addRef = (Button)findViewById(R.id.btn1 );
        manage = (Button)findViewById(R.id.btn2 );
        firebaseAuth = FirebaseAuth.getInstance();


        recyclerView = findViewById(R.id.recycler);
//        recyclerView.setLayoutManager(new LinearLayoutManager (this));


        reference=database.getReference("RefBook");
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

















    }

    @Override
    protected void onResume() {
        super.onResume();
        addRef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ref = new Intent(LecturerDashBoard.this,AddRefBook.class);
                startActivity(ref);
            }
        });
        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ref = new Intent(LecturerDashBoard.this,ManageRefBook.class);
                startActivity(ref);
            }
        });
    }
    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(LecturerDashBoard.this, LecturerLogin.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.logoutMenu:{
               Logout();
                break;
            }
            case R.id.profileMenu:
                startActivity(new Intent(LecturerDashBoard.this, LecturerProfileActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }








}