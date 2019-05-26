package edu.jit.PortScanner;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.regex.Pattern;

public class GUI {
    private JPanel rootPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextArea statusArea;
    private JButton startButton;
    private JLabel IPLable;
    private JLabel PortsLable;
    private JTextField a5TextField;
    private JTextArea textArea1;
    private JScrollPane scrollPane;

    Thread4Scan scanner;
    public GUI() {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent eve) {
                if(IPLable.getText()=="×"){
                    textArea1.setText("IP输入有误,请检查");
                    return;
                }
                textArea1.setText("正在扫描........");
                String IP=textField1.getText();
                String ports=textField2.getText();
                try {
                    int threads=Integer.parseInt(a5TextField.getText());
                    if(threads>100){
                        threads=100;
                        textArea1.append("线程数过大,自动调整为100\n");
                        a5TextField.setText("100");
                    }else if(threads<=0){
                        threads=1;
                        textArea1.append("线程数过小,自动调整为1\n");
                        a5TextField.setText("1");
                    }
                    Thread4Scan scanner=new Thread4Scan(IP,ports);
                    System.out.println("初始化扫描的端口:"+scanner.ports);
                    ThreadGroup tg=new ThreadGroup("tg");
                    for(int i=0;i<threads;i++){
                        Thread t=new Thread(tg,scanner,"t"+i);
                        t.start();
                    }
                    while (tg.activeCount()>0);     //TODO:这一行负责等待所有线程执行完扫描任务,此时GUI仍然不会更新(表现为卡死,但实际上线程仍在正常执行)
                    System.out.println("完成扫描,已开启的端口:"+scanner.avaliables);
                    textArea1.setText("完成扫描,已开启的端口:\n"+scanner.avaliables);
                    textArea1.setCaretPosition(textArea1.getText().length());
                }catch (UnvalidPortsInputException e){
                    textArea1.append("端口输入有误,请检查错误:\n\t"+e.error+"\n");
                }catch (NumberFormatException e){
                    textArea1.append("线程输入有误,请检查错误\n");
                }


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
        textArea1.setText(
                "使用方法:\n"+
                "将IP与端口填入上方文本框,IP仅能填写一个,多个端口可用英文逗号分隔,连续端口可用-连接\n"+
                "       例如:22,23,80-88,443\n"+
                "常用端口举例:\n"+
                " 21 \tFTP文件传输服务\n"+
                " 22 \tSSH安全的远程连接协议\n"+
                " 25 \t邮件传输协议\n"+
                " 80 \tHTTP网页服务\n"+
                " 443\tHTTPS加密的网页服务\n"
        );

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new GUI().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //testPorts();  //带GUI的调试
    }

    public static void testPorts(){
        //带GUI的调试
        String openPorts="2233,5555,5560";
        try{
            //在本机上开放端口
            String[] oPorts=openPorts.split(",");
            ServerSocket[] ss=new ServerSocket[oPorts.length];
            for(int i=0;i<oPorts.length;i++){
                ss[i]=new ServerSocket(Integer.parseInt(oPorts[i]));
                System.out.println("端口"+oPorts[i]+"已开放");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

