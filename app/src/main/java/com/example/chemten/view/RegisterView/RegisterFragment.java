package com.example.chemten.view.RegisterView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chemten.R;
import com.example.chemten.view.LoginView.LoginFragmentDirections;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    TextView btn_register, btn_login;
    EditText name_input, email_input, pass_input;

    private RegisterViewModel registerViewModel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Register.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_login = view.findViewById(R.id.btn_login_register_fragment);
        btn_login.setOnClickListener(view1 -> {
            NavDirections action = RegisterFragmentDirections.actionRegisterToLoginFragment2();
            Navigation.findNavController(view1).navigate(action);
        });

        name_input = view.findViewById(R.id.name_input_register_fragment);
        email_input = view.findViewById(R.id.email_input_register_fragment);
        pass_input = view.findViewById(R.id.pass_input_register_fragment);
        btn_register = view.findViewById(R.id.btn_register_register_fragment);

        registerViewModel = new ViewModelProvider(getActivity()).get(RegisterViewModel.class);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!name_input.getText().toString().isEmpty()
                &&!email_input.getText().toString().isEmpty()
                &&!pass_input.getText().toString().isEmpty()){
                    String name = name_input.getText().toString().trim();
                    String email = email_input.getText().toString().trim();
                    String pass = pass_input.getText().toString().trim();
                    registerViewModel.register(name, email, pass).observe(requireActivity(), registerResponse -> {
                        if(registerResponse != null){
                            NavDirections actions = RegisterFragmentDirections.actionRegisterToLoginFragment2();
                            Navigation.findNavController(view).navigate(actions);
                            Toast.makeText(requireActivity(), "Register Success", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(requireActivity(), "Register Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    Toast.makeText(requireActivity(), "All field must be filled", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}