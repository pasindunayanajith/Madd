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

public class ManageRefBookDisplay extends AppCompatActivity {

    private RecyclerView recyclerView;

    private FloatingActionButton add;
    private RefBookAdapterDisplay disapter;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_manage_ref_book_display );

        recyclerView = findViewById ( R.id.recycler );
        recyclerView.setLayoutManager ( new LinearLayoutManager ( this ) );

        FirebaseRecyclerOptions<RefBook> options =
                new FirebaseRecyclerOptions.Builder<RefBook> ( )
                        .setQuery ( FirebaseDatabase.getInstance ( ).getReference ( ).child ( "RefBook" ), RefBook.class )
                        .build ( );

        disapter = new RefBookAdapterDisplay ( options, this );
        recyclerView.setAdapter ( disapter );


        add = findViewById ( R.id.add );


    }


    @Override
    protected void onStart() {
        super.onStart ( );
        disapter.startListening ( );
    }

    @Override
    protected void onStop() {
        super.onStop ( );
        disapter.stopListening ( );
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ( ).inflate ( R.menu.searchmenu, menu );

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

    FirebaseRecyclerOptions<RefBook> options =
            new FirebaseRecyclerOptions.Builder<RefBook> ( )
                    .setQuery ( FirebaseDatabase.getInstance ( ).getReference ( ).child ( "RefBook" ).orderByChild ( "modulekey" ).startAt ( s ).endAt ( s+"\uf8ff" ), RefBook.class )
                    .build ( );

        disapter=new RefBookAdapterDisplay ( options );
        disapter.startListening ();
        recyclerView.setAdapter ( disapter);


}

}




