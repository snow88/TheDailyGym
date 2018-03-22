package com.example.dell.DTUhack;

import android.app.NotificationManager;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.webkit.WebView;
import android.widget.Toast;

import com.example.dell.DTUhack.adapters.RecyclerItemClickListener;
import com.example.dell.DTUhack.adapters.RecyclerListAdapter;
import com.example.dell.DTUhack.asynctasks.dbreadtask;
import com.example.dell.DTUhack.asynctasks.dbwritetaskinitial;
import com.example.dell.DTUhack.models.Task;
import com.example.dell.DTUhack.room_db.AppDatabase;

import java.util.ArrayList;

public class TabbedActivity extends AppCompatActivity {
    static ArrayList<Task> tasklist = new ArrayList<>();
    AppDatabase appDatabase;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    int goal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.icon)
                        .setContentTitle("THE DAILY GYM")
                        .setContentText("Your personalized workout plan is here.");

        int notificationId = 001;
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, mBuilder.build());

        goal = getIntent().getIntExtra("GOAL", 1);

        appDatabase = AppDatabase.getInstance(this);

        new dbwritetaskinitial(appDatabase, new dbwritetaskinitial.AsyncListener() {
            @Override
            public void onPostExecute() {

            }
        }).execute();

        new dbreadtask(new dbreadtask.AsyncListener() {
            @Override
            public void onPostExecute(ArrayList<Task> tasks) {
                tasklist = tasks;
            }
        }).execute(appDatabase);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        mViewPager.setCurrentItem(goal);                                //person's need specific tab is opened

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tabbed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuitem1) {
            startActivity(new Intent(TabbedActivity.this, LoginActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        }

        return super.onOptionsItemSelected(item);
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
        public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = null;
            int i = getArguments().getInt(ARG_SECTION_NUMBER);

            if (i==4) {
                rootView = inflater.inflate(R.layout.fragment_tab4, container, false);
                WebView wv = rootView.findViewById(R.id.frag4webv);
                wv.loadUrl("https://www.muscleandstrength.com/workout-routines");
            }

            if (i==3) {
                rootView = inflater.inflate(R.layout.fragment_tab3, container, false);
                RecyclerView rv = rootView.findViewById(R.id.frag3rv);
                final ArrayList<Task> itemlist = new ArrayList<>();
                itemlist.clear();
                for (Task t: tasklist) {
                    for (int g=0; g<t.getGoal().length(); g++) {
                        if (t.getGoal().contains(Integer.toString(i))) {
                            itemlist.add(t);
                            break;
                        }
                    }
                }
                RecyclerListAdapter recyclerListAdapter = new RecyclerListAdapter(getContext(), itemlist);
                rv.setLayoutManager(new LinearLayoutManager(getContext()));
                rv.setAdapter(recyclerListAdapter);
                rv.addOnItemTouchListener(
                        new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                            @Override public void onItemClick(View view, int position) {
                                Intent intent = new Intent(getContext(), WorkoutActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.putExtra("TITLE", itemlist.get(position).getName());
                                intent.putExtra("DESC", itemlist.get(position).getDesc());
                                intent.putExtra("IMG", itemlist.get(position).getImgdrawable());
                                startActivity(intent);
                            }
                        })
                );
            }

            if (i==2) {
                rootView = inflater.inflate(R.layout.fragment_tab2, container, false);
                RecyclerView rv = rootView.findViewById(R.id.frag2rv);
                final ArrayList<Task> itemlist = new ArrayList<>();
                itemlist.clear();
                for (Task t: tasklist) {
                    for (int g=0; g<t.getGoal().length(); g++) {
                        if (t.getGoal().contains(Integer.toString(i))) {
                            itemlist.add(t);
                            break;
                        }
                    }
                }
                RecyclerListAdapter recyclerListAdapter = new RecyclerListAdapter(getContext(), itemlist);
                rv.setLayoutManager(new LinearLayoutManager(getContext()));
                rv.setAdapter(recyclerListAdapter);
                rv.addOnItemTouchListener(
                        new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                            @Override public void onItemClick(View view, int position) {
                                Intent intent = new Intent(getContext(), WorkoutActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.putExtra("TITLE", itemlist.get(position).getName());
                                intent.putExtra("DESC", itemlist.get(position).getDesc());
                                intent.putExtra("IMG", itemlist.get(position).getImgdrawable());
                                startActivity(intent);
                            }
                        })
                );

            }

            if (i==1) {
                rootView = inflater.inflate(R.layout.fragment_tab1, container, false);
                RecyclerView rv = rootView.findViewById(R.id.frag1rv);
                final ArrayList<Task> itemlist = new ArrayList<>();
                itemlist.clear();
                for (Task t: tasklist) {
                    for (int g=0; g<t.getGoal().length(); g++) {
                        if (t.getGoal().contains(Integer.toString(i))) {
                            itemlist.add(t);
                            break;
                        }
                    }
                }
                RecyclerListAdapter recyclerListAdapter = new RecyclerListAdapter(getContext(), itemlist);
                rv.setLayoutManager(new LinearLayoutManager(getContext()));
                rv.setAdapter(recyclerListAdapter);
                rv.addOnItemTouchListener(
                        new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                            @Override public void onItemClick(View view, int position) {
                                Intent intent = new Intent(getContext(), WorkoutActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.putExtra("TITLE", itemlist.get(position).getName());
                                intent.putExtra("DESC", itemlist.get(position).getDesc());
                                intent.putExtra("IMG", itemlist.get(position).getImgdrawable());
                                startActivity(intent);
                            }
                        })
                );
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
            return 4;
        }
    }
}
