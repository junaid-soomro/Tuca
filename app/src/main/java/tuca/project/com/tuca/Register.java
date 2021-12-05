package tuca.project.com.tuca;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import tuca.project.com.tuca.Patient.PatientDashboard;
import tuca.project.com.tuca.Singletons.RequestQueues;
import tuca.project.com.tuca.Volley.LoginRegRequest;

/**
 * Created by abd on 28-Mar-18.
 */

public class Register extends AppCompatActivity {

    EditText name,phone,email,age,gender,username,password;

    TextView register,cance;

    String NAME,PHONE,EMAIL,AGE,GENDER,USER,PASS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initiliaze();
        setValues();

    }

    private void setValues() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NAME = name.getText().toString();
                PHONE = phone.getText().toString();
                EMAIL = email.getText().toString();
                AGE = age.getText().toString();
                GENDER = gender.getText().toString();
                USER = username.getText().toString();
                PASS = password.getText().toString();

                if(Validation(NAME,PHONE,EMAIL,AGE,GENDER,USER,PASS)){
                    registerUser();
                }



            }
        });

    }

    private boolean Validation(String name, String phone, String email, String age, String gender, String user, String pass) {

        if(name.equals("") || phone.equals("") || email.equals("") || age.equals("") || gender.equals("") || user.equals("") || pass.equals("")){
            Toast.makeText(this, "One or more fields empty.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(gender.equals("MaleFemale") || gender.equals("Male | Female") || gender.equals("Male Female")){
            Toast.makeText(this, "Enter proper gender.", Toast.LENGTH_SHORT).show();
            return false;
        }
    return true;

    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(Register.this,Welcome.class));
    }

    private void registerUser() {

        final ProgressDialog progressDialog = new ProgressDialog(Register.this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Registering user");
        progressDialog.setCancelable(false);
        progressDialog.show();

        LoginRegRequest regRequest = new LoginRegRequest(NAME, PHONE, EMAIL, AGE, GENDER, USER, PASS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
progressDialog.dismiss();

try{
    JSONObject object = new JSONObject(response);
    if(new JSONObject(response).getBoolean("status")){
        Toast.makeText(Register.this, "Username exists", Toast.LENGTH_SHORT).show();
        return;
    }
                if (object.getBoolean("success")) {

                    Toast.makeText(Register.this, "User registered.", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(Register.this,Welcome.class));
                    return;

                } else {
                    Toast.makeText(Register.this, "Some error", Toast.LENGTH_SHORT).show();
                }
                if(object.getBoolean("username")){
                    Toast.makeText(Register.this, "Username exists in database,", Toast.LENGTH_SHORT).show();
                    return;
                }



            } catch (Exception e) {
                progressDialog.dismiss();
                Log.i("Exception", e.getMessage().toString());
                Toast.makeText(Register.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.i("Volley error", error.getMessage());
            }
        });
        RequestQueues.getInstance(Register.this).addToRequestQue(regRequest);

    }

    private void initiliaze() {

        name = (EditText)findViewById(R.id.reg_name);
        phone =(EditText)findViewById(R.id.reg_phone);
        email = (EditText)findViewById(R.id.reg_email);
        age = (EditText)findViewById(R.id.reg_age);
        gender = (EditText)findViewById(R.id.reg_gender);
        username = (EditText)findViewById(R.id.reg_username);
        password = (EditText)findViewById(R.id.reg_password);

        register = (TextView)findViewById(R.id.reg_confirm);
        cance = (TextView)findViewById(R.id.reg_cancel);

        cance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(Register.this,Welcome.class));
            }
        });
    }

}
