package ru.samsung.itschool.mdev.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.work.ForegroundInfo;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;

import io.reactivex.annotations.NonNull;

import static android.content.Context.NOTIFICATION_SERVICE;

public class BackgroundWorker extends Worker {
    String CHANNEL_ID = "1";
    Context context;
    private NotificationManager notificationManager;

    public BackgroundWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
      //  notificationManager = (NotificationManager)
      //          context.getSystemService(NOTIFICATION_SERVICE);
    }



    @NonNull
    @Override
    public Result doWork() {

             //setForegroundAsync(
                sendNotification("Background Task","Successfully done");

        return Result.success();
    }

   void sendNotification(String title, String message) {

        Log.i("RRRRR", "doWork: start");
        NotificationManager mNotificationManager =
                (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("1",
                    "android",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("WorkManger");
            mNotificationManager.createNotificationChannel(channel);


        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "1")
                .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                .setContentTitle(title) // title for notification
                .setContentText(message)// message for notification
                .setAutoCancel(true); // clear notification after click

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pi);
        mNotificationManager.notify(0, mBuilder.build());
       Log.i("RRRRR", "doWork: end");



    }


}
