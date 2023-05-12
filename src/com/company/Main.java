package com.company;
import com.company.GameView.DisasterView;
import com.company.GameView.GameView;
import com.company.GameView.RedSeaGameView;
import com.company.GameView.TenCommandmentsView;
import com.company.Sprite.Moses;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JPanel implements KeyListener{
    public static final int CELL = 50;
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    public static final int ROW = HEIGHT / CELL;
    public static final int COLUMN = WIDTH / CELL;

    Moses moses;
    public static GameView gameView;
    private int level;

    public Main() {
        level = 1;
        resetGame(new DisasterView());
        addKeyListener(this);
    }

    public void resetGame(GameView game) {
        moses = new Moses(1,1);
        gameView = game;
        repaint();
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameView.drawView(g);
        moses.draw(g);
        requestFocusInWindow();
    }

    /* public void painComponent(Graphics g) {
        gameView.drawView(g);
        moses.draw(g);

    } */
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(new Main());
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setResizable(false);

    }
    private boolean changeLevel(String result) {
        if(result.equals("Next level")) {
            level ++;
            if(level == 2) {
                resetGame(new RedSeaGameView());
            } else if (level == 3) {
                resetGame(new TenCommandmentsView());
            }
            return true;
        }
        return false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Point mosesPoint = moses.getRelativePosition();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if(mosesPoint.y > 1) {
                    String result = moses.overlap(mosesPoint.x, mosesPoint.y - 1);
                    if(result.equals("Die")) {
                        //reset Game
                        level = 1;
                        JOptionPane.showMessageDialog(this,"You die.Please try again.");
                        resetGame(new DisasterView());

                        return;
                    }
                    if(!result.equals("Cannot move")) {
                        mosesPoint.y -= 1;
                    }
                    if(result.equals("Game over")) {
                        JOptionPane.showMessageDialog(this,"You win the game!!");
                        return;
                    }
                    if(changeLevel(result)) return;
                }
                break;
            case KeyEvent.VK_DOWN:
                if(mosesPoint.y < ROW) {
                    String result = moses.overlap(mosesPoint.x, mosesPoint.y + 1);
                    if(result.equals("Die")) {
                        //reset Game
                        level = 1;
                        JOptionPane.showMessageDialog(this,"You die.Please try again.");
                        resetGame(new DisasterView());

                        return;
                    }
                    if(!result.equals("Cannot move")) {
                        mosesPoint.y += 1;
                    }
                    if(result.equals("Game over")) {
                        JOptionPane.showMessageDialog(this,"You win the game!!");
                        return;
                    }
                    if(changeLevel(result)) return;

                }
                break;
            case KeyEvent.VK_RIGHT:
                if(mosesPoint.x < COLUMN) {
                    String result = moses.overlap(mosesPoint.x + 1, mosesPoint.y);
                    if(result.equals("Die")) {
                        //reset Game
                        level = 1;
                        JOptionPane.showMessageDialog(this,"You die.Please try again.");
                        resetGame(new DisasterView());

                        return;
                    }
                    if(!result.equals("Cannot move")) {
                        mosesPoint.x += 1;
                    }
                    if(result.equals("Game over")) {
                        JOptionPane.showMessageDialog(this,"You win the game!!");
                        return;
                    }
                    if(changeLevel(result)) return;

                }
                break;
            case KeyEvent.VK_LEFT:
                if(mosesPoint.x > 1) {
                    String result = moses.overlap(mosesPoint.x - 1, mosesPoint.y);
                    if(result.equals("Die")) {
                        //reset Game
                        level = 1;
                        JOptionPane.showMessageDialog(this,"You die.Please try again.");
                        resetGame(new DisasterView());

                        return;
                    }
                    if(!result.equals("Cannot move")) {
                        mosesPoint.x -= 1;
                    }
                    if(result.equals("Game over")) {
                        JOptionPane.showMessageDialog(this,"You win the game!!");
                        return;
                    }
                    if(changeLevel(result)) return;

                }
                break;
        }
        moses.setPosition(mosesPoint);
        repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}