package com.example.chemten.view.ProfileView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chemten.R;
import com.example.chemten.helper.SharedPreferenceHelper;
import com.example.chemten.model.DataUser;
import com.example.chemten.model.Users;
import com.example.chemten.view.HomeView.HomeFragmentDirections;
import com.example.chemten.view.HomeView.HomeViewModel;
import com.example.chemten.view.LeaderboardView.LeaderboardViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    ImageView profile_image_back, badge1, badge2, badge3, badge4, badge5, background, logo;
    TextView profile_text_logout, text_point, text_username, text_email;

    private ProfileViewModel profileViewModel;
    private HomeViewModel homeViewModel;
    private LeaderboardViewModel leaderboardViewModel;
    private SharedPreferenceHelper helper;
    private static final String TAG = "ProfileFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        background = view.findViewById(R.id.background_biru_profile_fragment);
        logo = view.findViewById(R.id.logo_profile_fragment);
        background.setVisibility(View.VISIBLE);
        logo.setVisibility(View.VISIBLE);
        helper = SharedPreferenceHelper.getInstance(requireActivity());
        profileViewModel = new ViewModelProvider(getActivity()).get(ProfileViewModel.class);
        profileViewModel.init(helper.getAccessToken());
        homeViewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
        homeViewModel.init(helper.getAccessToken());
        leaderboardViewModel = new ViewModelProvider(getActivity()).get(LeaderboardViewModel.class);
        leaderboardViewModel.init(helper.getAccessToken());
        profile_image_back = view.findViewById(R.id.profile_image_back);
        badge1 = view.findViewById(R.id.img_badge1_profile_fragment);
        badge2 = view.findViewById(R.id.img_badge2_profile_fragment);
        badge3 = view.findViewById(R.id.img_badge3_profile_fragment);
        badge4 = view.findViewById(R.id.img_badge4_profile_fragment);
        badge5 = view.findViewById(R.id.img_badge5_profile_fragment);
        text_point = view.findViewById(R.id.text_poin_profile_fragment);
        text_username = view.findViewById(R.id.text_username_profile_fragment);
        text_email = view.findViewById(R.id.text_email_profile_fragment);

        if(getArguments().getString("email") != null) {
            homeViewModel.getDataUser(getArguments().getString("email"));
        }else {
            homeViewModel.getDataUser("user@gmail.com");
        }
        homeViewModel.getResultDataUser().observe(getActivity(), showDataUser);
        leaderboardViewModel.getUserDetail(getArguments().getInt("user_id"));
        leaderboardViewModel.GetResultGetUserDetail().observe(getActivity(), showPoinUser);
        profile_image_back.setOnClickListener(view1 -> {
            NavDirections action = ProfileFragmentDirections.actionProfileFragmentToHomeFragment();
            Navigation.findNavController(view1).navigate(action);
        });

        profile_text_logout = view.findViewById(R.id.profile_text_logout);
        profile_text_logout.setOnClickListener(view1 -> {
            profileViewModel.logout().observe(requireActivity(), s -> {
                if(!s.isEmpty()){
                    helper.clearPref();
                    NavDirections action = ProfileFragmentDirections.actionProfileFragmentToLoginFragment2();
                    Navigation.findNavController(view1).navigate(action);
                    Toast.makeText(requireActivity(), s, Toast.LENGTH_SHORT).show();
                }
            });

        });
    }

    List<DataUser.User> listDataUser = new ArrayList<>();
    List<Users.Leaderboard> listLeaderboardUser = new ArrayList<>();
    private Observer<DataUser> showDataUser = new Observer<DataUser>() {
        @Override
        public void onChanged(DataUser dataUser) {
            listDataUser = dataUser.getUser();
            text_email.setText(listDataUser.get(0).getEmail());
            text_username.setText(listDataUser.get(0).getUsername());
        }
    };
    private Observer<Users> showPoinUser = new Observer<Users>() {
        @Override
        public void onChanged(Users users) {
            listLeaderboardUser = users.getLeaderboard();
            int Score = listLeaderboardUser.get(0).getRank_score();
            text_point.setText(String.valueOf(Score));
            if(Score < 100){
                badge1.setImageResource(R.drawable.badge1bw);
                badge2.setImageResource(R.drawable.badge2bw);
                badge3.setImageResource(R.drawable.badge3bw);
                badge4.setImageResource(R.drawable.badge4bw);
                badge5.setImageResource(R.drawable.badge5bw);
            }else if(Score < 200){
                badge2.setImageResource(R.drawable.badge2bw);
                badge3.setImageResource(R.drawable.badge3bw);
                badge4.setImageResource(R.drawable.badge4bw);
                badge5.setImageResource(R.drawable.badge5bw);
            }else if(Score < 300){
                badge3.setImageResource(R.drawable.badge3bw);
                badge4.setImageResource(R.drawable.badge4bw);
                badge5.setImageResource(R.drawable.badge5bw);
            }else if(Score < 400){
                badge4.setImageResource(R.drawable.badge4bw);
                badge5.setImageResource(R.drawable.badge5bw);
            }else if(Score < 500){
                badge5.setImageResource(R.drawable.badge5bw);
            }
            background.setVisibility(View.GONE);
            logo.setVisibility(View.GONE);
        }
    };

    @Override
    public void onDetach(){
        super.onDetach();
        getActivity().getViewModelStore().clear();;
    }
}