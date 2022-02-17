package com.a000webhostapp.heloiwan.tabunganku.utils;

import com.a000webhostapp.heloiwan.tabunganku.BuildConfig;

public class Constants {

    //api
    public static final String BASE_URL = BuildConfig.SERVER_URL;
    public static final String API_LOGIN = BASE_URL + "index.php/api/Auth";
    public static final String API_GET_NASABAH = BASE_URL + "index.php/api/Nasabah";
    public static final String API_GET_NASABAH_ID = BASE_URL + "index.php/api/Nasabah?kode=";
    public static final String API_GET_HISTORY = BASE_URL + "index.php/api/Entry/transaksi";
    public static final String API_GET_SALDO = BASE_URL + "index.php/api/Entry/getSaldoAll";
    public static final String API_POST_ENTRY = BASE_URL + "index.php/api/Entry";
    public static final String API_POST_ENTRY_UPDATE = BASE_URL + "index.php/api/Entry/update";

    //api-properties
    public static final String API_HEADER_K = "Content-Type";
    public static final String API_HEADER_V = "application/x-www-form-urlencoded";

    //field
    public static final String IS_LOGIN = "IS_LOGIN";
    public static final String USER_NAME = "nama";
    public static final String NASABAH_NOREK = "no_rekening";
    public static final String NASABAH_ID = "id_nasabah";

}
