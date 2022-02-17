package com.a000webhostapp.heloiwan.tabunganku.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.a000webhostapp.heloiwan.tabunganku.App;
import com.a000webhostapp.heloiwan.tabunganku.R;
import com.a000webhostapp.heloiwan.tabunganku.models.history.DataItem;
import com.a000webhostapp.heloiwan.tabunganku.models.history.GHistory;
import com.a000webhostapp.heloiwan.tabunganku.models.saldo.GSaldo;
import com.a000webhostapp.heloiwan.tabunganku.utils.Constants;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryFragment extends Fragment implements View.OnClickListener{

    Context mContext;
    View views;
    @BindView(R.id.tbLayout)
    TableLayout mTable;
    @BindView(R.id.tvSaldo)
    TextView mSaldo;

    App.LoadingPrimary pd;
    public static Boolean SET_TABLE_HEADER;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        pd = new App.LoadingPrimary(getActivity());
        mContext = getActivity();
        views = ((Activity) mContext).getWindow().getDecorView().findViewById(android.R.id.content);
        addHeaders();
//        setTableHeader(mTable);
        initSaldo();
        initData();
    }

    private void initSaldo() {
        AndroidNetworking.get(Constants.API_GET_SALDO)
                .build()
                .getAsObject(GSaldo.class, new ParsedRequestListener<GSaldo>() {
                        @Override
                        public void onResponse(GSaldo response) {
                            mSaldo.setText("Saldo : Rp. " +String.valueOf(response.getData()));
                        }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        App.TShort(error.getErrorDetail());
                    }
                });
    }

    private void initData() {
        pd.show();
        AndroidNetworking.get(Constants.API_GET_HISTORY)
                .setPriority(Priority.HIGH)
                .build()
                .getAsObject(GHistory.class, new ParsedRequestListener<GHistory>() {
                    @Override
                    public void onResponse(GHistory response) {
                        pd.dismiss();
                        if (response.isStatus()) {
                            List<DataItem> listData = response.getData();
                            for (int i = 0; i < listData.size(); i++) {
                                TableRow tr = new TableRow(mContext);
                                int numData = listData.get(i).getIdTransaksi().length();
                                tr.addView(getTextView(
                                        i + 1,
                                        listData.get(i).getIdTransaksi(),
                                        Color.WHITE,
                                        Typeface.NORMAL,
                                        ContextCompat.getColor(mContext, R.color.colorPrimary)));
                                tr.addView(getTextView(
                                        i + numData,
                                        listData.get(i).getNama(),
                                        Color.WHITE,
                                        Typeface.NORMAL,
                                        ContextCompat.getColor(mContext, R.color.colorPrimary)));
                                tr.addView(setCustomTextView(
                                        i + numData,
                                        listData.get(i).getNoRekening(),
                                        Color.WHITE,
                                        Typeface.NORMAL,
                                        ContextCompat.getColor(mContext, R.color.colorPrimary)));
                                tr.addView(getTextView(
                                        i + numData,
                                        listData.get(i).getTanggal(),
                                        Color.WHITE,
                                        Typeface.NORMAL,
                                        ContextCompat.getColor(mContext, R.color.colorPrimary)));
                                tr.addView(getTextView(
                                        i + numData,
                                        listData.get(i).getDebit(),
                                        Color.WHITE,
                                        Typeface.NORMAL,
                                        ContextCompat.getColor(mContext, R.color.colorPrimary)));
                                tr.addView(getTextView(
                                        i + numData,
                                        listData.get(i).getKredit(),
                                        Color.WHITE,
                                        Typeface.NORMAL,
                                        ContextCompat.getColor(mContext, R.color.colorPrimary)));
                                mTable.addView(tr, getTblLayoutParams());
                            }

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

    private TextView getTextView(int id, String title, int color, int typeface, int bgColor) {
        TextView tv = new TextView(getActivity());
        tv.setId(id);
        tv.setText(title);
        tv.setTextColor(color);
        tv.setPadding(40, 40, 40, 40);
        tv.setTypeface(Typeface.DEFAULT, typeface);
        tv.setBackgroundColor(bgColor);
        tv.setLayoutParams(getLayoutParams());
        tv.setOnClickListener(this);
        return tv;
    }

    @NonNull
    private TableRow.LayoutParams getLayoutParams() {
        TableRow.LayoutParams params = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT);
        params.setMargins(2, 0, 0, 2);
        return params;
    }

    @NonNull
    private TableLayout.LayoutParams getTblLayoutParams() {
        return new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
    }

    private TextView setCustomTextView(int id, String title, int color, int typeface, int bgColor) {
        TextView tv = new TextView(getActivity());
        tv.setId(id);
        tv.setText(title);
        tv.setTextColor(color);
        tv.setPadding(40, 40, 40, 40);
        tv.setTypeface(Typeface.DEFAULT, typeface);
        tv.setBackgroundColor(bgColor);
        tv.setLayoutParams(setLayoutParams());
        tv.setOnClickListener(this);
        return tv;
    }

    @NonNull
    private TableRow.LayoutParams setLayoutParams() {
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        TableRow.LayoutParams params = new TableRow.LayoutParams(
                width/2,
                TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(2, 0, 0, 2);
        return params;
    }

    public void addHeaders() {
        TableRow tr = new TableRow(mContext);
//        tr.setLayoutParams(getLayoutParams());
        tr.addView(getTextView(0, "Id", Color.WHITE, Typeface.BOLD, getResources().getColor(R.color.colorPrimaryDark)));
        tr.addView(getTextView(0, "Nama Siswa", Color.WHITE, Typeface.BOLD, getResources().getColor(R.color.colorPrimaryDark)));
        tr.addView(getTextView(0, "No Induk", Color.WHITE, Typeface.BOLD, getResources().getColor(R.color.colorPrimaryDark)));
        tr.addView(getTextView(0, "Tanggal", Color.WHITE, Typeface.BOLD, getResources().getColor(R.color.colorPrimaryDark)));
        tr.addView(getTextView(0, "Debit", Color.WHITE, Typeface.BOLD, getResources().getColor(R.color.colorPrimaryDark)));
        tr.addView(getTextView(0, "Kredit", Color.WHITE, Typeface.BOLD, getResources().getColor(R.color.colorPrimaryDark)));
        mTable.addView(tr, getTblLayoutParams());
    }

    public void setTableHeader(TableLayout tbl) {
        TableRow tr = new TableRow(mContext);


        tr.addView(getTextView(0, "Id", Color.WHITE, Typeface.BOLD, getResources().getColor(R.color.colorPrimaryDark)));
        tr.addView(getTextView(0, "Nama Siswa", Color.WHITE, Typeface.BOLD, getResources().getColor(R.color.colorPrimaryDark)));
        tr.addView(getTextView(0, "No Induk", Color.WHITE, Typeface.BOLD, getResources().getColor(R.color.colorPrimaryDark)));
        tr.addView(getTextView(0, "Tanggal", Color.WHITE, Typeface.BOLD, getResources().getColor(R.color.colorPrimaryDark)));
        tr.addView(getTextView(0, "Debit", Color.WHITE, Typeface.BOLD, getResources().getColor(R.color.colorPrimaryDark)));
        tr.addView(getTextView(0, "Kredit", Color.WHITE, Typeface.BOLD, getResources().getColor(R.color.colorPrimaryDark)));

        // Adding row to Table
        tbl.addView(tr);

        // Table Header Has Been Set
        SET_TABLE_HEADER = false;
    }

    @Override
    public void onClick(View v) {
//        int id = v.getId();
//        TextView tv = findViewById(id);
//        if (null != tv) {
//            Log.i("onClick", "Clicked on row :: " + id);
//            Toast.makeText(this, "Clicked on row :: " + id + ", Text :: " + tv.getText(), Toast.LENGTH_SHORT).show();
//        }
    }
}