package tuca.project.com.tuca.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import tuca.project.com.tuca.Models.ClinicDoc;
import tuca.project.com.tuca.Models.TimeModel;
import tuca.project.com.tuca.R;

/**
 * Created by abd on 07-Apr-18.
 */

public class TimeAdapter extends BaseAdapter {

    ArrayList<TimeModel> arrayList = new ArrayList<>();
    Context context;

    TextView time;

    public TimeAdapter(ArrayList<TimeModel> arrayList, Context context) {
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
        view = LayoutInflater.from(context).inflate(R.layout.clinic_doc_view,null,false);
        time = (TextView)view.findViewById(R.id.clinic_doc_name);

        time.setText(arrayList.get(i).getTime());
        return view;
    }
}
