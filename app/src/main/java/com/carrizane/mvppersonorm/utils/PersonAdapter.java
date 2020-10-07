package com.carrizane.mvppersonorm.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.carrizane.mvppersonorm.R;
import com.carrizane.mvppersonorm.entities.Person;

import java.util.List;

public class PersonAdapter extends BaseAdapter {
    private Context mContext;
    private List<Person> listPerson;

    public PersonAdapter(Context context, List<Person> list){
        this.mContext = context;
        this.listPerson = list;
    }

    @Override
    public int getCount() {
        return listPerson.size();
    }

    @Override
    public Object getItem(int position) {
        return listPerson.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Person person = listPerson.get(position);
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.list_row, null);

        TextView name = convertView.findViewById(R.id.nameTxt);
        TextView lastname = convertView.findViewById(R.id.lastNameTxt);

        name.setText(person.getName());

        lastname.setText((person.getLast_name()));

        return convertView;
    }
}

