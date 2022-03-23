package br.senai.sp.cotia.tictactoeapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.senai.sp.cotia.tictactoeapp.R;
import br.senai.sp.cotia.tictactoeapp.databinding.FragmentJogoBinding;


public class JogoFragment extends Fragment {

        //variavel para acessar os elementos do View
        private FragmentJogoBinding binding;

        //vetor de botoes para referenciar os botoes
        private Button[] botoes;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentJogoBinding.inflate(inflater, container, false);

        //instanciando o botao
        botoes = new Button[9];

        //associando o vetor aos botoes
        botoes[0] = binding.bt00;
        botoes[1] = binding.bt01;
        botoes[2] = binding.bt02;
        botoes[3] = binding.bt10;
        botoes[4] = binding.bt11;
        botoes[5] = binding.bt12;
        botoes[6] = binding.bt20;
        botoes[7] = binding.bt21;
        botoes[8] = binding.bt22;

        //associa o listener aos botoes
        for (Button bt : botoes){
            bt.setOnClickListener(listenerBotoes);
        }


        //retorna a view root do binding
        return  binding.getRoot();

    }

    //listener para os botoes
    private View.OnClickListener listenerBotoes = btPress -> {
//        Log.w("BOTAO", getContext().getResources().getResourceName(btPress.getId()));

        //obtem o nome do botoa
        String nomeBotao = getContext().getResources().getResourceName(btPress.getId());

        //extrai a posicao atraves do nome do botao
        String posicao = nomeBotao.substring(nomeBotao.length()-2);

        //extrai linha e coluna da string posicao
        int linha = Character.getNumericValue(posicao.charAt(0));
        int coluna = Character.getNumericValue(posicao.charAt(1));

        Log.w("BOTAO", linha+"");
        Log.w("BOTAO", coluna+"");

    };

}