package tuca.project.com.tuca.Volley;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import tuca.project.com.tuca.Constants;

/**
 * Created by abd on 28-Mar-18.
 */

public class AppointmentRequest extends StringRequest {

    Map<String,String> parameters;

    public AppointmentRequest(String patient_id, String date, String time,String doc_id, Response.Listener<String> listener,Response.ErrorListener errorListener){
        super(Method.POST,new Constants().book_appointment,listener,errorListener);

        parameters = new HashMap<>();

        parameters.put("patient_id",patient_id);
        parameters.put("date",date);
        parameters.put("time",time);
        parameters.put("doc_id",doc_id);
    }

    public AppointmentRequest(String patient_id, Response.Listener<String> listener,Response.ErrorListener errorListener){
        super(Method.POST,new Constants().book_appointment,listener,errorListener);

        parameters = new HashMap<>();

        parameters.put("history_id",patient_id);
    }
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }

}
