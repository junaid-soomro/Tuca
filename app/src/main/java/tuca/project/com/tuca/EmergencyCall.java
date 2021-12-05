package tuca.project.com.tuca;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import org.json.JSONException;
import org.json.JSONObject;

import tuca.project.com.tuca.Patient.BookAppointmet;
import tuca.project.com.tuca.Patient.PatientDashboard;
import tuca.project.com.tuca.Singletons.RequestQueues;
import tuca.project.com.tuca.Volley.EmergencyRequest;

public class EmergencyCall extends AppCompatActivity {

    Button pick_location,call,cancel;
    int PLACE_PICKER_REQUEST = 1;

    String latitude,longitude,name;

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(EmergencyCall.this,Welcome.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_call);
        pick_location = (Button)findViewById(R.id.pick_location);
        call = (Button)findViewById(R.id.send_location_emergecy);
        cancel = (Button)findViewById(R.id.cancel_emergencydept);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(EmergencyCall.this,Welcome.class));
            }
        });

        pick_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                    startActivityForResult(builder.build(EmergencyCall.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name==null){
                    Toast.makeText(EmergencyCall.this, "Please pick location first.", Toast.LENGTH_SHORT).show();
                }else{
                    helprequest();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == PLACE_PICKER_REQUEST) {
            if(resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(this, data);

                latitude = String.valueOf(place.getLatLng().latitude);
                longitude = String.valueOf(place.getLatLng().longitude);

                AlertDialog.Builder builder = new AlertDialog.Builder(EmergencyCall.this);
                builder.setTitle("Name!");
                builder.setMessage("Enter your name.");
                final EditText imput = new EditText(EmergencyCall.this);
                builder.setView(imput);
                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(imput.getText().toString().equals("")){
                            Toast.makeText(EmergencyCall.this, "Empty field", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        name = imput.getText().toString();
                    }
                });
                builder.setNeutralButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    //do nothing
                    }
                });
                builder.show();
            }
        }
    }

    private void helprequest() {
        final ProgressDialog progressDialog = new ProgressDialog(EmergencyCall.this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Calling for help");
        progressDialog.setCancelable(true);
        progressDialog.show();
        EmergencyRequest request = new EmergencyRequest(name, latitude, longitude, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            progressDialog.dismiss();

                try {
                    if(new JSONObject(response).getBoolean("status")){

                        Toast.makeText(EmergencyCall.this, "Request submitted.", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(EmergencyCall.this,Welcome.class));
                    }else{
                        Toast.makeText(EmergencyCall.this, "Unable to submit request.", Toast.LENGTH_SHORT).show();
                    }
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
        RequestQueues.getInstance(EmergencyCall.this).addToRequestQue(request);
    }
}
