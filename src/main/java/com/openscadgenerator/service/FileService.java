package com.openscadgenerator.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.openscadgenerator.scad.ScadString;

public class FileService {

    static void deleteDirectory(String path) throws InterruptedException, IOException {
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

    public static void writeScadStringToFile(ScadString scadString, String path, String filename) {
        writeToFile(scadString.content(), getOrCreateFile(path, filename + ".scad"));
    }

    static void writeToFile(String content, File file) {
        try {
            FileWriter myWriter = new FileWriter(file);
            myWriter.write(content);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @noinspection ResultOfMethodCallIgnored
     */
    static File getOrCreateFile(String path, String filename) {
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
}
