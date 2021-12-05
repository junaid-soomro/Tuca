package tuca.project.com.tuca.Volley;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import tuca.project.com.tuca.Constants;

/**
 * Created by abd on 11-Apr-18.
 */

public class timeRequest extends StringRequest {

    Map<String,String> parameters;

    public timeRequest(String id,String date, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, new Constants().fetch_time, listener, errorListener);
        parameters = new HashMap<>();
        parameters.put("doc_id", id);
        parameters.put("date",date);

    }


    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }
}
