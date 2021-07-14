package trungatom.tqt.alarmapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("check", "onReceive: " + "hello receive ne");
            Intent intentMusic = new Intent(context, AlarmMusic.class);
            context.startService(intentMusic);
        }

}
