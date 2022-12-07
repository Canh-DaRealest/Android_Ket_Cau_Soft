package com.example.android_ket_cau_soft.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_ket_cau_soft.R;
import com.example.android_ket_cau_soft.model.ItemTopic;

import java.util.List;

public class CourseTopicAdapter extends RecyclerView.Adapter<CourseTopicAdapter.TopicViewHolder> {
    private final Context context;
    private final List<ItemTopic> itemTopicsList;
    private MutableLiveData<Integer> itData = new MutableLiveData<>();

    public MutableLiveData<Integer> getItData() {
        return itData;
    }

    public CourseTopicAdapter(Context context, List<ItemTopic> itemTopicsList) {
        this.context = context;
        this.itemTopicsList = itemTopicsList;
    }


    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TopicViewHolder(LayoutInflater.from(context).inflate(R.layout.item_course_topic, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, int position) {
        ItemTopic itemTopic = itemTopicsList.get(position);
        holder.tvTopic.setText(itemTopic.getName());
        holder.tvTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ckickItemView(itemTopic.getId());
            }
        });


    }

    private void ckickItemView(Integer id) {
        itData.postValue(id);
    }


    @Override
    public int getItemCount() {

        if (itemTopicsList != null) {
            return itemTopicsList.size();
        }
        return 0;
    }

    public class TopicViewHolder extends RecyclerView.ViewHolder {
        TextView tvTopic;

        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTopic = itemView.findViewById(R.id.tv_topic);

        }
    }
}
