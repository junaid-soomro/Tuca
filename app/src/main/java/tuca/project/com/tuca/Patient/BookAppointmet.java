package tuca.project.com.tuca.Patient;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

import tuca.project.com.tuca.Adapters.ClinicDocAdapter;
import tuca.project.com.tuca.Adapters.DocAdapter;
import tuca.project.com.tuca.Adapters.TimeAdapter;
import tuca.project.com.tuca.Constants;
import tuca.project.com.tuca.Models.ClinicDoc;
import tuca.project.com.tuca.Models.TimeModel;
import tuca.project.com.tuca.R;
import tuca.project.com.tuca.SessionManager;
import tuca.project.com.tuca.Singletons.RequestQueues;
import tuca.project.com.tuca.Volley.AppointmentRequest;
import tuca.project.com.tuca.Volley.DoctorRequest;
import tuca.project.com.tuca.Volley.timeRequest;

public class BookAppointmet extends AppCompatActivity {

    TextView set_date,selected_time;

    DatePickerDialog.OnDateSetListener dateSetListener;

    ImageView date;
    String DATE;

    Button book;

    String real_date;

    Spinner clinic,doc,time_spinner;

    ArrayList<ClinicDoc> arrayList = new ArrayList<>();
    ArrayList<ClinicDoc> arrayListDoc = new ArrayList<>();
    ClinicDoc model;
    ClinicDoc model2;

    ArrayList<TimeModel> timeList = new ArrayList<>();
    TimeAdapter timeAdapter;
    TimeModel timeModel;

