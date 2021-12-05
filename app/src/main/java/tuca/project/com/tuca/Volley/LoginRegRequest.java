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

public class LoginRegRequest extends StringRequest {

    Map<String,String> parameters;

    public LoginRegRequest(String username, String password, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, new Constants().LOGIN, listener, errorListener);
        parameters = new HashMap<>();
        parameters.put("username", username);
        parameters.put("password",password);
    }

    public LoginRegRequest(String name,String phone,String email,String age,String gender,String username, String password, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, new Constants().REGISTER, listener, errorListener);
        parameters = new HashMap<>();
        parameters.put("name",name);
        parameters.put("phone",phone);
        parameters.put("email",email);
        parameters.put("age",age);
        parameters.put("gender",gender);
        parameters.put("username", username);
        parameters.put("password",password);
    }

    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }

}
