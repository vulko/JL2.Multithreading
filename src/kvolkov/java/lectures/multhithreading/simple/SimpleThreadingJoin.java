package kvolkov.java.lectures.multhithreading.simple;

import kvolkov.java.lectures.multhithreading.Utils;

/**
 * This is a simple example of using a {@link Thread#join()}.
 * 
 * @author Kirill Volkov (vulkovk@gmail.com)
 *         https://github.com/vulko
 */
public class SimpleThreadingJoin {

	public static void execute() {
		final int numPreprocessSteps = 10;
		
		// create thread 1
		final Thread preprocessThread = new Thread(new Runnable() {			
			@Override
			public void run() {
				int progress = 0;
				for(int i = 0; i < 10; ++i) {
					Utils.waitFor(1000, false);
					progress = 100 * (i+1)/numPreprocessSteps; 
					System.out.println("preprocessing... " + progress + "%");
				}
				System.out.println("Preprocessing is done!");
			}
		});

		// create thread 2
		Thread processThread = new Thread(new Runnable() {			
			@Override
			public void run() {
				try {
					preprocessThread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Start processing");
			}
		});
		
		// execute threads simultaneously
		preprocessThread.start();
		processThread.start();
	}
	
}
