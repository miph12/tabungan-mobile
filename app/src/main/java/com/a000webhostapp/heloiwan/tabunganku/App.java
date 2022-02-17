package com.a000webhostapp.heloiwan.tabunganku;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.pixplicity.easyprefs.library.Prefs;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class App extends MultiDexApplication {

    public static String appVersion = BuildConfig.VERSION_NAME;

    private static Context appContext;

    private static App instance;

    public App() {
        instance = this;
    }

    public static Context getContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName("spTabunganku")
                .setUseDefaultSharedPreference(true)
                .build();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        AndroidNetworking.enableLogging();
    }

    public static Context getAppContext() {
        return appContext;
    }

    public static void initAnimFly(View view){
        ValueAnimator vAnimator = ObjectAnimator.ofFloat(view, "alpha", 1,0f);
        vAnimator.setDuration(1000);
        vAnimator.setRepeatMode(ValueAnimator.REVERSE);
        vAnimator.setRepeatCount(1);

        ValueAnimator vAnimator2 = ObjectAnimator.ofFloat(view, "translationX", 100f);
        vAnimator2.setDuration(1000);
        vAnimator2.setRepeatMode(ValueAnimator.REVERSE);
        vAnimator2.setRepeatCount(ValueAnimator.INFINITE);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(vAnimator,vAnimator2);
        set.setStartDelay(0);

        set.start();

    }

    public static class LoadingPrimary extends Dialog {

        private ImageView iv;
        private TextView tv;

        public LoadingPrimary(Context context) {
            super(context, R.style.MyDialogLoading);
            WindowManager.LayoutParams wlmp = getWindow().getAttributes();
            wlmp.gravity = Gravity.CENTER_HORIZONTAL;
            getWindow().setAttributes(wlmp);
            setTitle(null);
            setCancelable(false);
            setOnCancelListener(null);
            LinearLayout layout = new LinearLayout(context);
            layout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            iv = new ImageView(context);
            iv.setImageResource(R.mipmap.ic_launcher);
            initAnimFly(iv);
            layout.addView(iv, params);

//            TextView tv = new TextView(context);
//            tv.setText("Please Wait");
//            tv.setTypeface(Typeface.SANS_SERIF);
//            tv.setGravity(Gravity.CENTER_HORIZONTAL);
//            tv.setTextColor(context.getResources().getColor(android.R.color.white));
//            layout.addView(tv, params);

            addContentView(layout, params);
        }
    }

    //toast
    public static void TShort(String title) {
        if (title.toLowerCase().contains("error")) {
            Toast.makeText(getAppContext(), getAppContext().getString(R.string.err_server), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getAppContext(), title, Toast.LENGTH_SHORT).show();
        }
    }

    public static void TLong(String title) {
        if (title.toLowerCase().contains("failed")) {
            Toast.makeText(getAppContext(), getAppContext().getString(R.string.err_server), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getAppContext(), title, Toast.LENGTH_LONG).show();
        }
    }

    public static void showToastContext(Context context, String title) {
        Toast.makeText(context, title, Toast.LENGTH_SHORT).show();
    }

    //date format
    public static String getBulan(String i) {
        String hasil = "";
        if (i.equalsIgnoreCase("01")) {
            hasil = "Januari";
        } else if (i.equalsIgnoreCase("02")) {
            hasil = "Februari";
        } else if (i.equalsIgnoreCase("03")) {
            hasil = "Maret";
        } else if (i.equalsIgnoreCase("04")) {
            hasil = "April";
        } else if (i.equalsIgnoreCase("05")) {
            hasil = "Mei";
        } else if (i.equalsIgnoreCase("06")) {
            hasil = "Juni";
        } else if (i.equalsIgnoreCase("07")) {
            hasil = "Juli";
        } else if (i.equalsIgnoreCase("08")) {
            hasil = "Agustus";
        } else if (i.equalsIgnoreCase("09")) {
            hasil = "September";
        } else if (i.equalsIgnoreCase("10")) {
            hasil = "Oktober";
        } else if (i.equalsIgnoreCase("11")) {
            hasil = "November";
        } else if (i.equalsIgnoreCase("12")) {
            hasil = "Desember";
        }

        return hasil;
    }

    public static String tglJamSqlToInd(String tgl) {
        try {
            String x[] = tgl.split(" ");
            String x1[] = x[0].split("-");
            String jam[] = x[1].split(":");


            return x1[2] + " " + getBulan(x1[1]) + " " + x1[0] + " | " + jam[0] + ":" + jam[1];
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void copyClipboard(String label) {
        ClipboardManager clipboard = (ClipboardManager) appContext.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label", label);
        clipboard.setPrimaryClip(clip);
    }

    public static Double d(String transPokok) {
        Double x = 0.0;
        try {
            x = Double.parseDouble(transPokok);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return x;
    }

    public static String toRupiah(String nominal) {

        DecimalFormat df = (DecimalFormat) DecimalFormat.getCurrencyInstance();

        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setCurrencySymbol("");
        dfs.setMonetaryDecimalSeparator(',');
        dfs.setGroupingSeparator('.');
        df.setDecimalFormatSymbols(dfs);
        df.setMaximumFractionDigits(0);
        String rupiah = df.format(d(nominal));

        return rupiah;
    }

    public static void callFullScreen(Activity activity) {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = activity.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = activity.getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    public static void triggerRebirthC(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(context.getPackageName());
        ComponentName componentName = intent.getComponent();
        Intent mainIntent = Intent.makeRestartActivityTask(componentName);
        context.startActivity(mainIntent);
        Runtime.getRuntime().exit(0);
    }

    public static void triggerRebirth(Activity context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(context.getPackageName());
        ComponentName componentName = intent.getComponent();
        Intent mainIntent = Intent.makeRestartActivityTask(componentName);
        context.startActivity(mainIntent);
        context.overridePendingTransition(android.R.anim.fade_out,android.R.anim.fade_in);
        Runtime.getRuntime().exit(0);
    }

    public static void restartActivity(Activity activity){
        Intent intent = new Intent();
        intent.setClass(activity, activity.getClass());
        activity.startActivity(intent);
        activity.overridePendingTransition(0, 0);
        activity.finish();
    }

    public static void drawableText(TextView v, int right, int bottom, int left, int top){
        v.setCompoundDrawablesWithIntrinsicBounds( left, top, right, bottom);
    }

    public static void intentFinish(Activity cx, Class cs){
        Intent i = new Intent(cx,cs);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        cx.startActivity(i);
        cx.finish();
    }

    public static class myProgressBar {
        private ProgressBar mProgressBar;
        private Context mContext;

        public myProgressBar(Context context) {
            mContext = context;

            ViewGroup layout = (ViewGroup) ((Activity) context).findViewById(android.R.id.content).getRootView();

            mProgressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleLarge);
            mProgressBar.setIndeterminate(true);

            RelativeLayout.LayoutParams params = new
                    RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

            RelativeLayout rl = new RelativeLayout(context);

            rl.setGravity(Gravity.TOP);
            rl.addView(mProgressBar);

            layout.addView(rl, params);

            hide();
        }

        public void show() {
            mProgressBar.setVisibility(View.VISIBLE);
        }

        public void hide() {
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    public static String GTAG(Class c){
        String name = c.getClass().getSimpleName();
        return name;
    }

//    public static String html2text(String html) {
//        return Jsoup.parse(html).text();
//    }

}
