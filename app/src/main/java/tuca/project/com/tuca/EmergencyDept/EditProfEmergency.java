package tuca.project.com.tuca.EmergencyDept;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import tuca.project.com.tuca.EditProfile;
import tuca.project.com.tuca.Patient.PatientDashboard;
import tuca.project.com.tuca.R;
import tuca.project.com.tuca.SessionManager;
import tuca.project.com.tuca.Singletons.RequestQueues;
import tuca.project.com.tuca.Volley.EditProfRequest;
import tuca.project.com.tuca.Welcome;

public class EditProfEmergency extends AppCompatActivity {

    EditText name,username,password;

    CheckBox up_pass;

    TextView update,cancel;

    String NAME,USER,PASS;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_emer_profile);
        initiliaze();
        setValues();
        work();
    }

    private void work() {
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NAME = name.getText().toString();
                USER = username.getText().toString();

                if(up_pass.isChecked()){
                    PASS = password.getText().toString();
                }
                else{
                    PASS = null;
                }
             if(Validation(NAME,USER,PASS)){uploadDetails();}
            }
        });
    }

    private boolean Validation(String name,String user, String pass) {

        if(PASS == null){
            if(name.equals("") || user.equals("")){
                Toast.makeText(this, "One or more fields empty.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            if(name.equals("") || user.equals("") || pass.equals("")){
                Toast.makeText(this, "One or more fields empty.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;

    }

    public void uploadDetails(){

        if(PASS == null){
            final ProgressDialog progressDialog = new ProgressDialog(EditProfEmergency.this);
            progressDialog.setTitle("Please Wait");
            progressDialog.setMessage("Updating user");
            progressDialog.setCancelable(false);
            progressDialog.show();

            EditProfRequest request = new EditProfRequest(new SessionManager(EditProfEmergency.this).getId(), NAME,USER, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    Log.i("Response Edit profile", response.toString());
                    try {
                        if(new JSONObject(response).getBoolean("status")){
                            Toast.makeText(EditProfEmergency.this, "Username exists", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(new JSONObject(response).getBoolean("success")){
                            Toast.makeText(EditProfEmergency.this, "Updated user.", Toast.LENGTH_SHORT).show();
                            finish();
                            new SessionManager(EditProfEmergency.this).Logout();
                            startActivity(new Intent(EditProfEmergency.this,Welcome.class));
                        }else{
                            Toast.makeText(EditProfEmergency.this, "No data changed.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        Log.i("Exception", e.getMessage());
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Log.i("Exception", error.getMessage());
                }
            });
            RequestQueues.getInstance(EditProfEmergency.this).addToRequestQue(request);

        }else{
            final ProgressDialog progressDialog = new ProgressDialog(EditProfEmergency.this);
            progressDialog.setTitle("Please Wait");
            progressDialog.setMessage("Updating user");
            progressDialog.setCancelable(false);
            progressDialog.show();

            EditProfRequest request = new EditProfRequest(new SessionManager(EditProfEmergency.this).getId(), NAME,USER, PASS, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    Log.i("Response Edit profile", response.toString());
                    try {
                        if(new JSONObject(response).getBoolean("success")){
                            Toast.makeText(EditProfEmergency.this, "Updated user.", Toast.LENGTH_SHORT).show();
                            finish();
                            new SessionManager(EditProfEmergency.this).Logout();
                            startActivity(new Intent(EditProfEmergency.this,Welcome.class));
                        }else{
                            Toast.makeText(EditProfEmergency.this, "failed to update", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        Log.i("Exception", e.getMessage());
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Log.i("Exception", error.getMessage());
                }
            });
            RequestQueues.getInstance(EditProfEmergency.this).addToRequestQue(request);
        }

    }


    private void setValues() {
        name.setText(new SessionManager(EditProfEmergency.this).getName());
        username.setText(new SessionManager(EditProfEmergency.this).getUsername());
    }
    private void initiliaze() {

        name = (EditText)findViewById(R.id.edit_emer_name);
        username = (EditText)findViewById(R.id.edit_emer_username);
        password = (EditText)findViewById(R.id.edit_emer_password);
        password.setVisibility(View.INVISIBLE);
        up_pass = (CheckBox)findViewById(R.id.up_emer_pass);

        up_pass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(up_pass.isChecked()){
                    password.setVisibility(View.VISIBLE);

                }else{
                    password.setVisibility(View.INVISIBLE);

                }
            }
        });

        update = (TextView)findViewById(R.id.edit_emer_confirm);
        cancel = (TextView)findViewById(R.id.edit_emer_cancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(EditProfEmergency.this,EmergencyDashboard.class));
            }
        });
    }
    public void onBackPressed() {
        fileList();
        startActivity(new Intent(EditProfEmergency.this, EmergencyDashboard.class));
    }

}
