package main.java.controller;

import java.io.*;
import java.nio.channels.*;

/*
 * This class prevents the application from trying to run multiple instances.
 * Code adapted and originally from: https://www.rgagnon.com/javadetails/java-0288.html (20.02.2022 00:00)
 * It creates a temporary file and locks it. If another instance tries to access this file, it fails.
 */

public class AppLocker {
    private File file;
    private FileChannel channel;
    private FileLock lock;

    public AppLocker() {
       
    }

    public boolean isAppActive() {
        try {
            file = new File
                 (System.getProperty("user.home"), "timeTrackerLockFile.tmp");
            channel = new RandomAccessFile(file, "rw").getChannel();

            try {
                lock = channel.tryLock();
            }
            catch (OverlappingFileLockException e) {
                // already locked
                closeLock();
                return true;
            }

            if (lock == null) {
                closeLock();
                return true;
            }

            Runtime.getRuntime().addShutdownHook(new Thread() {
                    // destroy the lock when the JVM is closing
                    public void run() {
                        closeLock();
                        deleteFile();
                    }
                });
            return false;
        }
        catch (Exception e) {
            closeLock();
            return true;
        }
    }

    private void closeLock() {
        try { lock.release();  }
        catch (Exception e) {  }
        try { channel.close(); }
        catch (Exception e) {  }
    }

    private void deleteFile() {
        try { file.delete(); }
        catch (Exception e) { }
    }
}
