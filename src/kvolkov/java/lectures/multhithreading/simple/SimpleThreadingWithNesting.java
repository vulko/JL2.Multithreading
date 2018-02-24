package kvolkov.java.lectures.multhithreading.simple;

import kvolkov.java.lectures.multhithreading.Utils;

/**
 * This is a simple example of extending a {@link Thread} class.
 * 
 * @author Kirill Volkov (vulkovk@gmail.com)
 *         https://github.com/vulko
 */
public class SimpleThreadingWithNesting {

	public static class SimpleWorkingThread extends Thread {
		
		private final int mThreadId;
		
		public SimpleWorkingThread(final int threadID) {
			super();

			mThreadId = threadID;
		}

		@Override
		public void run() {
			super.run();

			for (int i = 0; i < 10; ++i) {
				System.out.println("thread " + mThreadId + " run was called " + i + " times");
				Utils.waitFor(1000, false);
			}
		}
		
	}

	public static void execute() {
		// create thread 1
		SimpleWorkingThread workerThread1 = new SimpleWorkingThread(1);

		// create thread 2
		SimpleWorkingThread workerThread2 = new SimpleWorkingThread(2);
		
		// execute threads simultaneously
		workerThread1.start();
		workerThread2.start();
	}
	
}
