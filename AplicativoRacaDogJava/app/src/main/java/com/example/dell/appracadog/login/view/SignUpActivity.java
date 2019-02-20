package com.example.dell.appracadog.login.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.appracadog.R;
import com.example.dell.appracadog.home.view.HomeActivity;
import com.example.dell.appracadog.login.model.request.SignUpRequest;
import com.example.dell.appracadog.login.model.response.User;
import com.example.dell.appracadog.login.model.response.UserResponse;
import com.example.dell.appracadog.login.viewmodel.SignUpViewModel;
import com.example.dell.appracadog.util.StringUtils;

public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText emailAddress;
    private Button btnLogin;
    private SignUpViewModel viewModel;
    private SignUpRequest request;
    private User currentUser;
    private TextView instruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initAssets();

        btnLogin.setOnClickListener(v -> {

            String emailValue = emailAddress.getText().toString();

            if (!emailValue.isEmpty() && StringUtils.checkEmailValidity(emailValue)) {

                request = new SignUpRequest(emailValue);

                viewModel.signUp(request);
                viewModel.objectLiveData.observe(SignUpActivity.this, new Observer<UserResponse>() {
                    @Override
                    public void onChanged(@Nullable UserResponse response) {
                        currentUser = response.getUser();
                    }
                });

                viewModel.isLoading.observe(SignUpActivity.this, aBoolean -> {
                    if (!aBoolean) {
                        if (currentUser != null) {
                            startActivity(new Intent(SignUpActivity.this, HomeActivity.class));
                            this.finish();
                        } else {
                            Toast.makeText(SignUpActivity.this, "Registrando", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                Toast.makeText(SignUpActivity.this, "Por favor, insira um email válido", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initAssets() {
        instruction = findViewById(R.id.text_instruction);
        emailAddress = findViewById(R.id.text_email);
        btnLogin = findViewById(R.id.button_login);
        viewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);
    }
}