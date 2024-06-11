package com.dawn.dawn;

import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MultiThreadDownloader {
    private static final int THREAD_COUNT = 4;

    public static void downloadFile(String fileURL, String fileName) throws Exception {
        URL url = new URL(fileURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        int fileSize = connection.getContentLength();
        connection.disconnect();

        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
        raf.setLength(fileSize);

        long partSize = fileSize / THREAD_COUNT;
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < THREAD_COUNT; i++) {
            long start = i * partSize;
            long end = (i == THREAD_COUNT - 1) ? fileSize : start + partSize - 1;
            Thread thread = new Thread(new DownloadTask(fileURL, start, end, raf));
            thread.start();
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.join();
        }

        raf.close();
        System.out.println("Download completed.");
    }

    public static void main(String[] args) {
        try {
            String fileURL = "http://120.27.215.0:8081/files/0ef81805-5d90-4215-85fc-bed2098b69fa.jpg";
            String fileName = "file.jpg";
            downloadFile(fileURL, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
