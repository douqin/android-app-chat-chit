package JavaCode.Screen.Splash;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.dxlampro.appchat.R;
import com.dxlampro.appchat.databinding.SplashViewBinding;

import JavaCode.Screen.Login.LoginView;

public class SplashView extends Fragment {
    private SplashViewBinding splashViewBinding;
    private iAuth splViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        splashViewBinding = SplashViewBinding.inflate(getLayoutInflater());
        splViewModel = new ViewModelProvider(requireActivity()).get(SplashViewModel.class);
        splViewModel.getStateAuth().observe(getViewLifecycleOwner(),stateAuth -> {
            if(stateAuth == StateAuth.ERROR){
                final NavController navController = NavHostFragment.findNavController(SplashView.this);
                navController.navigate(R.id.loginView);
            }
            else if(stateAuth == StateAuth.SUCCESSFULLY){
                final NavController navController = NavHostFragment.findNavController(SplashView.this);
                navController.navigate(R.id.screenStartChat);
            }
        });
        splViewModel.auth();
        return splashViewBinding.getRoot();
    }
}
