package com.example.dhvanil.authi.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import com.example.dhvanil.authi.BasicClasses.GrpC;
import com.example.dhvanil.authi.R;

import java.util.ArrayList;

public class GrpAdapter extends ArrayAdapter<GrpC> {

    private int resourceLayout;
    private Context mContext;

    public GrpAdapter( Context context, int resource, ArrayList<GrpC> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }
        CheckBox checkBox = v.findViewById( R.id.GrpCheckBox );

        GrpC p = getItem(position);
        checkBox.setText( p.name );
        checkBox.setChecked( p.isChecked );
        return v;
    }

}
