package com.example.dell.DTUhack.asynctasks;

import android.os.AsyncTask;
import com.example.dell.DTUhack.models.Person;
import com.example.dell.DTUhack.room_db.AppDatabase;

public class dbwriteperson extends AsyncTask<Person,Void,Void> {
    AppDatabase appDatabase;

    public interface AsyncListener {
        public void onPostExecute();
    }

    private AsyncListener listener;

    public dbwriteperson(AppDatabase appDatabase, AsyncListener listener) {
        this.appDatabase = appDatabase;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Person... persons) {
        persons[0].setId(appDatabase.personDao().numofPersons()+1);
        appDatabase.personDao().insertAll(persons[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (this.listener!=null)
            listener.onPostExecute();
    }
}