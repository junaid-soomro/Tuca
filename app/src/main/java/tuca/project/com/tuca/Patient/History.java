package tuca.project.com.tuca.Patient;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import tuca.project.com.tuca.Adapters.cbcAdapter;
import tuca.project.com.tuca.Adapters.presAdapter;
import tuca.project.com.tuca.Models.cbcPresModel;
import tuca.project.com.tuca.R;
import tuca.project.com.tuca.SessionManager;
import tuca.project.com.tuca.Singletons.RequestQueues;
import tuca.project.com.tuca.Volley.fetchCbcPres;

public class History extends AppCompatActivity {

    ListView cbc_pres_list;

    ArrayList<cbcPresModel> cbcArray = new ArrayList<>();
    cbcPresModel cbcPresModel;
    cbcAdapter cbcAdapter;

    presAdapter presAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        cbc_pres_list = (ListView)findViewById(R.id.cbc_pres_list);
        if(getIntent().getBooleanExtra("cbc",false)){
            fetchCbc();
        }
        else{
            fetchPres();
        }


    }

    private void fetchPres() {
        final ProgressDialog progressDialog = new ProgressDialog(History.this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Fetching CBC reports");
        progressDialog.setCancelable(false);
        progressDialog.show();
        final fetchCbcPres request = new fetchCbcPres(new SessionManager(History.this).getId(),"smthn", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                int count = 0;
                JSONObject object;
                try {
                    JSONArray array = new JSONArray(response);
                    while (count<array.length()){
                        object = array.getJSONObject(count);
                        cbcPresModel = new cbcPresModel(object.getString("username"),object.getString("doc_name"),object.getString("med_name"),
                                object.getString("status"));
                        cbcArray.add(cbcPresModel);
                        count++;
                    }
                    setPres(cbcArray);

                } catch (JSONException e) {
                    Log.i("Exception", e.getMessage());
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.i("Volley Exception", error.getMessage());
            }
        });
        RequestQueues.getInstance(History.this).addToRequestQue(request);



    }

    private void setPres(ArrayList<cbcPresModel> cbcArray) {
    this.cbcArray = cbcArray;
    presAdapter = new presAdapter(cbcArray,History.this);
    cbc_pres_list.setAdapter(presAdapter);
    if(presAdapter.getCount()==0){
        Toast.makeText(this, "No presciptions made.", Toast.LENGTH_SHORT).show();
    }
    }

    private void fetchCbc() {

        final ProgressDialog progressDialog = new ProgressDialog(History.this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Fetching CBC reports");
        progressDialog.setCancelable(false);
        progressDialog.show();
        final fetchCbcPres request = new fetchCbcPres(new SessionManager(History.this).getId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
progressDialog.dismiss();

                int count = 0;
                        JSONObject object;
                try {
                    JSONArray array = new JSONArray(response);
                    while (count<array.length()){
                        object = array.getJSONObject(count);
                        cbcPresModel = new cbcPresModel(object.getString("wBc"),object.getString("rBc"),object.getString("plt"),object.getString("mono"),
                                object.getString("iym"),object.getString("other"),object.getString("time"));
                        cbcArray.add(cbcPresModel);
                        count++;
                    }
            setCbc(cbcArray);

                } catch (JSONException e) {
                    Log.i("Exception", e.getMessage());
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.i("Volley Exception", error.getMessage());
            }
        });
        RequestQueues.getInstance(History.this).addToRequestQue(request);


    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(History.this,PatientDashboard.class));
    }

    private void setCbc(ArrayList<cbcPresModel> cbcArray) {

        this.cbcArray = cbcArray;
        cbcAdapter = new cbcAdapter(cbcArray,History.this);
        cbc_pres_list.setAdapter(cbcAdapter);
        if(cbcAdapter.getCount()==0){
            Toast.makeText(this, "No cbc reports present.", Toast.LENGTH_SHORT).show();
        }

    }
}
