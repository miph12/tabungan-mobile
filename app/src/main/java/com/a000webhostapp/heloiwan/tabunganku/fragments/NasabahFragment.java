package com.a000webhostapp.heloiwan.tabunganku.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a000webhostapp.heloiwan.tabunganku.App;
import com.a000webhostapp.heloiwan.tabunganku.R;
import com.a000webhostapp.heloiwan.tabunganku.adapters.NasabahAdapter;
import com.a000webhostapp.heloiwan.tabunganku.models.nasabah.DataItem;
import com.a000webhostapp.heloiwan.tabunganku.models.nasabah.GNasabah;
import com.a000webhostapp.heloiwan.tabunganku.utils.Constants;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NasabahFragment extends Fragment {

    @BindView(R.id.card_recycler_view)
    RecyclerView recyclerView;

    App.LoadingPrimary pd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nasabah, container, false);
    }

    private RecyclerView.Adapter adapter;
    private List<DataItem> myRequestLists;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        pd = new App.LoadingPrimary(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new NasabahAdapter(myRequestLists, getActivity());
        recyclerView.setAdapter(adapter);
        myRequestLists = new ArrayList<>();
        initBerita();
    }

    private void initBerita() {
        pd.show();
        AndroidNetworking.get(Constants.API_GET_NASABAH)
                .setPriority(Priority.HIGH)
                .build()
                .getAsObject(GNasabah.class, new ParsedRequestListener<GNasabah>() {
                    @Override
                    public void onResponse(GNasabah response) {
                        pd.dismiss();
                        if (response.isStatus()) {
                                myRequestLists = response.getData();
                                adapter = new NasabahAdapter(myRequestLists, getActivity());
                                recyclerView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                        } else {
                            App.TShort(getString(R.string.err_server));
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        pd.dismiss();
                        // handle error
                        App.TShort(error.getErrorDetail());
                    }
                });
    }
}