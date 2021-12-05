package tuca.project.com.tuca.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import tuca.project.com.tuca.Models.cbcPresModel;
import tuca.project.com.tuca.R;

/**
 * Created by abd on 07-Apr-18.
 */

public class cbcAdapter extends BaseAdapter {

    ArrayList<cbcPresModel> arrayList = new ArrayList<>();
    Context context;

    TextView wbc,rbc,plt,mono,iym,other,time;

    public cbcAdapter(ArrayList<cbcPresModel> arrayList, Context context) {
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
        view = LayoutInflater.from(context).inflate(R.layout.cbc_view,null,false);
        initiliaze(view);

        wbc.setText(arrayList.get(i).getWbc());
        rbc.setText(arrayList.get(i).getRbc());
        plt.setText(arrayList.get(i).getPlt());
        mono.setText(arrayList.get(i).getMono());
        iym.setText(arrayList.get(i).getIym());
        other.setText(arrayList.get(i).getOther());
        time.setText(arrayList.get(i).getTime());

        return view;
    }

    private void initiliaze(View view) {

        wbc = (TextView)view.findViewById(R.id.wbc);
        rbc = (TextView)view.findViewById(R.id.rbc);
        plt = (TextView)view.findViewById(R.id.platelets);
        mono = (TextView)view.findViewById(R.id.mono);
        iym = (TextView)view.findViewById(R.id.iym);
        other = (TextView)view.findViewById(R.id.other);
        time = (TextView)view.findViewById(R.id.time_stamp);
    }
}
