package Notepad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitAdapter implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		CloseWindowAdapter.closeWindow();
		
	}

}
