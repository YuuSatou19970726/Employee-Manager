package com.example.employeemanager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class ManagerEmployeeFragment extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private PagerMainAdapter pagerMainAdapter;
    private String userManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_manager_fragment);

        pagerMainAdapter = new PagerMainAdapter(getSupportFragmentManager());

        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(pagerMainAdapter);
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        //get data from MainActivity
        userManager  = getIntent().getExtras().getString("USER_NAME_MANAGER_EMPLOYEE_FRAGMENT");
    }

    private class PagerMainAdapter extends FragmentPagerAdapter {

        public PagerMainAdapter(FragmentManager fragmentManager){
            super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    final ManagerEmployeeAdd managerEmployeeAdd = ManagerEmployeeAdd.newInstance(userManager);
                    managerEmployeeAdd.setButtonFragmentListener(new ManagerEmployeeAdd.ButtonFragmentListener() {
                        @Override
                        public void onAddSuccess() {
                            Toast.makeText(ManagerEmployeeFragment.this, "Add Success",
                                    Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onReturnSuccess() {
                            Toast.makeText(ManagerEmployeeFragment.this, "Return Success",
                                    Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onResetSuccess() {
                            Toast.makeText(ManagerEmployeeFragment.this, "Reset Success",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                    return managerEmployeeAdd;
                    default:
                        return ManagerEmployeeList.newInstance(userManager);
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        public CharSequence getPageTitle(int position){
            switch (position){
                case 0:
                    return "Employee Add";
                default:
                    return "Employee List";

            }
        }
    }
}
