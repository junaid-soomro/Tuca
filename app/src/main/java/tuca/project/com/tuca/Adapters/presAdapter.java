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

public class presAdapter extends BaseAdapter {

    ArrayList<cbcPresModel> arrayList = new ArrayList<>();
    Context context;

    TextView user,doc,med,status;

    public presAdapter(ArrayList<cbcPresModel> arrayList, Context context) {
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
        view = LayoutInflater.from(context).inflate(R.layout.pres_view,null,false);
        initiliaze(view);

        user.setText(arrayList.get(i).getUsername());
        doc.setText(arrayList.get(i).getDoc_name());
        med.setText(arrayList.get(i).getMedicine());

        if(arrayList.get(i).getStatus().equals("0")){
         status.setText("Not Collected");
        }else{
            status.setText("Collected");
        }

        return view;
    }

    private void initiliaze(View view) {

        user = (TextView)view.findViewById(R.id.user_pre_name);
        doc = (TextView)view.findViewById(R.id.doc_pre_name);
        med = (TextView)view.findViewById(R.id.med_pre_name);
        status = (TextView)view.findViewById(R.id.pres_status);
    }

}
