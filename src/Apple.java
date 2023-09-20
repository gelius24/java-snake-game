import javax.swing.*;
import java.awt.*;
import java.util.Random;
/* 
 * La pomme apparait a un endroit au hasard dans la limite du terrain
 * quand le serpent le mange
*/
class Apple extends JPanel{
    Random rand = new Random();
    int x,y,UNITE;

    Apple(int largeur, int hauteur, int unite){
        UNITE = unite;
        newApple(largeur, hauteur);
    }

    public void newApple(int l, int h){
        x = rand.nextInt(l / UNITE) * UNITE;
        y = rand.nextInt(h / UNITE) * UNITE;
    }

    public void drawApple(Graphics g){
        g.setColor(Color.RED);
        g.fillOval(x,y,UNITE,UNITE);
    }

    public int getX() { return x; }
    public int getY() { return y; }
}