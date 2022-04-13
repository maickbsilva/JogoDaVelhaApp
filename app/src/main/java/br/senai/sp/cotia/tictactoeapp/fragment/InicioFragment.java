package br.senai.sp.cotia.tictactoeapp.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.senai.sp.cotia.tictactoeapp.R;
import br.senai.sp.cotia.tictactoeapp.databinding.ActivityMainBinding;
import br.senai.sp.cotia.tictactoeapp.databinding.FragmentInicioBinding;
import br.senai.sp.cotia.tictactoeapp.databinding.FragmentJogoBinding;
import br.senai.sp.cotia.tictactoeapp.util.PrefsUtil;


public class InicioFragment extends Fragment {

    private FragmentInicioBinding binding;

    private String nomeJog1, nomeJog2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_inicio, container, false);
        binding = FragmentInicioBinding.inflate(inflater, container, false);

        binding.buttonInicial.setOnClickListener (v -> {
            NavHostFragment.findNavController(InicioFragment.this).navigate(R.id.action_inicioFragment_to_jogoFragment);



            nomeJog1 = binding.idJog1.getText().toString();
            nomeJog2 = binding.idJog2.getText().toString();

            PrefsUtil.setNomeJog1(nomeJog1, getContext());
            PrefsUtil.setNomeJog2(nomeJog2, getContext());
        });



        return binding.getRoot();
    }

    @Override
    public void onStart(){
        super.onStart();
        //pegar a referencia para a activite
        AppCompatActivity minhaActivite = (AppCompatActivity) getActivity();
        //oculta a action bar
        minhaActivite.getSupportActionBar().hide();
        //desbloqueia a seta de retornar
        minhaActivite.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

}