package com.douqin.chatchitVN.common;

import android.content.Context;

import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava3.RxDataStore;

public class PreferencesManager {
    private final RxDataStore<Preferences> dataStore;

    // Key-Value keys
    private static final String KEY = "KEY";
    // Add other keys here

    public PreferencesManager(Context context) {
        dataStore = new RxPreferenceDataStoreBuilder(context, KEY).build();
    }
}
