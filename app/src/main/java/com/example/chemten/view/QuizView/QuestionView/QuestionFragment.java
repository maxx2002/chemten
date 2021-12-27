package com.example.chemten.view.QuizView.QuestionView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chemten.R;
import com.example.chemten.helper.SharedPreferenceHelper;
import com.example.chemten.model.Exercises;
import com.example.chemten.view.QuizView.StartQuizViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment {
    TextView text_choice1, text_choice2, text_choice3, text_choice4, text_soal, text_totalpertanyaan;
    CardView btn_choice1, btn_choice2, btn_choice3, btn_choice4;
    List<Exercises.Question> questionList = new ArrayList<>();

    private int currentquestion = 0;
    private int score = 0;
    private Exercises exercises;
    private StartQuizViewModel startQuizViewModel;
    private SharedPreferenceHelper helper;
    private final static String TAG = "QuestionFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuestionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuestionFragment newInstance(String param1, String param2) {
        QuestionFragment fragment = new QuestionFragment();
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
        return inflater.inflate(R.layout.fragment_question, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        text_soal = view.findViewById(R.id.text_soal_question_fragment);
        text_totalpertanyaan = view.findViewById(R.id.text_totalpertanyaan_question_fragment);
        text_choice1 = view.findViewById(R.id.text_choice1_question_fragment);
        text_choice2 = view.findViewById(R.id.text_choice2_question_fragment);
        text_choice3 = view.findViewById(R.id.text_choice3_question_fragment);
        text_choice4 = view.findViewById(R.id.text_choice4_question_fragment);
        btn_choice1 = view.findViewById(R.id.btn_choice1_question_fragment);
        btn_choice2 = view.findViewById(R.id.btn_choice2_question_fragment);
        btn_choice3 = view.findViewById(R.id.btn_choice3_question_fragment);
        btn_choice4 = view.findViewById(R.id.btn_choice4_question_fragment);

        helper = SharedPreferenceHelper.getInstance(requireActivity());
        startQuizViewModel = new ViewModelProvider(getActivity()).get(StartQuizViewModel.class);
        startQuizViewModel.init(helper.getAccessToken());
        startQuizViewModel.getExerciseDetail(getArguments().getInt("exercise_id"));
        startQuizViewModel.getResultExerciseDetail().observe(getActivity(), showLessonsDetail);

        btn_choice1.setOnClickListener(view1 -> {
            if(text_choice1.getText().equals(questionList.get(currentquestion).getCorrectanswer())){
                score += 100;
            }
            Log.d(TAG, "User Choose: A");
            currentquestion++;
            if(currentquestion < questionList.size()) {
                inisialisasiSoal();
            }else{
                munculkanscore();
            }
        });
        btn_choice2.setOnClickListener(view12 -> {
            if(text_choice2.getText().equals(questionList.get(currentquestion).getCorrectanswer())){
                score += 100;
            }
            Log.d(TAG, "User Choose: B");
            currentquestion++;
            if(currentquestion < questionList.size()) {
                inisialisasiSoal();
            }else{
                munculkanscore();
            }
        });
        btn_choice3.setOnClickListener(view13 -> {
            if(text_choice3.getText().equals(questionList.get(currentquestion).getCorrectanswer())){
                score += 100;
            }
            Log.d(TAG, "User Choose: C");
            currentquestion++;
            if(currentquestion < questionList.size()) {
                inisialisasiSoal();
            }else{
                munculkanscore();
            }
        });
        btn_choice4.setOnClickListener(view14 -> {
            if(text_choice4.getText().equals(questionList.get(currentquestion).getCorrectanswer())){
                score += 100;
            }
            Log.d(TAG, "User Choose: D");
            currentquestion++;
            if(currentquestion < questionList.size()) {
                inisialisasiSoal();
            }else{
                munculkanscore();
            }
        });
    }
    private Observer<Exercises> showLessonsDetail = new Observer<Exercises>() {
        @Override
        public void onChanged(Exercises exercises) {
            questionList = exercises.getQuestion();

            inisialisasiSoal();
        }
    };

    private void inisialisasiSoal(){
        text_soal.setText(questionList.get(currentquestion).getQuestion_description());
        text_totalpertanyaan.setText("Pertanyaan "+(currentquestion+1)+" dari "+questionList.size());
        text_choice1.setText(questionList.get(currentquestion).getQchoice1());
        text_choice2.setText(questionList.get(currentquestion).getQchoice2());
        text_choice3.setText(questionList.get(currentquestion).getQchoice3());
        text_choice4.setText(questionList.get(currentquestion).getQchoice4());
    }

    private void munculkanscore(){
        text_soal.setText(String.valueOf(score));
        text_totalpertanyaan.setText("Congratulations");
        btn_choice1.setVisibility(View.INVISIBLE);
        btn_choice2.setVisibility(View.INVISIBLE);
        btn_choice3.setVisibility(View.INVISIBLE);
        btn_choice4.setVisibility(View.INVISIBLE);
    }
}