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


public class SupplierBookAdapterDisplay extends FirebaseRecyclerAdapter<SupplierBook, SupplierBookAdapterDisplay.SupplierBookViewHolder> {

    private Context context;

    public SupplierBookAdapterDisplay(@NonNull FirebaseRecyclerOptions<SupplierBook> options, Context context) {
        super ( options );
        this.context = context;
    }

    public SupplierBookAdapterDisplay(FirebaseRecyclerOptions<SupplierBook> options) {
        super ( options );
    }

    @Override
    protected void onBindViewHolder(@NonNull final SupplierBookViewHolder holder, final int i, @NonNull final SupplierBook supplierbook) {


        holder.txtmodulekey.setText ( "Module Key :  " + supplierbook.getModulekey ( ) );
        holder.txtname.setText ( "Book Name :  " + supplierbook.getName ( ) );
        holder.txtouthor.setText ( "Outhor : " + supplierbook.getOuthor ( ) );
        holder.txtprice.setText ( "Price :  " + supplierbook.getPrice ( ) );
        holder.txtshop.setText ( "More Details :  " + supplierbook.getShop ( ) );


    }

    @NonNull
    @Override
    public SupplierBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from ( parent.getContext ( ) )
                .inflate ( R.layout.supplierbookdisplay, parent, false );

        return new SupplierBookViewHolder ( view );
    }

    class SupplierBookViewHolder extends RecyclerView.ViewHolder {

        TextView txtmodulekey, txtname, txtouthor, txtprice, txtshop;
       // ImageView edit, delete;


        public SupplierBookViewHolder(@NonNull View itemView) {
            super ( itemView );

            txtmodulekey = itemView.findViewById ( R.id.modulekey );
            txtname = itemView.findViewById ( R.id.name );
            txtouthor = itemView.findViewById ( R.id.othor);
            txtprice = itemView.findViewById ( R.id.price );
            txtshop = itemView.findViewById ( R.id.shop );

        }
    }
}

