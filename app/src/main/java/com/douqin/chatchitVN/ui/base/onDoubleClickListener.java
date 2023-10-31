package com.douqin.chatchitVN.ui.base;

import android.view.View;

public class onDoubleClickListener implements View.OnClickListener {

    long timeClicked;
    boolean isClicked;

    Callback callback;

    public onDoubleClickListener(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onClick(View view) {
        if (this.isClicked) {
            if (System.currentTimeMillis() - timeClicked <= 1000) {
                this.isClicked = false;
                timeClicked = System.currentTimeMillis();
                this.callback.onDoubleClickListener();
            }
        }
        this.isClicked = true;
        timeClicked = System.currentTimeMillis();
    }

    public interface Callback {
        void onDoubleClickListener();
    }
}
