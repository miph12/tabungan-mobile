package com.a000webhostapp.heloiwan.tabunganku.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a000webhostapp.heloiwan.tabunganku.R;
import com.a000webhostapp.heloiwan.tabunganku.activity.DetailActivity;
import com.a000webhostapp.heloiwan.tabunganku.models.nasabah.DataItem;
import com.a000webhostapp.heloiwan.tabunganku.utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NasabahAdapter extends RecyclerView.Adapter<NasabahAdapter.ViewHolder> {

    private List<DataItem> requestLists;
    private Context context;

    public NasabahAdapter(List<DataItem> requestLists, Context context) {

        this.requestLists = requestLists;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_row_nasabah, parent, false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvNorek)
        TextView tvNorek;
        @BindView(R.id.cvMyRequest)
        CardView mMyRequest;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final DataItem requestList = requestLists.get(position);
        holder.tvNorek.setText(requestList.getNoRekening());
        holder.mMyRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataItem requestList1 = requestLists.get(position);
                Intent i = new Intent(v.getContext(), DetailActivity.class);
                Bundle b = new Bundle();
                b.putString(Constants.NASABAH_NOREK, requestList1.getNoRekening());
                i.putExtras(b);
                v.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        int a;
        if (requestLists != null && !requestLists.isEmpty()) {
            a = requestLists.size();
        } else {
            a = 0;
        }

        return a;
    }
}
