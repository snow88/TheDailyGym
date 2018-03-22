package com.example.dell.DTUhack.asynctasks;

import android.os.AsyncTask;

import com.example.dell.DTUhack.models.Task;
import com.example.dell.DTUhack.room_db.AppDatabase;

import java.util.ArrayList;
import java.util.List;


public class dbreadtask extends AsyncTask<AppDatabase,Void,ArrayList<Task>> {
    public static final String TAG = "ASYNC ";
    AppDatabase appDatabase;
    ArrayList<Task> tasklist= new ArrayList<>();

    public interface AsyncListener {
        public void onPostExecute(ArrayList<Task> tasks);
    }

    private AsyncListener listener;

    public dbreadtask(AsyncListener listener) {
        this.listener = listener;
    }

    @Override
    protected ArrayList<Task> doInBackground(AppDatabase... appDatabases) {
        appDatabase=appDatabases[0];

        List<Task> i = appDatabase.taskDao().getAll();
        tasklist = new ArrayList<>(i);

        return tasklist;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<Task> tasks) {
        super.onPostExecute(tasks);
        if (this.listener!=null)
            listener.onPostExecute(tasks);
    }
}
