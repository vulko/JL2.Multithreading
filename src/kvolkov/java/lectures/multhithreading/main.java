package kvolkov.java.lectures.multhithreading;

import kvolkov.java.lectures.multhithreading.complex.BlockingQueueExample;
import kvolkov.java.lectures.multhithreading.complex.DownloadSpeedTestFuture;
import kvolkov.java.lectures.multhithreading.complex.DownloadSpeedTestThreadPoolExecutor;
import kvolkov.java.lectures.multhithreading.complex.DownloadSpeedTestThreaded;
import kvolkov.java.lectures.multhithreading.simple.SimpleThreading;
import kvolkov.java.lectures.multhithreading.simple.SimpleThreadingDaemon;
import kvolkov.java.lectures.multhithreading.simple.SimpleThreadingJoin;
import kvolkov.java.lectures.multhithreading.simple.SimpleThreadingPriorityPreformanceCompare;
import kvolkov.java.lectures.multhithreading.simple.SimpleThreadingStop;
import kvolkov.java.lectures.multhithreading.simple.SimpleThreadingStopWithInterrupt;
import kvolkov.java.lectures.multhithreading.simple.SimpleThreadingWaitNotify;
import kvolkov.java.lectures.multhithreading.simple.SimpleThreadingWithNesting;
import kvolkov.java.lectures.multhithreading.synchronization.SimpleCounterExample;
import kvolkov.java.lectures.multhithreading.synchronization.SimpleDeadlockExample;
import kvolkov.java.lectures.multhithreading.synchronization.SimpleVolatileExample;

public class main {
	
	private enum JL2_EXAMPLE {
		RUNNABLE_EXAMPLE,
		THREAD_EXTENDING_EXAMPLE,
		DAEMON_THREAD_EXAMPLE,
		STOP_THREAD_EXAMPLE,
		INTERRUPT_THREAD_EXAMPLE,
		JOIN_THREAD_EXAMPLE,
		PRIORITY_COMPARSION_EXAMPLE,
		WAIT_NOTIFY_EXAMPLE,
		COUNTER_EXAMPLE,
		COUNTER_EXAMPLE_SYNCHRONIZED,
		DEADLOCK_EXAMPLE,
		VOLATILE_EXAMPLE_NOT_SYNCED,
		VOLATILE_EXAMPLE_SYNCED,
		SINGLETHREADED_DOWNLOAD_SPEED_MEASUREMENT,
		THREADPOOL_DOWNLOAD_SPEED_MEASUREMENT,
		FUTURE_DOWNLOAD_SPEED_MEASUREMENT,
		BLOCKING_QUEUE_EXAMPLE
	}
	
	public static void main(String[] args) {
		
		// Select an example to run
		final JL2_EXAMPLE executedExample = JL2_EXAMPLE.FUTURE_DOWNLOAD_SPEED_MEASUREMENT;
		
		switch(executedExample) {
		case RUNNABLE_EXAMPLE:
			// simple thread example, implementing Runnable
			SimpleThreading.execute();
			break;

		case THREAD_EXTENDING_EXAMPLE:
			// simple thread example, extending Thread
			SimpleThreadingWithNesting.execute();
			break;

		case DAEMON_THREAD_EXAMPLE:
			// simple thread example, implementing Runnable showing Daemon
			SimpleThreadingDaemon.execute();
			break;

		case STOP_THREAD_EXAMPLE:
			// simple stopping thread example
			SimpleThreadingStop.execute();
			Utils.waitFor(1000, true);
			SimpleThreadingStop.stop();
			break;

		case INTERRUPT_THREAD_EXAMPLE:
			// simple stopping thread example
			SimpleThreadingStopWithInterrupt.execute();
			Utils.waitFor(1000, true);
			SimpleThreadingStopWithInterrupt.stop();
			break;

		case JOIN_THREAD_EXAMPLE:
			// simple joinable thread example
			SimpleThreadingJoin.execute();
			break;

		case PRIORITY_COMPARSION_EXAMPLE:
			// compare thread priority
			SimpleThreadingPriorityPreformanceCompare.execute();
			break;

		case WAIT_NOTIFY_EXAMPLE:
			// simple wait/notify example
			SimpleThreadingWaitNotify.execute();
			break;

		case COUNTER_EXAMPLE:
			// simple example with a counter modified by 2 threads, with no
			// synchronization, so sometimes "expected value != printed value"
			SimpleCounterExample.execute();
			break;

		case COUNTER_EXAMPLE_SYNCHRONIZED:
			// simple example with a counter modified by 2 threads, synchronized
			// version without any issues
			SimpleCounterExample.executeSynchronously();
			break;

		case DEADLOCK_EXAMPLE:
			// simple deadlock example
			SimpleDeadlockExample.execute();
			break;

		case VOLATILE_EXAMPLE_NOT_SYNCED:
			// simple example when missing volatile causes issues
			SimpleVolatileExample.execute();
			break;


		case VOLATILE_EXAMPLE_SYNCED:
			// simple example when adding volatile resolves issues
			SimpleVolatileExample.executeSynced();
			break;

		case SINGLETHREADED_DOWNLOAD_SPEED_MEASUREMENT:
			// simple example of download speed measurement using a single thread
			DownloadSpeedTestThreaded.execute();
			break;

		case THREADPOOL_DOWNLOAD_SPEED_MEASUREMENT:
			// simple example of download speed measurement using a thread pool
			DownloadSpeedTestThreadPoolExecutor.execute();
			break;

		case FUTURE_DOWNLOAD_SPEED_MEASUREMENT:
			// simple example of download speed measurement using a thread pool
			DownloadSpeedTestFuture.execute();
			break;

		case BLOCKING_QUEUE_EXAMPLE:
			// simple example of BlockingQueue usage
			BlockingQueueExample.execute();
			break;

		default:
				System.out.println("No example selected");
		}

	}

}