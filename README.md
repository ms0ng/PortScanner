# PortScanner
---
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
      * 生成程序可交互界面(GUI)的组件清单,上面记录着所有组件的摆放方式
- GUI.java  
      * 与  GUI.form 相绑定,用于处理交互事件发生时所需要做的事,也是整个程序的执行入口类
- TestClass.java
      * 不带GUI,以控制台输出结果的用于调试的类,可以删除
- Thread4Scan.java
      * 执行端口扫描的重要方法和多线程处理机制都在这里
- UnvalidPortsInputException.java  
      * 这是一个自定义的异常类,当你填错了端口号的时候抛出,交由上一级调用的方法处理

---
## 部署
### 从源码进行
  [点击这里](https://github.com/ms0ng/PortScanner/archive/v0.1.zip)或从本页面下载源码,然后使用 [Eclipse](https://www.eclipse.org/downloads/) 或者 [IntelliJ IDEA](https://www.jetbrains.com/idea/) 导入项目,从`GUI.java`(这是程序的入口)或者`TestClass.java`运行  

        Tip.这个程序是我在IntelliJ IDEA上写的
### 从JAR进行
1. 请到本项目的 [Realse界面](https://github.com/ms0ng/PortScanner/releases) 下载`PortScanner.jar`
2. 安装好[Java运行环境(JRE)](https://www.java.com/zh_CN/)后双击`PortScanner.jar`即可双击运行

---
## 已知BUG
- 单击开始扫描按钮时GUI界面会卡住,直到扫描完才会更新内容

         这是由于GUI.java中第54行while()是阻塞的,因此在多线程任务全部执行完成前,程序不会继续执行完后面的代码
         但是线程仍在正常运行,只是表面看上去无响应而已
- 端口处不按格式乱写一通可能会崩溃  ~~因为try-catch模块用的很少~~
- 重复填写的并且已被扫描到的端口在结果中仍会有重复
- 少部分情况下扫描出的结果是乱序的 ~~(只是猜测,没遇见过)~~

- ~~BUG什么的是不可能修复的,这辈子都不可能修复的,就只能继续越写越多,才能维持的了生活这个样子~~
---
## 编写环境
这一条目仅用于参考,在部分情况下可以用来对比配置环境来解决问题

      IDE版本:
      IntelliJ IDEA 2019.1.2 (Community Edition)
      Build #IC-191.7141.44, built on May 7, 2019
      JRE: 1.8.0_202-release-1483-b49 amd64
      JVM: OpenJDK 64-Bit Server VM by JetBrains s.r.o
      Windows 10 10.0

      Java:
      java version "1.8.0_211"
      Java(TM) SE Runtime Environment (build 1.8.0_211-b12)
      Java HotSpot(TM) 64-Bit Server VM (build 25.211-b12, mixed mode)
      javac 12
