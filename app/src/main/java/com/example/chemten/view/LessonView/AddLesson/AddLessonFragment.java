//package com.example.chemten.view.LessonView.AddLesson;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.lifecycle.ViewModelProvider;
//import androidx.navigation.NavDirections;
//import androidx.navigation.Navigation;
//
//import com.example.chemten.R;
//import com.example.chemten.view.HomeView.HomeFragmentDirections;
//import com.google.android.material.textfield.TextInputLayout;
//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link AddLessonFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
//public class AddLessonFragment extends Fragment {
//
//    ImageView home_image_profile;
//    EditText lessonCode, lessonTopic, lessonLevel, lessonDescription;
//    Button btn_submit_lesson;
//
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public AddLessonFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment LoginFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static AddLessonFragment newInstance(String param1, String param2) {
//        AddLessonFragment fragment = new AddLessonFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false);
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        home_image_profile = view.findViewById(R.id.home_image_profile);
//        home_image_profile.setOnClickListener(view1 -> {
//            NavDirections action = HomeFragmentDirections.actionHomeFragmentToProfileFragment();
//            Navigation.findNavController(view1).navigate(action);
//        });
//        lessonCode = view.findViewById(R.id.lesson_code_input);
//        lessonTopic = view.findViewById(R.id.lesson_topic_input);
//        lessonLevel = view.findViewById(R.id.lesson_level_input);
//        lessonDescription = view.findViewById(R.id.lesson_desc_input);
//        btn_submit_lesson = view.findViewById(R.id.btn_submit_lesson);
//
//        helper = SharedPreferenceHelper.getInstance(requireActivity());
//        addCourseViewModel = new ViewModelProvider(getActivity()).get(AddCourseViewModel.class);
//        addCourseViewModel.init(helper.getAccessToken());
//
//        if(pageCode == null) {
//            toolbar.setTopic(" Add Course");
//
//            btn_submit_lesson.setOnClickListener(view1 -> {
//                if (!lessonCode.getEditText().getText().toString().isEmpty() &&
//                        !lessonTopic.getEditText().getText().toString().isEmpty() &&
//                        !lessonLevel.getEditText().getText().toString().isEmpty() &&
//                        !lessonSks.getEditText().getText().toString().isEmpty() &&
//                        !lessonDescription.getEditText().getText().toString().isEmpty()) {
//                    String code = lessonCode.getEditText().getText().toString().trim();
//                    String topic = lessonTopic.getEditText().getText().toString().trim();
//                    String level = lessonLevel.getEditText().getText().toString().trim();
//                    String sks = lessonSks.getEditText().getText().toString().trim();
//                    String desc = lessonDescription.getEditText().getText().toString().trim();
//
//                    Course.Courses lessons = addCourseData(code, topic, level, sks, desc);
//                    addCourseViewModel.createCourse(lessons).observe(requireActivity(), lessons1 -> {
//                        if (lessons1 != null) {
//                            NavDirections actions = AddCourseFragmentDirections.actionAddCourseFragmentToCourseFragment2();
//                            Navigation.findNavController(view1).navigate(actions);
//                            Toast.makeText(requireActivity(), "Add Course Success", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(requireActivity(), "Add Course Failed", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//
//                } else {
//                    Toast.makeText(requireActivity(), "Must not be Empty", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }else{
//            toolbar.setTopic("Edit Course");
//            lessonViewModel = new ViewModelProvider(getActivity()).get(CourseViewModel.class);
//            lessonViewModel.init(helper.getAccessToken());
//            lessonViewModel.getCourseDetail(pageCode);
//            lessonViewModel.getResultCourseDetail().observe(getActivity(), getCourseDetail);
//            btn_submit_lesson.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (!lessonCode.getEditText().getText().toString().isEmpty() &&
//                            !lessonTopic.getEditText().getText().toString().isEmpty() &&
//                            !lessonLevel.getEditText().getText().toString().isEmpty() &&
//                            !lessonSks.getEditText().getText().toString().isEmpty() &&
//                            !lessonDescription.getEditText().getText().toString().isEmpty()) {
//                        String code = lessonCode.getEditText().getText().toString().trim();
//                        String topic = lessonTopic.getEditText().getText().toString().trim();
//                        String level = lessonLevel.getEditText().getText().toString().trim();
//                        String sks = lessonSks.getEditText().getText().toString().trim();
//                        String desc = lessonDescription.getEditText().getText().toString().trim();
//                        Course.Courses lessons = addCourseData(code, topic, level, sks, desc);
//                        addCourseViewModel.editCourse(pageCode, lessons).observe(requireActivity(), lessons1 -> {
//                            if(lessons1 != null){
//                                NavDirections actions = AddCourseFragmentDirections.actionAddCourseFragmentToCourseFragment2();
//                                Navigation.findNavController(view).navigate(actions);
//                                Toast.makeText(requireActivity(), "Edit Course Success", Toast.LENGTH_SHORT).show();
//                            }else {
//                                Toast.makeText(requireActivity(), "Edit Course Failed", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }else{
//                        Toast.makeText(requireActivity(), "All field must not empty", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        }
//    }
//}