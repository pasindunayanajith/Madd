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

public class SupplierBookAdapter extends FirebaseRecyclerAdapter<SupplierBook, SupplierBookAdapter.SupplierBookViewHolder> {

    private Context context;

    public SupplierBookAdapter(@NonNull FirebaseRecyclerOptions<SupplierBook> options, Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull final SupplierBookViewHolder holder, final int i, @NonNull final SupplierBook supplierbook) {


        holder.txtmodulekey.setText( "Module Key :  "+supplierbook.getModulekey ());
        holder.txtname.setText("Book Name :  "+ supplierbook.getName ());
        holder.txtouthor.setText("Outhor : "+supplierbook.getOuthor ());
        holder.txtprice.setText("Price :  "+ supplierbook.getPrice ());
        holder.txtshop.setText("More Details :  "+ supplierbook.getShop ( ));



        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference()
                        .child("SupplierBook")
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
                        .setContentHolder(new ViewHolder (R.layout.contentsupplierbook))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                View holderView = (LinearLayout)dialog.getHolderView();

                final EditText txtmodulekey=holderView.findViewById(R.id.modulekey );
                final EditText txtname=holderView.findViewById(R.id.name );
                final EditText txtouthor=holderView.findViewById(R.id.outhor );
                final EditText txtprice=holderView.findViewById(R.id.price );
                final EditText txtshop=holderView.findViewById ( R.id.shop );


                txtmodulekey.setText(supplierbook.getModulekey ());
                txtname.setText(supplierbook.getName ());
                txtouthor.setText(supplierbook.getOuthor ());
                txtprice.setText(supplierbook.getPrice ());
                txtshop.setText(supplierbook.getShop ());


                Button update=holderView.findViewById(R.id.update);

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Map<String,Object> map=new HashMap<> ();
                        map.put("name",txtmodulekey.getText().toString());
                        map.put("age",txtname.getText().toString());
                        map.put("email",txtouthor.getText().toString());
                        map.put("phone",txtprice.getText().toString());
                        map.put("nic",txtshop.getText().toString());




                        FirebaseDatabase.getInstance().getReference()
                                .child("SupplierBook")
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
    public SupplierBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.supplierbook, parent, false);

        return new SupplierBookViewHolder(view);
    }

    class SupplierBookViewHolder extends RecyclerView.ViewHolder {

        TextView txtmodulekey,txtname,txtouthor,txtprice,txtshop;
        ImageView edit,delete;


        public SupplierBookViewHolder(@NonNull View itemView) {
            super(itemView);

            txtmodulekey=itemView.findViewById(R.id.modulekey );
            txtname=itemView.findViewById(R.id.name );
            txtouthor=itemView.findViewById(R.id.outhor );
            txtprice=itemView.findViewById(R.id.price );
           txtshop=itemView.findViewById(R.id.shop);

            edit=itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);
        }
    }
}
