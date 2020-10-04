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

public class SupplierAdapter extends FirebaseRecyclerAdapter<Supplier, SupplierAdapter.SupplierViewHolder> {

    private Context context;

    public SupplierAdapter(@NonNull FirebaseRecyclerOptions<Supplier> options, Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull final SupplierViewHolder holder, final int i, @NonNull final Supplier supplier) {


        holder.name.setText("Name :  "+supplier.getName( ));
        holder.age.setText("Age :  "+supplier.getAge());
        holder.email.setText("Email :  "+supplier.getEmail());
        holder.phone.setText("phone :  "+supplier.getPhone());
        holder.nic.setText("NIC :  "+supplier.getNic());
        holder.password.setText("Password :  "+supplier.getPassword ());


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference()
                        .child("Supplier")
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
                        .setContentHolder(new ViewHolder(R.layout.contentsupplier))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                View holderView = (LinearLayout)dialog.getHolderView();

                final EditText name=holderView.findViewById(R.id.name );
                final EditText age=holderView.findViewById(R.id.age );
                final EditText email=holderView.findViewById(R.id.email);
                final EditText phone=holderView.findViewById(R.id.phone);
                final EditText nic=holderView.findViewById(R.id.nic );
                final EditText password=holderView.findViewById (R.id.password );


                name.setText(supplier.getName());
                age.setText(supplier.getAge());
                email.setText(supplier.getEmail());
                phone.setText(supplier.getPhone());
                nic.setText(supplier.getNic());
                password.setText (supplier.getPassword () );

                Button update=holderView.findViewById(R.id.update);

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Map<String,Object> map=new HashMap<>();
                        map.put("name",name.getText().toString());
                        map.put("age",age.getText().toString());
                        map.put("email",email.getText().toString());
                        map.put("phone",phone.getText().toString());
                        map.put("nic",nic.getText().toString());
                        map.put ( "password",password.getText ().toString () );



                        FirebaseDatabase.getInstance().getReference()
                                .child("Supplier")
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
    public SupplierViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.supplier, parent, false);

        return new SupplierViewHolder(view);
    }

    class SupplierViewHolder extends RecyclerView.ViewHolder {

        TextView name,age,email,phone,nic,password;
        ImageView edit,delete;


        public SupplierViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);
            age=itemView.findViewById(R.id.age );
            email=itemView.findViewById(R.id.email );
            phone=itemView.findViewById(R.id.phone );
            nic=itemView.findViewById(R.id.nic );
            password=itemView.findViewById (R.id.password );
            edit=itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);
        }
    }
}
