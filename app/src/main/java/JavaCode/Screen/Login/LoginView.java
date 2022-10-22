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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.dxlampro.appchat.Main;
import com.dxlampro.appchat.R;
import com.dxlampro.appchat.databinding.FragmentLoginBinding;

import JavaCode.Screen.StartChat.StartChatView;
import JavaCode.network.Session_ME;


public class LoginView extends Fragment {
    FragmentLoginBinding viewLoginBinding;
    private iLogin mainViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewLoginBinding = FragmentLoginBinding.inflate(getLayoutInflater());
        mainViewModel = new ViewModelProvider(requireActivity(), new LoginModelFactory(getActivity().getApplication())).get(LoginViewModel.class);
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
        mainViewModel.getUsername().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });
        mainViewModel.getPassword().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });
        mainViewModel.getTextStatusLogin().observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                viewLoginBinding.iconloading.setVisibility(View.GONE);
                viewLoginBinding.messLogin.setText(s);
                viewLoginBinding.messLogin.setVisibility(View.VISIBLE);
            }
        });
        mainViewModel.getStateLogin().observe(getViewLifecycleOwner(), new Observer<StateLogin>() {
            @Override
            public void onChanged(StateLogin stateLogin) {

                switch (stateLogin){
                    case SUCCESSFULLY:
                        viewLoginBinding.iconloading.post(() -> {
                            viewLoginBinding.iconloading.setVisibility(View.GONE);
                        });
                        final NavController navController = NavHostFragment.findNavController(LoginView.this);
                        navController.navigate(R.id.screenStartChat);
                        break;
                    case ERROR:
                        viewLoginBinding.iconloading.post(() -> {
                            viewLoginBinding.iconloading.setVisibility(View.GONE);
                        });
                        break;
                    case NO_STATE:
                        break;
                }
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

