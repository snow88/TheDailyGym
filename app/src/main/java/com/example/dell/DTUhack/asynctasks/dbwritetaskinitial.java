package com.example.dell.DTUhack.asynctasks;

import android.os.AsyncTask;

import com.example.dell.DTUhack.R;
import com.example.dell.DTUhack.models.Task;
import com.example.dell.DTUhack.room_db.AppDatabase;

public class dbwritetaskinitial extends AsyncTask<Void,Void,Void> {
    AppDatabase appDatabase;

    public interface AsyncListener {
        public void onPostExecute();
    }

    private AsyncListener listener;

    public dbwritetaskinitial(AppDatabase appDatabase, AsyncListener listener) {
        this.appDatabase = appDatabase;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        Task t1 = new Task(1, "Push Up", "Push-ups work your arms (triceps and forearms), your chest, lower back, abs, quads and calves. They work the intercostal muscles covering your ribs and if you do enough of them and breathe in and out explosively as you do them they even work your lungs which means they become an aerobic exercise that also works your cardiovascular and aerobic systems.",
                R.drawable.pushup, "12", "13");
        Task t2 = new Task(2, "Diet Guide", "Push-ups work your arms (triceps and forearms), your chest, lower back, abs, quads and calves. They work the intercostal muscles covering your ribs and if you do enough of them and breathe in.", R.drawable.veggie, "12", "123");
        Task t3 = new Task(3, "Cobra Pose", "Push-ups work your arms (triceps and forearms), your chest, lower back, abs, quads and calves. They work the intercostal muscles covering your ribs and if you do enough of them and breathe in.", R.drawable.cobrapose, "12", "23");
        Task t4 = new Task(4, "Lift Yourself", "Push-ups work your arms (triceps and forearms), your chest, lower back, abs, quads and calves. They work the intercostal muscles covering your ribs and if you do enough of them and breathe in.", R.drawable.lift, "12", "13");
        Task t5 = new Task(5, "Meditation", "Push-ups work your arms (triceps and forearms), your chest, lower back, abs, quads and calves. They work the intercostal muscles covering your ribs and if you do enough of them and breathe in.", R.drawable.meditate, "12", "12");
        Task t6 = new Task(6, "Run Run Run", "Push-ups work your arms (triceps and forearms), your chest, lower back, abs, quads and calves. They work the intercostal muscles covering your ribs and if you do enough of them and breathe in.", R.drawable.running, "12", "2");

        appDatabase.taskDao().insertAll(t1, t2, t3, t4, t5, t6);

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (this.listener!=null)
            listener.onPostExecute();
    }
}