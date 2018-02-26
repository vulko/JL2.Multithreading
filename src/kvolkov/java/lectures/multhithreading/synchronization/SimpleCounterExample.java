package kvolkov.java.lectures.multhithreading.synchronization;

import kvolkov.java.lectures.multhithreading.Utils;

/**
 * This a simple example of a counter, that can be modified by 2 threads simultaneously.
 * Sometimes expected result is not the same that is actually printed.
 * 
 * This code might run without issues, but this highly depends on {@link #MAX_ITERATIONS}} value.
 * The bigger it is, more chance that counter value will be wrong (printed result != expected result).
 * 
 * Call {@link #execute()} to see issues.
 * Call {@link #executeSynchronously()} to resolve "printed result != expected result" issue.
 * 
 * @author Kirill Volkov (vulkovk@gmail.com)
 *         https://github.com/vulko
 */
public class SimpleCounterExample {
	
	private static class Counter {
	    
		private int mValue = 0;
		
		public void reset() {
			mValue = 0;
		}

	    public void increment() {
	        mValue++;
	    }

	    public void decrement() {
	        mValue--;
	    }
	    
	    public int getValue() {
	    	return mValue;
	    }

	    public void printValue() {
			System.out.println(Thread.currentThread().getName() + "  is printing a value = " + mValue);
	    }
	}
	
	private static Counter mCounter = new Counter();
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
					System.out.println(Thread.currentThread().getName() + " is expecting a value = " + mCounter.getValue());
					mCounter.printValue();
				}
			}
		});

		// create thread 2
		Thread workerThread2 = new Thread(new Runnable() {			
			@Override
			public void run() {
				for (int i = 0; i < MAX_ITERATIONS; ++i) {
					mCounter.decrement();
					System.out.println(Thread.currentThread().getName() + " is expecting a value = " + mCounter.getValue());
					mCounter.printValue();
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
	 * Execute example with synchronization.
	 */
	public static void executeSynchronously() {
		mCounter.reset();

		// create thread 1
		Thread workerThread1 = new Thread(new Runnable() {			
			@Override
			public void run() {
				for (int i = 0; i < MAX_ITERATIONS; ++i) {
					Utils.waitFor(10, false);
					synchronized (mCounter) {
						mCounter.increment();
						System.out.println(Thread.currentThread().getName() + " is expecting a value = " + mCounter.getValue());
						mCounter.printValue();
					}
				}
			}
		});

		// create thread 2
		Thread workerThread2 = new Thread(new Runnable() {			
			@Override
			public void run() {
				for (int i = 0; i < MAX_ITERATIONS; ++i) {
					Utils.waitFor(10, false);
					synchronized (mCounter) {
						mCounter.decrement();
						System.out.println(Thread.currentThread().getName() + " is expecting a value = " + mCounter.getValue());
						mCounter.printValue();
					}
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
