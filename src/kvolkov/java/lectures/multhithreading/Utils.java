package kvolkov.java.lectures.multhithreading;

public class Utils {
	
	public static void waitFor(final long millis, final boolean printToConsoleWhenStartsAndFinishes) {
		try {
			if (printToConsoleWhenStartsAndFinishes) {
				System.out.println("Starting to wait for " + millis + "ms");
			}
			
			Thread.sleep(millis);

			if (printToConsoleWhenStartsAndFinishes) {
				System.out.println("Done waiting");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
