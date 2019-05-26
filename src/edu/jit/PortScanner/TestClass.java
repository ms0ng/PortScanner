package edu.jit.PortScanner;

import java.io.IOException;
import java.net.ServerSocket;
//用于不带GUI的调试类
public class TestClass {
    /*
    openPorts:尝试在本机上开放的端口,多个端口用英文逗号分开,不支持连续端口
    scanPorts:尝试扫描的端口,多个端口用英文逗号分开,连续端口用-连接.
    threads:线程数
     */
    static String openPorts="233,344,65500";
    static String scanPorts="22-25,55,66,77,88,200-233,340-350,65500";
    static int threads=5;
    public static void main(String[] args) {
        try {
            //在本机上开放端口
            String[] oPorts=openPorts.split(",");
            ServerSocket[] ss=new ServerSocket[oPorts.length];
            for(int i=0;i<oPorts.length;i++){
                ss[i]=new ServerSocket(Integer.parseInt(oPorts[i]));
            }

            Thread4Scan scanner=new Thread4Scan("127.0.0.1",scanPorts);
            System.out.println("初始化扫描的端口:"+scanner.ports);
            ThreadGroup tg=new ThreadGroup("tg");
            for(int i=0;i<threads;i++){
                Thread t=new Thread(tg,scanner,"t"+i);
                t.start();
            }
            while (tg.activeCount()>0);
            System.out.println("已开启的端口:"+scanner.avaliables);

            //调试结束,关闭端口
            for(int i=0;i<ss.length;i++){
                ss[i].close();
            }


        }catch (UnvalidPortsInputException e){
            e.printError();
        }catch (IOException e){
            //e.printStackTrace();
            System.out.println("Scoket建立失败,请检查端口是否被占用!");
        }
    }
}
