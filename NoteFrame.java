package Notepad;

import java.awt.Container;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
 

public class NoteFrame {//记事本界面
	public JFrame jFrame;//顶层容器（框架窗口）
	JMenuBar menuBar;//定义菜单栏
	JMenu fileMenu,helpMenu;//定义菜单文件和帮助
	//定义文件菜单文件中的子选项"新建"，"打开"，"保存"，"另存为"，"退出"
	JMenuItem newFileItem,openFileItem,saveFileItem,saveAsItem,exitItem;
    //定义文件菜单帮助中的子选项"注意"
	JMenuItem actionItem;
	//定义一个文本框
    public JTextArea jTextArea;
    //定义一个滚动条
    JScrollPane jScrollPane;
    //定义一个用于保存打开的File
    public File openFile;
    //判断记事本状态
    public int state=0;
    /*
     * 0：初始状态
     * 1：记事本中被写入内容
     * 2：打开一个文本
     * 3：打开一个文本并修改内容
     */
    public NoteFrame()//初始化记事本界面
    {
    	jFrame=new JFrame("无标题-记事本");//新建的记事本窗口
    	jFrame.setBounds(600, 100, 600, 600);//设置窗口尺寸
    	jFrame.setResizable(true);//记事本窗口可视化
    	Container contentPane =jFrame.getContentPane();//用getContentPane()方法获得JFrame的内容面板，再对其加入组件
    	
    	jFrame.addWindowListener(new CloseWindowAdapter(this));//在窗口添加一个Windows事件消息，目的是我们关闭窗口的时候可以正常的退出
    	
    	menuBar=new JMenuBar();//初始化菜单栏
    	fileMenu=new JMenu("文件");
    	helpMenu=new JMenu("帮助");
    	newFileItem=new JMenuItem("新建");
    	newFileItem.addActionListener(new NewFileAdapter(this));//新建文件的监听
    	
    	openFileItem=new JMenuItem("打开");
    	openFileItem.addActionListener(new OpenFileAdapter(this));//打开文件的监听
    	
    	saveFileItem=new JMenuItem("保存");
    	saveFileItem.addActionListener(new SaveAdapter(this));//保存文件的监听
    	
    	saveAsItem=new JMenuItem("另保为");
    	saveAsItem.addActionListener(new SaveAsAdapter(this));//另存为文件的监听
    	
    	exitItem = new JMenuItem("退出");
    	exitItem.addActionListener(new ExitAdapter());//文件退出的监听
    	actionItem = new JMenuItem("温馨提示");
    	actionItem.addActionListener(new ActionListener() {//直接初始化温馨提示的监听
    	       public void actionPerformed(ActionEvent e) 
    	       {
    	            new JOptionPane().showMessageDialog(null,"记事本主要功能：\n"
    						+ "1.对文件的新建、打开、保存。\n"
    						+ "2.自动设置水平和垂直滚动条\n"
    						+ "3.能够按照ASCII字符编码（0-255）加密，加密时对每一字符+10，（若超过255，减去255），解密时作对应反变换。\n");
    	            //出现显示自定义内容文字的窗口
    	       }
    	});
    		 
    		jTextArea = new JTextArea();
    		jTextArea.addKeyListener(new KeyAdapter(this));//为键盘设置监听
    		jScrollPane = new JScrollPane(jTextArea);//给文本框设立一个滚动条
    		
    		fileMenu.add(newFileItem);//菜单文件子选项添加到菜单中
    		fileMenu.add(openFileItem);
    		fileMenu.add(saveFileItem);
    		fileMenu.add(saveAsItem);
    		fileMenu.add(exitItem);
    		helpMenu.add(actionItem);//菜单帮助子选项添加到菜单中
    		
    		menuBar.add(fileMenu);//菜单放到菜单栏中
    		menuBar.add(helpMenu);
    		
    		contentPane.add(jScrollPane);//将滚动条放入JFrame的内容面板里面
    		jFrame.setJMenuBar(menuBar);//初始化jFrame的菜单栏
    		
    		//jFrame.show();//show方法已过时，从 JDK 1.5 版开始，由 Component.setVisible(boolean) 取代。
    		jFrame.setVisible(true);
    		
    		 


    }
   
  	
  	 
  
}
