package com.example.android_ket_cau_soft.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android_ket_cau_soft.R;
import com.example.android_ket_cau_soft.model.CourseData;

import java.util.List;

public class CourseAdapterRecyclerView extends RecyclerView.Adapter<CourseAdapterRecyclerView.ViewHolder> {
    private Context context;
    private List<CourseData> newsDataList;
    private MutableLiveData<CourseData> liveData = new MutableLiveData<>();

    public MutableLiveData<CourseData> getLiveData() {
        return liveData;
    }

    public CourseAdapterRecyclerView(Context context, List<CourseData> newsDataList) {
        this.context = context;
        this.newsDataList = newsDataList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_course_recyclerview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CourseData data = newsDataList.get(position);

        holder.courseName.setText(data.getName());

        Glide.with(context).load(data.getImageLink()).into(holder.courseImage);
        holder.courseRating.setText(data.getRated()+"");
        holder.courseInfor.setText(data.getInfo());
        holder.courseInfor.setTextColor(data.getInfo().equals("Free") ? context.getColor( R.color.color_orange) : context.getColor(R.color.red));
        holder.courseMentor.setText(data.getMentor());

        holder.cardView.setTag(data.getId());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setClickItem(data);
            }
        });

    }

    private void setClickItem(CourseData data) {
        liveData.postValue(data);
    }


    @Override
    public int getItemCount() {
        Log.e("TAG", "getItemCount: aaaaaa" + newsDataList.size());
        return newsDataList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        TextView courseName;
        TextView courseMentor;
        ImageView courseImage;
        TextView courseRating;
        TextView courseInfor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cv_course_card_detail);
            courseName = itemView.findViewById(R.id.tv_course_name_detail);
            courseMentor = itemView.findViewById(R.id.tv_course_mentor_detail);
            courseImage = itemView.findViewById(R.id.iv_course_image_detail);
            courseRating = itemView.findViewById(R.id.tv_course_rating_detail);
            courseInfor = itemView.findViewById(R.id.tv_course_info_detail);

        }
    }

}
