package tuca.project.com.tuca.EmergencyDept;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import tuca.project.com.tuca.R;
import tuca.project.com.tuca.SessionManager;
import tuca.project.com.tuca.Welcome;

public class EmergencyDashboard extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_dashboard);

        findViewById(R.id.notifications).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(EmergencyDashboard.this,Notifications.class));

            }
        });
        findViewById(R.id.logout_emergency).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SessionManager(EmergencyDashboard.this).Logout();
                finish();
                startActivity(new Intent(EmergencyDashboard.this, Welcome.class));
            }
        });
        findViewById(R.id.edit_prof_emergency).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(EmergencyDashboard.this,EditProfEmergency.class));
            }
        });
    }
}
