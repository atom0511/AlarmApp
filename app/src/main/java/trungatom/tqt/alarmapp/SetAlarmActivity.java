package trungatom.tqt.alarmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Switch;
import android.widget.TimePicker;
import java.util.Calendar;

public class SetAlarmActivity extends AppCompatActivity {

    TimePicker timePicker;
    Calendar calendar;
    Button btnSave;
    Switch btnSwitch;
    PendingIntent pendingIntent;
    private AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_alarm_activity);
        timePicker = findViewById(R.id.tp_time_picker);
        btnSave = findViewById(R.id.btn_save);
        btnSwitch = findViewById(R.id.btn_switch);
        calendar = Calendar.getInstance();
        int check = getIntent().getIntExtra("ON_OFF", 0);
        Log.d("check", "onCreate: " + check);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intentNotification = new Intent(SetAlarmActivity.this, AlarmReceiver.class);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                String hour = timePicker.getCurrentHour().toString();
                String minute = timePicker.getCurrentMinute().toString();
                String timeSet = hour + ":" + minute;

                pendingIntent = PendingIntent.getBroadcast(SetAlarmActivity.this, 0, intentNotification, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                Intent intent = new Intent(SetAlarmActivity.this, MainActivity.class);
                intent.putExtra("TIME_SET", timeSet);
                intent.putExtra("IS_ACTIVE", true);
                startActivity(intent);

            }
        });
    }
}
