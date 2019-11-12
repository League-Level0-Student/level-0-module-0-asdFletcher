package _03_morning_zombie;

import javax.swing.JOptionPane;

public class MorningZombie {
	public static void main(String[] args) throws Exception {
		String[] routineItems = {
			"get up",
			"stumble around",
			"drink water",
			"eat brai... breakfast",
			"go to work",
			"morning over",
		};
		
		for (int i = 0; i < routineItems.length; i++) {
			String currentMessage = routineItems[i];
			JOptionPane.showMessageDialog(null,currentMessage);
		}
	}
}
 