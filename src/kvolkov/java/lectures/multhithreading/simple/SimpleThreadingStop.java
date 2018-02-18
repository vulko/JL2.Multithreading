package kvolkov.java.lectures.multhithreading.simple;

import kvolkov.java.lectures.multhithreading.Utils;

public class SimpleThreadingStop {
	
	private static SimpleWorkingThread mWorkerThread;

	public static class SimpleWorkingThread extends Thread {
		
		private volatile boolean mIsRunning = true;
		
		public void finish() {
			mIsRunning = false;
		}

		@Override
		public void run() {
			super.run();

			int counter = 0;
			while (mIsRunning) {
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
			mWorkerThread.finish();
			mWorkerThread = null;
		}
	}

}
