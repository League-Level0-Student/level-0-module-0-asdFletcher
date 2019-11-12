package _05_greeter;

import javax.swing.JOptionPane;

public class Greeter {

	public static void main(String[] args) throws Exception {
		String userInputName = JOptionPane.showInputDialog("What is your name?");
		
		String responseMessage = "Why hello there, " + userInputName + "!"; 
		JOptionPane.showMessageDialog(null,responseMessage);
	}
}
 