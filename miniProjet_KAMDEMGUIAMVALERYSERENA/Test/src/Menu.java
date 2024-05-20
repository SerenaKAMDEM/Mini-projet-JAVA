import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame{
    private JButton btnAccueil;
    private JPanel MenuPanel;
    private JButton btnDocumentaires;
    private JButton btnSeries;
    private JButton btnFilms;
    private JComboBox comboBox1;

    public Menu(JFrame parent) {

        setTitle("MENU");
        setContentPane(MenuPanel);
        setMinimumSize(new Dimension(500, 700));

        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main (String [] args){

        Menu menu = new Menu(null);
    }
}
