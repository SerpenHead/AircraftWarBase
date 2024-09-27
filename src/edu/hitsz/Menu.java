package edu.hitsz;

import edu.hitsz.application.Game;
import edu.hitsz.application.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    private JPanel StartMenu;
    private JButton DifficultyLevel_1;
    private JButton DifficultyLevel_2;
    private JButton DifficultyLevel_3;
    private JTextField TextField;
    private JButton MusicSettings;

    public Menu() {
        DifficultyLevel_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game game = new Game(1, 1);
                Main.cardPanel.add(game);
                Main.cardLayout.last(Main.cardPanel);
                game.action();
            }
        });
        DifficultyLevel_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game game = new Game(2,1);
                Main.cardPanel.add(game);
                Main.cardLayout.last(Main.cardPanel);
                game.action();
            }
        });
        DifficultyLevel_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game game = new Game(3,1);
                Main.cardPanel.add(game);
                Main.cardLayout.last(Main.cardPanel);
                game.action();
            }
        });
    }

    public JPanel getMenuMainPanel() {
        return StartMenu;
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("StartMenu");
        frame.setContentPane(new Menu().StartMenu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
