package com.example.android_ket_cau_soft.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_ket_cau_soft.R;
import com.example.android_ket_cau_soft.model.exam.QuestionData;

import java.util.List;

public class FalseAnswerAdapter extends RecyclerView.Adapter<FalseAnswerAdapter.FalseAnswerHolder> {
    private final Context context;
    private final List<QuestionData> itemLessonList;

    public FalseAnswerAdapter(Context context, List<QuestionData> itemTopicsList) {
        this.context = context;
        this.itemLessonList = itemTopicsList;
    }


    @NonNull
    @Override
    public FalseAnswerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FalseAnswerHolder(LayoutInflater.from(context).inflate(R.layout.item_false_answer, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FalseAnswerHolder holder, int position) {
        QuestionData falseResult = itemLessonList.get(position);
        holder.tvQsNumber.setText("Câu số "+falseResult.getQuestionId());
        holder.tvqsContent.setText(falseResult.getQuestion());

        holder.tvTrueAnswer.setText(falseResult.getAnswerList().get(falseResult.getTrueCase() - 1));


    }

    @Override
    public int getItemCount() {

        if (itemLessonList != null) {
            return itemLessonList.size();
        }
        return 0;
    }

    public class FalseAnswerHolder extends RecyclerView.ViewHolder {
        TextView tvQsNumber;
        TextView tvqsContent;
        TextView tvTrueAnswer;

        public FalseAnswerHolder(@NonNull View itemView) {
            super(itemView);

            tvQsNumber = itemView.findViewById(R.id.tv_false_question_number);
            tvqsContent = itemView.findViewById(R.id.tv_false_question_content);
            tvTrueAnswer = itemView.findViewById(R.id.tv_false_question_answer_true);
        }
    }
}
