package JavaCode.Clib;

import android.content.Context;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dxlampro.appchat.Main;

public abstract class MotherCanvas{
    public static int height = -1;
    public static int width = -1;

    public MotherCanvas(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;
    }



}
