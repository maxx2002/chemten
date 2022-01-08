package com.example.chemten.view.QuizView.QuestionView;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.chemten.R;
import com.example.chemten.helper.SharedPreferenceHelper;
import com.example.chemten.model.Exercises;
import com.example.chemten.view.Dialog.BackDialog;
import com.example.chemten.view.QuizView.StartQuizViewModel;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment {
    HtmlTextView question_soal;
    TextView btn_next, btn_prev, text_totalpertanyaan, text_congratulations, text_poin, btn_finish;
    List<Exercises.Question> questionList = new ArrayList<>();
    ImageView btn_back;
    RadioButton choice1, choice2, choice3, choice4;
    Object[] arrayjawaban;
    RadioGroup radioGroup;

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
        question_soal = view.findViewById(R.id.text_soal_question_fragment);
        text_totalpertanyaan = view.findViewById(R.id.text_totalpertanyaan_question_fragment);
        choice1 = view.findViewById(R.id.radioButton_choice1_question_fragment);
        choice2 = view.findViewById(R.id.radioButton_choice2_question_fragment);
        choice3 = view.findViewById(R.id.radioButton_choice3_question_fragment);
        choice4 = view.findViewById(R.id.radioButton_choice4_question_fragment);
        btn_back = view.findViewById(R.id.btn_back_question_fragment);
        btn_next = view.findViewById(R.id.btn_next_question_fragment);
        btn_prev = view.findViewById(R.id.btn_prev_question_fragment);
        radioGroup = view.findViewById(R.id.radioGroup_question_fragment);
        text_congratulations = view.findViewById(R.id.text_congratulations_question_fragment);
        text_poin = view.findViewById(R.id.text_poin_question_fragment);
        btn_finish = view.findViewById(R.id.btn_finish_question_fragment);

        helper = SharedPreferenceHelper.getInstance(requireActivity());
        startQuizViewModel = new ViewModelProvider(getActivity()).get(StartQuizViewModel.class);
        startQuizViewModel.init(helper.getAccessToken());
        startQuizViewModel.getExerciseDetail(getArguments().getInt("exercise_id"));
        startQuizViewModel.getResultExerciseDetail().observe(getActivity(), showLessonsDetail);

        btn_next.setOnClickListener(view16 -> {
            pilihjawaban();
            currentquestion++;
            if(currentquestion < questionList.size()) {
                inisialisasiSoal();
            }else{
                munculkanscore();
            }
        });
        btn_prev.setOnClickListener(view17 -> {
            pilihjawaban();
            currentquestion--;
            if(currentquestion < questionList.size()) {
                inisialisasiSoal();
            }else if(currentquestion == 0){
                FragmentManager fm = getActivity().getSupportFragmentManager();
                BackDialog dialog = new BackDialog();
                dialog.show(fm, "dialog");
            }
        });


        btn_back.setOnClickListener(view15 -> {
            FragmentManager fm = getActivity().getSupportFragmentManager();
            BackDialog dialog = new BackDialog();
            dialog.show(fm, "dialog");
        });

        btn_finish.setOnClickListener(view1 -> {
            getActivity().onBackPressed();
        });
    }
    private Observer<Exercises> showLessonsDetail = new Observer<Exercises>() {
        @Override
        public void onChanged(Exercises exercises) {
            questionList = exercises.getQuestion();
            arrayjawaban = new Object[questionList.size()];
            inisialisasiSoal();
        }
    };

    private void inisialisasiSoal(){
        question_soal.setHtml(questionList.get(currentquestion).getQuestion_description(), new HtmlHttpImageGetter(question_soal));
        text_totalpertanyaan.setText("Pertanyaan "+(currentquestion+1)+" dari "+questionList.size());
        choice1.setText(questionList.get(currentquestion).getQchoice1());
        choice2.setText(questionList.get(currentquestion).getQchoice2());
        choice3.setText(questionList.get(currentquestion).getQchoice3());
        choice4.setText(questionList.get(currentquestion).getQchoice4());
        if(arrayjawaban[currentquestion] != null){
            if(arrayjawaban[currentquestion].equals(questionList.get(currentquestion).getQchoice1())){
                choice1.setChecked(true);
            }else if(arrayjawaban[currentquestion].equals(questionList.get(currentquestion).getQchoice2())){
                choice2.setChecked(true);
            }else if(arrayjawaban[currentquestion].equals(questionList.get(currentquestion).getQchoice3())){
                choice3.setChecked(true);
            }else if(arrayjawaban[currentquestion].equals(questionList.get(currentquestion).getQchoice4())){
                choice4.setChecked(true);
            }
        }
    }

    private void munculkanscore(){
        checkscore();
        question_soal.setVisibility(View.GONE);;
        text_totalpertanyaan.setVisibility(View.GONE);
        radioGroup.setVisibility(View.GONE);
        btn_next.setVisibility(View.GONE);
        btn_prev.setVisibility(View.GONE);
        text_congratulations.setVisibility(View.VISIBLE);
        text_poin.setVisibility(View.VISIBLE);
        btn_finish.setVisibility(View.VISIBLE);

    }
    private void pilihjawaban(){
        if(choice1.isChecked()){
            Log.d(TAG, "User Choose: A");
            arrayjawaban[currentquestion] = questionList.get(currentquestion).getQchoice1();
        }else if(choice2.isChecked()){
            Log.d(TAG, "User Choose: B");
            arrayjawaban[currentquestion] = questionList.get(currentquestion).getQchoice2();
        }else if(choice3.isChecked()){
            Log.d(TAG, "User Choose: C");
            arrayjawaban[currentquestion] = questionList.get(currentquestion).getQchoice3();
        }else if(choice4.isChecked()){
            Log.d(TAG, "User Choose: D");
            arrayjawaban[currentquestion] = questionList.get(currentquestion).getQchoice4();
        }
        radioGroup.clearCheck();
    }
    private void checkscore(){
        for(int i = 0; i<questionList.size(); i++){
            if(arrayjawaban[i] != null) {
                if (arrayjawaban[i].equals(questionList.get(i).getCorrectanswer())) {
                    score += 10;
                }
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back button's click listener
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    BackDialog dialog = new BackDialog();
                    dialog.show(fm, "dialog");
                    return true;
                }
                return false;
            }
        });

    }
}