package View;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BookList {

    public BookList() {
        MenuBookList();
    }

    public static void MenuBookList() {

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

        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

        JButton transactionsButton = new JButton("Menu Transaksi");
        transactionsButton.setBounds(50, 180, 100, 30);
        jPanel.add(transactionsButton);


        myFrame.add(jPanel);
        myFrame.setVisible(true);

    }

    public static void main(String[] args) {
        MenuBookList();
    }
}
