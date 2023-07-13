package Notepad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class SaveAdapter implements ActionListener {
	NoteFrame noteFrame;
	public SaveAdapter(NoteFrame noteFrame){
	this.noteFrame = noteFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (noteFrame.state) {
		case 0:
		  new JOptionPane().showMessageDialog(null, "你没有输入任何文字！");
		  break;
		case 1:
		  JFileChooser jFileChooser = new JFileChooser("D:");
		  int buffInt = jFileChooser.showSaveDialog(noteFrame.jFrame);
		  if(JFileChooser.APPROVE_OPTION == buffInt){
		       File saveFile = jFileChooser.getSelectedFile();
		  try {
		     FileWriter fileWriter = new FileWriter(saveFile);
		     fileWriter.write(noteFrame.jTextArea.getText());
		     fileWriter.close();
		     JOptionPane.showMessageDialog(null, "保存成功！");
		     noteFrame.state = 2;
		   } catch (IOException e1) {
		     e1.printStackTrace();
		   }
		   } 
		   else {
		   //无操作
		   }
		   break;
		case 2:
		new JOptionPane().showMessageDialog(null, "你未作任何修改！");
		break;
		case 3:
			try {
			//FileOutputStream = new FileOutputStream(noteFrame.openFile)
			   FileWriter fileWriter = new FileWriter(noteFrame.openFile);
			   String string = noteFrame.jTextArea.getText();
			   fileWriter.write(string,0,string.length());
			   fileWriter.close();
			   JOptionPane.showMessageDialog(null, "保存成功！");
			   noteFrame.state = 2;
		    } catch (IOException e1) {
			    e1.printStackTrace();
			}
			break;
		default:
			break;
		}
			


	}

}
