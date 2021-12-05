package tuca.project.com.tuca.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.sql.BatchUpdateException;
import java.util.ArrayList;

import tuca.project.com.tuca.Models.EmergencyModel;
import tuca.project.com.tuca.R;

/**
 * Created by abd on 04-Apr-18.
 */

public class EmergencyAdapter extends BaseAdapter {

    TextView name,reqid,lat,lon;
    Button view_location,respond;

    ArrayList<EmergencyModel> models = new ArrayList<>();
    Context context;

    public EmergencyAdapter(ArrayList<EmergencyModel> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.notification_view,null,false);
        name = (TextView)view.findViewById(R.id.view_name);
        reqid =(TextView)view.findViewById(R.id.reqid);

        lat = (TextView)view.findViewById(R.id.lat);
        lon =(TextView)view.findViewById(R.id.lon);

        lat.setVisibility(View.INVISIBLE);
        lon.setVisibility(View.INVISIBLE);
        reqid.setVisibility(View.INVISIBLE);

        lat.setText(models.get(i).getLat());
        lon.setText(models.get(i).getLon());

        reqid.setText(models.get(i).getReq_id());
        name.setText(models.get(i).getPatien_name());
        view_location = (Button)view.findViewById(R.id.view_location);
        respond = (Button)view.findViewById(R.id.respond);

        view_location.setFocusable(false);
        respond.setFocusable(false);
        return view;
    }
}
