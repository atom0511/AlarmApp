package trungatom.tqt.alarmapp.model;

public class ItemAlarm {
    String timeSet;
    Boolean isChecked;

    public ItemAlarm(String timeSet, Boolean isChecked) {
        this.timeSet = timeSet;
        this.isChecked = isChecked;
    }

    public String getTimeSet() {
        return timeSet;
    }

    public void setTimeSet(String timeSet) {
        this.timeSet = timeSet;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }
}
