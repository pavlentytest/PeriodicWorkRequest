package ru.samsung.itschool.mdev.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.annotations.Nullable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("RRRR", "started");
        final PeriodicWorkRequest periodicWorkRequest1 = new PeriodicWorkRequest.Builder(
                BackgroundWorker.class,15, TimeUnit.MINUTES)
                .build();

        WorkManager workManager =  WorkManager.getInstance(this);
        workManager.enqueue(periodicWorkRequest1);

        workManager.getWorkInfoByIdLiveData(periodicWorkRequest1.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(@Nullable WorkInfo workInfo) {
                        Log.i("RRRR", "onChanged: " + workInfo.getState());

                    }
                });
    }

}