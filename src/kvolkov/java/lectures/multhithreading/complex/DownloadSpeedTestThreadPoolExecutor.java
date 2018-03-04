package kvolkov.java.lectures.multhithreading.complex;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * This is a simple example of download speed measurement using a {@link ThreadPoolExecutor}. 
 * 
 * @author Kirill Volkov (vulkovk@gmail.com)
 *         https://github.com/vulko
 *
 */
public class DownloadSpeedTestThreadPoolExecutor {
	
	private static class MeasuredDownloader implements Runnable {
		
		private long mStartTS = 0L;
		private long mEndTS = 0L;
		private long mTotalBytesRead = 0L;
		private URL mUrl;
		private URLConnection mConnection;
		
		MeasuredDownloader(String downloadUrl) {
			try {
				mUrl = new URL(downloadUrl);
				mConnection = mUrl.openConnection();
			} catch (IOException e) {
				System.out.println("Can't create instance! " + e.getMessage());
			}			
		}
		
		void doMeasurement() {
			if (mConnection == null) {
				System.out.println("Can't start speed measurement! Connection is null!");
				return;
			}

			InputStream inputStream = null;
			byte[] buf = new byte[1024];
			int bytesRead;

			try {
				inputStream = mConnection.getInputStream();

				mStartTS = System.currentTimeMillis();
				do {
					bytesRead = inputStream.read(buf, 0, 1024);
					mTotalBytesRead += bytesRead;
				} while (bytesRead > 0);
				mEndTS = System.currentTimeMillis();

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		@Override
		public void run() {
			doMeasurement();
			long timeElapsed = mEndTS - mStartTS;
			float bps = (float) mTotalBytesRead / timeElapsed * 1000.0f;
			float mbps = bps / (1024.0f * 1024.0f);
			mSpeedResults.add(mbps);
			System.out.println("*RESULT* -> DOWNLOAD SPEED = " + mbps  + "[MB/s]");
		}
		
	}
	
	private static final int MAX_CORES = 1;
	private static final int NUM_ITERATIONS = 20;
	
	private static List<Float> mSpeedResults = new ArrayList<>();
	
	public static void execute() {
		mSpeedResults.clear();
		
		// Start thread pool executor
		ExecutorService executor = Executors.newFixedThreadPool(MAX_CORES);
		for (int i = 0; i < NUM_ITERATIONS; i++) {
			executor.execute(new MeasuredDownloader("http://ipv4.ikoula.testdebit.info/1M.iso"));
		}

		// Wait till all threads finish
		executor.shutdown();
		while (!executor.isTerminated()) {
		}

		float avgMbpsValue = 0.f;
		for (int i = 0; i < mSpeedResults.size(); ++i) {
			avgMbpsValue += mSpeedResults.get(i);
		}
		avgMbpsValue = avgMbpsValue / mSpeedResults.size();
		System.out.println("SPEED MEASUREMENT DONE!");
		System.out.println("   -> FINAL RESULT: " + avgMbpsValue + "[Mb/s]");
	}

}
