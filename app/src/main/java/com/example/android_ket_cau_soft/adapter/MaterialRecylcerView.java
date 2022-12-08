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
import com.example.android_ket_cau_soft.model.ObjectResult;
import com.example.android_ket_cau_soft.model.material_search.LiveLoadData;
import com.example.android_ket_cau_soft.model.material_search.LiveLoadData2;
import com.example.android_ket_cau_soft.model.material_search.LiveLoadData3;
import com.example.android_ket_cau_soft.model.material_search.NaturalData;
import com.example.android_ket_cau_soft.model.material_search.NaturalData2;
import com.example.android_ket_cau_soft.model.material_search.NaturalData3;
import com.example.android_ket_cau_soft.model.material_search.RawMaterialData;
import com.example.android_ket_cau_soft.model.material_search.RawMaterialData2;
import com.example.android_ket_cau_soft.model.material_search.RawMaterialData3;

import java.util.List;

public class MaterialRecylcerView extends RecyclerView.Adapter<MaterialRecylcerView.TopicViewHolder> {
    private final Context context;
    //de dung chung cho 3 loai data

    private final List dataList;
    private MutableLiveData<ObjectResult> listMutableLiveData2 = new MutableLiveData<>();


    public MutableLiveData<ObjectResult> getListMutableLiveData2() {
        return listMutableLiveData2;
    }

    public MaterialRecylcerView(Context context, List dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    private int dataType = 1;

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new TopicViewHolder(LayoutInflater.from(context).inflate(R.layout.item_material_text, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, int position) {

        if (dataType == 1) {
            List<LiveLoadData> liveLoadList = (List<LiveLoadData>) dataList;
            LiveLoadData itemTopic = liveLoadList.get(position);

            holder.tvItem.setText(itemTopic.getAddress_1());

            holder.tvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickItemView1(itemTopic.getAddress_1(), itemTopic.getLiveLoadData2List());
                }
            });
        } else if (dataType == 2) {
            List<LiveLoadData2> liveLoadList = (List<LiveLoadData2>) dataList;
            LiveLoadData2 itemTopic = liveLoadList.get(position);

            holder.tvItem.setText(itemTopic.getAddress_2());

            holder.tvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickItemView2(itemTopic.getAddress_2(), itemTopic.getData_2());
                }
            });
        } else if (dataType == 3) {
            List<RawMaterialData> liveLoadList = (List<RawMaterialData>) dataList;
            RawMaterialData itemTopic = liveLoadList.get(position);

            holder.tvItem.setText(itemTopic.getAddress_1());

            holder.tvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickItemView3(itemTopic.getAddress_1(), itemTopic.getLiveLoadData1List());
                }
            });
        } else if (dataType == 4) {
            List<RawMaterialData2> liveLoadList = (List<RawMaterialData2>) dataList;
            RawMaterialData2 itemTopic = liveLoadList.get(position);

            holder.tvItem.setText(itemTopic.getAddress_2());

            holder.tvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickItemView4(itemTopic.getAddress_2(), itemTopic.getData3());
                }
            });
        } else if (dataType == 5) {
            List<NaturalData> liveLoadList = (List<NaturalData>) dataList;
            NaturalData itemTopic = liveLoadList.get(position);

            holder.tvItem.setText(itemTopic.getAddress_1());

            holder.tvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickItemView5(itemTopic.getAddress_1(), itemTopic.getNaturalData1List());
                }
            });
        } else if (dataType == 6) {
            List<NaturalData2> liveLoadList = (List<NaturalData2>) dataList;
            NaturalData2 itemTopic = liveLoadList.get(position);

            holder.tvItem.setText(itemTopic.getAddress_2());

            holder.tvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickItemView6(itemTopic.getAddress_2(), itemTopic.getData3());
                }
            });
        }


    }

    private void clickItemView5(String address_2, List<NaturalData2> data3) {
        listMutableLiveData2.postValue(new ObjectResult(address_2, data3));
    }

    private void clickItemView6(String address_2, NaturalData3 data3) {
        listMutableLiveData2.postValue(new ObjectResult(address_2, data3));
    }


    private void clickItemView3(String address_1, List<RawMaterialData2> liveLoadData1List) {
        listMutableLiveData2.postValue(new ObjectResult(address_1, liveLoadData1List));
    }

    private void clickItemView4(String address_2, RawMaterialData3 data3) {
        listMutableLiveData2.postValue(new ObjectResult(address_2, data3));
    }


    private void clickItemView1(String address_1, List<LiveLoadData2> content) {
        listMutableLiveData2.postValue(new ObjectResult(address_1, content));
    }

    private void clickItemView2(String address_1, LiveLoadData3 content) {
        listMutableLiveData2.postValue(new ObjectResult(address_1, content));
    }


    @Override
    public int getItemCount() {

        if (dataList != null) {
            return dataList.size();
        }
        return 0;
    }

    public class TopicViewHolder extends RecyclerView.ViewHolder {
        TextView tvItem;

        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);

            tvItem = itemView.findViewById(R.id.tv_material_item);

        }
    }
}
