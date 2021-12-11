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

import com.example.chemten.model.Lesson;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.CardViewViewHolder> {
    private Context context;
    private List<Lesson.Lessons> lessonsList;

    public LessonAdapter(Context context){
        this.context = context;
    }
    public List<Lesson.Lessons> getLessonsList(){
        return lessonsList;
    }
    public void setLessonsList (List<Lesson.Lessons> lessons){
        this.lessonsList = lessonsList;
    }

    @NonNull
    @Override
    public LessonAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cv_lesson, parent, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonAdapter.CardViewViewHolder holder, int position) {
        final Lesson.Lessons results = getLessonsList().get(position);
        holder.lesson_id.setText(results.getLesson_id());
        holder.lesson_topic.setText(results.getLesson_topic());
        holder.lesson_level.setText(results.getLesson_level());
        holder.lesson_image.setText(results.getLesson_image());
        holder.lesson_description.setText(results.getLesson_description());
        holder.cardView.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("lesson_id", results.getLesson_id(). toLowerCase());
            Navigation.findNavController(view).navigate(R.id.action_lessonFragment2_to_detailLessonFragment, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return lessonsList.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView lesson_id, lesson_topic, lesson_level, lesson_image, lesson_description;
        CardView cardView;
        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            lesson_id = itemView.findViewById(R.id.lesson_id);
            lesson_topic = itemView.findViewById(R.id.lesson_topic);
            lesson_level = itemView.findViewById(R.id.lesson_level);
            lesson_image = itemView.findViewById(R.id.lesson_image);
            lesson_description = itemView.findViewById(R.id.lesson_description);
            cardView = itemView.findViewById(R.id.cv_layout_lesson);
        }
    }
}
