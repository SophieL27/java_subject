package Notepad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class OpenFileAdapter implements ActionListener {
	NoteFrame noteFrame;
	public OpenFileAdapter(NoteFrame noteFrame){
	     this.noteFrame = noteFrame;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(noteFrame.state) 
		{
		case 0:
			break;
		case 1:
			int ret=JOptionPane.showOptionDialog(null, "已经打开的文件尚未保存，需要保存吗？", "提示",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE,null, null, null);
			if(JOptionPane.YES_OPTION==ret) {
				JFileChooser jFileChooser=new JFileChooser("D:");
				int buffInt=jFileChooser.showSaveDialog(noteFrame.jFrame);
				if(JFileChooser.APPROVE_OPTION==buffInt) {
					File saveFile=jFileChooser.getSelectedFile();
					try {
						FileWriter fileWriter=new FileWriter(saveFile);
						fileWriter.write(noteFrame.jTextArea.getText());
						fileWriter.close();
						noteFrame.state=2;
					}catch(IOException e1) {
						e1.printStackTrace();
					}
				}
				else
				{
					
				}
			}
			else if(JOptionPane.NO_OPTION==ret)
			{
				noteFrame.state=2;
				
			}
			else if(JOptionPane.CANCEL_OPTION==ret)
			{
				
			}
			else
			{
				
			}
			break;
		case 2:
			  
			 break;
		case 3:
			try {
				FileWriter fileWriter=new FileWriter(noteFrame.openFile);
				fileWriter.write(noteFrame.jTextArea.getText());
				fileWriter.close();
				noteFrame.state=2;
			}catch(IOException e1)
			{
				e1.printStackTrace();
			}
			break;
		default:
				break;
		}
		JFileChooser jFileChooser_open=new JFileChooser();
		int ifOpen=jFileChooser_open.showOpenDialog(noteFrame.jFrame);
		if(JFileChooser.APPROVE_OPTION==ifOpen) {
			File openFile =jFileChooser_open.getSelectedFile();
			try {
				FileReader fileReader=new FileReader(openFile);
				int buffGet;
				String string="";
				while((buffGet=fileReader.read())!=-1) {
					string+=(char)buffGet;
				}
				noteFrame.jTextArea.setText(string);
				fileReader.close();
				noteFrame.openFile=openFile;
				noteFrame.state=2;
			}catch(IOException e1)
			{
				e1.printStackTrace();
			}
			 
			
		}
		else
		{
			
		}
	}

}
