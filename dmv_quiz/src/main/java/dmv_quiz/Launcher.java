package dmv_quiz;

import javax.swing.SwingUtilities;

public class Launcher {

	public static void runLauncher() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainWindow main = new MainWindow();
				main.show();
			}
		});
	}
}
