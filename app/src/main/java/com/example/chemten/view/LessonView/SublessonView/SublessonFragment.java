package com.example.chemten.view.LessonView.SublessonView;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.chemten.R;
import com.example.chemten.helper.Const;
import com.squareup.picasso.Picasso;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SublessonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SublessonFragment extends Fragment {
    private TextView sublesson_topic;
    private HtmlTextView sublesson_desc;
    ImageView img_test, btn_back, background, logo;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SublessonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SublessonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SublessonFragment newInstance(String param1, String param2) {
        SublessonFragment fragment = new SublessonFragment();
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
        return inflater.inflate(R.layout.fragment_sublesson, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        background = view.findViewById(R.id.background_biru_sublesson_fragment);
        logo = view.findViewById(R.id.logo_sublesson_fragment);
        background.setVisibility(View.VISIBLE);
        logo.setVisibility(View.VISIBLE);
        sublesson_desc = view.findViewById(R.id.text_sublesson_desc_sublesson_fragment);
        sublesson_desc.setHtml(getArguments().getString("sublesson_desc"), new HtmlHttpImageGetter(sublesson_desc));
        sublesson_topic = view.findViewById(R.id.text_sublesson_topic_sublesson_fragment);
        img_test = view.findViewById(R.id.img_sublesson_fragment);
        btn_back = view.findViewById(R.id.btn_back_sublesson_fragment);
        Picasso.get().load(Const.BASE_URL + "image/"+getArguments().getString("sublesson_image")).resize(1000, 0).into(img_test);
        sublesson_topic.setText(getArguments().getString("sublesson_topic"));
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                background.setVisibility(View.GONE);
                logo.setVisibility(View.GONE);
            }
        }, 500);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }
}