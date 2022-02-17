package com.a000webhostapp.heloiwan.tabunganku.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.a000webhostapp.heloiwan.tabunganku.App;
import com.a000webhostapp.heloiwan.tabunganku.R;
import com.a000webhostapp.heloiwan.tabunganku.models.entry.PEntry;
import com.a000webhostapp.heloiwan.tabunganku.models.nasabahid.GIdNasabah;
import com.a000webhostapp.heloiwan.tabunganku.utils.Constants;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.pixplicity.easyprefs.library.Prefs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity implements Validator.ValidationListener {

    @NotEmpty
    @BindView(R.id.etId)
    EditText mId;
    @NotEmpty
    @BindView(R.id.etName)
    EditText mName;
    @NotEmpty
    @BindView(R.id.etSaldo)
    EditText mSaldo;
    @NotEmpty
    @BindView(R.id.etDate)
    EditText mDate;
    @NotEmpty
    @BindView(R.id.etDebit)
    EditText mDebit;
    @NotEmpty
    @BindView(R.id.etKredit)
    EditText mKredit;
    @BindView(R.id.etDesc)
    EditText mKet;
    Calendar mCal = Calendar.getInstance();
    private App.LoadingPrimary pd;
    Validator validator;
    @OnClick(R.id.btnEntry)
    public void ocEntry() {
        validator.validate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tambah Transaksi");
        ButterKnife.bind(this);
        pd = new App.LoadingPrimary(this);
        validator = new Validator(this);
        validator.setValidationListener(this);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                mCal.set(Calendar.YEAR, year);
                mCal.set(Calendar.MONTH, monthOfYear);
                mCal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
        mDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(DetailActivity.this, date, mCal
                        .get(Calendar.YEAR), mCal.get(Calendar.MONTH),
                        mCal.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        getNasabahId();
    }

    private void getNasabahId() {
        pd.show();
        AndroidNetworking.get(Constants.API_GET_NASABAH_ID+getIntent().getStringExtra(Constants.NASABAH_NOREK))
                .setPriority(Priority.HIGH)
                .build()
                .getAsObject(GIdNasabah.class, new ParsedRequestListener<GIdNasabah>() {
                    @Override
                    public void onResponse(GIdNasabah ret) {
                        pd.dismiss();
                        if (ret.isStatus()) {
                            Prefs.putString(Constants.NASABAH_ID,ret.getData().get(0).getIdNasabah());
                            mId.setText(ret.getData().get(0).getIdNasabah());
                            mName.setText(ret.getData().get(0).getNama());
                            mSaldo.setText(ret.getData().get(0).getSaldo());
                        } else {
                            App.TShort("Can't get nasabah id");
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        pd.dismiss();
                        App.TShort(error.getErrorDetail());
                    }
                });
    }

    private void updateLabel() {
//        String myFormat = "MM/dd/yy";
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        mDate.setText(sdf.format(mCal.getTime()));
    }

    @Override
    public void onValidationSucceeded() {
        pd.show();
        AndroidNetworking.post(Constants.API_POST_ENTRY)
                .addBodyParameter("idNasabah", Prefs.getString(Constants.NASABAH_ID,""))
                .addBodyParameter("tanggal", mDate.getText().toString())
                .addBodyParameter("debit", mDebit.getText().toString())
                .addBodyParameter("kredit", mKredit.getText().toString())
                .addBodyParameter("ket", mKet.getText().toString())
                .setPriority(Priority.HIGH)
                .build()
                .getAsObject(PEntry.class, new ParsedRequestListener<PEntry>() {
                    @Override
                    public void onResponse(PEntry entry) {
                        pd.dismiss();
                        if (entry.getStatus().equals("ok")) {
                            App.TShort(entry.getMessages());
                            initUpdate(Prefs.getString(Constants.NASABAH_ID,""));
//                            App.intentFinish(DetailActivity.this, MainActivity.class);
                        } else {
                            App.TShort(entry.getMessages());
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        pd.dismiss();
                        App.TShort(error.getErrorDetail());
                    }
                });
    }

    public void initUpdate(String id){
        pd.show();
        AndroidNetworking.put(Constants.API_POST_ENTRY_UPDATE)
                .addBodyParameter("idNasabah", id)
                .setPriority(Priority.HIGH)
                .build()
                .getAsObject(PEntry.class, new ParsedRequestListener<PEntry>() {
                    @Override
                    public void onResponse(PEntry entry) {
                        pd.dismiss();
                        if (entry.getStatus().equals("ok")) {
//                            App.TShort(entry.getMessages());
                            App.intentFinish(DetailActivity.this, MainActivity.class);
                        } else {
//                            App.TShort(entry.getMessages());
                            App.intentFinish(DetailActivity.this, MainActivity.class);
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        pd.dismiss();
//                        App.TShort(error.getErrorDetail());
                        App.intentFinish(DetailActivity.this, MainActivity.class);
                    }
                });
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getApplicationContext());
            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                super.onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
