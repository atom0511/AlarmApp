package trungatom.tqt.alarmapp.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import trungatom.tqt.alarmapp.model.ItemAlarm;

public class AlarmViewModel extends ViewModel {

    private MutableLiveData<List<ItemAlarm>> listItemAlarmLiveData;
    private List<ItemAlarm> listItemAlarm;

    public AlarmViewModel() {
        listItemAlarmLiveData = new MutableLiveData<>();
        initData();
    }

    private void initData(){
        listItemAlarm = new ArrayList<>();
        //need to set data for this list
//        listItemAlarm.add(new ItemAlarm(timeSet));
        listItemAlarmLiveData.setValue(listItemAlarm);
    }

    public MutableLiveData<List<ItemAlarm>> getListItemAlarmLiveData() {
        return listItemAlarmLiveData;
    }

    public void addAlarm(ItemAlarm itemAlarm){
        listItemAlarm.add(itemAlarm);
        listItemAlarmLiveData.setValue(listItemAlarm);
    }
}
