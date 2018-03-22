package com.example.dell.DTUhack.asynctasks;

import android.os.AsyncTask;

import com.example.dell.DTUhack.models.Task;
import com.example.dell.DTUhack.room_db.AppDatabase;

public class dbwritetask extends AsyncTask<Task,Void,Void> {
    AppDatabase appDatabase;

    public interface AsyncListener {
        public void onPostExecute();
    }

    private AsyncListener listener;

    public dbwritetask(AppDatabase appDatabase, AsyncListener listener) {
        this.appDatabase = appDatabase;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Task... tasks) {
        tasks[0].setId(appDatabase.taskDao().numofTasks()+1);
        appDatabase.taskDao().insertAll(tasks[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (this.listener!=null)
            listener.onPostExecute();
    }
}
