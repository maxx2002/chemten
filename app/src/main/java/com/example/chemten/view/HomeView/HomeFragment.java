package com.example.chemten.view.HomeView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chemten.R;
import com.example.chemten.helper.SharedPreferenceHelper;
import com.example.chemten.model.Lessons;
import com.example.chemten.retrofit.RetrofitService;
import com.example.chemten.view.LeaderboardView.LeaderboardFragment;
import com.example.chemten.view.LoginView.LoginFragmentDirections;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    ImageView home_image_profile;
    TextView leaderboard;

    private HomeViewModel homeViewModel;
    private HomeAdapter homeAdapter;
    private RecyclerView recyclerView;
    private SharedPreferenceHelper helper;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        home_image_profile = view.findViewById(R.id.home_image_profile);
        home_image_profile.setOnClickListener(view1 -> {
            NavDirections action = HomeFragmentDirections.actionHomeFragmentToProfileFragment();
            Navigation.findNavController(view1).navigate(action);
        });

        leaderboard = view.findViewById(R.id.leaderboard);
        leaderboard.setOnClickListener(view2 -> {
            NavDirections action = HomeFragmentDirections.actionHomeFragmentToLeaderboardFragment();
            Navigation.findNavController(view2).navigate(action);
        });

        recyclerView = view.findViewById(R.id.rv_lesson_home_fragment);
        helper = SharedPreferenceHelper.getInstance(requireActivity());
        homeViewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
        homeViewModel.init(helper.getAccessToken());
        homeViewModel.getLesson();
        homeViewModel.getResultLesson().observe(getActivity(), showLessons);
    }

    List<Lessons.Lesson> results = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;

    private Observer<Lessons> showLessons = new Observer<Lessons>() {
        @Override
        public void onChanged(Lessons lesson) {
            results = lesson.getLesson();
            linearLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(linearLayoutManager);
            homeAdapter = new HomeAdapter(getActivity());
            homeAdapter.setLessonsList(results);
            recyclerView.setAdapter(homeAdapter);
        }
    };

    @Override
    public void onDetach(){
        super.onDetach();
        getActivity().getViewModelStore().clear();
    }
}