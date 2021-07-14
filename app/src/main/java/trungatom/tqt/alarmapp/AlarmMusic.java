package trungatom.tqt.alarmapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class AlarmMusic extends Service {
    MediaPlayer mediaPlayer;
    public static final String CHANNEL_ID = "CHANNEL_1";

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.music);
        createNotificationChannel();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("check", "onReceive: " + "hello music ne");
        pushNotification();
        mediaPlayer.start();

        return START_STICKY;
    }

    private void pushNotification(){
        Intent snoozeIntent = new Intent(MainActivity.this, MainActivity.class) ;
        snoozeIntent.putExtra("STOP?", "yes");
        PendingIntent snoozePendingIntent = PendingIntent. getBroadcast (this, 0 , snoozeIntent , 0) ;

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Bao Thuc")
                .setContentText("Rengggggggggggg")
                .setSmallIcon(R.drawable.ic_baseline_access_alarm_24)
                .setLargeIcon(bitmap)
                .addAction(R.drawable.ic_baseline_access_alarm_24, "STOPPPPPPP", snoozePendingIntent)
                .build();
        if(notification != null) {
            notificationManager.notify(1, notification);
        }
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel 1";
            String description = "This is channel 1";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}
