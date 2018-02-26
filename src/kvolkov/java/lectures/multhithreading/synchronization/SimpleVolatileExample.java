package kvolkov.java.lectures.multhithreading.synchronization;

import kvolkov.java.lectures.multhithreading.Utils;

/**
 * This a simple example of volatile usage.
 * 
 * @author Kirill Volkov (vulkovk@gmail.com)
 *         https://github.com/vulko
 */
public class SimpleVolatileExample {
	
	private static class Counter {
	    
		private int mValue = 0;
		
		public void reset() {
			mValue = 0;
		}

	    public void increment() {
	        mValue++;
	    }

	    public int getValue() {
	    	return mValue;
	    }
	}
	
	private static class VolatileCounter {
	    
		private volatile int mValue = 0;
	
		public void reset() {
			mValue = 0;
		}

	    public void increment() {
	        mValue++;
	    }

	    public int getValue() {
	    	return mValue;
	    }
	}

	
	private static Counter mCounter = new Counter();
	private static VolatileCounter mVolatileCounter = new VolatileCounter();
	private static final int MAX_ITERATIONS = 10;
	
	/**
	 * Execute example.
	 */
	public static void execute() {
		mCounter.reset();

		// create thread 1
		Thread workerThread1 = new Thread(new Runnable() {			
			@Override
			public void run() {
				for (int i = 0; i < MAX_ITERATIONS; ++i) {
					mCounter.increment();
					System.out.println(Thread.currentThread().getName() + " counter value = " + mCounter.getValue());
					Thread.yield();
				}
			}
		});

		// create thread 2
		Thread workerThread2 = new Thread(new Runnable() {			
			@Override
			public void run() {
				for (int i = 0; i < MAX_ITERATIONS; ++i) {
					System.out.println(Thread.currentThread().getName() + " counter value = " + mCounter.getValue());
					Thread.yield();
				}
			}
		});
		
		workerThread1.setName("(!) thread 1");
		workerThread2.setName("--> thread 2");
		
		// execute threads simultaneously
		workerThread1.start();
		workerThread2.start();
	}
	
	/**
	 * Execute example with volatile.
	 */
	public static void executeSynced() {
		mVolatileCounter.reset();

		// create thread 1
		Thread workerThread1 = new Thread(new Runnable() {			
			@Override
			public void run() {
				for (int i = 0; i < MAX_ITERATIONS; ++i) {
					synchronized (mVolatileCounter) {
						mVolatileCounter.increment();
						System.out.println(Thread.currentThread().getName() + " counter value = " + mVolatileCounter.getValue());
					}

					Utils.waitFor(1, false);
				}
			}
		});

		// create thread 2
		Thread workerThread2 = new Thread(new Runnable() {			
			@Override
			public void run() {
				for (int i = 0; i < MAX_ITERATIONS; ++i) {
					synchronized (mVolatileCounter) {
						mVolatileCounter.increment();
						System.out.println(Thread.currentThread().getName() + " counter value = " + mVolatileCounter.getValue());
					}

					Utils.waitFor(1, false);
				}
			}
		});
		
		workerThread1.setName("(!) thread 1");
		workerThread2.setName("--> thread 2");
		
		// execute threads simultaneously
		workerThread1.start();
		workerThread2.start();
	}
	
}
