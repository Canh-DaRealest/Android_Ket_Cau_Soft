package com.example.android_ket_cau_soft.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android_ket_cau_soft.R;
import com.example.android_ket_cau_soft.model.lesson.ItemLesson;
import com.example.android_ket_cau_soft.model.lesson.ItemLessonPath;

import java.util.List;

public class ItemLessonPathAdapter extends RecyclerView.Adapter<ItemLessonPathAdapter.ItemLessonViewHolder> {
    private final Context context;
    private final List<ItemLessonPath> itemLessonList;
    private MutableLiveData<ItemLesson> itemPathLiveData = new MutableLiveData<>();

    public MutableLiveData<ItemLesson> getItemPathLiveData() {
        return itemPathLiveData;
    }

    public ItemLessonPathAdapter(Context context, List<ItemLessonPath> itemTopicsList) {
        this.context = context;
        this.itemLessonList = itemTopicsList;
    }


    @NonNull
    @Override
    public ItemLessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemLessonViewHolder(LayoutInflater.from(context).inflate(R.layout.item_lesson_path, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemLessonViewHolder holder, int position) {
        ItemLessonPath itemTopic = itemLessonList.get(position);


        holder.tvItemLessonPathName.setText(itemTopic.getPartName());

        ItemLessonAdapter itemLessonAdapter = new ItemLessonAdapter(context, itemTopic.getLessonItem());

        holder.rvItemLessonPath.setAdapter(itemLessonAdapter);
        holder.rvItemLessonPath.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        holder.rvItemLessonPath.addItemDecoration(itemDecoration);
        itemLessonAdapter.getItData().observe((LifecycleOwner) context, new Observer<ItemLesson>() {
            @Override
            public void onChanged(ItemLesson itemLesson) {
                ckickItemView(itemLesson);
            }
        });

    }

    private void ckickItemView(ItemLesson itemLesson) {
        itemPathLiveData.postValue(itemLesson);
    }


    @Override
    public int getItemCount() {

        if (itemLessonList != null) {
            return itemLessonList.size();
        }
        return 0;
    }

    public class ItemLessonViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemLessonPathName;
        RecyclerView rvItemLessonPath;

        public ItemLessonViewHolder(@NonNull View itemView) {
            super(itemView);

            tvItemLessonPathName = itemView.findViewById(R.id.tv_item_lesson_path_name);
            rvItemLessonPath = itemView.findViewById(R.id.rv_item_lesson);

        }
    }
}
