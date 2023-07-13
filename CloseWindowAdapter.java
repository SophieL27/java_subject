package Notepad;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class CloseWindowAdapter extends WindowAdapter{//关闭窗口的监听类
	static int state;//记事本状态
	static JFrame jFrame;//记事本窗口
	static JTextArea jTextArea;//记事本文本框
	static File openFile;//打开文件
	NoteFrame noteFrame;//定义一个记事本
	
	public CloseWindowAdapter(NoteFrame noteFrame) {//构造初始化一个记事本
		this.noteFrame=noteFrame;
	}
	
	static void closeWindow()//关闭窗口的方法，静态的方便其他监听类调用
	{
		switch(state) {//通过记事本不同状态调用不同的方法
		case 0:
			System.exit(0);//直接关闭窗口
			break;
		case 1:
			int ret=JOptionPane.showOptionDialog(null, "已经打开的文件尚未保存，需要保存吗？", "提示",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE,null, null, null);
			if(JOptionPane.YES_OPTION==ret) {
				JFileChooser jFileChooser=new JFileChooser("D:");
				int buffInt=jFileChooser.showSaveDialog(jFrame);
				if(JFileChooser.APPROVE_OPTION==buffInt) {
					File saveFile=jFileChooser.getSelectedFile();
					try {
						FileWriter fileWriter=new FileWriter(saveFile);
						fileWriter.write(jTextArea.getText());
						fileWriter.close();
						 
					}catch(IOException e1) {
						e1.printStackTrace();
					}
				}
				else
				{
					//什么也不做
				}
				System.exit(1);//退出
			}
			else if(JOptionPane.NO_OPTION==ret)//选择否（NO）
			{
				System.exit(1);//退出
				
			}
			else if(JOptionPane.CANCEL_OPTION==ret)//选择取消（CANCEL）
			{
				//什么也不做
			}
			else
			{
				//什么也不做
			}
			break;
		case 2:
			System.exit(0);//直接关闭
			break;
		case 3:
			try {
				FileWriter fileWriter=new FileWriter(openFile);//定义一个字符输出流
				String string =jTextArea.getText();//将文本框中的内容变成字符串
				fileWriter.write(string,0,string.length());//将文本框中的内容写入
				fileWriter.close();//关闭字符输入流
				state=2; //将记事本状态修改成2
			}catch(IOException e1)
			{
				e1.printStackTrace();
			}
			break;
			default:
				break;
		}
	}
	public void windowClosing(WindowEvent e)//监听方法
	{
		state=noteFrame.state;
		jFrame=noteFrame.jFrame;
		jTextArea=noteFrame.jTextArea;
		openFile=noteFrame.openFile;
		this.closeWindow();//调用关闭窗口方法
	}
	
}