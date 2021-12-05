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

public class EditProfRequest extends StringRequest {

    Map<String,String> parameters;

    public EditProfRequest(String id, String name, String phone, String email, String age, String gender, String username, String pass, Response.Listener<String> listener
    ,Response.ErrorListener errorListener){

        super(Method.POST,new Constants().EDIT_PROFILE,listener,errorListener);

        parameters = new HashMap<>();

        parameters.put("id",id);
        parameters.put("name",name);
        parameters.put("phone",phone);
        parameters.put("email",email);
        parameters.put("age",age);
        parameters.put("gender",gender);
        parameters.put("username",username);
        parameters.put("pass",pass);

    }
    public EditProfRequest(String id, String name, String phone, String email, String age, String gender, String username, Response.Listener<String> listener
            ,Response.ErrorListener errorListener){

        super(Method.POST,new Constants().EDIT_PROFILE,listener,errorListener);

        parameters = new HashMap<>();

        parameters.put("id",id);
        parameters.put("name",name);
        parameters.put("phone",phone);
        parameters.put("email",email);
        parameters.put("age",age);
        parameters.put("gender",gender);
        parameters.put("username",username);

    }
    public EditProfRequest(String id, String name,String username, Response.Listener<String> listener
            ,Response.ErrorListener errorListener){

        super(Method.POST,new Constants().EDIT_EMER,listener,errorListener);

        parameters = new HashMap<>();

        parameters.put("id",id);
        parameters.put("name",name);
        parameters.put("username",username);

    }
    public EditProfRequest(String id, String name,String username,String password, Response.Listener<String> listener
            ,Response.ErrorListener errorListener){

        super(Method.POST,new Constants().EDIT_EMER,listener,errorListener);

        parameters = new HashMap<>();

        parameters.put("id",id);
        parameters.put("name",name);
        parameters.put("username",username);
        parameters.put("pass",password);
    }
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }

}
