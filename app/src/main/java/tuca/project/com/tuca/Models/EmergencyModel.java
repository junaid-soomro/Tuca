package tuca.project.com.tuca.Models;

/**
 * Created by abd on 04-Apr-18.
 */

public class EmergencyModel {

    String req_id,lat,lon,patien_name;

    public EmergencyModel(String req_id, String lat, String lon, String patien_name) {
        this.req_id = req_id;
        this.lat = lat;
        this.lon = lon;
        this.patien_name = patien_name;
    }

    public String getReq_id() {
        return req_id;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public String getPatien_name() {
        return patien_name;
    }
}
