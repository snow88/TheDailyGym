package com.example.dell.DTUhack.asynctasks;

import android.os.AsyncTask;

import com.example.dell.DTUhack.models.Person;
import com.example.dell.DTUhack.room_db.AppDatabase;

import java.util.ArrayList;
import java.util.List;


public class dbreadperson extends AsyncTask<AppDatabase,Void,ArrayList<Person>> {
    public static final String TAG = "ASYNC ";
    AppDatabase appDatabase;
    ArrayList<Person> personlist= new ArrayList<>();

    public interface AsyncListener {
        public void onPostExecute(ArrayList<Person> persons);
    }

    private AsyncListener listener;

    public dbreadperson(AsyncListener listener) {
        this.listener = listener;
    }

    @Override
    protected ArrayList<Person> doInBackground(AppDatabase... appDatabases) {
        appDatabase=appDatabases[0];

        List<Person> i = appDatabase.personDao().getAll();
        personlist = new ArrayList<>(i);

        return personlist;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<Person> persons) {
        super.onPostExecute(persons);
        if (this.listener!=null)
            listener.onPostExecute(persons);
    }
}