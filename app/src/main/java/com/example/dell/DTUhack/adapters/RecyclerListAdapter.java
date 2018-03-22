package com.example.dell.DTUhack.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.DTUhack.R;
import com.example.dell.DTUhack.models.Task;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.ViewHolder> {
    Context context;
    ArrayList<Task> itemlist;


    public RecyclerListAdapter(Context context, ArrayList<Task> itemlist) {
        this.context = context;
        this.itemlist = itemlist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemview = li.inflate(viewType, parent,false);
        return new ViewHolder(itemview);
    }

    @Override

    public void onBindViewHolder(ViewHolder holder, int position) {
        Task i = itemlist.get(position);

        holder.tvtitle.setText(i.getName());
        holder.tvdesc.setText(i.getDesc());
        Picasso.with(context)
                .load(i.getImgdrawable())
                .resize(400, 80)
                .centerCrop()
                .into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.listitem;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvtitle, tvdesc;
        ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            tvtitle = itemView.findViewById(R.id.tvtitle);
            tvdesc = itemView.findViewById(R.id.tvdesc);
            iv = itemView.findViewById(R.id.iv);
        }
    }
}