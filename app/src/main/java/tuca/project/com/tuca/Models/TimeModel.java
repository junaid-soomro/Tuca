package tuca.project.com.tuca.Models;

/**
 * Created by abd on 07-Apr-18.
 */

public class TimeModel {

    String id,time;

    public String getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public TimeModel(String id, String time) {
        this.id = id;
        this.time = time;
    }
}
