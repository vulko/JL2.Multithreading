package kvolkov.java.lectures.multhithreading;

import kvolkov.java.lectures.multhithreading.simple.SimpleThreading;
import kvolkov.java.lectures.multhithreading.simple.SimpleThreadingDaemon;
import kvolkov.java.lectures.multhithreading.simple.SimpleThreadingJoin;
import kvolkov.java.lectures.multhithreading.simple.SimpleThreadingPriorityPreformanceCompare;
import kvolkov.java.lectures.multhithreading.simple.SimpleThreadingStop;
import kvolkov.java.lectures.multhithreading.simple.SimpleThreadingStopWithInterrupt;
import kvolkov.java.lectures.multhithreading.simple.SimpleThreadingWaitNotify;
import kvolkov.java.lectures.multhithreading.simple.SimpleThreadingWithNesting;

public class main {
	
	private enum JL2_EXAMPLE {
		RUNNABLE_EXAMPLE,
		THREAD_EXTENDING_EXAMPLE,
		DAEMON_THREAD_EXAMPLE,
		STOP_THREAD_EXAMPLE,
		INTERRUPT_THREAD_EXAMPLE,
		JOIN_THREAD_EXAMPLE,
		PRIORITY_COMPARSION_EXAMPLE,
		WAIT_NOTIFY_EXAMPLE
	}
	
	public static void main(String[] args) {
		
		// Select an example to run
		final JL2_EXAMPLE executedExample = JL2_EXAMPLE.RUNNABLE_EXAMPLE;
		
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

			default:
				System.out.println("No example selected");
		}

	}

}
