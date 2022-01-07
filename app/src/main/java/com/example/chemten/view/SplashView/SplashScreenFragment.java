package com.example.chemten.view.SplashView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chemten.R;
import com.example.chemten.helper.SharedPreferenceHelper;
import com.example.chemten.view.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SplashScreenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SplashScreenFragment extends Fragment {

    private static int SplashScreen = 3000;
    private static final String TAG = "SplashScreenFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SplashScreenFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SplashScreenFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SplashScreenFragment newInstance(String param1, String param2) {
        SplashScreenFragment fragment = new SplashScreenFragment();
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
        return inflater.inflate(R.layout.fragment_splash_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferenceHelper helper = SharedPreferenceHelper.getInstance(requireActivity());

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            NavDirections action;
            if (helper.getAccessToken().isEmpty()) {
                action = SplashScreenFragmentDirections.actionSplashScreenFragment2ToLoginFragment2();
            } else {
                action = SplashScreenFragmentDirections.actionSplashScreenFragment2ToHomeFragment();
            }
            Navigation.findNavController(view).navigate(action);
        }, SplashScreen);
    }
}