import java.awt.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener {
    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textField = new JLabel();
    JButton[] buttons = new JButton[9];
    JButton resetGame = new JButton();
    boolean player1_turn;

    TicTacToe(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textField.setBackground(new Color(25,25,25));
        textField.setForeground(new Color(25,255,0));
        textField.setFont(new Font("Ink Free",Font.BOLD,75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Tic-Tac-Toe");
        textField.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,800,100);

        button_panel.setLayout(new GridLayout(3,3));
        button_panel.setBackground(new Color(150,150,150));


        for(int i=0;i<9;i++){
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli",Font.BOLD,120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }
/*
        resetGame.setText("Reset game?");
        resetGame.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Resetting game");
                    }
                }
        );
*/
        title_panel.add(textField);
        frame.add(title_panel,BorderLayout.NORTH);
        frame.add(button_panel);
        frame.add(resetGame,BorderLayout.SOUTH);
        firstPlayer();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0;i<9;i++){
            if(e.getSource()==buttons[i]){
                if(player1_turn){
                    if(buttons[i].getText()==""){
                        buttons[i].setForeground(new Color(255,0,0));
                        buttons[i].setText("X");
                        player1_turn=false;
                        textField.setText("O turn");
                        checkWinner();
                    }
                }
                else{
                    if(buttons[i].getText()==""){
                        buttons[i].setForeground(new Color(0,0,255));
                        buttons[i].setText("O");
                        player1_turn=true;
                        textField.setText("X turn");
                        checkWinner();
                    }
                }
            }
        }
    }
    public void firstPlayer(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(random.nextInt(2)==0){
            player1_turn=true;
            textField.setText("X turn");
        }
        else{
            player1_turn=false;
            textField.setText("O turn");
        }
    }

    public void checkWinner(){
        int[][] winningCombinations = {
                {0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{2,4,6}
        };
        for(int[] comb:winningCombinations){
            if(buttons[comb[0]].getText()=="X" && buttons[comb[1]].getText()=="X"
            && buttons[comb[2]].getText()=="X"){
                winner(comb[0],comb[1],comb[2],"X");
            }
            if(buttons[comb[0]].getText()=="O" && buttons[comb[1]].getText()=="O"
                    && buttons[comb[2]].getText()=="O"){
                winner(comb[0],comb[1],comb[2],"O");
            }
        }
    }
    public void winner(int a,int b,int c,String winner){
        buttons[a].setBackground(new Color(0,255,0));
        buttons[b].setBackground(new Color(0,255,0));
        buttons[c].setBackground(new Color(0,255,0));
        for(int i=0;i<9;i++){
            buttons[i].setEnabled(false);
        }
        textField.setText(winner+" wins!!");
    }


}
