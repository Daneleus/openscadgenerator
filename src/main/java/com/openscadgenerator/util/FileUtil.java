package com.openscadgenerator.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileUtil {

    public static void deleteDirectory(String path) throws InterruptedException, IOException {
        for (int i = 0; i <= 10; i++) {
            File dir = new File(path);
            try {
                FileUtils.deleteDirectory(dir);
                break;
            } catch (IOException e) {
                Thread.sleep(1000);
                if (i == 10) {
                    throw e;
                }
            }
        }
    }

    /**
     * @noinspection ResultOfMethodCallIgnored
     */
    public static File getOrCreateFile(String path, String filename) {
        File dir = new File(path);
        File file = new File(dir, filename);
        dir.mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static void writeToFile(String content, File file) {
        try {
            FileWriter myWriter = new FileWriter(file);
            myWriter.write(content);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
