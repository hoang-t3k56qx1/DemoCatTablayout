package android.com.demotablayout.adapter;

import android.com.demotablayout.R;
import android.com.demotablayout.model.Cat;
import android.com.demotablayout.model.DeleteListener;
import android.com.demotablayout.model.UpdateListener;
import android.com.demotablayout.R;
import android.com.demotablayout.model.Cat;
import android.com.demotablayout.model.DeleteListener;
import android.com.demotablayout.model.UpdateListener;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CatAdapter  extends RecyclerView.Adapter<CatAdapter.ViewHolderCat> {

    private Context context;
    private ArrayList<Cat> arrayCat;
    private DeleteListener deleteListener;
    private UpdateListener updateListener;




    public CatAdapter(Context context, DeleteListener deleteListener, UpdateListener updateListener) {
        this.context = context;
        this.deleteListener = deleteListener;
        this.updateListener = updateListener;

    }

    public CatAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Cat> getArrayCat() {
        return arrayCat;
    }

    public void setArrayCat(ArrayList<Cat> arrayCat) {

        this.arrayCat = arrayCat;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolderCat onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_cat, viewGroup, false);
        return new ViewHolderCat(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCat holder, int i) {
        final int postion = i;
        Cat cat = arrayCat.get(i);
        if(cat == null){
            return;
        }
        holder.img.setImageResource(cat.getImg());
        holder.txtName.setText(cat.getName());
        holder.txtPrice.setText(cat.getPrice() + " USD");
        holder.txtDiscribe.setText(cat.getDiscribe());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteListener.listener(postion);
            }
        });
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateListener.listener(postion);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (arrayCat == null){
            return 0;
        }
        return arrayCat.size();
    }


    public class ViewHolderCat extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txtName, txtPrice, txtDiscribe;
        Button btnDelete;
        RelativeLayout item;
        public ViewHolderCat(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgCatt);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtDiscribe = itemView.findViewById(R.id.txtDescribe);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            item = itemView.findViewById(R.id.itemCat);
        }
    }

}
