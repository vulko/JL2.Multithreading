package kvolkov.java.lectures.multhithreading.simple;

import java.util.Date;
import java.util.HashMap;
import java.util.HashMap;

import kvolkov.java.lectures.multhithreading.Utils;

/**
 * This is a simple example of comparsion of {@link Thread}'s running with different
 * priority. Starts 3 threads simultaneously. Results highly depend on the number of CPU cores.
 * 
 * @author Kirill Volkov (vulkovk@gmail.com)
 *         https://github.com/vulko
 */
public class SimpleThreadingPriorityPreformanceCompare {
	
	public static class MeasuredThread extends Thread {
		
		private static final int MAX_ITERATIONS = 10000000;
		
		@Override
		public void run() {
			super.run();

			long startTS = 0;
			long finishTS = 0;

			startTS = System.currentTimeMillis();
			for (int i = 0; i < MAX_ITERATIONS; ++i) {
                double d = Math.tan(Math.atan(Math.tan(Math.atan(Math.tan(Math.atan(Math.tan(Math.atan(Math.tan(Math.atan(123456789.123456789))))))))));
                double root = Math.cbrt(d);
            }
			finishTS = System.currentTimeMillis();
			double elapsedTime = ((double) (finishTS - startTS)) / 1000.0d;
			System.out.println(getName() + " finished job in " + elapsedTime + " seconds");
		}
		
	}

	public static void execute() {
		// create threads
		MeasuredThread workerThreadMinPrio = new MeasuredThread();
		MeasuredThread workerThreadNormPrio = new MeasuredThread();
		MeasuredThread workerThreadMaxPrio = new MeasuredThread();
		
		// set priority
		workerThreadMinPrio.setName("Thread with priority  MIN");
		workerThreadMinPrio.setPriority(Thread.MIN_PRIORITY);
		workerThreadNormPrio.setName("Thread with priority NORM");
		workerThreadNormPrio.setPriority(Thread.NORM_PRIORITY);
		workerThreadMaxPrio.setName("Thread with priority  MAX");
		workerThreadMaxPrio.setPriority(Thread.MAX_PRIORITY);
		
		// execute threads simultaneously
		workerThreadMaxPrio.start();
		workerThreadNormPrio.start();
		workerThreadMinPrio.start();
	}

}
