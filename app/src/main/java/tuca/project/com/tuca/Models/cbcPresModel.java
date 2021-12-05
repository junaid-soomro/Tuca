package tuca.project.com.tuca.Models;

/**
 * Created by abd on 07-Apr-18.
 */

public class cbcPresModel {
//cbc
    String wbc,rbc,plt,mono,iym,other,time;
//pres
    String username,doc_name,medicine,status;

    public cbcPresModel(String username, String doc_name, String medicine,String status) {
        this.username = username;
        this.doc_name = doc_name;
        this.medicine = medicine;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getUsername() {
        return username;
    }

    public String getDoc_name() {
        return doc_name;
    }

    public String getMedicine() {
        return medicine;
    }

    public cbcPresModel(String wbc, String rbc, String plt, String mono, String iym, String other, String time) {
        this.wbc = wbc;
        this.rbc = rbc;
        this.plt = plt;
        this.mono = mono;
        this.iym = iym;
        this.other = other;
        this.time = time;
    }

    public String getWbc() {
        return wbc;
    }

    public String getRbc() {
        return rbc;
    }

    public String getPlt() {
        return plt;
    }

    public String getMono() {
        return mono;
    }

    public String getIym() {
        return iym;
    }

    public String getOther() {
        return other;
    }

    public String getTime() {
        return time;
    }
}
