package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity implements View.OnClickListener{
    public CardView card1,card2,card3,card4,card5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );

        setContentView ( R.layout.activity_home );
        card1=(CardView)findViewById(R.id.btn1 );
            card2=(CardView) findViewById ( R.id.btn2 );
        card3=(CardView) findViewById ( R.id.btn3 );

        card4=(CardView) findViewById ( R.id.btn4);
        card5=(CardView) findViewById ( R.id.btn5 );

            card1.setOnClickListener ( this );
        card2.setOnClickListener ( this );
        card3.setOnClickListener ( this );
        card4.setOnClickListener ( this );
        card4.setOnClickListener ( this );
        card5.setOnClickListener ( this );




    }

    @Override
    public void onClick(View v) {
            Intent i;

            switch (v.getId ()){

                case R.id.btn1:
                    i=new Intent ( this,StudentLogin.class );
                    startActivity ( i );
                    break;
                case R.id.btn2:
                    i=new Intent ( this,LecturerLogin.class );
                    startActivity ( i );
                    break;
                case R.id.btn3:
                    i=new Intent ( this,SupplierLogin.class );
                    startActivity ( i );
                    break;
                case R.id.btn4:
                    i=new Intent ( this,StudentManagerLogin.class );
                    startActivity ( i );
                    break;

                case R.id.btn5:
                    i=new Intent ( this,AdminLogin.class );
                    startActivity ( i );
                        break;









            }
    }
}