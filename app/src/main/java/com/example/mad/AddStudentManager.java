package com.example.mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class AddStudentManager extends AppCompatActivity {
    private EditText studentName, studentPassword, studentEmail, studentAge,studentNic,studentPhone;
    private Button regButton;
    private TextView studentLogin;
    private FirebaseAuth firebaseAuth;
    //    private ImageView adminProfilePic;
    String email, name, age, password,phone,nic;
    //   private static int PICK_IMAGE = 123;
    Uri imagePath;
    Student studentProfile;

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
        setContentView(R.layout.activity_add_student_manager);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance ( );

        storageReference = firebaseStorage.getReference();




        regButton.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View view) {
                if(validate()){
                    //Upload data to the database
                    String student_email = studentEmail.getText().toString().trim();
                    String student_password = studentPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(student_email, student_password).addOnCompleteListener(new OnCompleteListener<AuthResult> () {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                sendEmailVerification();
                                sendUserData();
                                firebaseAuth.signOut();
                                Toast.makeText(AddStudentManager.this, "Successfully Registered, Upload complete!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent (AddStudentManager.this, StudentManagerLogin.class));
                            }else{
                                Toast.makeText(AddStudentManager.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

        //StudentLogin.setOnClickListener(new View.OnClickListener() {
        // @Override
        // public void onClick(View view) {
        //     startActivity(new Intent(AddStudent.this, studentLogin.class));
        // }
        // });

    }

    private void setupUIViews(){
        studentName = (EditText)findViewById(R.id.txtmname );
        studentPassword = (EditText)findViewById(R.id.txtmpassword );
        studentEmail = (EditText)findViewById(R.id.txtmemail );
        studentAge = (EditText)findViewById(R.id.txtmage );
        studentPhone=(EditText)findViewById ( R.id.txtmphone );
        studentNic=(EditText)findViewById ( R.id.txtmnic ) ;
        regButton =(Button)findViewById(R.id.btnsave);

        // adminProfilePic = (ImageView)findViewById(R.id.ivProfile);
    }

    private Boolean validate(){
        Boolean result = false;

        name = studentName.getText().toString();
        password = studentPassword.getText().toString();
        email = studentEmail.getText().toString();
        age = studentAge.getText().toString();
        nic=studentNic.getText().toString ();
        phone=studentPhone.getText ().toString ();


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
                        sendUserData();
                        Toast.makeText(AddStudentManager.this, "Successfully Registered, Verification mail sent!", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(AddStudentManager.this, StudentManagerLogin.class));
                    }else{
                        Toast.makeText(AddStudentManager.this, "Verification mail has'nt been sent!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }}


    private void sendUserData( ){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference().child ( "studentmanager" ).child ( firebaseAuth.getUid ());
        StorageReference imageReference = storageReference.child(firebaseAuth.getUid()).child("Images").child("Profile Pic");  //User id/Images/Profile Pic.jpg
        UploadTask uploadTask = imageReference.putFile( Uri.parse ( "imagepath" ) );
        StorageTask<UploadTask.TaskSnapshot> taskSnapshotStorageTask = uploadTask.addOnFailureListener ( new OnFailureListener ( ) {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText ( AddStudentManager.this, "Upload failed!", Toast.LENGTH_SHORT ).show ( );
            }
        } ).addOnCompleteListener ( new OnCompleteListener<UploadTask.TaskSnapshot> ( ) {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                Toast.makeText ( AddStudentManager.this, "Upload successful!", Toast.LENGTH_SHORT ).show ( );
            }


        } );









     StudentManager studentProfile = new StudentManager(name,age,email,phone,nic,password);
        myRef.setValue(studentProfile);
    }


}