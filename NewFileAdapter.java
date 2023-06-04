package Notepad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

 

public class NewFileAdapter implements ActionListener{//新建设置监听器接口
	NoteFrame noteFrame;//作为参数方便修改应用
	
	public NewFileAdapter(NoteFrame noteFrame)//构造初始化记事本
	{
		this.noteFrame=noteFrame;
	}
	void newFile()//新建一个记事本
	{
		noteFrame.jFrame.setTitle("无标题-记事本");//设置一个窗口
		noteFrame.jTextArea.setText("");//设置一个文本框
		noteFrame.state=0;//设置状态为0（新建未向记事本中输入内容）
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(noteFrame.state) //通过记事本的状态确定接下来的操作
		{
		case 0:
			JOptionPane.showMessageDialog(null, "已新建！");
			break;//状态为0什么也不做
			
		case 1:
			//记事本中被写入内容状态改变成1，出现保存选择框（是，否，取消）
			int ret=JOptionPane.showOptionDialog(null, "已经打开的文件尚未保存，需要保存吗？", "提示",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE,null, null, null);
			if(JOptionPane.YES_OPTION==ret) {
				//选择是（YES）的选项
				JFileChooser jFileChooser=new JFileChooser("D:");//创建文件选择器
				int buffInt=jFileChooser.showSaveDialog(noteFrame.jFrame);
				if(JFileChooser.APPROVE_OPTION==buffInt) {
					//JFileChooser.APPROVE_OPTION: 点击了确认或保存 
					File saveFile=jFileChooser.getSelectedFile();//获取选择的文件并将其赋给saveFile保存文件
					try {
						 //try块中的代码可能会抛出异常的代码，如果发生异常，程序将跳转到catch块中，并执行其中的代码
						FileWriter fileWriter=new FileWriter(saveFile);//创建文件字符输出流
						fileWriter.write(noteFrame.jTextArea.getText());//利用重载方法write() 中的某一个写出内容（文本框中的内容）
						fileWriter.close();//关闭文件输出流
						newFile();//新建一个空的记事本
						 
						
						 
					}catch(IOException e1) {//IOException输入输出异常
						//处理异常的代码
						e1.printStackTrace();//printStackTrace()方法的意思是：在命令行打印异常信息在程序中出错的位置及原因。
					}
				}
				else
				{
					//什么也不做
				}
			}
			else if(JOptionPane.NO_OPTION==ret)//选择否（NO）
			{
				newFile();//新建一个记事本
				
			}
			else if(JOptionPane.CANCEL_OPTION==ret)//选择取消（CANCEL)
			{
				//什么也不做
			}
			else
			{
				//什么也不做
			}
			break;
		case 2://打开了一个记事本但未修改其内容点击新建
			 newFile();//新建一个记事本
			 JOptionPane.showMessageDialog(null, "已新建！");
			 break;
		case 3://打开了一个记事本并修改了其中的内容
			try {
				FileWriter fileWriter=new FileWriter(noteFrame.openFile);//创建文件字符输出流
				fileWriter.write(noteFrame.jTextArea.getText());//利用重载方法write() 中的某一个写出内容（文本框中的内容）
				fileWriter.close();//关闭文件输出流
				newFile();//新建一个记事本
			}catch(IOException e1)//IOException输入输出异常
			{
				e1.printStackTrace();
			}
			break;
		}
		
		
	}
	 
}