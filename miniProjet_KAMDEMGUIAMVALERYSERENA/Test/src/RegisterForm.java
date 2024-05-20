
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;


public class RegisterForm extends JDialog{
    private JTextField tfNAME;
    private JTextField tfSurName;
    private JTextField tfBirthday;
    private JTextField tfRIB;
    private JTextField tfEmail;
    private JTextField tfPhone;
    private JPasswordField pfpassword;
    private JPasswordField pfConfirmPassword;
    private JButton btnRegister;
    private JButton btnCancel;
    private JPanel registerPanel;


    public RegisterForm(JFrame parent){
        super(parent);
        setTitle("create a new account");
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(500, 700));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
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

    private void registerUser() {
        String name = tfNAME.getText();
        String surname = tfSurName.getText();
        String birthday = tfBirthday.getText();
        String rib = tfRIB.getText();
        String email = tfEmail.getText();
        String phone = tfPhone.getText();
        String password = String.valueOf(pfpassword.getPassword());
        String confirmpassword = String.valueOf(pfConfirmPassword.getPassword());

        if (name.isEmpty() || surname.isEmpty() || birthday.isEmpty() || rib.isEmpty() || email.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Please enter all fiels",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmpassword)) {
            JOptionPane.showMessageDialog(this,
                    "confirm password does not match",
                    "try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        user = addUserToDatabase(name,surname,birthday,rib,phone,email,password);
        if(user != null){
            dispose();
        }
        else {
            JOptionPane.showMessageDialog(this,
                    "Failed to register new user",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public User user;
    private User addUserToDatabase(String name, String surname, String birthday, String rib, String phone, String email, String password) {
        User user = null;
        final String DB_URL = "jdbc:mysql://localhost/phpmyadmin/index.php?route=/database/structure&db=Netflix";
        final String USERNAME = "root";
        final  String PASSWORD = "";

        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME,PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO users (name,surname,birthday,rib,email,phone,password) " + "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = ((Connection) conn).prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,surname);
            preparedStatement.setString(3,birthday);
            preparedStatement.setString(4,rib);
            preparedStatement.setString(5,email);
            preparedStatement.setString(6,phone);
            preparedStatement.setString(7,password);

            //INSERT ROW INTO THE TABLE
            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0){
                user = new User();
                user.name = name;
                user.surname = surname;
                user.birthday = birthday;
                user.rib = rib;
                user.email = email;
                user.phone = phone;
                user.password = password;
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }


    public static void main(String[] args) {
        RegisterForm myForm = new RegisterForm(null);
        User user = myForm.user;
        if (user != null){
            System.out.println("successful registration of: " + user.name + user.surname);
        }
        else {
            System.out.println("registration cancelled");
        }
    }
}