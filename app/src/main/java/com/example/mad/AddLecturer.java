package com.example.mad;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class AddLecturer extends AppCompatActivity {

    private EditText lecturerName, lecturerPassword, lecturerEmail, lecturerAge,lecturerNic,lecturerPhone;
    private Button regButton;
    private TextView lecturerLogin;
    private FirebaseAuth firebaseAuth;
//    private ImageView adminProfilePic;
    String email, name, age, password,phone,nic;
 //   private static int PICK_IMAGE = 123;
    Uri imagePath;
      Lecturer lecturerProfile;

    private StorageReference storageReference;

    @Override
 //   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      //  if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData ( ) != null) {
          //  imagePath = data.getData ( );
          //  try {
          //     Bitmap bitmap = MediaStore.Images.Media.getBitmap ( getContentResolver ( ), imagePath );
              //  adminProfilePic.setImageBitmap ( bitmap );
           // } catch (IOException e) {
           //     e.printStackTrace ( );
        //    }
       // }
       // super.onActivityResult ( requestCode, resultCode, data );



   // }



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lecturer);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance ( );

        storageReference = firebaseStorage.getReference();




        regButton.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View view) {
                if(validate()){
                    //Upload data to the database
                    String lecturer_email = lecturerEmail.getText().toString().trim();
                    String lecturer_password = lecturerPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(lecturer_email, lecturer_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                sendEmailVerification();
                                sendUserData();
                                firebaseAuth.signOut();
                                Toast.makeText(AddLecturer.this, "Successfully Registered, Upload complete!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(AddLecturer.this, LecturerLogin.class));
                            }else{
                                Toast.makeText(AddLecturer.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

    //LecturerLogin.setOnClickListener(new View.OnClickListener() {
          // @Override
          // public void onClick(View view) {
           //     startActivity(new Intent(AddLecturer.this, LecturerLogin.class));
           // }
       // });

    }

    private void setupUIViews(){
        lecturerName = (EditText)findViewById(R.id.txtlname );
       lecturerPassword = (EditText)findViewById(R.id.txtlpassword );
        lecturerEmail = (EditText)findViewById(R.id.txtlemail );

        lecturerPhone=(EditText)findViewById ( R.id.txtlphone );
     lecturerNic=(EditText)findViewById ( R.id.txtlnic ) ;
        regButton =(Button)findViewById(R.id.btnsave);
        lecturerAge = (EditText)findViewById(R.id.txtname );
       // adminProfilePic = (ImageView)findViewById(R.id.ivProfile);
    }

    private Boolean validate(){
        Boolean result = false;

        name = lecturerName.getText().toString();
        password = lecturerPassword.getText().toString();
        email = lecturerEmail.getText().toString();
        age = lecturerAge.getText().toString();
        nic=lecturerNic.getText().toString ();
        phone=lecturerPhone.getText ().toString ();


        if(name.isEmpty() || password.isEmpty() || email.isEmpty() || age.isEmpty()||nic.isEmpty ()||phone.isEmpty ()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }

        return result;
    }


    private void sendEmailVerification(){
        FirebaseUser user= firebaseAuth.getCurrentUser();
        if(user!=null){
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        sendUserData ();
                        Toast.makeText(AddLecturer.this, "Successfully Registered, Verification mail sent!", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(AddLecturer.this, LecturerLogin.class));
                    }else{
                        Toast.makeText(AddLecturer.this, "Verification mail has'nt been sent!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }}
    private void sendUserData( ){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference().child ( "lecturer" ).child ( firebaseAuth.getUid ());
        StorageReference imageReference = storageReference.child(firebaseAuth.getUid()).child("Images").child("Profile Pic");  //User id/Images/Profile Pic.jpg
        UploadTask uploadTask = imageReference.putFile( Uri.parse ( "imagepath" ) );
        StorageTask<UploadTask.TaskSnapshot> taskSnapshotStorageTask = uploadTask.addOnFailureListener ( new OnFailureListener ( ) {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText ( AddLecturer.this, "Upload failed!", Toast.LENGTH_SHORT ).show ( );
            }
        } ).addOnCompleteListener ( new OnCompleteListener<UploadTask.TaskSnapshot> ( ) {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                Toast.makeText ( AddLecturer.this, "Upload successful!", Toast.LENGTH_SHORT ).show ( );
            }


        } );









        Lecturer lecturerProfile = new Lecturer (name,age,email,phone,nic,password);
        myRef.setValue(lecturerProfile);
    }
}




