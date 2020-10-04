package com.example.mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminDash extends AppCompatActivity {

    Button Lmanager;
    Button Smanager;
    Button Supmanager;
    Button Paymanager;
    private FirebaseAuth firebaseAuth;
    private Button logout;
    private FloatingActionButton add;
    FirebaseDatabase database = FirebaseDatabase.getInstance ( );
    DatabaseReference reference;
    TextView tv,tv1,tv2;
    int sum = 0;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dash);
        Lmanager = (Button)findViewById(R.id.btn1 );
        Smanager = (Button)findViewById(R.id.btn2 );
        Supmanager = (Button)findViewById(R.id.button3 );

        firebaseAuth = FirebaseAuth.getInstance();



        View recyclerView = findViewById ( R.id.recycler );
//        recyclerView.setLayoutManager(new LinearLayoutManager (this));


        reference = database.getReference ( "student" );
        reference.keepSynced ( true );
        tv= findViewById ( R.id.main_text2 );

        reference.addValueEventListener ( new ValueEventListener ( ) {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapsoht) {

                if (dataSnapsoht.exists ( )) {
                    sum = (int) dataSnapsoht.getChildrenCount ( );
                   tv.setText ( "All student:" + Integer.toString ( sum ) );
                } else {

                 tv.setText ( "All Student 0 " );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );


        //2


        reference = database.getReference ( "Supplier" );
        reference.keepSynced ( true );
        tv1 = findViewById ( R.id.main_text1 );

        reference.addValueEventListener ( new ValueEventListener ( ) {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapsoht) {

                if (dataSnapsoht.exists ( )) {
                    sum = (int) dataSnapsoht.getChildrenCount ( );
                    tv1.setText ( "All Suppliers:" + Integer.toString ( sum ) );
                } else {

                    tv1.setText ( "All Supplier 0 " );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );





        reference = database.getReference ( "lecturer" );
        reference.keepSynced ( true );
        tv2 = findViewById ( R.id.main_text );

        reference.addValueEventListener ( new ValueEventListener ( ) {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapsoht) {

                if (dataSnapsoht.exists ( )) {
                    sum = (int) dataSnapsoht.getChildrenCount ( );
                 tv2.setText("All Lecturer:" + Integer.toString ( sum ) );
                } else {

                    tv2.setText ( "All Lecturer 0 " );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );
    }







    // logout = (Button)findViewById(R.id.btnLogout);

       // logout.setOnClickListener(new View.OnClickListener() {
         //   @Override
          //  public void onClick(View view) {
          //      Logout();
         //   }
      //  });

    @Override
    protected void onResume() {
        super.onResume();
        Lmanager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Lec = new Intent(AdminDash.this,ManageLecturer.class);
                startActivity(Lec);
            }
        });
        Supmanager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Lec = new Intent(AdminDash.this,ManageSupplier.class);
                startActivity(Lec);
            }
        });
        Smanager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Lec = new Intent(AdminDash.this,ManageStudent.class);
                startActivity(Lec);
            }
        });

    }
    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(AdminDash.this, AdminLogin.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
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
                startActivity(new Intent(AdminDash.this, AdminProfileActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
