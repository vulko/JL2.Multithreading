package kvolkov.java.lectures.multhithreading.complex;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * This is a simple example of download speed measurement using a {@link Future}. 
 * 
 * @author Kirill Volkov (vulkovk@gmail.com)
 *         https://github.com/vulko
 *
 */
public class DownloadSpeedTestFuture {
	
	private static class MeasuredDownloader implements Callable<Float> {
		
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
		public Float call() throws Exception {
			doMeasurement();
			long timeElapsed = mEndTS - mStartTS;
			float bps = (float) mTotalBytesRead / timeElapsed * 1000.0f;
			float mbps = bps / (1024.0f * 1024.0f); 
			System.out.println("speed measure value = " + mbps  + "[MB/s]");
			return mbps;
		}
		
	}
	
	private static final int MAX_CORES = 8;
	private static final int NUM_ITERATIONS = 20;
	
	public static void execute() {
		List<Future> speedMeasureResults = new ArrayList<>(NUM_ITERATIONS);
		
		// Start thread pool executor
		ExecutorService executor = Executors.newFixedThreadPool(MAX_CORES);
		for (int i = 0; i < NUM_ITERATIONS; i++) {
			 Future measureSpeed = executor.submit(new MeasuredDownloader("http://ipv4.ikoula.testdebit.info/1M.iso"));
			 speedMeasureResults.add(measureSpeed);
		}

		// get value of future. Will block current thread until result is available
		float avgSpeed = 0.f;
		for (int i = 0; i < speedMeasureResults.size(); ++ i) {
			try {
				avgSpeed += (Float) speedMeasureResults.get(i).get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		System.out.println("SPEED MEASUREMENT DONE!");
		System.out.println("   -> FINAL RESULT: " + (avgSpeed / speedMeasureResults.size()) + "[Mb/s]");
	}

}
