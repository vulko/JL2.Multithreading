package kvolkov.java.lectures.multhithreading.simple;

import kvolkov.java.lectures.multhithreading.Utils;

/**
 * This is a simple example of {@link Thread} usage with generic interruption mechanism.
 * 
 * @author Kirill Volkov (vulkovk@gmail.com)
 *         https://github.com/vulko
 */
public class SimpleThreadingStopWithInterrupt {
	
	private static SimpleWorkingThread mWorkerThread;

	public static class SimpleWorkingThread extends Thread {
		
		@Override
		public void run() {
			super.run();

			int counter = 0;
			while (!Thread.interrupted()) {
				System.out.println("thread run was called " + (++counter) + " times");
				Utils.waitFor(100, false);
			}
		}
		
	}

	public static void execute() {
		mWorkerThread = new SimpleWorkingThread();

		// execute thread
		mWorkerThread.start();
	}
	
	public static void stop() {
		if (mWorkerThread != null) {
			mWorkerThread.interrupt();
			mWorkerThread = null;
		}
	}

}
