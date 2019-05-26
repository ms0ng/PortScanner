package edu.jit.PortScanner;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

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
                if(Integer.parseInt(plist[i])<=0||Integer.parseInt(plist[i])>65535)throw new UnvalidPortsInputException(plist[i]);
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

}
