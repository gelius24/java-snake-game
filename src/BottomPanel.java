import javax.swing.*;
import java.awt.*;

/* 
 * JPanel au bas de l'écran contenant des information diverse
*/
public class BottomPanel extends JPanel {
    int score = 0;
    JLabel myLabel = new JLabel("Score :" + score);
    JLabel label1 = new JLabel("Click G to add grid");

    BottomPanel(){
        setPreferredSize(new Dimension(500, 80));
        setBorder(BorderFactory.createLineBorder(Color.WHITE));
        setBackground(Color.black);
        myLabel.setForeground(Color.white);
        label1.setForeground(Color.white);
        add(myLabel);
        add(label1);
    }

    public void incrementScore(){ score++;}
}