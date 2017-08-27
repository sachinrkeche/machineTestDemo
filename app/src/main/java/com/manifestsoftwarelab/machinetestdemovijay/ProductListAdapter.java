package com.manifestsoftwarelab.machinetestdemovijay;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 07/04/2016.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    public Context context;

    MainActivity objProductList;
    List<StudentPojo> productListDetails;

    public ProductListAdapter(List<StudentPojo> productDetails, Context context) {
        super();

        objProductList = new MainActivity();
        this.productListDetails = productDetails;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final StudentPojo objSellerProductPOJO = productListDetails.get(position);


        holder.tvstudname.setText(objSellerProductPOJO.getStudent_name());
        holder.coursename.setText(objSellerProductPOJO.getCompany_name());
        holder.compname.setText(objSellerProductPOJO.getCourse_name());


    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


    @Override
    public int getItemCount() {
        return productListDetails.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvstudname;
        public TextView coursename;
        public TextView compname;


        public ViewHolder(View itemView) {
            super(itemView);

            tvstudname = (TextView) itemView.findViewById(R.id.tvstudname);
            coursename = (TextView) itemView.findViewById(R.id.coursename);
            compname = (TextView) itemView.findViewById(R.id.compname);


        }


    }
}
