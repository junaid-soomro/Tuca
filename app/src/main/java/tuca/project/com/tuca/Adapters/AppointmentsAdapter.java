package tuca.project.com.tuca.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import tuca.project.com.tuca.Models.AppointmentModel;
import tuca.project.com.tuca.R;

/**
 * Created by abd on 28-Mar-18.
 */

public class AppointmentsAdapter extends BaseAdapter {

    TextView name,date,time,status;

    ArrayList<AppointmentModel> arrayList = new ArrayList<>();
    Context context;

    public AppointmentsAdapter(ArrayList<AppointmentModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
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
        view = LayoutInflater.from(context).inflate(R.layout.apppoint_layout,null,false);
        initiliaze(view);

        name.setText(arrayList.get(i).getName());
        date.setText(arrayList.get(i).getDate());
        time.setText(arrayList.get(i).getTime());
        ;

        return view;
    }

    private void initiliaze(View view) {

    name = (TextView)view.findViewById(R.id.patient_list_name);
        date = (TextView)view.findViewById(R.id.patient_list_date);
        time = (TextView)view.findViewById(R.id.patient_list_time);
    }
}
