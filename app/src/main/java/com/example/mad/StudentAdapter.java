package com.example.mad;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class StudentAdapter extends FirebaseRecyclerAdapter<Student, StudentAdapter.StudentViewHolder> {

    private Context context;

    public StudentAdapter(@NonNull FirebaseRecyclerOptions<Student> options, Context context) {
        super(options);
        this.context=context;
    }

    public StudentAdapter(FirebaseRecyclerOptions<Student> options) {
        super (options );
    }

    @Override
    protected void onBindViewHolder(@NonNull final StudentViewHolder holder, final int i, @NonNull final Student student) {

//name display wenne
        holder.names.setText("Name :  "+student.getNames());
        holder.ages.setText("Age :  " +student.getAges());
        holder.emails.setText("Email :  "+student.getEmails());
        holder.phones.setText("Phone Number :  "+student.getPhones());
        holder.nics.setText("NIC :  "+student.getNics());
        holder.passwords.setText("Password :  "+student.getPasswords ());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference()
                        .child("student")
                        .child(getRef(i).getKey())
                        .setValue(null)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialog = DialogPlus.newDialog(context)
                        .setGravity(Gravity.CENTER)
                        .setMargin(50,0,50,0)
                        .setContentHolder(new ViewHolder(R.layout.contentstudent))
                                .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                View holderView = (LinearLayout)dialog.getHolderView();

                final EditText names=holderView.findViewById(R.id.names );
                final EditText ages=holderView.findViewById(R.id.ages );
                final EditText emails=holderView.findViewById(R.id.emails );
                final EditText phones=holderView.findViewById(R.id.phones );
                final EditText nics=holderView.findViewById(R.id.nics );
                final EditText passwords=holderView.findViewById(R.id.password );

                names.setText(student.getNames());
                ages.setText(student.getAges());
                emails.setText(student.getEmails());
                phones.setText(student.getPhones());
                nics.setText(student.getNics());
               passwords.setText(student.getPasswords ());

                Button update=holderView.findViewById(R.id.update);

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Map<String,Object> map=new HashMap<>();
                        map.put("names",names.getText().toString());
                        map.put("ages",ages.getText().toString());
                        map.put("emails",emails.getText().toString());
                        map.put("phones",phones.getText().toString());
                        map.put("nics",nics.getText().toString());
                        map.put("passwords",passwords.getText().toString());



                        FirebaseDatabase.getInstance().getReference()
                                .child("student")
                                .child(getRef(i).getKey())
                                .updateChildren(map)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        dialog.dismiss();
                                    }
                                });


                    }
                });
                dialog.show();

            }
        });
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student, parent, false);

        return new StudentViewHolder(view);
    }



    class StudentViewHolder extends RecyclerView.ViewHolder {

        TextView names,ages,emails,phones,nics,passwords;
        ImageView edit,delete;


        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            names=itemView.findViewById(R.id.names );
            ages=itemView.findViewById(R.id.ages );
            emails=itemView.findViewById(R.id.emails );
            phones=itemView.findViewById(R.id.phones );
            nics=itemView.findViewById(R.id.nics );
            passwords=itemView.findViewById(R.id.password );
            edit=itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);
        }
    }
}
