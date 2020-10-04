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

public class StudentManagerAdapter extends FirebaseRecyclerAdapter<StudentManager, StudentManagerAdapter.StudentManagerViewHolder> {

    private Context context;

    public StudentManagerAdapter(@NonNull FirebaseRecyclerOptions<StudentManager> options, Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull final StudentManagerViewHolder holder, final int i, @NonNull final StudentManager studentManager) {

//name display wenne
        holder.namem.setText("Name :  "+studentManager.getNamem());
        holder.agem.setText("Age :  " +studentManager.getAgem());
        holder.emailm.setText("Email :  "+studentManager.getEmailm());
        holder.phonem.setText("Phone Number :  "+studentManager.getPhonem());
        holder.nicm.setText("NIC :  "+studentManager.getNicm());
        holder.passwordm.setText("Password :  "+studentManager.getPasswordm());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference()
                        .child("studentmanager")
                        .child(getRef(i).getKey())
                        .setValue(null)
                        .addOnCompleteListener(new OnCompleteListener<Void> () {
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
                        .setGravity( Gravity.CENTER)
                        .setMargin(50,0,50,0)
                        .setContentHolder(new ViewHolder (R.layout.contentstudentmanager))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                View holderView = (LinearLayout)dialog.getHolderView();

                final EditText namem=holderView.findViewById(R.id.modulekey );
                final EditText agem=holderView.findViewById(R.id.name );
                final EditText emailm=holderView.findViewById(R.id.email );
                final EditText phonem=holderView.findViewById(R.id.phone );
                final EditText nicm=holderView.findViewById(R.id.nics );
                final EditText passwordm=holderView.findViewById(R.id.password );

                namem.setText(studentManager.getNamem());
                agem.setText(studentManager.getAgem());
                emailm.setText(studentManager.getEmailm());
                phonem.setText(studentManager.getPhonem());
                nicm.setText(studentManager.getNicm());
                passwordm.setText(studentManager.getPasswordm ());

                Button update=holderView.findViewById(R.id.update);

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Map<String,Object> map=new HashMap<> ();
                        map.put("names",namem.getText().toString());
                        map.put("ages",agem.getText().toString());
                        map.put("emails",emailm.getText().toString());
                        map.put("phones",phonem.getText().toString());
                        map.put("nics",nicm.getText().toString());
                        map.put("passwords",passwordm.getText().toString());



                        FirebaseDatabase.getInstance().getReference()
                                .child("studentmanager")
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
    public StudentManagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.studentmanager, parent, false);

        return new StudentManagerViewHolder(view);
    }



    class StudentManagerViewHolder extends RecyclerView.ViewHolder {

        TextView namem,agem,emailm,phonem,nicm,passwordm;
        ImageView edit,delete;


        public StudentManagerViewHolder(@NonNull View itemView) {
            super(itemView);

            namem=itemView.findViewById(R.id.modulekey );
            agem=itemView.findViewById(R.id.name );
            emailm=itemView.findViewById(R.id.email );
            phonem=itemView.findViewById(R.id.email );
            nicm=itemView.findViewById(R.id.nics );
            passwordm=itemView.findViewById(R.id.password);
            edit=itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);
        }
    }
}
