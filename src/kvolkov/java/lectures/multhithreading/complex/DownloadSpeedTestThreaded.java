package kvolkov.java.lectures.multhithreading.complex;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * This is a simple example of download speed measurement using a single thread. 
 * 
 * @author Kirill Volkov (vulkovk@gmail.com)
 *         https://github.com/vulko
 *
 */
public class DownloadSpeedTestThreaded {
	
	private static class MeasuredDownloader {
		
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
		
		void startMeasurement() {
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
					long timeElapsed = System.currentTimeMillis() - mStartTS;
					float bps = (float) mTotalBytesRead / timeElapsed * 1000.0f;
					System.out.println("Speed rate = " + bps / (1024.0f * 1024.0f) + "[MB/s]");
				} while (bytesRead > 0);
				mEndTS = System.currentTimeMillis();

				long timeElapsed = mEndTS - mStartTS;
				float bps = (float) mTotalBytesRead / timeElapsed * 1000.0f;
				System.out.println("*RESULT* -> DOWNLOAD SPEED = " + bps / (1024.0f * 1024.0f) + "[MB/s]");
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
		
	}
	
	public static void execute() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				MeasuredDownloader mDownloader = new MeasuredDownloader("http://ipv4.ikoula.testdebit.info/1M.iso");
				mDownloader.startMeasurement();
			}
		}).start();
	}

}
