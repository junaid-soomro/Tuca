package tuca.project.com.tuca;

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

import java.util.UUID;

import tuca.project.com.tuca.Patient.PatientDashboard;
import tuca.project.com.tuca.Singletons.RequestQueues;
import tuca.project.com.tuca.Volley.EditProfRequest;

public class EditProfile extends AppCompatActivity {

    EditText name,phone,email,age,gender,username,password;

    CheckBox up_pass;
    
    TextView update,cance;

    String NAME,PHONE,EMAIL,AGE,GENDER,USER,PASS;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        initiliaze();
        setValues();
        work();
    }

    private void setValues() {

        name.setText(new SessionManager(EditProfile.this).getName());
        phone.setText(new SessionManager(EditProfile.this).getPhone());
        age.setText(new SessionManager(EditProfile.this).getAge());
        gender.setText(new SessionManager(EditProfile.this).getGender());
        email.setText(new SessionManager(EditProfile.this).getEmail());
        username.setText(new SessionManager(EditProfile.this).getUsername());
    }

    private void work() {
        
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NAME = name.getText().toString();
                PHONE = phone.getText().toString();
                EMAIL = email.getText().toString();
                AGE = age.getText().toString();
                GENDER = gender.getText().toString();
                USER = username.getText().toString();
                
                if(up_pass.isChecked()){
                    PASS = password.getText().toString();
                }
                else{
                    PASS = null;
                }
                if(Validation(NAME,PHONE,EMAIL,AGE,GENDER,USER,PASS)){
                    uploadDetails();
                }
            }
        });



    }

    private boolean Validation(String name, String phone, String email, String age, String gender, String user, String pass) {

        if(PASS == null){
        if(name.equals("") || phone.equals("") || email.equals("") || age.equals("") || gender.equals("") || user.equals("")){
            Toast.makeText(this, "One or more fields empty.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(gender.equals("MaleFemale") || gender.equals("Male | Female") || gender.equals("Male Female")){
            Toast.makeText(this, "Enter proper gender.", Toast.LENGTH_SHORT).show();
            return false;
            }
        }else{
            if(name.equals("") || phone.equals("") || email.equals("") || age.equals("") || gender.equals("") || user.equals("") || pass.equals("")){
                Toast.makeText(this, "One or more fields empty.", Toast.LENGTH_SHORT).show();
                return false;
            }
            if(gender.equals("MaleFemale") || gender.equals("Male | Female") || gender.equals("Male Female")){
                Toast.makeText(this, "Enter proper gender.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;

    }
    public void uploadDetails(){

        if(PASS == null){
            final ProgressDialog progressDialog = new ProgressDialog(EditProfile.this);
            progressDialog.setTitle("Please Wait");
            progressDialog.setMessage("Updating user");
            progressDialog.setCancelable(false);
            progressDialog.show();

            EditProfRequest request = new EditProfRequest(new SessionManager(EditProfile.this).getId(), NAME, PHONE, EMAIL, AGE, GENDER, USER, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    Log.i("Response Edit profile", response.toString());
                    try {
                        if(new JSONObject(response).getBoolean("status")){
                            Toast.makeText(EditProfile.this, "Username exists", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(new JSONObject(response).getBoolean("success")){
                            Toast.makeText(EditProfile.this, "Updated user.", Toast.LENGTH_SHORT).show();
                            finish();
                            new SessionManager(EditProfile.this).Logout();
                            startActivity(new Intent(EditProfile.this,Welcome.class));
                        }else{
                            Toast.makeText(EditProfile.this, "No data changed.", Toast.LENGTH_SHORT).show();
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
            RequestQueues.getInstance(EditProfile.this).addToRequestQue(request);

        }else{
            final ProgressDialog progressDialog = new ProgressDialog(EditProfile.this);
            progressDialog.setTitle("Please Wait");
            progressDialog.setMessage("Updating user");
            progressDialog.setCancelable(false);
            progressDialog.show();

            EditProfRequest request = new EditProfRequest(new SessionManager(EditProfile.this).getId(), NAME, PHONE, EMAIL, AGE, GENDER, USER, PASS, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    Log.i("Response Edit profile", response.toString());
                    try {
                        if(new JSONObject(response).getBoolean("success")){
                            Toast.makeText(EditProfile.this, "Updated user.", Toast.LENGTH_SHORT).show();
                            finish();
                            new SessionManager(EditProfile.this).Logout();
                            startActivity(new Intent(EditProfile.this,Welcome.class));
                        }else{
                            Toast.makeText(EditProfile.this, "failed to update", Toast.LENGTH_SHORT).show();
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
            RequestQueues.getInstance(EditProfile.this).addToRequestQue(request);
        }

    }


    @Override
    public void onBackPressed() {
        fileList();
        startActivity(new Intent(EditProfile.this, PatientDashboard.class));
    }
    private void initiliaze() {

        name = (EditText)findViewById(R.id.edit_name);
        phone =(EditText)findViewById(R.id.edit_phone);
        email = (EditText)findViewById(R.id.edit_email);
        age = (EditText)findViewById(R.id.edit_age);
        gender = (EditText)findViewById(R.id.edit_gender);
        username = (EditText)findViewById(R.id.edit_username);
        password = (EditText)findViewById(R.id.edit_password);
        password.setVisibility(View.INVISIBLE);
        up_pass = (CheckBox)findViewById(R.id.up_pass); 
        
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
        
        update = (TextView)findViewById(R.id.edit_confirm);
        cance = (TextView)findViewById(R.id.edit_cancel);

        cance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(EditProfile.this,PatientDashboard.class));
            }
        });
    }
}
