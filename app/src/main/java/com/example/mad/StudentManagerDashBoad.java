package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentManagerDashBoad extends AppCompatActivity {

    private Button button4,button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_student_manager_dash_boad );


        button4 = (Button) findViewById ( R.id.button4 );
        button4.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                openActivity2 ( );

            }


        } );

        button5 = (Button) findViewById ( R.id.button5);
        button5.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                openActivity3 ( );

            }


        } );

    }
        public void openActivity2(){

            Intent intent=new Intent (this, AddStudent.class);

            startActivity ( intent );

        }
        public void openActivity3(){

            Intent intent=new Intent (this, ManageStudent.class);

            startActivity ( intent );

        }







}