package com.example.android_ket_cau_soft.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_ket_cau_soft.R;
import com.example.android_ket_cau_soft.model.lesson.ItemLesson;

import java.util.List;

public class ItemLessonAdapter extends RecyclerView.Adapter<ItemLessonAdapter.ItemLessonViewHolder> {
    private final Context context;
    private final List<ItemLesson> itemLessonList;
    private MutableLiveData<ItemLesson> itData = new MutableLiveData<>();

    public MutableLiveData<ItemLesson> getItData() {
        return itData;
    }

    public ItemLessonAdapter(Context context, List<ItemLesson> itemTopicsList) {
        this.context = context;
        this.itemLessonList = itemTopicsList;
    }


    @NonNull
    @Override
    public ItemLessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemLessonViewHolder(LayoutInflater.from(context).inflate(R.layout.item_lesson, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemLessonViewHolder holder, int position) {
        ItemLesson itemTopic = itemLessonList.get(position);
        holder.tvItemLessonName.setText(itemTopic.getName());
        holder.lnItemLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ckickItemView(itemTopic);
            }
        });


    }

    private void ckickItemView(ItemLesson item) {
        itData.postValue(item);
    }


    @Override
    public int getItemCount() {

        if (itemLessonList != null) {
            return itemLessonList.size();
        }
        return 0;
    }

    public class ItemLessonViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemLessonName;
        LinearLayout lnItemLesson;

        public ItemLessonViewHolder(@NonNull View itemView) {
            super(itemView);

            tvItemLessonName = itemView.findViewById(R.id.tv_item_lesson_name);
            lnItemLesson = itemView.findViewById(R.id.ln_item_lesson);

        }
    }
}
