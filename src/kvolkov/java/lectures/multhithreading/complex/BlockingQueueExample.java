package kvolkov.java.lectures.multhithreading.complex;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import kvolkov.java.lectures.multhithreading.Utils;

/**
 * This is a simple example of {@link BlockingQueue} usage. 
 * 
 * @author Kirill Volkov (vulkovk@gmail.com)
 *         https://github.com/vulko
 *
 */
public class BlockingQueueExample {
	
	private static final BlockingQueue<Apple> mQueue = new ArrayBlockingQueue<Apple>(10);

	private static class Apple {

		private static int mAppleID = 0;
		
		public String toString() {
			return "apple #" + (++mAppleID);
		}
	}
	
	private static class Tree implements Runnable {

		public void run() {
			try {
				while (true) {
					mQueue.put(produce());
				}
			} catch (InterruptedException ex) {
			}
		}

		Apple produce() {
			return new Apple();
		}
	}

	private static class AppleLover implements Runnable {
		
		private String mName;
		private int mApplesConsumed = 0;
		
		AppleLover(String name) {
			mName = name;
		}

		public void run() {
			try {
				while (true) {
					Utils.waitFor(500, false);
					consume(mQueue.take());
				}
			} catch (InterruptedException ex) {
			}
		}

		void consume(Apple apple) {
			System.out.println(mName + " eaten an " + apple.toString());
		}
	}

	public static void execute() {
		Tree appleTree = new Tree();
		AppleLover adam = new AppleLover("Adam");
		AppleLover eve = new AppleLover("Eve");
		
		new Thread(appleTree).start();
		new Thread(adam).start();
		new Thread(eve).start();
	}

}
