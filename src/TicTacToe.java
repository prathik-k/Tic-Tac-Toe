import java.awt.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class TicTacToe implements ActionListener {
    Board mainBoard = new Board();
    Random random = new Random();
    boolean player1_turn;
    boolean noWinner;
    TicTacToe(){
        for(int i=0;i<9;i++){
            mainBoard.buttons[i].addActionListener(this);
        }
        mainBoard.resetGame.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        reset();
                    }
                }
        );
        mainBoard.exit.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                }
        );
        firstPlayer();
    }

    private void reset() {
        for(int i=0;i<9;i++){
            mainBoard.buttons[i].setEnabled(true);
            mainBoard.buttons[i].setText("");
            mainBoard.buttons[i].setBackground(new JButton().getBackground());
        }
        firstPlayer();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0;i<9;i++){
            if(e.getSource()==mainBoard.buttons[i]){
                if(player1_turn){
                    if(mainBoard.buttons[i].getText()==""){
                        mainBoard.buttons[i].setForeground(new Color(255,0,0));
                        mainBoard.buttons[i].setText("X");
                        player1_turn=false;
                        mainBoard.textField.setText("O turn");
                        checkWinner();
                    }
                }
                else{
                    if(mainBoard.buttons[i].getText()==""){
                        mainBoard.buttons[i].setForeground(new Color(0,0,255));
                        mainBoard.buttons[i].setText("O");
                        player1_turn=true;
                        mainBoard.textField.setText("X turn");
                        checkWinner();
                    }
                }
            }
        }
    }
    public void firstPlayer(){

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(random.nextInt(2)==0){
            player1_turn=true;
            mainBoard.textField.setText("X turn");
        }
        else{
            player1_turn=false;
            mainBoard.textField.setText("O turn");
        }
    }

    public void checkWinner(){
        int[][] winningCombinations = {
                {0,1,2},{3,4,5},{6,7,8},{0,4,8},{1,4,7},{2,5,8},{2,4,6}
        };
        for(int[] comb:winningCombinations){
            if(mainBoard.buttons[comb[0]].getText()=="X" && mainBoard.buttons[comb[1]].getText()=="X"
            && mainBoard.buttons[comb[2]].getText()=="X"){
                System.out.printf("%d%d%d", comb[0],comb[1],comb[2]);
                winner(comb[0],comb[1],comb[2],"X");
            }
            if(mainBoard.buttons[comb[0]].getText()=="O" && mainBoard.buttons[comb[1]].getText()=="O"
                    && mainBoard.buttons[comb[2]].getText()=="O"){
                winner(comb[0],comb[1],comb[2],"O");
                System.out.printf("%d%d%d", comb[0],comb[1],comb[2]);
            }
        }
        for(int i=0;i<9;i++) {
            if(mainBoard.buttons[i].getText()==""){
                noWinner=false;
                break;
            }
            else noWinner=true;
        }
        if(noWinner){
            noWinner=false;
            reset();
        }
    }
    public void winner(int a,int b,int c,String winner){
        mainBoard.buttons[a].setBackground(new Color(0,255,0));
        mainBoard.buttons[b].setBackground(new Color(0,255,0));
        mainBoard.buttons[c].setBackground(new Color(0,255,0));
        for(int i=0;i<9;i++){
            mainBoard.buttons[i].setEnabled(false);
        }
        mainBoard.textField.setText(winner+" wins!!");
        reset();
    }


}
