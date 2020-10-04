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

public class LecturerAdapter extends FirebaseRecyclerAdapter<Lecturer, LecturerAdapter.LecturerViewHolder> {

    private Context context;

    public LecturerAdapter(@NonNull FirebaseRecyclerOptions<Lecturer> options, Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull final LecturerViewHolder holder, final int i, @NonNull final Lecturer lecturer) {


   holder.namel.setText("Name :  "+lecturer.getNamel());
    holder.agel.setText("Age :  "+lecturer.getAgel());
    holder.emaill.setText("Email :  "+lecturer.getEmaill());
    holder.phonel.setText("Phone"+lecturer.getPhonel());
    holder.nicl.setText("NIC"+lecturer.getNicl());
    holder.passwordl.setText("Password"+lecturer.getPasswordl());

    holder.delete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
                                  FirebaseDatabase.getInstance().getReference()
                                  .child("lecturer")
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
                    .setContentHolder(new ViewHolder(R.layout.contentlecturer))
                    .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                    .create();
            View holderView = (LinearLayout)dialog.getHolderView();

            final EditText namel=holderView.findViewById(R.id.namel );
            final EditText agel=holderView.findViewById(R.id.agel );
            final EditText emaill=holderView.findViewById(R.id.emaill );
           final EditText phonel=holderView.findViewById(R.id.phonel );
            final EditText nicl=holderView.findViewById(R.id.nicl );
            final EditText passwordl=holderView.findViewById(R.id.passwordl );

            namel.setText(lecturer.getNamel());
            agel.setText(lecturer.getAgel());
            emaill.setText(lecturer.getEmaill());
            phonel.setText(lecturer.getPhonel());
            nicl.setText(lecturer.getNicl());
            passwordl.setText(lecturer.getPasswordl ());

            Button update=holderView.findViewById(R.id.update);

            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Map<String,Object> map=new HashMap<>();
                    map.put("namel",namel.getText().toString());
                    map.put("agel",agel.getText().toString());
                    map.put("emaill",emaill.getText().toString());
                    map.put("phonel",phonel.getText().toString());
                    map.put("nicl",nicl.getText().toString());
                    map.put("passwordl",passwordl.getText().toString());



                    FirebaseDatabase.getInstance().getReference()
                            .child("lecturer")
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
    public LecturerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lecturer, parent, false);

        return new LecturerViewHolder(view);
    }

    class LecturerViewHolder extends RecyclerView.ViewHolder {


        TextView namel,agel,emaill,phonel,nicl,passwordl;
        ImageView edit,delete;


       public LecturerViewHolder(@NonNull View itemView) {
           super(itemView);

        namel=itemView.findViewById(R.id.namel );
        agel=itemView.findViewById(R.id.agel );
          emaill=itemView.findViewById(R.id.emaill );
         phonel=itemView.findViewById(R.id.phonel);
         nicl=itemView.findViewById(R.id.nicl );
          passwordl=itemView.findViewById(R.id.passwordl );
           edit=itemView.findViewById(R.id.edit);

           delete=itemView.findViewById(R.id.delete);
       }
   }
}
