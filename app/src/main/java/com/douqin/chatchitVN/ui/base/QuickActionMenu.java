package com.douqin.chatchitVN.ui.base;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.ContextCompat;
import com.douqin.chatchitVN.R;

public class QuickActionMenu implements PopupMenu.OnDismissListener {

    protected Context mContext;
    protected PopupWindow mWindow;
    protected View mRootView;
    protected Drawable mBackground = null;
    private int mChildPos;
    protected WindowManager mWindowManager;
    private PopupWindow.OnDismissListener mDismissListener;
    private ViewGroup mTrack;
    private LayoutInflater inflater;
    public QuickActionMenu(Context context) {
        mContext = context;
        mWindow 	= new PopupWindow(context);
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        inflater 	= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        setRootViewId(R.layout.quick_action);
        mChildPos		= 0;
    }
    public void setRootViewId(int id) {
        mRootView	= inflater.inflate(id, null);
        mTrack 		= mRootView.findViewById(R.id.tracks);
        mRootView.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT));
        mWindow.setContentView(mRootView);
    }
    @Override
    public void onDismiss(PopupMenu menu) {

    }
    protected void preShow() {
        if (mRootView == null)
            throw new IllegalStateException("setContentView was not called with a view to display.");
        mBackground = ContextCompat.getDrawable(mContext, R.drawable.bg_reaction_menu);
        mWindow.setBackgroundDrawable(mBackground);
        mWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        mWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        mWindow.setTouchable(true);
        mWindow.setFocusable(true);
        mWindow.setOutsideTouchable(true);
        mWindow.setContentView(mRootView);
    }
    public void show (View anchor) {
        preShow();

        int[] location 		= new int[2];

        anchor.getLocationOnScreen(location);

        Rect anchorRect 	= new Rect(location[0], location[1], location[0] + anchor.getWidth(), location[1]
                + anchor.getHeight());

        mRootView.measure(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

        int rootWidth 		= mRootView.getMeasuredWidth();
        int rootHeight 		= mRootView.getMeasuredHeight();

        int screenWidth 	= 0;

        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        screenWidth = metrics.widthPixels;
        int xPos 			= (screenWidth - rootWidth) / 2;
        int yPos	 		= anchorRect.top - rootHeight;
        boolean onTop		= true;
        // display on bottom
        if (rootHeight > anchor.getTop()) {
            yPos 	= anchorRect.bottom;
            onTop	= false;
        }
        mWindow.showAtLocation(anchor, Gravity.NO_GRAVITY, xPos, yPos);
    }
    public void addActionItem(ActionItem actionItem) {
        String title 	= actionItem.title;
        Drawable icon 	= actionItem.icon;
        View container	= inflater.inflate(R.layout.action_item, null);
        ImageView img 	= container.findViewById(R.id.iv_icon);
        TextView text 	= container.findViewById(R.id.tv_title);

        if (icon != null) {
            // FIXME: fix size icon
            img.setImageDrawable(icon);
        } else {
            img.setVisibility(View.GONE);
        }

        if (title != null) {
            text.setText(title);
        } else {
            text.setVisibility(View.GONE);
        }
        container.setOnClickListener(view -> {
            if(actionItem.action != null){
                actionItem.action.invoke();
            }
            QuickActionMenu.this.mWindow.dismiss();
        });
        container.setFocusable(true);
        container.setClickable(true);

        mTrack.addView(container);
        mChildPos++;
    }
}
