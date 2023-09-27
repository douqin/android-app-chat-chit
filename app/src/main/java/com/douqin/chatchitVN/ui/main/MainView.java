package com.douqin.chatchitVN.ui.main;

import static androidx.navigation.ui.AppBarConfigurationKt.AppBarConfiguration;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.douqin.chatchitVN.R;
import com.douqin.chatchitVN.common.MotherCanvas;
import com.douqin.chatchitVN.common.SaveDT;
import com.douqin.chatchitVN.databinding.ActivityMainBinding;
import com.douqin.chatchitVN.network.socketIO.SocketIO;

import java.util.Objects;

public class MainView extends AppCompatActivity {
    private static final String TAG = "Main";
    private ActivityMainBinding mainView;
    private MainViewModel mainViewModel;

    public static String tokenNotification = SaveDT.loadData("tokenNotification");

    @SuppressLint({"ResourceAsColor", "NonConstantResourceId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initGame(this);
        super.onCreate(savedInstanceState);
        mainView = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainView.getRoot();
        setContentView(view);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(mainView.Main.getId());
        assert navHostFragment != null;
        NavController navCo = navHostFragment.getNavController();
        navCo.addOnDestinationChangedListener((controller, destination, arguments) -> {
            switch (destination.getId()){
                case R.id.storyView:
                    MainView.this.mainView.bannerMain.setVisibility(View.VISIBLE);
                    MainView.this.mainView.tvNameScreen.setText("Stories");
                break;
                case R.id.splashView:
                    MainView.this.mainView.bannerMain.setVisibility(View.GONE);
                    MainView.this.mainView.navigation.setVisibility(View.GONE);
                    break;
                case R.id.loginView:
                    MainView.this.mainView.bannerMain.setVisibility(View.GONE);
                    MainView.this.mainView.navigation.setVisibility(View.GONE);
                    break;
                case R.id.groupMessage:
                    MainView.this.mainView.bannerMain.setVisibility(View.VISIBLE);
                    MainView.this.mainView.tvNameScreen.setText("Chats");
                    MainView.this.mainView.navigation.setVisibility(View.VISIBLE);
                    break;
                case R.id.screenMessage:
                    MainView.this.mainView.bannerMain.setVisibility(View.GONE);
                    MainView.this.mainView.navigation.setVisibility(View.GONE);
                    break;
                case R.id.screenInforUser:
                    MainView.this.mainView.bannerMain.setVisibility(View.GONE);
                    MainView.this.mainView.navigation.setVisibility(View.GONE);
                    break;
            }
        });
        this.mainView.navigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.groupMessage:
                    if(navCo.getCurrentDestination().getId() == R.id.groupMessage)
                        return false;
                    navCo.popBackStack(R.id.groupMessage, false);
                    return true;
                case R.id.storyView:
                    navCo.navigate(R.id.action_groupMessage_to_storyView);
                    return true;
            }
            return false;
        });
    }

    private void initGame(Context c) {
        MotherCanvas.init(this);
        this.setView();
        this._initViewModel();
    }

    private void setView() {
        Objects.requireNonNull(this.getSupportActionBar()).hide();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }

    @SuppressLint("SetTextI18n")
    private void _initViewModel() {
        mainViewModel = new ViewModelProvider(this, new MainViewModelFactory(this.getApplication())).get(MainViewModel.class);
        mainViewModel.getStatusApp().observe(this, str -> {
            mainView.checkInternet.setVisibility(View.VISIBLE);
            if (str.isEmpty()) {
                mainView.checkInternet.setVisibility(View.GONE);
            }
            mainView.notificationApp.setText(str);
        });
    }

    public static void loadTokenNoti() {
//        FirebaseMessaging.getInstance().getToken()
//                .addOnCompleteListener(task -> {
//                    if (!task.isSuccessful()) {
//                        Log.w(TAG, "Fetching FCM registration token failed", task.getException());
//                        return;
//                    }
//                    // Get new FCM registration token
//                    String token = task.getResult();
//                    // Log and toast
//                    Log.w(TAG, token);
//                    SaveDT.saveStr("notificationToken", token);
//                });
    }

    @Override
    protected void onDestroy() {
        SocketIO.gI().disconnect();
        super.onDestroy();
    }
}
