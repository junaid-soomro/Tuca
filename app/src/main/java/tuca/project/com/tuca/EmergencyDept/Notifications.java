package tuca.project.com.tuca.EmergencyDept;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.security.cert.CertPathBuilderSpi;
import java.util.ArrayList;
import java.util.Locale;

import tuca.project.com.tuca.Adapters.EmergencyAdapter;
import tuca.project.com.tuca.Constants;
import tuca.project.com.tuca.EmergencyCall;
import tuca.project.com.tuca.Models.EmergencyModel;
import tuca.project.com.tuca.R;
import tuca.project.com.tuca.Singletons.RequestQueues;
import tuca.project.com.tuca.Volley.EmergencyRequest;

public class Notifications extends AppCompatActivity {

    ListView notifications;

    ArrayList<EmergencyModel> arrayList = new ArrayList<>();
    EmergencyAdapter adapter;
    EmergencyModel model;
    
    String ID,latt,lonn;

    String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        notifications = (ListView)findViewById(R.id.notification_list);
        fetchRequests();
        work();

    }

    private void work() {

            notifications.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    TextView lat = (TextView)view.findViewById(R.id.lat);
                    TextView lon = (TextView)view.findViewById(R.id.lon);
                    TextView id = (TextView)view.findViewById(R.id.reqid);

                    ID = id.getText().toString();
                    latt = lat.getText().toString();
                    lonn = lon.getText().toString();

                    Button view_location = (Button)view.findViewById(R.id.view_location);
                    Button respond = (Button)view.findViewById(R.id.respond);

                view_location.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                                String uri = String.format(Locale.ENGLISH, "geo:%s,%s", latt,lonn );
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                                startActivity(intent);
                    }
                });
                respond.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Notifications.this);
                        builder.setTitle("Choose action!");
                        builder.setMessage("What do you want to do?");
                        builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                status = "approved";
                                responserequest();
                            }
                        });
                        builder.setNegativeButton("Reject", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                status="rejected";
                                responserequest();
                            }
                        });
                        builder.show();
                    }
                });
                    }
                });
                }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(Notifications.this,EmergencyDashboard.class));
    }

    private void responserequest() {
        final ProgressDialog progressDialog = new ProgressDialog(Notifications.this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Responding Request");
        progressDialog.setCancelable(true);
        progressDialog.show();

        EmergencyRequest request = new EmergencyRequest(String.valueOf(ID), status, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                try {
                    if(new JSONObject(response).getBoolean("status")){
                        Toast.makeText(Notifications.this, "Responded succesfully.", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(Notifications.this,EmergencyDashboard.class));
                        arrayList.remove(ID);
                        adapter.notifyDataSetChanged();
                    }else{
                        Toast.makeText(Notifications.this, "Unable to respond.", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Log.i("Volley exception", e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.i("Volley exception", error.getMessage());
            }
        });
        RequestQueues.getInstance(Notifications.this).addToRequestQue(request);
    }

    private void fetchRequests() {
        final ProgressDialog progressDialog = new ProgressDialog(Notifications.this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Fetching requests");
        progressDialog.setCancelable(true);
        progressDialog.show();

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, new Constants().emergency, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressDialog.dismiss();
                Log.i("Response", response.toString());

            int count = 0;
                JSONObject object;

                while (count<response.length()){
                    try {
                        object = response.getJSONObject(count);
                        model = new EmergencyModel(object.getString("req_id"),object.getString("lat"),object.getString("lon"),object.getString("name"));
                        arrayList.add(model);
                        count++;
                    } catch (JSONException e) {
                        Log.d("Exception", e.getMessage());
                    }
                }
setValues(arrayList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.d("Volley Exception", error.getMessage());
            }
        });
        RequestQueues.getInstance(Notifications.this).addToRequestQue(request);
    }

    private void setValues(ArrayList<EmergencyModel> arrayList) {
    this.arrayList = arrayList;
    adapter = new EmergencyAdapter(arrayList,Notifications.this);
    notifications.setAdapter(adapter);
    if(adapter.getCount()==0){

        Toast.makeText(this, "No emergency requests made.", Toast.LENGTH_SHORT).show();
    }
    }
}
