package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;


    public class ManageSupplierBookDisplay extends AppCompatActivity {
        private RecyclerView recyclerView;
        private SupplierBookAdapterDisplay disadapter;
        private FloatingActionButton add;


        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate ( savedInstanceState );
            setContentView ( R.layout.activity_manage_supplier_book_display);

            recyclerView = findViewById ( R.id.recycler );
            recyclerView.setLayoutManager ( new LinearLayoutManager ( this ) );

            FirebaseRecyclerOptions<SupplierBook> options =
                    new FirebaseRecyclerOptions.Builder<SupplierBook> ( )
                            .setQuery ( FirebaseDatabase.getInstance ( ).getReference ( ).child ( "SupplierBook" ), SupplierBook.class )
                            .build ( );

            disadapter = new SupplierBookAdapterDisplay ( options, this );
            recyclerView.setAdapter ( disadapter );


            //add = findViewById ( R.id.add );


        }


        @Override
        protected void onStart() {
            super.onStart ( );
            disadapter.startListening ( );
        }


        @Override
        protected void onStop() {
            super.onStop ( );
            disadapter.stopListening ( );
        }



        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater ( ).inflate ( R.menu.searchmenusupplierdis, menu );

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
            FirebaseRecyclerOptions<SupplierBook> options =
                    new FirebaseRecyclerOptions.Builder<SupplierBook> ( )

                            .setQuery ( FirebaseDatabase.getInstance ( ).getReference ( ).child ( "SupplierBook" ).orderByChild ( "modulekey" ).startAt ( s ).endAt ( s+"\uf8ff" ), SupplierBook.class )

                            .build ( );


            disadapter=new SupplierBookAdapterDisplay( options );
            disadapter.startListening ();
            recyclerView.setAdapter ( disadapter);


        }











    }