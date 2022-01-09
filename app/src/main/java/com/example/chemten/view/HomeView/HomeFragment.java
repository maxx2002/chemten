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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chemten.R;
import com.example.chemten.helper.SharedPreferenceHelper;
import com.example.chemten.model.DataUser;
import com.example.chemten.model.Lessons;
import com.example.chemten.model.Users;
import com.example.chemten.retrofit.RetrofitService;
import com.example.chemten.view.LeaderboardView.LeaderboardFragment;
import com.example.chemten.view.LeaderboardView.LeaderboardViewModel;
import com.example.chemten.view.LoginView.LoginFragmentDirections;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    ImageView home_image_profile, background, logo;
    TextView leaderboard, welcome_name;

    private HomeViewModel homeViewModel;
    private HomeAdapter homeAdapter;
    private LeaderboardViewModel leaderboardViewModel;
    private RecyclerView recyclerView;
    private SharedPreferenceHelper helper;
    private int rank_score, lesson_level;

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
        background = view.findViewById(R.id.background_biru_home_fragment);
        logo = view.findViewById(R.id.logo_home_fragment);
        background.setVisibility(View.VISIBLE);
        logo.setVisibility(View.VISIBLE);
        String UserEmail = getArguments().getString("email");
        home_image_profile = view.findViewById(R.id.home_image_profile);
        welcome_name = view.findViewById(R.id.text_name_home_fragment);
        home_image_profile.setOnClickListener(view1 -> {
            Bundle bundle = new Bundle();
            bundle.putString("email", UserEmail);
            if(UserEmail != null) {
                bundle.putInt("user_id", listDataUser.get(0).getId());
            }else {
                bundle.putInt("user_id", 1);
            }
            Navigation.findNavController(view1).navigate(R.id.action_homeFragment_to_profileFragment, bundle);
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
        leaderboardViewModel = new ViewModelProvider(getActivity()).get(LeaderboardViewModel.class);
        leaderboardViewModel.init(helper.getAccessToken());
        homeViewModel.getLesson();
        if(UserEmail != null) {
            homeViewModel.getDataUser(UserEmail);
        }else {
            homeViewModel.getDataUser("user@gmail.com");
        }
        leaderboardViewModel.getUserDetail(getArguments().getInt("user_id"));
        leaderboardViewModel.GetResultGetUserDetail().observe(getActivity(), showPoinUser);
    }

    List<Lessons.Lesson> results = new ArrayList<>();
    List<DataUser.User> listDataUser = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;

    private Observer<Lessons> showLessons = new Observer<Lessons>() {
        @Override
        public void onChanged(Lessons lesson) {
            results = lesson.getLesson();
            linearLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(linearLayoutManager);
            homeAdapter = new HomeAdapter(getActivity());
            homeAdapter.setLessonsList(results);
            homeAdapter.setLesson_count(lesson_level);
            recyclerView.setAdapter(homeAdapter);
        }
    };
    private Observer<DataUser> showDataUser = new Observer<DataUser>() {
        @Override
        public void onChanged(DataUser dataUser) {
            listDataUser = dataUser.getUser();
            homeAdapter = new HomeAdapter(getActivity());
            homeAdapter.setUserID(listDataUser.get(0).getId());
            welcome_name.setText("Hi, "+listDataUser.get(0).getName());
            background.setVisibility(View.GONE);
            logo.setVisibility(View.GONE);
        }
    };

    List<Users.Leaderboard> listLeaderboardUser = new ArrayList<>();
    private Observer<Users> showPoinUser = new Observer<Users>() {
        @Override
        public void onChanged(Users users) {
            listLeaderboardUser = users.getLeaderboard();
            rank_score = listLeaderboardUser.get(0).getRank_score();
            lesson_level = 1;
            if(rank_score > 300){
                lesson_level = 5;
            }else if(rank_score > 225){
                lesson_level = 4;
            }else if(rank_score > 150){
                lesson_level = 3;
            }else if(rank_score > 75){
                lesson_level = 2;
            }
            Log.d("HomeFragment", "Rank Score, Lesson Level: "+rank_score+ lesson_level);

            homeViewModel.getResultLesson().observe(getActivity(), showLessons);
            homeViewModel.getResultDataUser().observe(getActivity(), showDataUser);
        }
    };
    @Override
    public void onDetach(){
        super.onDetach();
        getActivity().getViewModelStore().clear();
    }
}