package com.trizz.thaiapp;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {

    private EditText email;
    private EditText password;
    private Button login;
    private ProgressDialog loginProg;
    private FirebaseAuth mAuth;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        login.setOnClickListener(v -> {
            if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                Toast.makeText(requireContext(), "Please fills in all fields", Toast.LENGTH_LONG).show();
            } else {
                loginProg.setTitle("Logging In...");
                loginProg.setMessage("Logging in your account, please wait...");
                loginProg.setCanceledOnTouchOutside(false);
                loginProg.show();
                loginUser(email.getText().toString(), password.getText().toString());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        login = view.findViewById(R.id.login_btn);
        email = view.findViewById(R.id.email_id_input);
        password = view.findViewById(R.id.pwd_input);
        mAuth = FirebaseAuth.getInstance();
        loginProg = new ProgressDialog(requireContext());
        return view;
    }

    private void loginUser(String uEmail, String pwd) {
        mAuth.signInWithEmailAndPassword(uEmail, pwd).addOnCompleteListener(requireActivity(), task -> {
            if (task.isSuccessful()) {
                loginProg.dismiss();
                FirebaseUser user = mAuth.getCurrentUser();
                Intent loggedInIntent = new Intent(requireContext(), HomeActivity.class);
                loggedInIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(loggedInIntent);
                requireActivity().finish();
            } else {
                loginProg.hide();
                // If sign in fails, display a message to the user.
                Toast.makeText(requireActivity(), "Authentication failed.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}