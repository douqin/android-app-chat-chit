package JavaCode.Screen.Splash;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.dxlampro.appchat.R;
import com.dxlampro.appchat.databinding.SplashViewBinding;

import JavaCode.Repository.User.UserManager;
import JavaCode.Screen.Login.LoginView;
import JavaCode.Screen.Login.LoginViewDirections;

public class SplashView extends Fragment {
    private SplashViewBinding splashViewBinding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        splashViewBinding = SplashViewBinding.inflate(getLayoutInflater());
        NavController navController = NavHostFragment.findNavController(this);
        try{
            UserManager.gI().initializeDAO(requireActivity().getApplication());
            JavaCode.Screen.Splash.SplashViewDirections.ActionSplashViewToScreenStartChat  action = JavaCode.Screen.Splash.SplashViewDirections.actionSplashViewToScreenStartChat();
            action.setIsStartFromScrLogin(false);
            navController.navigate(action);
        }
        catch (Exception e){
            navController.navigate(R.id.loginView);
        }
        return splashViewBinding.getRoot();
    }
}
