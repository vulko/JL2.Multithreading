package kvolkov.java.lectures.multhithreading.simple;

import kvolkov.java.lectures.multhithreading.Utils;

/**
 * This is a simple example of {@link Object#wait()},  {@link Object#notify()} usage.
 * 
 * @author Kirill Volkov (vulkovk@gmail.com)
 *         https://github.com/vulko
 */
public class SimpleThreadingWaitNotify {

	private static class Message {
		private String mMsg;
	    
	    public Message(String msg) { setMsg(msg); }
	    public String getMsg() { return mMsg; }
	    public void setMsg(String msg) { mMsg = msg; }
	}
	
	private static class Waiter implements Runnable{
	    private final Message mMsg;
	    
	    public Waiter(Message msg) { mMsg = msg; }

	    @Override
	    public void run() {
	        final String name = Thread.currentThread().getName();
	        long startTS = 0L;
	        long endTS = 0L;

            System.out.println(name + " start waiting...");
	        startTS = System.currentTimeMillis();
	        
	        synchronized (mMsg) {
	            try {
	                mMsg.wait();
	            } catch(InterruptedException e) {
	                e.printStackTrace();
	            }
		        endTS = System.currentTimeMillis();
	            System.out.println(name + " was notified after " + (endTS - startTS) + "ms");
	            System.out.println(name + " processed: " + mMsg.getMsg());
	        }
	    }
	}
	
	private static class Notifier implements Runnable {
	    private Message mMsg;
	    
	    public Notifier(Message msg) { mMsg = msg; }

	    @Override
	    public void run() {
	        final String name = Thread.currentThread().getName();
	        System.out.println(name + " started");
	        
	        try {
	            Thread.sleep(1000);
	            synchronized (mMsg) {
	                mMsg.setMsg("hello from " + name);
	                mMsg.notifyAll();
	            }
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        
	    }

	}

	public static void execute() {
        Message msg = new Message("process it");
        Waiter waiter = new Waiter(msg);
        new Thread(waiter, "waiter").start();
        
        Waiter waiter1 = new Waiter(msg);
        new Thread(waiter1, "waiter1").start();
        
        Notifier notifier = new Notifier(msg);
        new Thread(notifier, "notifier").start();
        System.out.println("All the threads are started");
	}
	
}
