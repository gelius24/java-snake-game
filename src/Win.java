import javax.swing.*;
import java.awt.*;

/*
 * BorderLayout par défaut
 */
public class Win extends JFrame {
    // variable de classe & d'état
    JPanel contentPane = (JPanel) getContentPane();
    JPanel mainPanel;
    JPanel bottomPanel;

    // le constructeur
    public Win(){
        bottomPanel = new BottomPanel();
        mainPanel = new MainPanel((BottomPanel)bottomPanel);
        configuration();
    }

    // Cette fonction donne les configs basic de la fenêtre
    public void configuration(){
        setTitle("JEU DU SERPENT");
        setSize(600, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane.setLayout(new FlowLayout());
        contentPane.setBackground(Color.BLACK);
        contentPane.add(mainPanel);
        contentPane.add(bottomPanel);
    }

    
}