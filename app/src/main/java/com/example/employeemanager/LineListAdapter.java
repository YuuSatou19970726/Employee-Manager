package com.example.employeemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.employeemanager.scheme.Employee;

import java.util.ArrayList;

public class LineListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Employee> employeeArrayList;

    public LineListAdapter(Context context, ArrayList<Employee> employeeArrayList) {
        this.context = context;
        this.employeeArrayList = employeeArrayList;
    }

    @Override
    public int getCount() {
        return employeeArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return employeeArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null){
            LayoutInflater layoutInflater =LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.list_dong, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        viewHolder = (ViewHolder) convertView.getTag();
        Employee employee = (Employee) getItem(position);
        viewHolder.tvName.setText(employee.getName());
        viewHolder.tvAddress.setText(employee.getAddress());
        viewHolder.tvPhoneNumber.setText(employee.getPhoneNumber());
        viewHolder.tvEmail.setText(employee.getEmail());

        return convertView;
    }

    private class ViewHolder{
        TextView tvName;
        TextView tvAddress;
        TextView tvPhoneNumber;
        TextView tvEmail;

        public ViewHolder(View view){
            tvName = view.findViewById(R.id.tv_ten2);
            tvAddress = view.findViewById(R.id.tv_diachi2);
            tvPhoneNumber= view.findViewById(R.id.tv_sdt2);
            tvEmail = view.findViewById(R.id.tv_email2);
        }
    }
}
