package trungatom.tqt.alarmapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import trungatom.tqt.alarmapp.OnItemClickListener;
import trungatom.tqt.alarmapp.R;
import trungatom.tqt.alarmapp.model.ItemAlarm;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> {

    private List<ItemAlarm> listItemAlarm;

    public AlarmAdapter(List<ItemAlarm> listItemAlarm) {
        this.listItemAlarm = listItemAlarm;
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public AlarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alram_design, parent, false);
        return new AlarmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmViewHolder holder, int position) {
        ItemAlarm itemAlarm = listItemAlarm.get(position);
        if (itemAlarm == null) {
            return;
        }
        holder.tvTimeSet.setText(itemAlarm.getTimeSet());
        holder.btnSwitch.setChecked(itemAlarm.getChecked());
    }

    @Override
    public int getItemCount() {
        if (listItemAlarm != null) {
            return listItemAlarm.size();
        }
        return 0;
    }



    public class AlarmViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTimeSet;
        public SwitchCompat btnSwitch;
        public AlarmViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (listener != null && pos != RecyclerView.NO_POSITION) {
                        listener.onItemClick(listItemAlarm.get(pos));
                    }
                }
            });
            tvTimeSet = itemView.findViewById(R.id.tv_time_set);
            btnSwitch = itemView.findViewById(R.id.btn_switch);
        }
    }
}
