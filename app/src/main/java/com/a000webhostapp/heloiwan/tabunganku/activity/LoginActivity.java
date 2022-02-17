package com.a000webhostapp.heloiwan.tabunganku.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.a000webhostapp.heloiwan.tabunganku.App;
import com.a000webhostapp.heloiwan.tabunganku.R;
import com.a000webhostapp.heloiwan.tabunganku.models.login.PLogin;
import com.a000webhostapp.heloiwan.tabunganku.utils.Constants;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Pattern;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements Validator.ValidationListener {

    private App.LoadingPrimary pd;
    Validator validator;

    @NotEmpty
    @BindView(R.id.etLoginUser)
    EditText etUser;
    @NotEmpty
    @BindView(R.id.etLoginPassword)
    EditText etPass;

    @OnClick(R.id.btnLogin)
    public void ocLogin() {
        validator.validate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        pd = new App.LoadingPrimary(this);
        validator = new Validator(this);
        validator.setValidationListener(this);

        if(Prefs.getBoolean(Constants.IS_LOGIN, false)){
            startApp();
        }
    }

    private void startApp() {
        App.intentFinish(LoginActivity.this, MainActivity.class);
    }

    @Override
    public void onValidationSucceeded() {
        pd.show();
        AndroidNetworking.post(Constants.API_LOGIN)
                .addHeaders(Constants.API_HEADER_K,Constants.API_HEADER_V)
                .addBodyParameter("username", etUser.getText().toString())
                .addBodyParameter("password", etPass.getText().toString())
                .setPriority(Priority.HIGH)
                .build()
                .getAsObject(PLogin.class, new ParsedRequestListener<PLogin>() {
                    @Override
                    public void onResponse(PLogin user) {
                        pd.dismiss();
                        if (user.isIsLoggedIn()) {
                            Prefs.putBoolean(Constants.IS_LOGIN, user.isIsLoggedIn());
                            Prefs.putString(Constants.USER_NAME, user.getData().get(0).getNama());
                            startApp();
                        } else {
                            App.TShort(getString(R.string.err_login));
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        pd.dismiss();
                        App.TShort(error.getErrorDetail());
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
}
