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

public class RefBookAdapterDisplay extends FirebaseRecyclerAdapter<RefBook, RefBookAdapterDisplay.RefBookViewHolder> {

    private Context context;

    public RefBookAdapterDisplay(@NonNull FirebaseRecyclerOptions<RefBook> options, Context context) {
        super(options);
        this.context=context;
    }

    public RefBookAdapterDisplay(FirebaseRecyclerOptions<RefBook> options) {
        super ( options );
    }


    @Override
    protected void onBindViewHolder(@NonNull final RefBookAdapterDisplay.RefBookViewHolder holder, final int i, @NonNull final RefBook refbook) {


        holder.modulekey.setText("Book ModuleKey :  "+refbook.getModulekey ());
        holder.bookname.setText("Book Name :  "+refbook.getBookname());
        holder.rcb.setText("Reference Book Criteria :  "+refbook.getRcb());




      //  holder.edit.setOnClickListener(new View.OnClickListener() {
           // @Override
           // public void onClick(View v) {
//                final DialogPlus dialog = DialogPlus.newDialog(context)
                  //      .setGravity(Gravity.CENTER)
                   //     .setMargin(50,0,50,0)
                   //     .setContentHolder(new ViewHolder(R.layout.contentrefbook))
                   //     .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                  //      .create();
            //    View holderView = (LinearLayout)dialog.getHolderView();

            //    final EditText modeulkey=holderView.findViewById(R.id.txtmodulekey);
           //     final EditText bookname=holderView.findViewById(R.id.txtname );
           //     final EditText rcb=holderView.findViewById(R.id.txtrcb );

            ////    modeulkey.setText(refbook.getModulekey ());
             //   bookname.setText(refbook.getBookname());
             //   rcb.setText(refbook.getRcb());

             //   Button update=holderView.findViewById(R.id.update);

            //    update.setOnClickListener(new View.OnClickListener() {
                 //   @Override
                 ///   public void onClick(View view) {

                  //      Map<String,Object> map=new HashMap<>();
                   //     map.put("modeul key",modeulkey.getText().toString());
                  //      map.put("bookname",bookname.getText().toString());
                   //     map.put("rcb",rcb.getText().toString());
//



                      //  FirebaseDatabase.getInstance().getReference()
                      //          .child("RefBook")
                       //         .child(getRef(i).getKey())
                       //         .updateChildren(map)
                        //        .addOnCompleteListener(new OnCompleteListener<Void>() {
                             //      @Override
                           //         public void onComplete(@NonNull Task<Void> task) {
                                       // dialog.dismiss();
                            //        }
                            //    });


                  //  }
             //   });
              //  dialog.show();

            }
      //  });
   //}

    @NonNull
    @Override
    public RefBookAdapterDisplay.RefBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.refbookdisplay , parent, false);

        return new RefBookAdapterDisplay.RefBookViewHolder (view);
    }


    class RefBookViewHolder extends RecyclerView.ViewHolder {

        TextView modulekey,bookname,rcb;
        ImageView edit,delete;


        public RefBookViewHolder(@NonNull View itemView) {
            super(itemView);

            modulekey=itemView.findViewById(R.id.txtmodulekey);
            bookname=itemView.findViewById(R.id.txtname );
            rcb=itemView.findViewById(R.id.txtrcb );
            edit=itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);
        }
    }
}
