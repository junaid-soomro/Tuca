package tuca.project.com.tuca;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by abd on 28-Mar-18.
 */

public class SessionManager {

    String id,name,phone,email,age,gender,username,type;

    public SessionManager(Context c)
    {
        session= c.getSharedPreferences(new Constants().SESSION,Context.MODE_PRIVATE);
        this. id = session.getString("id",null);
        this.name = session.getString("name",null);
        this.phone = session.getString("phone",null);
        this.username = session.getString("username",null);
        this.age = session.getString("age",null);
        this.email = session.getString("email",null);
        this.gender = session.getString("gender",null);
        this.type = session.getString("type",null);
    }

    public SessionManager(Context c ,String id, String name, String phone, String email, String age, String gender, String username,String type) {
        session= c.getSharedPreferences(new Constants().SESSION,Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = session.edit();
        editor.putString("id", id);
        editor.putString("name",name);
        editor.putString("phone",phone);
        editor.putString("username",username);
        editor.putString("email",email);
        editor.putString("age",age);
        editor.putString("gender",gender);
        editor.putString("type",type);
        editor.commit();

        new SessionManager(c);
    }

    public String getType() {
        return type;
    }

    public boolean CheckIfSessionExist(){
        if (session.contains("id"))
            return true;
        else
            return  false;

    }
    public void Logout (){
        SharedPreferences.Editor editor = session.edit();
        editor.clear();
        editor.commit();

        this.id=null;
        this.name=null;
        this.phone=null;
        this.username = null;
        this.gender = null;
        this.email = null;
        this.age = null;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getUsername() {
        return username;
    }

    SharedPreferences session ;

}
