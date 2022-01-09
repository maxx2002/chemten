package com.example.chemten.view.LeaderboardView;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.chemten.R;
import com.example.chemten.helper.SharedPreferenceHelper;
import com.example.chemten.model.Lessons;
import com.example.chemten.model.Users;
import com.example.chemten.view.HomeView.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LeaderboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LeaderboardFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LeaderboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LeaderboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LeaderboardFragment newInstance(String param1, String param2) {
        LeaderboardFragment fragment = new LeaderboardFragment();
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

    private RecyclerView RecyclerView;
    private LeaderboardViewModel leaderboardViewModel;
    private LeaderboardAdapter leaderboardAdapter;
    private SharedPreferenceHelper helper;
    ImageView btn_back, logo, background;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_leaderboard, container, false);
        background = view.findViewById(R.id.background_biru_leaderboard_fragment);
        logo = view.findViewById(R.id.logo_leaderboard_fragment);
        background.setVisibility(View.VISIBLE);
        logo.setVisibility(View.VISIBLE);
        btn_back = view.findViewById(R.id.btn_back_lb);
        RecyclerView = view.findViewById(R.id.rv_leaderboard);
        helper = SharedPreferenceHelper.getInstance(requireActivity());
        leaderboardViewModel = new ViewModelProvider(getActivity()).get(LeaderboardViewModel.class);
        leaderboardViewModel.init(helper.getAccessToken());
        leaderboardViewModel.getUser();
        leaderboardViewModel.GetResultGetUser_id().observe(getActivity(), showLeaderboard);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        return view;
    }

    List<Users.Leaderboard> results = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;

    private Observer<Users> showLeaderboard = new Observer<Users>() {
        @Override
        public void onChanged(Users users) {
            results = users.getLeaderboard();
            linearLayoutManager = new LinearLayoutManager(getActivity());
            RecyclerView.setLayoutManager(linearLayoutManager);
            leaderboardAdapter = new LeaderboardAdapter(getActivity());
            leaderboardAdapter.setListUser(results);
            RecyclerView.setAdapter(leaderboardAdapter);
            background.setVisibility(View.GONE);
            logo.setVisibility(View.GONE);
            }
    };
}