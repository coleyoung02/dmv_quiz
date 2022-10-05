package dmv_quiz;





public class Main {

	private static boolean textBased = true;
	
	
	
	public static void main(String[] args) {
		
		// vv Text Based vv
		
		if (textBased) {
			TextBased q = new TextBased();
			q.run();
		}
		else {
		// GUI Based
		Launcher.runLauncher();
		}
		
	}

}
