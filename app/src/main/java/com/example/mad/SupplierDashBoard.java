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

public class SupplierDashBoard extends AppCompatActivity {
    private Button logout,button1,button2,button3;
    private FirebaseAuth firebaseAuth;
    private RecyclerView recyclerView;
    private FloatingActionButton add;
    FirebaseDatabase database=FirebaseDatabase.getInstance ();
    DatabaseReference reference;
    TextView tv;
    int sum=0;
    Button addSupplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_supplier_dash_board );
        firebaseAuth = FirebaseAuth.getInstance();
        addSupplier = (Button)findViewById(R.id.add );


        recyclerView = findViewById(R.id.recycler);
//        recyclerView.setLayoutManager(new LinearLayoutManager (this));


        button1 = (Button) findViewById ( R.id.btn1 );
        button1.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                openActivity2();

            }



        } );

        button2 = (Button) findViewById ( R.id.btn2 );
        button2.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                openActivity3();

            }



        } );

        button3= (Button) findViewById ( R.id.btn3 );
        button3.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                openActivity4();

            }



        } );






        reference=database.getReference("SupplierBook");
        reference.keepSynced ( true );
        tv=findViewById ( R.id.main_text3 );

        reference.addValueEventListener ( new ValueEventListener ( ) {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapsoht) {

                if(dataSnapsoht.exists () ){
                    sum=(int) dataSnapsoht.getChildrenCount ();
                    tv.setText ( "All Published Books:"+Integer.toString ( sum ));
                }else {

                    tv.setText ( "All Published Book 0 " );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );










    }
    //linking part two
    public void openActivity2(){

        Intent intent=new Intent (this, ManageSupplierBook.class);

        startActivity ( intent );

    }
    public void openActivity3(){

        Intent intent=new Intent (this, AddSupplierBook.class);

        startActivity ( intent );

    }
    public void openActivity4(){

        Intent intent=new Intent (this, ManageSupplierBookDisplay.class);

        startActivity ( intent );

    }

   // @Override
   // protected void onResume() {
       // super.onResume();
       // addSupplier.setOnClickListener(new View.OnClickListener() {
        //    @Override
          //  public void onClick(View v) {
           //     Intent addSupplier = new Intent(SupplierDashBoard.this,AddSupplier.class);
            //    startActivity(addSupplier);
          //  }
    //    });
  //  }




    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(SupplierDashBoard.this,SupplierLogin.class));
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
                startActivity(new Intent(SupplierDashBoard.this, SupplierProfileActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }}
















