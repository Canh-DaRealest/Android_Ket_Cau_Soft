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

import com.example.android_ket_cau_soft.R;
import com.example.android_ket_cau_soft.api.response.họmefrgres.notification.NotifiData;
import com.example.android_ket_cau_soft.model.NewsData;

import java.util.List;

public class HotNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List newsDataList;
    private MutableLiveData<Object> NewsliveData = new MutableLiveData<>();
    private int viewType;
    public static final int VIEWPAGER_TYPE = 1;
    public static final int RECYCLER_TYPE = 2;


    public MutableLiveData<Object> getLiveData() {
        return NewsliveData;
    }

    public void setType(int type) {
        this.viewType = type;
    }

    public HotNewsAdapter(Context context, List newsDataList) {
        this.context = context;
        this.newsDataList = newsDataList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEWPAGER_TYPE) {
            return new ViewPagerHolder(LayoutInflater.from(context).inflate(R.layout.item_news, parent, false));
        } else if (viewType == RECYCLER_TYPE) {

            return new RecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news_notification, parent, false));
        }

        return new ViewPagerHolder(LayoutInflater.from(context).inflate(R.layout.item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder.getItemViewType() == VIEWPAGER_TYPE) {
            List<NewsData> mDataList = (List<NewsData>) newsDataList;
            NewsData data = mDataList.get(position);

            String time = handleTime(data.getTime());

            ViewPagerHolder viewPagerHolder = (ViewPagerHolder) holder;

            viewPagerHolder.name.setText(data.getName());

            viewPagerHolder.time.setText(time);

            viewPagerHolder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setClickItem(data);
                }
            });


        } else if (holder.getItemViewType() == RECYCLER_TYPE) {
            List<NotifiData> mDataList = (List<NotifiData>) newsDataList;
            NotifiData data = mDataList.get(position);


            String time = handleTime(data.getTime());

            RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) holder;

            recyclerViewHolder.name.setText(data.getName());

            recyclerViewHolder.time.setText(time);
            recyclerViewHolder.layout.setBackgroundColor(data.getReaded() ? context.getColor(R.color.white) : context.getColor(R.color.color_hot_news));
            recyclerViewHolder.bell.setImageResource(data.getReaded() ? R.drawable.ic_bell_readed : R.drawable.ic_bell_new);
            recyclerViewHolder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    data.setReaded(true);
                    setClickItem(data);
                    notifyItemRangeChanged(0, mDataList.size());
                }
            });

        }

    }

    private String handleTime(String time) {
        String newTime = getTime(time);
        String gio = "";
        String giomin = "";
//set time
        for (int i = 0; i < newTime.length(); i++) {
            char character = newTime.charAt(i);
            if (character == '-') {

                gio = giomin + gio;
                gio = "/" + gio;
                giomin = "";
            } else if (character == 'T') {
                gio = "-" + giomin + gio;
                giomin = "";
            } else {
                giomin += character;

            }
        }
        gio = giomin + gio;
        return gio;
    }


    private void setClickItem(Object dataItem) {

        NewsliveData.postValue(dataItem);
        notifyItemRangeChanged(0, newsDataList.size());
    }

    @Override
    public int getItemViewType(int position) {

        return this.viewType;


    }

    @Override
    public int getItemCount() {
        Log.e("TAG", "getItemCount: aaaaaa" + newsDataList.size());
        if (newsDataList != null) {
            return newsDataList.size();
        }
        return 0;

    }

    public class ViewPagerHolder extends RecyclerView.ViewHolder {
        CardView layout;
        TextView name;
        TextView time;

        public ViewPagerHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.item_child_hotnews);
            name = itemView.findViewById(R.id.tv_news_name);
            time = itemView.findViewById(R.id.tv_new_time);
        }
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        TextView name;
        TextView time;
        ImageView bell;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.cl_contraint_notification);
            name = itemView.findViewById(R.id.tv_news_notification);
            time = itemView.findViewById(R.id.tv_notification_date_time);
            bell = itemView.findViewById(R.id.iv_bell);
        }
    }


    private String getTime(String time) {

        String gio = "";
        for (int i = 0; i < time.length(); i++) {
            char character = time.charAt(i);
            if (character == '.') {
                Log.e("TAG", "getTime: " + character);
                return gio;
            } else {
                gio += character;
                Log.e("TAG", "getTime: " + character);
            }
        }
        return gio;
    }
}
