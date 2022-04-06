package br.senai.sp.cotia.tictactoeapp.fragment;

import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;
import br.senai.sp.cotia.tictactoeapp.R;

public class PrefFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
    }
}