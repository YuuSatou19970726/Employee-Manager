package com.example.employeemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.employeemanager.scheme.Employee;

import java.util.ArrayList;

public class EmployeeAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Employee> employeeArrayList;

    public EmployeeAdapter(Context context, ArrayList<Employee> employeeArrayList) {
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
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.employee_adapter, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        viewHolder = (ViewHolder) convertView.getTag();

        Employee employee = (Employee) getItem(position);
        viewHolder.tvName.setText(employee.getName());
        viewHolder.tvPhoneNumber.setText(employee.getPhoneNumber());
        viewHolder.tvEmail.setText(employee.getEmail());
        viewHolder.tvTN.setText(employee.getSeniority());
        viewHolder.tvAddress.setText(employee.getAddress());
        viewHolder.tvSalary.setText(employee.getSalary());

        return convertView;
    }

    private class ViewHolder{

        TextView tvName;
        TextView tvPhoneNumber;
        TextView tvEmail;
        TextView tvTN;
        TextView tvAddress;
        TextView tvSalary;

        public ViewHolder(View view){
            tvName = view.findViewById(R.id.tv_ten_manager2);
            tvPhoneNumber = view.findViewById(R.id.tv_sdt_manager2);
            tvEmail = view.findViewById(R.id.tv_email_manager2);
            tvTN = view.findViewById(R.id.tv_tn_manager2);
            tvAddress = view.findViewById(R.id.tv_diachi_manager2);
            tvSalary = view.findViewById(R.id.tv_muc_luong_manager2);
        }
    }
}
