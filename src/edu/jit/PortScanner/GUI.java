package edu.jit.PortScanner;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.regex.Pattern;

public class GUI {
    private JPanel rootPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextArea statusArea;
    private JButton startButton;
    private JLabel IPLable;
    private JLabel PortsLable;

    Thread4Scan scanner;
    public GUI() {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        textField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if( Pattern.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}", textField1.getText())){
                    IPLable.setText("√");
                }else{
                    IPLable.setText("×");
                }
            }
        });
        textField2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                //判断端口正确
            }
        });

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new GUI().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public  boolean portisValid(int port){
        if(port<=0||port>65535)return false;
        return true;
    }
}

