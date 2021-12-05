package tuca.project.com.tuca.Models;

/**
 * Created by abd on 31-Mar-18.
 */

public class ClinicDoc {

    String clinicid,clinicname,doc_id,doc_name,doc;


    public String getDoc_id() {
        return doc_id;
    }

    public String getDoc_name() {
        return doc_name;
    }

    public ClinicDoc(String doc_id, String doc_name, String doc) {
        this.doc_id = doc_id;
        this.doc_name = doc_name;
        this.doc = doc;
    }

    public ClinicDoc(String cliid, String cliname) {
        this.clinicid = cliid;
        this.clinicname = cliname;
    }

    public String getcliId() {
        return clinicid;
    }

    public String getcliName() {
        return clinicname;
    }
}
