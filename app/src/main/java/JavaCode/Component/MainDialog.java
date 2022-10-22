package JavaCode.Component;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MainDialog extends Dialog {
    public MainDialog(@NonNull Context context) {
        super(context);
    }

    public MainDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected MainDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
    public void update(){

    };
}
