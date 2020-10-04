package com.example.mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class AdminRegistrationActivity extends AppCompatActivity {


    private EditText adminName, adminPassword, adminEmail, adminAge;
    private Button regButton;
    private TextView adminLogin;
    private FirebaseAuth firebaseAuth;
    private ImageView adminProfilePic;
    String email, name, age, password;
    private static int PICK_IMAGE = 123;
    Uri imagePath;
    AdminProfile adminProfile;

    private StorageReference storageReference;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData ( ) != null) {
            imagePath = data.getData ( );
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap ( getContentResolver ( ), imagePath );
                adminProfilePic.setImageBitmap ( bitmap );
            } catch (IOException e) {
                e.printStackTrace ( );
            }
        }
        super.onActivityResult ( requestCode, resultCode, data );



    }





    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_registration);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance ( );

        storageReference = firebaseStorage.getReference();


        adminProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/png image/jpeg");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE);
            }
        });



        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    //Upload data to the database
                    String admin_email = adminEmail.getText().toString().trim();
                    String admin_password = adminPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(admin_email, admin_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                //sendEmailVerification();
                                sendUserData();
                                firebaseAuth.signOut();
                                Toast.makeText(AdminRegistrationActivity.this, "Successfully Registered, Upload complete!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(AdminRegistrationActivity.this, AdminLogin.class));
                            }else{
                                Toast.makeText(AdminRegistrationActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminRegistrationActivity.this, AdminLogin.class));
            }
        });

    }

    private void setupUIViews(){
        adminName = (EditText)findViewById(R.id.txtname );
        adminPassword = (EditText)findViewById(R.id.modulekey );
        adminEmail = (EditText)findViewById(R.id.etAdminEmail);
        regButton = (Button)findViewById(R.id.btnRegister);
        adminLogin = (TextView)findViewById(R.id.tvAdminLogin);
        adminAge = (EditText)findViewById(R.id.etAge);
        adminProfilePic = (ImageView)findViewById(R.id.ivProfile);
    }

    private Boolean validate(){
        Boolean result = false;

        name = adminName.getText().toString();
        password = adminPassword.getText().toString();
        email = adminEmail.getText().toString();
        age = adminAge.getText().toString();


        if(name.isEmpty() || password.isEmpty() || email.isEmpty() || age.isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }

        return result;
    }


    private void sendEmailVerification(){
        FirebaseUser firebaseAdmin = firebaseAuth.getCurrentUser();
        if(firebaseAdmin!=null){
            firebaseAdmin.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        sendUserData ();
                        Toast.makeText(AdminRegistrationActivity.this, "Successfully Registered, Verification mail sent!", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(AdminRegistrationActivity.this, AdminLogin.class));
                    }else{
                        Toast.makeText(AdminRegistrationActivity.this, "Verification mail has'nt been sent!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }}
        private void sendUserData( ){
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference myRef = firebaseDatabase.getReference().child ( "admin" ).child ( firebaseAuth.getUid());
            StorageReference imageReference = storageReference.child(firebaseAuth.getUid()).child("Images").child("Profile Pic");  //User id/Images/Profile Pic.jpg
            UploadTask uploadTask = imageReference.putFile( Uri.parse ( "imagepath" ) );
            StorageTask<UploadTask.TaskSnapshot> taskSnapshotStorageTask = uploadTask.addOnFailureListener ( new OnFailureListener ( ) {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText ( AdminRegistrationActivity.this, "Upload failed!", Toast.LENGTH_SHORT ).show ( );
                }
            } ).addOnCompleteListener ( new OnCompleteListener<UploadTask.TaskSnapshot> ( ) {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    Toast.makeText ( AdminRegistrationActivity.this, "Upload successful!", Toast.LENGTH_SHORT ).show ( );
                }


            } );










            AdminProfile adminProfile = new AdminProfile(age, email, name,password);
            myRef.setValue(adminProfile);
        }
    }



