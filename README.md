# PortScanner
## 文件列表
下面这个列表列出了部分这个程序所涉及的文件
- README.md
- src
  - edu.jit.PortScanner
    - GUI.form
    - GUI.java
    - TestClass.java
    - Thread4Scan.java
    - UnvalidPortsInputException.java  


针对src文件夹,它包含了以下的几个文件

- GUI.form
  - 生成程序可交互界面(GUI)的组件清单,上面记录着所有组件的摆放方式
- GUI.java
  - 与GUI.form相绑定,用于处理交互事件发生时所需要做的事,也是 ** 整个程序的执行入口类 **  
- TestClass.java
  - 不带GUI,以控制台输出结果的用于调试的类,可以删除
- Thread4Scan.java
  - 执行端口扫描的重要方法和多线程处理机制都在这里
- UnvalidPortsInputException.java  
  - 这是一个自定义的异常类,当你填错了端口号的时候抛出,交由上一级调用的方法处理

## 部署
### 从源码进行
  [点击这里](https://github.com/ms0ng/PortScanner/archive/v0.1.zip)或从本页面下载源码,然后使用Eclipse或者IntelliJ IDEA导入项目,从GUI.java或者TestClass.java运行  

        Tip.这个程序是我在IntelliJ IDEA上写的
### 从JAR进行
1. 请到本项目的 [Realse](https://github.com/ms0ng/PortScanner/releases) 界面下载PortScanner.jar
2. 安装好[Java运行环境(JRE)](https://www.java.com/zh_CN/)后双击PortScanner.jar即可运行

## 已知BUG
- 单击开始扫描按钮时GUI界面会卡住,直到扫描完才会更新内容

         这是由于GUI.java中第54行while()是阻塞的,因此在多线程任务全部执行完成前,程序不会继续执行完后面的代码
