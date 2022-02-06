package id.rusheila.projecthomework;

public class Konfigurasi {
    //url dimana web API berada
    //melihat data
    public static final String URL_GET_ALL = "http://172.20.10.7/nasabah/tampilSemuaNsb.php";
    public static final String URL_GET_DETAIL = "http://172.20.10.7/nasabah/tampilNsb.php?id=";

    //flag JSON
    public static final String TAG_JSON_ARRAY = "result";
    public static final String TAG_JSON_ID = "id";
    public static final String TAG_JSON_NAMA = "name";
    public static final String TAG_JSON_NOREK = "noRek";
    public static final String TAG_JSON_NOTELF = "noTelf";
    public static final String TAG_JSON_EMAIL = "email";
    public static final String TAG_JSON_SALDO = "saldo";


    //variabel ID pegawai
    public static final String NSB_ID = "nsb_id";
}
