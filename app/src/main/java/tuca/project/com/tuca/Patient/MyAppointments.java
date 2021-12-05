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

import tuca.project.com.tuca.Adapters.AppointmentsAdapter;
import tuca.project.com.tuca.Models.AppointmentModel;
import tuca.project.com.tuca.R;
import tuca.project.com.tuca.SessionManager;
import tuca.project.com.tuca.Singletons.RequestQueues;
import tuca.project.com.tuca.Volley.AppointmentRequest;

public class MyAppointments extends AppCompatActivity {


    ListView list;
    ArrayList<AppointmentModel> arrayList = new ArrayList<>();
    AppointmentsAdapter adapter;

    AppointmentModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_appointments);
        list = (ListView)findViewById(R.id.history_list);
        fetchHistory();


    }

    private void fetchHistory() {
        final ProgressDialog progressDialog = new ProgressDialog(MyAppointments.this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Fetching history");
        progressDialog.setCancelable(false);
        progressDialog.show();

        final AppointmentRequest request = new AppointmentRequest(new SessionManager(MyAppointments.this).getId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
progressDialog.dismiss();
                int count = 0;
                JSONObject object;

                try {
                    JSONArray array = new JSONArray(response);
                    while (count<array.length()){
                        object = array.getJSONObject(count);
                        model = new AppointmentModel(object.getString("name"),object.getString("date"),object.getString("time"));
                        arrayList.add(model);
                        count++;
                    }
                setValues(arrayList);
                }
                catch (JSONException e) {
                    progressDialog.dismiss();
                    Log.i("Exception", e.getMessage());
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                Log.i("Volley error", error.getMessage());
            }
        });
        RequestQueues.getInstance(MyAppointments.this).addToRequestQue(request);
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(MyAppointments.this,PatientDashboard.class));
    }

    private void setValues(ArrayList<AppointmentModel> arrayList) {
    this.arrayList = arrayList;
    adapter = new AppointmentsAdapter(arrayList,MyAppointments.this);
    list.setAdapter(adapter);
    if(adapter.getCount()==0){
        Toast.makeText(this, "No appointments scheduled.", Toast.LENGTH_SHORT).show();
    }

    }
}
