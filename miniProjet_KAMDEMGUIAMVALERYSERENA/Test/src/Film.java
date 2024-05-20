import javax.swing.*;
import java.awt.*;

public class Film extends JFrame {
    private JButton ACCUEILButton;
    private JButton FILMSButton;
    private JButton DOCUMENTAIREButton;
    private JButton PLAYButton;
    private JPanel FilmPanel;
    private JButton LISTEButton;
    private JComboBox comboBox2;
    private JButton TELECHARGERButton;
    private JButton SERIEButton;

    public Film(JFrame parent){

            setTitle("FILM");
            setContentPane(FilmPanel);
            setMinimumSize(new Dimension(500, 700));

            setLocationRelativeTo(parent);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setVisible(true);

    }

        public static void main (String [] args) {

            Film film = new Film(null);
        }


}
