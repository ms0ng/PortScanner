package edu.jit.PortScanner;
//当Thread4Scan类无法识别填写的端口时,抛出这个异常
public class UnvalidPortsInputException extends Exception {
    String error;
    UnvalidPortsInputException(){
        error=null;
    }

    //储存导致发生异常的文本
    UnvalidPortsInputException(String str){
        this.error=str;
    }
    String printError(){
        System.out.println(error);
        return error;
    }
}
