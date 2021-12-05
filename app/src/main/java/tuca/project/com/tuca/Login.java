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

import tuca.project.com.tuca.EmergencyDept.EmergencyDashboard;
import tuca.project.com.tuca.Patient.PatientDashboard;
import tuca.project.com.tuca.Singletons.RequestQueues;
import tuca.project.com.tuca.Volley.LoginRegRequest;

/**
 * Created by abd on 27-Mar-18.
 */

public class Login extends AppCompatActivity {

    TextView login,cancel;

    EditText username,pass;

    String USER,PASS;

    public void onBackPressed(){
        finish();
        startActivity(new Intent(Login.this,Welcome.class));
    }

    private void checkSession() {

        if(new SessionManager(Login.this).CheckIfSessionExist()){

            if(new SessionManager(Login.this).getType().equals("emergency")){

                startActivity(new Intent(Login.this,EmergencyDashboard.class));
                finish();
            }
            else if(new SessionManager(Login.this).getType().equals("user")){

                startActivity(new Intent(Login.this, PatientDashboard.class));
                finish();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initiliaze();
        checkSession();
        loginUser();
    }



    private void loginUser() {



    login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            USER = username.getText().toString();
            PASS = pass.getText().toString();

            final ProgressDialog progressDialog = new ProgressDialog(Login.this);
            progressDialog.setTitle("Please Wait");
            progressDialog.setMessage("Logging You In");
            progressDialog.setCancelable(false);
            progressDialog.show();

            LoginRegRequest regRequest = new LoginRegRequest(USER, PASS, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.getBoolean("success")) {


                            new SessionManager(Login.this,jsonObject.getString("ID"),jsonObject.getString("Name"),
                                    jsonObject.getString("Phone"),jsonObject.getString("Email"),jsonObject.getString("age")
                                    ,jsonObject.getString("gender"),jsonObject.getString("Username"),jsonObject.getString("type"));

                            Toast.makeText(Login.this, "Login success", Toast.LENGTH_SHORT).show();

                            if(jsonObject.getString("type").equals("emergency")){
                                startActivity(new Intent(Login.this,EmergencyDashboard.class));
                                finish();
                                return;
                            }else if(jsonObject.getString("type").equals("user")){

                                startActivity(new Intent(Login.this,PatientDashboard.class));
                                finish();
                                return;
                            }

                            Toast.makeText(Login.this, jsonObject.getString("type"), Toast.LENGTH_SHORT).show();

                        } else {
                            if(jsonObject.getString("status").equals("INVALID"))
                                Toast.makeText(Login.this, "User Not Found", Toast.LENGTH_SHORT).show();
                            else {

                                Toast.makeText(Login.this, "Passwords dont match.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(Login.this, "Bad Response From Server "+e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Log.i("Volley erro", error.getMessage());
                }
            });
            RequestQueues.getInstance(Login.this).addToRequestQue(regRequest);
        }
    });

    }


    private void initiliaze() {

            login = (TextView)findViewById(R.id.Login);
            cancel = (TextView)findViewById(R.id.cancel_login);

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                    startActivity(new Intent(Login.this,Welcome.class));
                }
            });

            username = (EditText)findViewById(R.id.username);
            pass = (EditText)findViewById(R.id.password);

    }
}
