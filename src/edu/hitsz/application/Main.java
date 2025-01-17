package edu.hitsz.application;

import edu.hitsz.Menu;

import javax.swing.*;
import java.awt.*;

/**
 * 程序入口
 *
 * @author hitsz
 */
public class Main {

    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;
    public static final CardLayout cardLayout = new CardLayout(0,0);
    public static final JPanel cardPanel = new JPanel(cardLayout);


    public static void main(String[] args) {

        System.out.println("Hello Aircraft War");

        JFrame frame = new JFrame("main page");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(cardPanel);

        Menu menu = new Menu();
        cardPanel.add(menu.getMenuMainPanel());
        frame.setVisible(true);
    }
}
