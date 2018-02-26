package kvolkov.java.lectures.multhithreading.synchronization;

import kvolkov.java.lectures.multhithreading.Utils;

/**
 * This a simple deadlock example.
 * 
 * @author Kirill Volkov (vulkovk@gmail.com)
 *         https://github.com/vulko
 */
public class SimpleDeadlockExample {
	
	/**
	 * Execute example.
	 */
	public static void execute() {
        final Object obj1 = new Object();
        final Object obj2 = new Object();
        
        new Thread(new Runnable() {
        	public void run() {

        		synchronized (obj1) {
            		System.out.println("Thread 1 captured LOCK 1");
    				Utils.waitFor(100, false);

        			synchronized (obj2) {
                		System.out.println("Thread 1 captured LOCK 1 and LOCK 2");
        			}
        		}

        		System.out.println("You will never see this message!");
        	}
        	}).start();

        new Thread(new Runnable() {
        	public void run() {

        		synchronized (obj2) {
            		System.out.println("Thread 2 captured LOCK 2");
        			Utils.waitFor(100, false);

        			synchronized (obj1) {
                		System.out.println("Thread 2 captured LOCK 1 and LOCK 2");
        			}
        		}
        		
        		System.out.println("You will never see this message!");
        	}
        	}).start();
	}
	
}
