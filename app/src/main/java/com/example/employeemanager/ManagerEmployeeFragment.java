package com.example.employeemanager;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class ManagerEmployeeFragment extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_manager_fragment);

        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new PagerMainAdapter());
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private class PagerMainAdapter extends FragmentPagerAdapter {

        public PagerMainAdapter() {
            super(getSupportFragmentManager());
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    ManagerEmployeeAdd buttonFragmentAdd = new ManagerEmployeeAdd();
                    buttonFragmentAdd.setButtonFragmentListener(new ManagerEmployeeAdd.ButtonFragmentListener() {
                        @Override
                        public void onAddSuccess() {
                            Toast.makeText(ManagerEmployeeFragment.this, "onButtonClickAdd",
                                    Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onListSuccess() {
                            Toast.makeText(ManagerEmployeeFragment.this, "onButtonClickList",
                                    Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onResetSuccess() {
                            Toast.makeText(ManagerEmployeeFragment.this, "onButtonClickReset",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                    return buttonFragmentAdd;
                case 1:
                    return new ManagerEmployeeList();
                default:
                    return new ErrorFragment();
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
