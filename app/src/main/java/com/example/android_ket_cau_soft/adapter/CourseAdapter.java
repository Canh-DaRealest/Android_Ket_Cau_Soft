package com.example.android_ket_cau_soft.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android_ket_cau_soft.R;
import com.example.android_ket_cau_soft.model.CourseData;


import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<CourseData> newsDataList;
    private MutableLiveData<String> liveData = new MutableLiveData<>();

    public MutableLiveData<String> getLiveData() {
        return liveData;
    }

    private int rvType = TYPE_AUTO_SLIDE;

    public int getRvType() {
        return rvType;
    }

    public void setRvType(int rvType) {
        this.rvType = rvType;
    }

    public static final int TYPE_AUTO_SLIDE = 0;
    public static final int TYPE_GRID = 1;


    public CourseAdapter(Context context, List<CourseData> newsDataList) {
        this.context = context;
        this.newsDataList = newsDataList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (rvType == TYPE_AUTO_SLIDE) {
            return new AutoSlideVieHolder(LayoutInflater.from(context).inflate(R.layout.item_course, parent, false));

        } else if (rvType == TYPE_GRID) {
            return new GridViewHolder(LayoutInflater.from(context).inflate(R.layout.item_course_recyclerview, parent, false));

        }
        return new AutoSlideVieHolder(LayoutInflater.from(context).inflate(R.layout.item_course, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CourseData data = newsDataList.get(position);

        if (rvType == TYPE_AUTO_SLIDE) {
            AutoSlideVieHolder autoSlideVieHolder = (AutoSlideVieHolder) holder;

            autoSlideVieHolder.courseName.setText(data.getName());

            Glide.with(context).load(data.getImageLink()).into(autoSlideVieHolder.courseImage);
            autoSlideVieHolder.courseRating.setText(data.getRated() + "");
            autoSlideVieHolder.courseInfor.setText(data.getInfo());
            autoSlideVieHolder.courseInfor.setTextColor(data.getInfo().equals("Free") ? context.getColor(R.color.dark_green) : context.getColor(R.color.red));
            autoSlideVieHolder.courseMentor.setText(data.getMentor());

            autoSlideVieHolder.cardView.setTag(data.getId() + "");
            autoSlideVieHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setClickItem((String) autoSlideVieHolder.cardView.getTag());
                }
            });
        } else if (rvType == TYPE_GRID) {

            GridViewHolder gridViewHolder = (GridViewHolder) holder;
            gridViewHolder.courseName.setText(data.getName());

            Glide.with(context).load(data.getImageLink()).into(gridViewHolder.courseImage);
            gridViewHolder.courseRating.setText(data.getRated() + "");
            gridViewHolder.courseInfor.setText(data.getInfo());
            gridViewHolder.courseInfor.setTextColor(data.getInfo().equals("Free") ? context.getColor(R.color.color_orange) : context.getColor(R.color.red));
            gridViewHolder.courseMentor.setText(data.getMentor());

            gridViewHolder.cardView.setTag(data.getId() + "");
            gridViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setClickItem((String) gridViewHolder.cardView.getTag());
                }
            });


        }
    }


    private void setClickItem(String data) {
        liveData.postValue(data);
        notifyItemRangeChanged(0, newsDataList.size() - 1);
    }

    @Override
    public int getItemViewType(int position) {
        return rvType;
    }

    @Override
    public int getItemCount() {
        Log.e("TAG", "getItemCount: aaaaaa" + newsDataList.size());
        return newsDataList.size();
    }

    public class AutoSlideVieHolder
            extends RecyclerView.ViewHolder {
        CardView cardView;

        TextView courseName;
        TextView courseMentor;
        ImageView courseImage;
        TextView courseRating;
        TextView courseInfor;

        public AutoSlideVieHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cv_course_card);
            courseName = itemView.findViewById(R.id.tv_course_name);
            courseMentor = itemView.findViewById(R.id.tv_course_mentor);
            courseImage = itemView.findViewById(R.id.iv_course_image);
            courseRating = itemView.findViewById(R.id.tv_course_rating);
            courseInfor = itemView.findViewById(R.id.tv_course_info);

        }
    }

    private String getTime(String time) {

        String gio = "";
        for (int i = 0; i < time.length(); i++) {
            char character = time.charAt(i);
            if (character == 'T') {
                Log.e("TAG", "getTime: " + character);
                return gio;
            } else {
                gio += character;
                Log.e("TAG", "getTime: " + character);
            }
        }
        return gio;
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        TextView courseName;
        TextView courseMentor;
        ImageView courseImage;
        TextView courseRating;
        TextView courseInfor;

        public GridViewHolder(@NonNull View itemView) {
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
