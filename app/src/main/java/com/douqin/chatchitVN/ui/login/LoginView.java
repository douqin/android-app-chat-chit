package com.douqin.chatchitVN.ui.login;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.douqin.chatchitVN.R;
import com.douqin.chatchitVN.databinding.FragmentLoginBinding;

public class LoginView extends Fragment {
    FragmentLoginBinding viewLoginBinding;
    private LoginViewModel loginViewModel;

    private boolean stateShowPassword = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewLoginBinding = FragmentLoginBinding.inflate(getLayoutInflater());
        loginViewModel = new ViewModelProvider(this, new LoginModelFactory(requireActivity().getApplication())).get(LoginViewModel.class);
        viewLoginBinding.username.setText(loginViewModel.getUsername().getValue());
        viewLoginBinding.password.setText(loginViewModel.getPassword().getValue());
        viewLoginBinding.btnLogin.setOnClickListener(v -> {
            this.animationLoadingLogin();
            loginViewModel.login(viewLoginBinding.username.getText().toString(), viewLoginBinding.password.getText().toString());
        });
        this.animationShowPassword();
        viewLoginBinding.singUp.setOnClickListener(view -> showDialogSignUp());
        this.listenViewModel();
        return viewLoginBinding.getRoot();
    }

    private void listenViewModel() {
        loginViewModel.getUsername().observe(getViewLifecycleOwner(), s -> {

        });
        loginViewModel.getPassword().observe(getViewLifecycleOwner(), s -> {

        });
        loginViewModel.getStateLogin().observe(getViewLifecycleOwner(), stateLogin -> {
            switch (stateLogin.stateLogin) {
                case LOGGING:
                    viewLoginBinding.iconloading.post(() -> {
                        viewLoginBinding.messLogin.setVisibility(View.GONE);
                        viewLoginBinding.iconloading.setVisibility(View.VISIBLE);
                    });
                    break;
                case SUCCESSFULLY:
                    viewLoginBinding.iconloading.post(() -> {
                        viewLoginBinding.iconloading.setVisibility(View.GONE);
                    });
                    LoginViewDirections.ActionScreenLoginToScreenStartChat action = LoginViewDirections.actionScreenLoginToScreenStartChat();
                    action.setIsStartFromScrLogin(true);
                    final NavController navController = NavHostFragment.findNavController(LoginView.this);
                    navController.navigate(action);
                    break;
                case ERROR:
                    viewLoginBinding.iconloading.post(() -> {
                        viewLoginBinding.iconloading.setVisibility(View.VISIBLE);
                    });
                    viewLoginBinding.messLogin.post(() -> {
                        viewLoginBinding.messLogin.setVisibility(View.VISIBLE);
                        viewLoginBinding.messLogin.setText(stateLogin.message);
                    });
                case NO_STATE:
                    viewLoginBinding.iconloading.post(() -> {
                        viewLoginBinding.iconloading.setVisibility(View.GONE);
                    });
                    break;
            }
        });
    }

    public void showDialogSignUp() {

    }

    private void animationShowPassword() {
        this.viewLoginBinding.showPassword.setOnClickListener((v) -> {
            if (stateShowPassword) {
                this.viewLoginBinding.showPassword.setBackground(ContextCompat.getDrawable(this.requireContext(), R.drawable.crossed_eye_icon));
                this.viewLoginBinding.password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                ;
            } else {
                this.viewLoginBinding.showPassword.setBackground(ContextCompat.getDrawable(this.requireContext(), R.drawable.eye_icon));
                this.viewLoginBinding.password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                ;
            }
            stateShowPassword = !stateShowPassword;
        });
    }

    public void animationLoadingLogin() {
        viewLoginBinding.iconloading.post(() -> {
            viewLoginBinding.iconloading.setVisibility(View.VISIBLE);
        });
    }
}

