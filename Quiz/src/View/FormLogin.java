package View;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class FormLogin {

    public FormLogin() {
        MenuLogin();
    }

    public static void MenuLogin() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        final int FRAME_WIDTH = 1200;
        final int FRAME_HEIGHT = 800;

        int start_x = screenWidth / 2 - (FRAME_WIDTH / 2);
        int start_y = screenHeight / 2 - (FRAME_HEIGHT / 2);

        JFrame myFrame = new JFrame("Form Login");

        myFrame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT);
        myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel formLogin = new JPanel();
        formLogin.setLayout(null);
        formLogin.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

        JLabel labelEmail = new JLabel("Input Email:");
        labelEmail.setBounds(50, 50, 200, 50);
        formLogin.add(labelEmail);

        JTextField textFieldEmail = new JTextField();
        textFieldEmail.setBounds(250, 60, 300, 30);
        formLogin.add(textFieldEmail);

        JLabel labelPassword = new JLabel("Input Password:");
        labelPassword.setBounds(50, 120, 200, 50);
        formLogin.add(labelPassword);

        JPasswordField textFieldPassword = new JPasswordField();
        textFieldPassword.setBounds(250, 130, 300, 30);
        formLogin.add(textFieldPassword);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(250, 180, 100, 30);
        formLogin.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = textFieldEmail.getText();
                String password = new String(textFieldPassword.getPassword());

                if (email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Email atau Password tidak boleh kosong.");
                    return;
                }

                if (checkLogin(email, password)) {
                    JOptionPane.showMessageDialog(null, "Login berhasil! Menu Book List akan ditampilkan.");
                    myFrame.dispose();
                    new BookList();
                }
            }
        });

        myFrame.add(formLogin);
        myFrame.setVisible(true);
    }

    public static boolean checkLogin(String email, String password) {
        Connection connection = null;

        try {
            String url = "jdbc:mysql://localhost:3306/quiz_pbo";
            String username = "root"; 
            String pass = "";

            connection = DriverManager.getConnection(url, username, pass);

            String query = "SELECT * FROM users WHERE email = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                if (storedPassword.equals(password)) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Login gagal. Password salah.");
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Login gagal.");
                return false;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan SQL: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
    public static void main(String[] args) {
        MenuLogin();
    }
}
