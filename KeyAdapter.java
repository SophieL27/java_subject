package Notepad;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyAdapter implements KeyListener{//键盘监听
	NoteFrame noteFrame;//定义一个NoteFrame类作为参数方便修改应用
	public KeyAdapter(NoteFrame noteFrame)//构造初始化记事本
	{
		this.noteFrame=noteFrame;
	}

	@Override
	public void keyTyped(KeyEvent e) {//通过监听键盘修改记事本状态
		// TODO Auto-generated method stub
		if(0==noteFrame.state)//如果记事本的状态为0（新建记事本之后并未输入内容）
		{
			noteFrame.state=1;//将记事本的状态变为1（向记事本中输入了内容）
		}
		else if(2==noteFrame.state)//如果记事本的状态为1（打开之前保存的记事本但未修改内容）
		{
			noteFrame.state=3;//如果记事本的状态为未1（打开之前保存的记事本并修改了内容）
		}
		else
		{
			//什么也不做
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
