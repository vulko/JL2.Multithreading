package kvolkov.java.lectures.multhithreading;

import kvolkov.java.lectures.multhithreading.simple.SimpleThreading;
import kvolkov.java.lectures.multhithreading.simple.SimpleThreadingDaemon;
import kvolkov.java.lectures.multhithreading.simple.SimpleThreadingJoin;
import kvolkov.java.lectures.multhithreading.simple.SimpleThreadingStop;
import kvolkov.java.lectures.multhithreading.simple.SimpleThreadingStopWithInterrupt;
import kvolkov.java.lectures.multhithreading.simple.SimpleThreadingWithNesting;

public class main {
	
	public static void main(String[] args) {
		
		// simple thread example, implementing Runnable
		SimpleThreading.execute();

		// simple thread example, extending Thread
		SimpleThreadingWithNesting.execute();

		// simple thread example, implementing Runnable showing Daemon
		SimpleThreadingDaemon.execute();

		// simple stopping thread example
		SimpleThreadingStop.execute();
		Utils.waitFor(1000, true);
		SimpleThreadingStop.stop();

		// simple stopping thread example
		SimpleThreadingStopWithInterrupt.execute();
		Utils.waitFor(1000, true);
		SimpleThreadingStopWithInterrupt.stop();

		// simple joinable thread example
		SimpleThreadingJoin.execute();

	}

}
