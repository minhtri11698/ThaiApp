package com.trizz.thaiapp.SplashAndLogin;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.trizz.thaiapp.Home.HomeActivity;
import com.trizz.thaiapp.R;

public class SignUpFragment extends Fragment {

    private EditText emailInput;
    private EditText password;
    private EditText passwordConfirm;
    private Button signUp;
    private FirebaseAuth mAuth;

    public SignUpFragment() {
        // Required empty public constructor
    }

    public static SignUpFragment newInstance() {
        SignUpFragment fragment = new SignUpFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signUp.setOnClickListener(v -> {
            String email = emailInput.getText().toString();
            String pwd = password.getText().toString();
            String confirmPwd = passwordConfirm.getText().toString();
            if (email.isEmpty() || pwd.isEmpty() || confirmPwd.isEmpty()) {
                Toast.makeText(requireContext(), "Info is blank", Toast.LENGTH_LONG).show();
            } else if (!pwd.equals(confirmPwd)) {
                Toast.makeText(requireContext(), "Incorect confirm password", Toast.LENGTH_LONG).show();
            } else {
                createUser(email, pwd);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        mAuth = FirebaseAuth.getInstance();
        emailInput = view.findViewById(R.id.sign_email_input);
        password = view.findViewById(R.id.sign_pwd_input);
        passwordConfirm = view.findViewById(R.id.sign_pwd_confirm_input);
        signUp = view.findViewById(R.id.sign_up_btn);
        return view;
    }

    private void createUser(String email, String pass) {
        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(requireActivity(), task -> {
            if (task.isSuccessful()) {
                Intent loggedInIntent = new Intent(requireContext(), HomeActivity.class);
                loggedInIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(loggedInIntent);
                requireActivity().finish();
            }
        });
    }
}