package tuca.project.com.tuca.Volley;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import tuca.project.com.tuca.Constants;

/**
 * Created by abd on 07-Apr-18.
 */

public class fetchCbcPres extends StringRequest {

    Map<String,String> parameters;

    //for cbc
    public fetchCbcPres(String id, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, new Constants().fetchcbcPres, listener, errorListener);
        parameters = new HashMap<>();
        parameters.put("patient_id_cbc", id);

    }
    //for presription
    public fetchCbcPres(String id,String pres, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, new Constants().fetchcbcPres, listener, errorListener);
        parameters = new HashMap<>();
        parameters.put("patient_id_pres", id);

    }


    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }

}
