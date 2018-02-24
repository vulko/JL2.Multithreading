package kvolkov.java.lectures.multhithreading.simple;

import kvolkov.java.lectures.multhithreading.Utils;

/**
 * This is a simple example of implementing a {@link Runnable}.
 * 
 * @author Kirill Volkov (vulkovk@gmail.com)
 *         https://github.com/vulko
 */
public class SimpleThreading {

	private static void work(final int threadID, final int counter) {
		System.out.println("thread " + threadID + " run was called " + counter + " times");
		Utils.waitFor(1000, false);
	}

	public static void execute() {
		// create thread 1
		Thread workerThread1 = new Thread(new Runnable() {			
			@Override
			public void run() {
				for(int i = 0; i < 10; ++i) {
					work(1, i);
				}
			}
		});

		// create thread 2
		Thread workerThread2 = new Thread(new Runnable() {			
			@Override
			public void run() {
				for(int i = 0; i < 10; ++i) {
					work(2, i);
				}
			}
		});
		
		// execute threads simultaneously
		workerThread1.start();
		workerThread2.start();
	}
	
}
