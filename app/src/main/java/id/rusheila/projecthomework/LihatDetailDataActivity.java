package id.rusheila.projecthomework;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;

public class LihatDetailDataActivity extends AppCompatActivity {
    EditText edit_id, edit_nama, edit_norek, edit_notelf, edit_email, edit_saldo;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_detail_data);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail Data Nasabah");

        edit_id = findViewById(R.id.edit_id);
        edit_nama = findViewById(R.id.edit_name);
        edit_norek = findViewById(R.id.edit_norek);
        edit_notelf = findViewById(R.id.edit_notelf);
        edit_email = findViewById(R.id.edit_email);
        edit_saldo = findViewById(R.id.edit_saldo);

        // menerima intent dari class LihatDataActivity
        Intent receiveIntent = getIntent();
        id = receiveIntent.getStringExtra(Konfigurasi.NSB_ID);
        edit_id.setText(id);

        // mengambil data JSON
        getJSON();
    }

    private void getJSON() {
        // bantuan dari class AsyncTask
        class GetJSON extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatDetailDataActivity.this,
                        "Mengambil Data", "Harap menunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_DETAIL, id);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                displayDetailData(message);
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void displayDetailData(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
            JSONObject object = result.getJSONObject(0);

            String nama = object.getString(Konfigurasi.TAG_JSON_NAMA);
            String norek = object.getString(Konfigurasi.TAG_JSON_NOREK);
            String notelf = object.getString(Konfigurasi.TAG_JSON_NOTELF);
            String email = object.getString(Konfigurasi.TAG_JSON_EMAIL);
            String saldo = object.getString(Konfigurasi.TAG_JSON_SALDO);

            edit_nama.setText(nama);
            edit_norek.setText(norek);
            edit_notelf.setText(notelf);
            edit_email.setText(email);
            edit_saldo.setText(saldo);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return true;
    }
}