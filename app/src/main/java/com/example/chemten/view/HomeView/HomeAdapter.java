package com.example.chemten.view.HomeView;

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
import com.example.chemten.model.DataUser;
import com.example.chemten.model.Lessons;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.CardViewViewHolder>{
    private Context context;
    private List<Lessons.Lesson> lessonsList;
    private int user_id;

    public HomeAdapter(Context context){
        this.context = context;
    }
    public List<Lessons.Lesson> getLessonsList(){
        return lessonsList;
    }
    public void setLessonsList (List<Lessons.Lesson> lessonsList){
        this.lessonsList = lessonsList;
    }
    public int getUserID(){
        return user_id;
    }
    public void setUserID (int user_id){
        this.user_id = user_id;
    }

    @NonNull
    @Override
    public HomeAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.course_card, parent, false);
        return new HomeAdapter.CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.CardViewViewHolder holder, int position) {
        final Lessons.Lesson results = getLessonsList().get(position);
        holder.lesson_topic.setText(results.getLesson_topic());
        holder.lesson_level.setText(results.getLesson_level());
        holder.lesson_icon.setText(String.valueOf(results.getId()));
        holder.cardView.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putInt("lesson_id", results.getId());
            bundle.putString("lesson_topic", results.getLesson_topic());
            bundle.putString("lesson_level", results.getLesson_level());
            bundle.putInt("user_id", user_id);
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_lessonFragment, bundle);
        });
    }

    @Override
    public int getItemCount() {
        if(lessonsList == null){
            return 0;
        }else{
            return lessonsList.size();
        }

    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView lesson_topic, lesson_level, lesson_icon;
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
