package tuca.project.com.tuca.Volley;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import tuca.project.com.tuca.Constants;

/**
 * Created by abd on 04-Apr-18.
 */

public class EmergencyRequest extends StringRequest {

    Map<String,String> parameters;

    public EmergencyRequest(String name, String lat,String lon, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, new Constants().emergency, listener, errorListener);
        parameters = new HashMap<>();
        parameters.put("name", name);
        parameters.put("lat",lat);
        parameters.put("lon", lon);
    }

    public EmergencyRequest(String id, String status, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, new Constants().respond_emergency, listener, errorListener);
        parameters = new HashMap<>();
        parameters.put("req_id", id);
        parameters.put("status",status);

    }


    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }
}
