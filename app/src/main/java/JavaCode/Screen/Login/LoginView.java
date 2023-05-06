package JavaCode.Screen.Login;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import com.dxlampro.appchat.R;
import com.dxlampro.appchat.databinding.FragmentLoginBinding;

import JavaCode.Screen.Login.LoginViewDirections;

public class LoginView extends Fragment {
    FragmentLoginBinding viewLoginBinding;
    private LoginViewModel mainViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewLoginBinding = FragmentLoginBinding.inflate(getLayoutInflater());
        mainViewModel = new ViewModelProvider(requireActivity(), new LoginModelFactory(requireActivity().getApplication())).get(LoginViewModel.class);
        viewLoginBinding.username.setText(mainViewModel.getUsername().getValue());
        viewLoginBinding.password.setText(mainViewModel.getPassword().getValue());
        viewLoginBinding.btnLogin.setOnClickListener(v -> {
            this.loadingLogin();
            mainViewModel.login(viewLoginBinding.username.getText().toString(), viewLoginBinding.password.getText().toString());
        });
        viewLoginBinding.singUp.setOnClickListener(view -> showDilogSignUp());
        this.listenViewModel();
//        Session_ME.CheckConnect(); chua lam xong
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                LoginView.this.requireActivity().finish();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), callback);
        return viewLoginBinding.getRoot();
    }

    private void listenViewModel() {
        mainViewModel.getUsername().observe(getViewLifecycleOwner(), s -> {

        });
        mainViewModel.getPassword().observe(getViewLifecycleOwner(), s -> {

        });
        mainViewModel.getStateLogin().observe(getViewLifecycleOwner(), stateLogin -> {
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
                   JavaCode.Screen.Login.LoginViewDirections.ActionScreenLoginToScreenStartChat action = LoginViewDirections.actionScreenLoginToScreenStartChat();
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

    public void showDilogSignUp() {
        Dialog dialog = new Dialog(this.getActivity());
        dialog.setContentView(R.layout.dialog_signup);
        Button signUponWeb = dialog.findViewById(R.id.signUponWeb);
        signUponWeb.setOnClickListener(v -> {
            String urlString = "https://dxlampro.xyz/signup.html";
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setPackage("com.android.chrome");
            try {
                Toast.makeText(this.getActivity(), "Test", Toast.LENGTH_SHORT).show();
                startActivity(i);
            } catch (ActivityNotFoundException e) {
                // Chrome is probably not installed
                Toast.makeText(this.getActivity(), "Chrome is probably not installed", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void loadingLogin() {
        viewLoginBinding.iconloading.post(() -> {
            viewLoginBinding.iconloading.setVisibility(View.VISIBLE);
        });
    }
}

