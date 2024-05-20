import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Subcription extends JFrame {
    private JPanel SubscribePanel;
    private JComboBox cbxJour;
    private JComboBox cbxMois;
    private JButton btnRen;
    private JButton btnResil;
    private JButton btnProfil;
    private JComboBox cbxAnnee;
    private JComboBox comboBox1;

    public Subcription(JFrame parent) {

        setTitle("ABONNEMENT");
        setContentPane(SubscribePanel);
        setMinimumSize(new Dimension(500, 700));

        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);


        btnRen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main (String [] args){

        Subcription subcription = new Subcription(null);
    }

}

