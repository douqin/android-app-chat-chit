package com.douqin.chatchitVN.ui.base;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import java.util.Objects;

public class SnapOnScrollListener extends RecyclerView.OnScrollListener {
    private SnapHelper snapHelper;
    private Behavior behavior;
    private OnSnapPositionChangeListener onSnapPositionChangeListener;
    private int snapPosition = RecyclerView.NO_POSITION;

    public SnapOnScrollListener(SnapHelper snapHelper) {
        this.snapHelper = snapHelper;
        this.behavior = Behavior.NOTIFY_ON_SCROLL;
        this.onSnapPositionChangeListener = null;
    }

    public SnapOnScrollListener(SnapHelper snapHelper, Behavior behavior, OnSnapPositionChangeListener onSnapPositionChangeListener) {
        this.snapHelper = snapHelper;
        this.behavior = behavior;
        this.onSnapPositionChangeListener = onSnapPositionChangeListener;
    }

    public enum Behavior {
        NOTIFY_ON_SCROLL,
        NOTIFY_ON_SCROLL_STATE_IDLE
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (behavior == Behavior.NOTIFY_ON_SCROLL) {
            maybeNotifySnapPositionChange(recyclerView);
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if (behavior == Behavior.NOTIFY_ON_SCROLL_STATE_IDLE && newState == RecyclerView.SCROLL_STATE_IDLE) {
            maybeNotifySnapPositionChange(recyclerView);
        }
    }

    private void maybeNotifySnapPositionChange(RecyclerView recyclerView) {
        int snapPosition = this.getSnapPosition(recyclerView);
        boolean snapPositionChanged = this.snapPosition != snapPosition;
        if (snapPositionChanged) {
            if (onSnapPositionChangeListener != null) {
                onSnapPositionChangeListener.onSnapPositionChange(snapPosition);
            }
            this.snapPosition = snapPosition;
        }
    }

    public int getSnapPosition(RecyclerView recyclerView) {
        View snapView = this.snapHelper.findSnapView(recyclerView.getLayoutManager());
        assert snapView != null;
        return Objects.requireNonNull(recyclerView.getLayoutManager()).getPosition(snapView);
    }
}