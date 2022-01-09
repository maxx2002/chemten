package com.example.chemten.view.LessonView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chemten.R;

import java.util.List;

import com.example.chemten.model.Lessons;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.CardViewViewHolder> {
    private Context context;
    private List<Lessons.Sublesson> lessonsList;
    private int counter = 1;

    public LessonAdapter(Context context){
        this.context = context;
    }
    public List<Lessons.Sublesson> getSublessonsList(){
        return lessonsList;
    }
    public void setLessonsList (List<Lessons.Sublesson> lessonsList){
        this.lessonsList = lessonsList;
    }

    @NonNull
    @Override
    public LessonAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.course_card, parent, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonAdapter.CardViewViewHolder holder, int position) {
        final Lessons.Sublesson results = getSublessonsList().get(position);
        holder.lesson_topic.setHtml(results.getSublesson_description(), new HtmlHttpImageGetter(holder.lesson_topic));
        holder.lesson_level.setText(results.getSublesson_topic());
        holder.lesson_icon.setText(String.valueOf(counter));
        counter++;
        holder.cardView.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putInt("sublesson_id", results.getId());
            bundle.putString("sublesson_topic", results.getSublesson_topic());
            bundle.putString("sublesson_desc", results.getSublesson_description());
            bundle.putString("sublesson_image", results.getSublesson_image());
            Navigation.findNavController(view).navigate(R.id.action_lessonFragment_to_sublessonFragment, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return lessonsList.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        HtmlTextView lesson_topic;
        TextView lesson_level, lesson_icon;
        CardView cardView;
        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            lesson_topic = itemView.findViewById(R.id.text_topic_cv_lesson);
            lesson_level = itemView.findViewById(R.id.text_level_cv_lesson);
            lesson_icon = itemView.findViewById(R.id.text_icon_cv_lesson);
            cardView = itemView.findViewById(R.id.cv_layout_lesson);
        }
    }
}
