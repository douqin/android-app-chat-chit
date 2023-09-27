package com.douqin.chatchitVN.ui.group;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.douqin.chatchitVN.databinding.GroupBottomSheetDialogBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class GroupBottomSheetDiaLogFragment extends BottomSheetDialogFragment {

    private final int id;

    private GroupBottomSheetDiaLogFragment(int id) {
        this.id = id;
    }

    public static GroupBottomSheetDiaLogFragment newInstance(int id) {
        return new GroupBottomSheetDiaLogFragment(id);
    }

    GroupBottomSheetDialogBinding groupBottomSheetDialogBinding;

    GroupViewModel groupViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        groupBottomSheetDialogBinding = GroupBottomSheetDialogBinding.inflate(inflater);
        return groupBottomSheetDialogBinding.getRoot();
    }

}
