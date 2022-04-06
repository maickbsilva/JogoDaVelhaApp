package br.senai.sp.cotia.tictactoeapp.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

import br.senai.sp.cotia.tictactoeapp.R;
import br.senai.sp.cotia.tictactoeapp.databinding.FragmentJogoBinding;
import br.senai.sp.cotia.tictactoeapp.util.PrefsUtil;


public class JogoFragment extends Fragment {

        //variavel para acessar os elementos do View
        private FragmentJogoBinding binding;

        //vetor de botoes para referenciar os botoes
        private Button[] botoes;

        //matriz de String que representa o tabuleiro
        private String[][] tabuleiro;

        //variaveis para os simbolos
        private String simbolo1, simbolo2, simbolo;

        //variavel random para ver quem inicia
        private Random random;

        //variavel para controlar o numero de jogadas
        private int numJogadas =0;

        //variaveis para o placar
        private int placarJog1 = 0, placarJog2 = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //habiltar o menu
        setHasOptionsMenu(true);

        //instanciar o biding
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

        //instanciar o tabuleiro
        tabuleiro = new String[3][3];

        //preenche a matriz com ""
        for (int i = 0; i < 3; i++){
            for (int j = 0; j <3; j++){
                tabuleiro[i][j] = "";
            }
        }

        //define os simbolos do jogador 1 e jogador 2
        simbolo1 = PrefsUtil.getSimboloJog1(getContext());
        simbolo2 = PrefsUtil.getSimboloJog2(getContext());

        //atualizando o placar com os simbolos
        binding.jogador1.setText(getResources().getString(R.string.jogador1, simbolo1));
        binding.jogador2.setText(getResources().getString(R.string.jogador2, simbolo2));

        //instanciar o random
        random = new Random();

        //sorteia quem iniciara o jogo
        sorteia();

        //chama a troca de cor do jogador
        atualizaVez();

        //retorna a view root do binding
        return  binding.getRoot();

    }

    private void sorteia(){
        //se gerar um valor TRUE, jogador 1 começa, caso contrario, jogador 2 começa

        //gera true e false aleatoriamente
        if (random.nextBoolean()){
            simbolo = simbolo1;
        }else{
            simbolo = simbolo2;
        }


    }

    private void atualizaPlacar(){
        binding.placar1.setText(placarJog1+"");
        binding.placar2.setText(placarJog2+"");
    }

    private void atualizaVez(){
        if (simbolo.equals(simbolo1)){
            binding.linear1.setBackgroundColor(Color.CYAN);
            binding.linear2.setBackgroundColor(getResources().getColor(R.color.creme));
        }else{
            binding.linear2.setBackgroundColor(Color.CYAN);
            binding.linear1.setBackgroundColor(getResources().getColor(R.color.creme));
        }

    }

    private boolean venceu(){
        //verificar se venceu nas linhas
        for (int li = 0; li <3; li++){
            if (tabuleiro[li][0].equals(simbolo) && tabuleiro[li][1].equals(simbolo) && tabuleiro[li][2].equals(simbolo)){
                return true;
            }
        }

        //verifica se venceu nas colunas
        for (int col = 0; col <3; col++){
            if (tabuleiro[0][col].equals(simbolo) && tabuleiro[1][col].equals(simbolo) && tabuleiro[2][col].equals(simbolo)){
                return true;
            }
        }

        //verifica se venceu nas diagonais
        if (tabuleiro[0][0].equals(simbolo) && tabuleiro[1][1].equals(simbolo) && tabuleiro[2][2].equals(simbolo)){
            return true;
        }

        if (tabuleiro[0][2].equals(simbolo) && tabuleiro[1][1].equals(simbolo) && tabuleiro[2][0].equals(simbolo)){
            return true;
        }

        return false;

    }

    private void reset(){
        for (Button bt : botoes){
            bt.setText("");
            bt.setBackgroundColor(getResources().getColor(R.color.azul));
            bt.setClickable(true);
        }
        //preenche a matriz com ""
        for (int i = 0; i < 3; i++){
            for (int j = 0; j <3; j++){
                tabuleiro[i][j] = "";
            }
        }
        numJogadas=0;
        sorteia();
        atualizaVez();

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //verificar qual item do menu foi selecionado
        switch (item.getItemId()){
            //caso seja a opção de resetar
            case R.id.menu_resetar:
                placarJog1 = 0;
                placarJog2 = 0;
                atualizaPlacar();
                reset();
                break;
            //caso seja a opcao de preferencias
            case R.id.menu_pref:
                NavHostFragment.findNavController(JogoFragment.this).navigate(R.id.action_jogoFragment_to_prefFragment);
                break;

        }

        return true;
    }

    //listener para os botoes
    private View.OnClickListener listenerBotoes = btPress -> {
//        Log.w("BOTAO", getContext().getResources().getResourceName(btPress.getId()));

        //incrementar numero de jogadas
        numJogadas++;

        //obtem o nome do botoa
        String nomeBotao = getContext().getResources().getResourceName(btPress.getId());

        //extrai a posicao atraves do nome do botao
        String posicao = nomeBotao.substring(nomeBotao.length()-2);

        //extrai linha e coluna da string posicao
        int linha = Character.getNumericValue(posicao.charAt(0));
        int coluna = Character.getNumericValue(posicao.charAt(1));

        //Log.w("BOTAO", linha+"");
        //Log.w("BOTAO", coluna+"");

        //preencher a posicao da matriz com o simbolo da vez
        tabuleiro[linha][coluna] = simbolo;

        //faz um casting da View para Button
        Button botao = (Button) btPress;

        //seta o simbolo no botao pressionado
        botao.setText(simbolo);

        //trocar o background color do botao para branco
        botao.setBackgroundColor(Color.GRAY);

        //desabilitar o botao que foi pressionado
        botao.setClickable(false);

        //verifica se venceu
        if (numJogadas >= 5 && venceu()){
            //informa que houve um vencedor
            Toast.makeText(getContext(), R.string.vencedor, Toast.LENGTH_LONG).show();

            if (simbolo.equals(simbolo1)){
                placarJog1++;
            }else{
                placarJog2++;
            }
            //atualiza o placar
            atualizaPlacar();
            //reseta
            reset();

        }else if(numJogadas == 9 && venceu() == false){
            Toast.makeText(getContext(), R.string.velha, Toast.LENGTH_LONG).show();
            reset();

        }else{

            //inverte os simbolos
            simbolo = simbolo.equals(simbolo1) ? simbolo2 : simbolo1;

            atualizaVez();

        }


    };
}