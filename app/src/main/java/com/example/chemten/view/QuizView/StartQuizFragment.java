package com.example.chemten.view.QuizView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chemten.R;
import com.example.chemten.helper.SharedPreferenceHelper;
import com.example.chemten.model.Exercises;
import com.example.chemten.model.Lessons;
import com.example.chemten.view.LessonView.LessonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StartQuizFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StartQuizFragment extends Fragment {

    TextView exercise_topic, exercise_desc, exercise_level, btn_start;

    private StartQuizViewModel startQuizViewModel;
    private SharedPreferenceHelper helper;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StartQuizFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StartQuizFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StartQuizFragment newInstance(String param1, String param2) {
        StartQuizFragment fragment = new StartQuizFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start_quiz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        exercise_topic = view.findViewById(R.id.exercise_topic_startquiz_fragment);
        exercise_level = view.findViewById(R.id.exercise_level_startquiz_fragment);
        exercise_desc = view.findViewById(R.id.exercise_desc_startquiz_fragment);
        btn_start = view.findViewById(R.id.btn_start_startquiz_fragment);

        helper = SharedPreferenceHelper.getInstance(requireActivity());
        startQuizViewModel = new ViewModelProvider(getActivity()).get(StartQuizViewModel.class);
        startQuizViewModel.init(helper.getAccessToken());
        String exercise_levels = getArguments().getString("exercise_level");
        int exercise_id = Integer.parseInt(exercise_levels.substring(6));
        startQuizViewModel.getExerciseDetail(exercise_id);
        startQuizViewModel.getResultExerciseDetail().observe(getActivity(), showLessonsDetail);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("exercise_id", exercise_id);
                Navigation.findNavController(view).navigate(R.id.action_startQuizFragment_to_questionFragment, bundle);
            }
        });
    }

    List<Exercises.Exercise> exerciseList = new ArrayList<>();
    List<Exercises.Question> questionList = new ArrayList<>();

    private Observer<Exercises> showLessonsDetail = new Observer<Exercises>() {
        @Override
        public void onChanged(Exercises exercises) {
            exerciseList = exercises.getExercise();
            questionList = exercises.getQuestion();

            exercise_topic.setText(exerciseList.get(0).getExercise_topic());
            exercise_level.setText(exerciseList.get(0).getExercise_level());
            exercise_desc.setText(exerciseList.get(0).getExercise_description());
        }
    };
}