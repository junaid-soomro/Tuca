package tuca.project.com.tuca.Patient;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import tuca.project.com.tuca.EditProfile;
import tuca.project.com.tuca.R;
import tuca.project.com.tuca.SessionManager;
import tuca.project.com.tuca.Welcome;

public class PatientDashboard extends AppCompatActivity {

    Button book,history,my_appointments;

    TextView name;

    ImageView edit_prof,logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_dashboard);
        initiliaze();
        name.setText(new SessionManager(PatientDashboard.this).getName());

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(PatientDashboard.this,BookAppointmet.class));
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(PatientDashboard.this,History.class);
                AlertDialog.Builder builder = new AlertDialog.Builder(PatientDashboard.this);
                builder.setTitle("Choose History!");
                builder.setMessage("Select to open the desired history");
                builder.setPositiveButton("CBC", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        intent.putExtra("cbc",true);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setNegativeButton("Prescription", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        intent.putExtra("prescription",true);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                   //do nothing
                    }
                });
                builder.show();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SessionManager(PatientDashboard.this).Logout();
                finish();
                startActivity(new Intent(PatientDashboard.this, Welcome.class));
            }
        });
        edit_prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(PatientDashboard.this, EditProfile.class));
            }
        });
        my_appointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(PatientDashboard.this,MyAppointments.class));
            }
        });
    }

    private void initiliaze() {

        book = (Button)findViewById(R.id.book_apointment);
        history = (Button)findViewById(R.id.view_history);
        my_appointments = (Button)findViewById(R.id.my_appointments);

        name = (TextView)findViewById(R.id.patient_name);

        edit_prof = (ImageView)findViewById(R.id.edit_profile_patient);
        logout = (ImageView)findViewById(R.id.logout_patient);


    }
}
