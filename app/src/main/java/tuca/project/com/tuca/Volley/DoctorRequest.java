package tuca.project.com.tuca.Volley;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import tuca.project.com.tuca.Constants;

/**
 * Created by abd on 01-Apr-18.
 */

public class DoctorRequest extends StringRequest {

    Map<String,String> parameters;

    public DoctorRequest(String clinic_id, Response.Listener<String> listener,Response.ErrorListener errorListener){
        super(Method.POST,new Constants().fetch_doctor,listener,errorListener);

        parameters = new HashMap<>();

        parameters.put("clinic_id",clinic_id);

    }
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }
}
