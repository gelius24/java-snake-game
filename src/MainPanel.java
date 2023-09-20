import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

/*
 * JPanel contetant le terrain (la ou le serpent se déplace)
 * FlowLayout par défaut
*/
public class MainPanel extends JPanel{
    // Les variables de la classe
    int LARGEUR = 500; int HAUTEUR = 500; int UNITE = 20;
    Snake snake = new Snake(LARGEUR, HAUTEUR, UNITE);
    Apple apple = new Apple(LARGEUR, HAUTEUR, UNITE);
    boolean playing = false;
    boolean gridOn = true;
    Timer timer;
    Ecouteur ecouteur = new Ecouteur();
    BottomPanel bottomPanel;

    // Le constructeur
    MainPanel(BottomPanel bottomPanel){
        config();
        this.bottomPanel = bottomPanel;
    }

    /*
     * Dessiner la grille ou le serpent se déplace
     */
    public void drawGrid(Graphics g){
        g.setColor(Color.WHITE);
        for (int i = 0; i < LARGEUR/UNITE; i++)
            g.drawLine(i*UNITE,0, i*UNITE, HAUTEUR);
        for (int i = 0; i < HAUTEUR/UNITE; i++)
            g.drawLine(0,i*UNITE, LARGEUR, i*UNITE);
    }

    /* 
     * Cette fonction vien de JPanel elle est automatiquement invoqué a chaque changement
    */
    public void paintComponent(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0,0,LARGEUR,HAUTEUR);
        if (gridOn)
            drawGrid(g);
        snake.drawSnake(g);
        apple.drawApple(g);
    }

    /* 
     * fonction activé jusqu'a la fin d'une partie
    */
    public void play(){
        timer = new Timer(90, e -> {
            checkCollision();
            snake.move();
            repaint();
        });
        timer.start();
    }

    /*
     * fonction activé a la fin d'une partie
     */
    public void gameOver(){
        snake.apparitionSnake(LARGEUR, HAUTEUR);
        apple.newApple(LARGEUR, HAUTEUR);
        bottomPanel.score = 0;
        bottomPanel.myLabel.setText("Score :" + bottomPanel.score);
        playing = false;
        timer.stop();
    }

    /*
     * Fonction qui détermine si le serpent est hors jeu
     * ou si la pommme a été mangé
     */
    void checkCollision(){
        // si le serpent mange la pomme
        if (snake.getX() == apple.getX() && snake.getY() == apple.getY()){
            snake.eat();
            apple.newApple(LARGEUR, HAUTEUR);
            bottomPanel.incrementScore();
            bottomPanel.myLabel.setText("Score :" + bottomPanel.score);
        }
        // si le serpent sort du terrain
        if (snake.getX() < 0 || snake.getX() > LARGEUR || snake.getY() < 0 || snake.getY() > HAUTEUR)
            gameOver();
        // si le serpent se mord
        if (snake.getBodySize() > 1){
            for (int i = 1; i < snake.getBodySize(); i++){
                if (snake.getX() == snake.x[i] && snake.getY() == snake.y[i])
                    gameOver();
            }
        }
    }

    /*
     * change la direction du serpent selon la dernière touche pesé
    */
    public class Ecouteur extends KeyAdapter{
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            if (!playing) {
                playing = true;
                play();
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
                snake.setDirection('r');
            if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
                snake.setDirection('l');
            if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W)
                snake.setDirection('u');
            if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
                snake.setDirection('d');
            if (e.getKeyCode() == KeyEvent.VK_G){
                if (!gridOn)
                    gridOn = true;
                else
                    gridOn = false;
            }
        }}

    public void config(){
        setBorder(BorderFactory.createLineBorder(Color.WHITE));
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(LARGEUR, HAUTEUR));
        setFocusable(true);
        addKeyListener(ecouteur);
    }
}
