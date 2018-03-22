package com.example.dell.DTUhack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import com.example.dell.DTUhack.asynctasks.dbwriteperson;
import com.example.dell.DTUhack.models.Person;
import com.example.dell.DTUhack.room_db.AppDatabase;


public class RegistrationTabbedActivity extends AppCompatActivity {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    static Person p = new Person();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_tabbed);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }


    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";
        public PlaceholderFragment() {
        }


        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = null;
            if (getArguments() != null) {
                int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
                switch (sectionNumber) {

                    case 1:
                        EditText etname;
                        final EditText etdob;
                        RadioButton rbmale;
                        RadioButton rbfemale;
                        EditText etheight;
                        EditText etweight;
                        Button btnnext;
                        rootView = inflater.inflate(R.layout.fragment_registration_tabbed, container, false);


                        etname = rootView.findViewById(R.id.et_name);
                        etdob = rootView.findViewById(R.id.et_dob);
                        rbmale = rootView.findViewById(R.id.rb_male);
                        rbfemale = rootView.findViewById(R.id.rb_female);
                        etheight = rootView.findViewById(R.id.et_height);
                        etweight = rootView.findViewById(R.id.et_weight);

                        p.setName(etname.getText().toString());
                        p.setDob(etdob.getText().toString());
                        if (rbmale.isChecked())
                            p.setGender(2);
                        else
                            p.setGender(1);

                        p.setBmi(18);
                        break;


                    case 2:
                        RadioButton rbsedentary;
                        RadioButton rbmoderate;
                        RadioButton rbactive;
                        RadioButton rbveryactive;
                        RadioButton rbfat;
                        RadioButton rbhealthy;
                        RadioButton rbmuscle;
                        Button btnsubmit;

                        rootView = inflater.inflate(R.layout.fragment_registrationform2, container, false);

                        rbsedentary = rootView.findViewById(R.id.rb_sedentary);
                        rbmoderate = rootView.findViewById(R.id.rb_moderate);
                        rbactive = rootView.findViewById(R.id.rb_active);
                        rbveryactive = rootView.findViewById(R.id.rb_Vactive);
                        rbfat = rootView.findViewById(R.id.rb_fat);
                        rbhealthy = rootView.findViewById(R.id.rb_healthy);
                        rbmuscle = rootView.findViewById(R.id.rb_muscle);

                        if (rbsedentary.isChecked())
                            p.setLifestyle(1);
                        else if (rbmoderate.isChecked())
                            p.setLifestyle(2);
                        else if (rbactive.isChecked())
                            p.setLifestyle(3);
                        else
                            p.setLifestyle(4);

                        btnsubmit = rootView.findViewById(R.id.btn_submit);

                        btnsubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                new dbwriteperson(AppDatabase.getInstance(getContext()), new dbwriteperson.AsyncListener() {
                                    @Override
                                    public void onPostExecute() {

                                    }
                                }).execute(p);
                                Intent i = new Intent(getContext(), TabbedActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                if (p.getBmi() <=18.5)
                                    i.putExtra("GOAL", 2);
                                else if (p.getBmi() <=24.9)
                                    i.putExtra("GOAL", 3);
                                else
                                    i.putExtra("GOAL", 1);
                                startActivity(i);
                            }
                        });
                        break;
                }
            }
            return rootView;
        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 2;
        }

    }
}