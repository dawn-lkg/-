package com.dawn.dawn;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author chenliming
 * @date 2024/5/31 ÏÂÎç6:24
 */
public class DownloadTask implements Runnable {
    private String url;
    private long start;
    private long end;
    private RandomAccessFile raf;

    public DownloadTask(String url, long start, long end, RandomAccessFile raf) {
        this.url = url;
        this.start = start;
        this.end = end;
        this.raf = raf;
    }

    @Override
    public void run() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestProperty("Range", "bytes=" + start + "-" + end);
            connection.connect();

            try (InputStream input = connection.getInputStream()) {
                raf.seek(start);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = input.read(buffer)) > 0) {
                    raf.write(buffer, 0, len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
