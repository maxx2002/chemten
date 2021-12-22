package com.example.chemten.view.LessonView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chemten.R;
import com.example.chemten.helper.SharedPreferenceHelper;
import com.example.chemten.model.Lessons;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LessonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LessonFragment extends Fragment {

    private LessonViewModel lessonViewModel;
    private LessonAdapter lessonAdapter;
    private RecyclerView recyclerView;
    private SharedPreferenceHelper helper;
    private TextView lesson_topic;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LessonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LessonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LessonFragment newInstance(String param1, String param2) {
        LessonFragment fragment = new LessonFragment();
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
        return inflater.inflate(R.layout.fragment_lesson, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.rv_sublesson_lesson_fragment);
        lesson_topic = view.findViewById(R.id.text_lesson_topic_lesson_fragment);
        helper = SharedPreferenceHelper.getInstance(requireActivity());
        lessonViewModel = new ViewModelProvider(getActivity()).get(LessonViewModel.class);
        lessonViewModel.init(helper.getAccessToken());
        int code = getArguments().getInt("lesson_id");
        String lesson_topic_bundle = getArguments().getString("lesson_topic");
        lesson_topic.setText(lesson_topic_bundle);
        lessonViewModel.getLessonDetail(code);
        lessonViewModel.getResultLessonDetail().observe(getActivity(), showLessonsDetail);


    }
    List<Lessons.Sublesson> results = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;

    private Observer<Lessons> showLessonsDetail = new Observer<Lessons>() {
        @Override
        public void onChanged(Lessons lesson) {
            results = lesson.getSublesson();
            linearLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(linearLayoutManager);
            lessonAdapter = new LessonAdapter(getActivity());
            lessonAdapter.setLessonsList(results);
            recyclerView.setAdapter(lessonAdapter);
        }
    };

    @Override
    public void onDetach(){
        super.onDetach();
        getActivity().getViewModelStore().clear();
    }
}