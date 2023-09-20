import javax.swing.*;
import java.awt.*;
import java.util.Random;
/* 
 * Le serpent apparait dans un endroit au hasard au début de chaque partie
 * Il se déplace et sa taille augmente quand il mange la pomme.
*/
public class Snake extends JPanel {
    Random rand = new Random();
    int[] x; int[] y;
    int bodySize; // taille du serpent
    int UNITE;
    char direction; // direction du serpent 

    // Constructeur avec largeur et hauteur du terrain en paramètre en plus de la variable UNITE désignant le nombre de carré 
    Snake(int largeur, int hauteur, int unite){
        UNITE = unite;
        x = new int[largeur];
        y = new int[hauteur];
        apparitionSnake(largeur, hauteur);
    }

    // Dessine le serpent
    public void drawSnake(Graphics g){
        g.setColor(Color.green);
        for (int i = 0; i < bodySize; i++)
            g.fillRect(x[i], y[i], UNITE,UNITE);
    }

    // Fonction fesant apparaître le serpent dans un endroit au hasard
    void apparitionSnake(int l, int h){
        direction = ' ';
        bodySize = 1;
        x[0] = rand.nextInt(l/UNITE) * UNITE;
        y[0] = rand.nextInt(h/UNITE) * UNITE;
    }

    /*
     * Déplacement du serpent
     */
    void move(){
        if (bodySize > 1){
            for (int i = bodySize; i > 0; i--) {
                x[i] = x[i - 1];
                y[i] = y[i - 1];
            }
        }
        if (direction == 'r')
            x[0] += UNITE;
        if (direction == 'l')
            x[0] -= UNITE;
        if (direction == 'u')
            y[0] -= UNITE;
        if (direction == 'd')
            y[0] += UNITE;
    }

    public void setDirection(char c){ direction = c; }
    public void eat(){ bodySize ++; }

    public int getX() { return x[0]; }
    public int getY() { return y[0]; }
    public int getBodySize() { return bodySize; }
}
