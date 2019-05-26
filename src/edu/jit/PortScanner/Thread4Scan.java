package edu.jit.PortScanner;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Thread4Scan implements Runnable{
    String IP;
    ArrayList<Integer> ports=new ArrayList<>();
    ArrayList<Integer> avaliables=new ArrayList<>();
    Thread4Scan(String IP,int port){
        this.IP=IP;
        ports.add(port);
    }
    Thread4Scan(String IP,int[] ports) {
        for (int i = 0; i < ports.length; i++) this.ports.add(ports[i]);
    }
    Thread4Scan(String IP,ArrayList<Integer> ports){
        this.ports=(ArrayList<Integer>) ports.clone();
    }
    Thread4Scan(String IP,String Ports) throws UnvalidPortsInputException{
        //String Ports中包含有的文本可能有xx,xx,xx-xx
        String[] plist=Ports.split(",");
        for(int i=0;i<plist.length;i++){
            try {
                ports.add(Integer.parseInt(plist[i]));
            }catch (NumberFormatException e){
                try {
                    String[] mports=plist[i].split("-");
                    int minPort=Integer.parseInt(mports[0]);
                    int maxPort=Integer.parseInt(mports[1]);
                    if(minPort>=maxPort)throw new UnvalidPortsInputException(plist[i]);
                    for (;minPort<=maxPort;minPort++)ports.add(minPort);
                }catch (NumberFormatException e1){
                    throw new UnvalidPortsInputException(plist[i]);
                }
            }
        }
    }

    public void add(int port){
        ports.add(port);
    }
    public void add(String ports){

    }

    @Override
    public void run() {
        int port;
        while (!ports.isEmpty()){
            synchronized (this){
                if(ports.isEmpty())return;
                port=ports.get(0);
                ports.remove(0);
            }
            try {
                Socket socket=new Socket(IP,port);
                socket.close();
                avaliables.add(port);
                System.out.println("端口"+port+"开启");
            }catch (IOException e){
                //端口不通,什么也不做
                System.out.println("端口"+port+"关闭");
            }
        }

    }

    public static void main(String[] args) {
        String str="22,33,44-55,333-350";
        try {
            ServerSocket socket=new ServerSocket(343);

            Thread4Scan scanner=new Thread4Scan("127.0.0.1",str);
            System.out.println("初始化扫描的端口:"+scanner.ports);
            ThreadGroup tg=new ThreadGroup("tg");
            int threads=5;
            for(int i=0;i<threads;i++){
                Thread t=new Thread(tg,scanner,"t"+i);
                t.start();
            }
            while (tg.activeCount()>0);
            System.out.println("已开启的端口:"+scanner.avaliables);
            socket.close();


        }catch (UnvalidPortsInputException e){
            e.printError();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
