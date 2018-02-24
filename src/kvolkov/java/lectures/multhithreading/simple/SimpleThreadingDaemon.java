package kvolkov.java.lectures.multhithreading.simple;

import kvolkov.java.lectures.multhithreading.Utils;

/**
 * This is a simple example of a daemon {@link Thread}.
 * 
 * @author Kirill Volkov (vulkovk@gmail.com)
 *         https://github.com/vulko
 */
public class SimpleThreadingDaemon {

	public static void execute() {
		// create thread 1
		Thread workerThread1 = new Thread(new Runnable() {			
			@Override
			public void run() {
				for(int i = 0; i < 10; ++i) {
					System.out.println("thread 1 run was called " + i + " times");
					Utils.waitFor(1000, false);
				}
			}
		});

		// create thread 2
		Thread workerThread2 = new Thread(new Runnable() {			
			@Override
			public void run() {
				for(int i = 0; i < 10; ++i) {
					System.out.println("thread 2 run was called " + i + " times");
					Utils.waitFor(10, false);
				}
			}
		});

		workerThread1.setDaemon(true);
		// execute threads simultaneously
		workerThread1.start();
		workerThread2.start();
	}
	
}
