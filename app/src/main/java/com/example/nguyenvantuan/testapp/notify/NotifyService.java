package com.example.nguyenvantuan.testapp.notify;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;

import com.example.nguyenvantuan.testapp.MainActivity;
import com.example.nguyenvantuan.testapp.R;

/**
 * Created by nguyenvantuan on 7/16/15.
 */
public class NotifyService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(this.getApplicationContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification mNotify = new Notification.Builder(this)
                .setContentTitle("Log Steps!")
                .setContentText("Log your steps for today")
//                .setSmallIcon(R.drawable.ic_launcher)
                .setContentIntent(pendingIntent)
                .setSound(sound)
                .addAction(0, "Load Website", pendingIntent)
                .build();

        notificationManager.notify(1, mNotify);
    }
}