    ClinicDocAdapter adapter;
    DocAdapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        initiliaze();
        selectdate();
        setSlots();
        setDoctor();
        bookAppointment();
    }

    private void setSlots() {

        doc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(timeList.size()!=0){timeList.clear();}
                fetchTime(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private void fetchTime(int i) {
        final ProgressDialog progressDialog = new ProgressDialog(BookAppointmet.this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Fetching Time");
        progressDialog.setCancelable(true);
        progressDialog.show();
        timeRequest request = new timeRequest(arrayListDoc.get(i).getDoc_id(),real_date,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    progressDialog.dismiss();
                Log.i("Time response", response);

                    int count = 0 ;
                    JSONObject object;

                        try {
                            JSONArray array = new JSONArray(response);
                            while (count<array.length()) {

                                object = array.getJSONObject(count);
                                timeModel = new TimeModel(object.getString("sc_id"), object.getString("time"));
                                timeList.add(timeModel);
                                count++;
                            }
                        } catch (JSONException e) {
                            Log.i("Exception", e.getMessage());
                        }

                    setTime(timeList);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.i("Volley error", error.getMessage());
            }
        });

        RequestQueues.getInstance(BookAppointmet.this).addToRequestQue(request);

    }

    private void setTime(ArrayList<TimeModel> timeList) {

        this.timeList = timeList;
        timeAdapter = new TimeAdapter(this.timeList,BookAppointmet.this);
        time_spinner.setAdapter(timeAdapter);
    }

    private void setDoctor() {

        clinic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(arrayListDoc.size()!=0){arrayListDoc.clear();}
                fetchDoctors(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void fetchDoctors(int i) {

        final ProgressDialog progressDialog = new ProgressDialog(BookAppointmet.this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Fetching doctors");
        progressDialog.setCancelable(true);
        progressDialog.show();

        DoctorRequest request = new DoctorRequest(arrayList.get(i).getcliId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Log.i("Response", response.toString());
                int count = 0;
                JSONObject object;
                try {
                    JSONArray array = new JSONArray(response);
                    while (count<array.length()){

                        object = array.getJSONObject(count);
                        model2 = new ClinicDoc(object.getString("doctor_id"),object.getString("doctor_name"),"nothing");
                        arrayListDoc.add(model2);
                        count++;
                    }
                    setDocs(arrayListDoc);
                } catch (JSONException e) {
                    Log.d("Exception", e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.d("Volley error", error.getLocalizedMessage());
            }
        });
        RequestQueues.getInstance(BookAppointmet.this).addToRequestQue(request);
    }

    private void setDocs(ArrayList<ClinicDoc> arrayListDoc) {
        this.arrayListDoc = arrayListDoc;
        adapter2 = new DocAdapter(arrayListDoc,BookAppointmet.this);
        doc.setAdapter(adapter2);

    }

    private void fetchClinic() {

        if(arrayList.size()!=0){
            arrayList.clear();
        }

        final ProgressDialog progressDialog = new ProgressDialog(BookAppointmet.this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Fetching clinics");
        progressDialog.setCancelable(true);
        progressDialog.show();

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, new Constants().fetch_clinic, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
progressDialog.dismiss();

            int count = 0;
            JSONObject object;
                while (count<response.length()){
                    try {
                        object = response.getJSONObject(count);
                        model = new ClinicDoc(object.getString("clinic_id"),object.getString("clinic_name"));
                        arrayList.add(model);
                        count++;
                    } catch (JSONException e) {
                        Log.d("Exception", e.getMessage());
                    }
                }
                setClinics(arrayList);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.d("Volley error", error.getLocalizedMessage());
            }
        });

        RequestQueues.getInstance(BookAppointmet.this).addToRequestQue(request);


    }

    private void setClinics(ArrayList<ClinicDoc> arrayList) {

        this.arrayList = arrayList;
        adapter = new ClinicDocAdapter(arrayList,BookAppointmet.this);
        clinic.setAdapter(adapter);

    }

    private void bookAppointment() {

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(set_date.getText().toString().equals("Tap image")){
                    Toast.makeText(BookAppointmet.this, "Please select date.", Toast.LENGTH_SHORT).show();
                    return;
                }

                int pos = doc.getSelectedItemPosition();
                int pos2 = time_spinner.getSelectedItemPosition();

                DATE = set_date.getText().toString();

                final ProgressDialog progressDialog = new ProgressDialog(BookAppointmet.this);
                progressDialog.setTitle("Please Wait");
                progressDialog.setMessage("Booking appointment");
                progressDialog.setCancelable(true);
                progressDialog.show();
                AppointmentRequest request = new AppointmentRequest(new SessionManager(BookAppointmet.this).getId(), DATE, timeList.get(pos2).getId(),arrayListDoc.get(pos).getDoc_id(),
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Log.d("Response", response.toString());
                                try {
                                    progressDialog.dismiss();
                                    if(new JSONObject(response).getBoolean("status")){
                                        finish();
                                        startActivity(new Intent(BookAppointmet.this,PatientDashboard.class));
                                        Toast.makeText(BookAppointmet.this, "Appointment submitted.", Toast.LENGTH_SHORT).show();
                                    }else{

                                        Toast.makeText(BookAppointmet.this, "Appintment submission failed.", Toast.LENGTH_SHORT).show();

                                    }
                                } catch (JSONException e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(BookAppointmet.this, "Exception occured", Toast.LENGTH_SHORT).show();
                                    Log.i("Exception", e.getMessage());
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(BookAppointmet.this, "Volley error", Toast.LENGTH_SHORT).show();
                        Log.i("Volley error", error.getMessage());
                    }
                });
                RequestQueues.getInstance(BookAppointmet.this).addToRequestQue(request);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(BookAppointmet.this,PatientDashboard.class));
    }

    private void selectdate() {

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int date = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(BookAppointmet.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener,
                        year,month,date
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;

                if(month<10 && day<10){
                    real_date = "0"+day+"/0"+month+"/"+year;
                    set_date.setText(real_date);
                    fetchClinic();
                    return;
                }
                if(month<10){
                     real_date = day+"/0"+month+"/"+year;
                    set_date.setText(real_date);
                    fetchClinic();
                    return;
                }if(day<10){
                    real_date = "0"+day+"/"+month+"/"+year;
                    set_date.setText(real_date);
                    fetchClinic();
                    return;
                }

            }
        };


    }

    private void initiliaze() {
        set_date = (TextView)findViewById(R.id.date_set);

        date = (ImageView)findViewById(R.id.date);

        book = (Button)findViewById(R.id.payment_go);

        clinic = (Spinner)findViewById(R.id.clinic_spinner);
        doc = (Spinner)findViewById(R.id.doctor_spinner);
        time_spinner = (Spinner)findViewById(R.id.time_spinner);
    }
}
