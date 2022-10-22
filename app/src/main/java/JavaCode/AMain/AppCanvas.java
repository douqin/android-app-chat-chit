package JavaCode.AMain;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.dxlampro.appchat.Main;
import com.dxlampro.appchat.R;

import JavaCode.Clib.MotherCanvas;

public class AppCanvas extends MotherCanvas {

    public AppCanvas(Context context) {
        super(context);
        initApp();
    }

    private void initApp() {
        this.beginGame();
    }

    private void beginGame() {
    }
}
