package stud.g01.problem.npuzzle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.*;

public class GUI extends javax.swing.JFrame {

    public static void createAndShowGUI() {

        // ȷ��һ��Ư������۷��
        JFrame.setDefaultLookAndFeelDecorated(false);

        // ���������ô���
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ��� "Hello World" ��ǩ
//        JLabel label = new JLabel("Hello World");
//        frame.getContentPane().add(label);

        // ��ʾ����
        frame.pack();
        frame.setVisible(true);
    }

}
