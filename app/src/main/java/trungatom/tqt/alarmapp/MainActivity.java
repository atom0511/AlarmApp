package trungatom.tqt.alarmapp;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

import trungatom.tqt.alarmapp.adapter.AlarmAdapter;
import trungatom.tqt.alarmapp.model.ItemAlarm;
import trungatom.tqt.alarmapp.viewmodel.AlarmViewModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvListAlarm;
    private FloatingActionButton fabAddItem;

    private AlarmAdapter alarmAdapter;
    private AlarmViewModel alarmViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvListAlarm = findViewById(R.id.rv_alarm);
        fabAddItem = findViewById(R.id.fab_add_alarm);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvListAlarm.setLayoutManager(linearLayoutManager);
        alarmViewModel = new ViewModelProvider(this).get(AlarmViewModel.class);

        addAlarmItem(getIntent(), "TIME_SET", alarmViewModel);

        alarmViewModel.getListItemAlarmLiveData().observe(this, new Observer<List<ItemAlarm>>() {
            @Override
            public void onChanged(List<ItemAlarm> itemAlarms) {
                alarmAdapter = new AlarmAdapter(itemAlarms);

                alarmAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(ItemAlarm itemAlarm) {
                        Toast.makeText(MainActivity.this, "check", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, SetAlarmActivity.class);
                        startActivity(intent);
                    }
                });
                rvListAlarm.setAdapter(alarmAdapter);
                alarmAdapter.notifyDataSetChanged();
            }

        });
        clickFab(fabAddItem);
        turnOffMusic();
    }

    private void clickFab(FloatingActionButton fabAddItem) {
        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SetAlarmActivity.class);
                startActivity(intent);
            }
        });
    }

    public String receiveMessage(Intent intent, String keyWord) {
        String timeSet = intent.getStringExtra(keyWord);
        Log.d("check", "addAlarmItem: " + timeSet);
        return timeSet;
    }

    public void addAlarmItem(Intent intent, String keyWord, AlarmViewModel alarmViewModel) {
        String timeSet = receiveMessage(intent, keyWord);
        Boolean isChecked = getIntent().getBooleanExtra("IS_ACTIVE", false);
        if (timeSet != null) {
            ItemAlarm newItem = new ItemAlarm(timeSet, isChecked);
            alarmViewModel.addAlarm(newItem);
        }
    }

    public void turnOffMusic(){

        Log.d("check", "turnOffMusic: " + getIntent().getStringExtra("STOP?"));
        if(getIntent().hasExtra("yes")){
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.stop();
        }
    }


}