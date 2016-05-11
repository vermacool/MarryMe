package com.example.ukstechno.marryme.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.ukstechno.marryme.AdapterItemClass.PartnerItem;
import com.example.ukstechno.marryme.CustomVolleyRequest;
import com.example.ukstechno.marryme.R;
import com.example.ukstechno.marryme.RegisterActivity;
import com.example.ukstechno.marryme.ViewDetails;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by verma on 3/29/2016.
 */
public class CardAdapter extends  RecyclerView.Adapter<CardAdapter.ViewHolder>{
    private ImageLoader imageLoader;
    private Context context;
    private Bitmap bitmap;

    //List of superHeroes
    ArrayList<PartnerItem> partnerlist;

    public CardAdapter(ArrayList<PartnerItem> lits, Context context){
        super();
        //Getting all the superheroes
        this.partnerlist = lits;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.yourpartneradapter, parent, false);

        ViewHolder viewHolder = new ViewHolder(v,context,partnerlist);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        PartnerItem Item =  partnerlist.get(position);

        imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
        imageLoader.get(Item.getUri(), ImageLoader.getImageListener(holder.imageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));

        holder.imageView.setImageUrl(Item.getUri(), imageLoader);
        holder.textViewName.setText(Item.getFirstname());
        holder.textViewRank.setText(Item.getLastname());
        holder.textViewRealName.setText(Item.getEmail());
        holder.textViewCreatedBy.setText(Item.getCity());
        holder.textViewFirstAppearance.setText(Item.getContact());

       /* String powers = "";

        for(int i = 0; i<superHero.getPowers().size(); i++){
            powers+= superHero.getPowers().get(i);
        }

        holder.textViewPowers.setText(powers);*/
    }

    @Override
    public int getItemCount() {
        return partnerlist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public NetworkImageView imageView;
        public TextView textViewName;
        public TextView textViewRank;
        public TextView textViewRealName;
        public TextView textViewCreatedBy;
        public TextView textViewFirstAppearance;
        ArrayList<PartnerItem> arrayList=new ArrayList<PartnerItem>();
        Context ctx;
        public ViewHolder(View itemView,Context ctx,ArrayList<PartnerItem> list) {
            super(itemView);
            this.arrayList=list;
            this.ctx=ctx;
            itemView.setOnClickListener(this);
            imageView = (NetworkImageView) itemView.findViewById(R.id.imageViewHero);
            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            textViewRank= (TextView) itemView.findViewById(R.id.textViewRank);
            textViewRealName= (TextView) itemView.findViewById(R.id.textViewRealName);
            textViewCreatedBy= (TextView) itemView.findViewById(R.id.textViewCreatedBy);
            textViewFirstAppearance= (TextView) itemView.findViewById(R.id.textViewFirstAppearance);

        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            PartnerItem partnerItem=this.arrayList.get(position);
            Intent intent=new Intent(this.ctx, ViewDetails.class);
            intent.putExtra("image",partnerItem.getUri());
            intent.putExtra("name",partnerItem.getFirstname());
            intent.putExtra("lastname",partnerItem.getLastname());
            intent.putExtra("email",partnerItem.getEmail());
            intent.putExtra("city",partnerItem.getCity());
            intent.putExtra("contact",partnerItem.getContact());
            this.ctx.startActivity(intent);

        }
    }
}
