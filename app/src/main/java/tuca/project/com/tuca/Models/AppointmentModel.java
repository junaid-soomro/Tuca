package tuca.project.com.tuca.Models;

/**
 * Created by abd on 28-Mar-18.
 */

public class AppointmentModel {

    String name,date,time;

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public AppointmentModel(String name, String date, String time) {
        this.name = name;
        this.date = date;
        this.time = time;
    }
}
