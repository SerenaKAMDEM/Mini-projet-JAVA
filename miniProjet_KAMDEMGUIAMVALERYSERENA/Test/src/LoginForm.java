import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginForm extends JDialog {
    private JTextField tfEmail;
    private JButton btnOK;
    private JButton btnCancel;
    private JPasswordField pfPassword;
    private JPanel LoginPanel;
    
    public LoginForm(JFrame parent){
        setModal(true);
        setTitle("Login");
        setContentPane(LoginPanel);
        setMinimumSize(new Dimension(500, 700));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = tfEmail.getText();
                String password = String.valueOf(pfPassword.getPassword());
                
                user = getAuthenticateUser(email, password);
                
                if (user != null){
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(LoginForm.this,
                            "Email or Password Invalid",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                }
            
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        setVisible(true);
    }
    
    public User user;
    private User getAuthenticateUser(String email,String password){
        User user = null;
        
        final String DB_URL = "jdbc:mysql://localhost/phpmyadmin/index.php?route=/database/structure&db=Netflix";
        final String USERNAME = "root";
        final  String PASSWORD = "";
        
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME,PASSWORD);
            
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()){
                user = new User();
                user.name = resultSet.getString("name");
                user.surname = resultSet.getString("surname");
                user.birthday = resultSet.getString("birthday");
                user.rib = resultSet.getString("rib");
                user.email = resultSet.getString("email");
                user.phone = resultSet.getString("phone");
                user.password = resultSet.getString("password");
            }
            
            stmt.close();
            conn.close();
            
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return user;
    }
    
    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm(null);
        User user = loginForm.user;
        if (user != null){
            System.out.println("successful Authentifcation of: " + user.name + user.surname);
            System.out.println("            Surname: " + user.surname);
            System.out.println("             Birthday: " + user.birthday);
            System.out.println("                 RIB: " + user.rib);
            System.out.println("             Email: " + user.email);
            System.out.println("             Phone: " + user.phone);
        }
        else {
            System.out.println("Authentifcation cancelled");
        }
    }
}

