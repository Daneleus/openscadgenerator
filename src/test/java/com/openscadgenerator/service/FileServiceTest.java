package com.openscadgenerator.service;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.openscadgenerator.scad.ScadString;

class FileServiceTest {

    public static String TEST_DIR = "test";

    public static String TEST_FILENAME = "test";

    public static String TEST_FILENAME_WITH_TYPE = "test.scad";

    @AfterAll
    static void cleanupFiles() throws InterruptedException, IOException {
        FileService.deleteDirectory(TEST_DIR);
    }

    @Test
    void deleteDirectory() throws IOException, InterruptedException {
        File testFile = FileService.getOrCreateFile(TEST_DIR, TEST_FILENAME);
        FileService.deleteDirectory(TEST_DIR);
        Assertions.assertNotEquals(testFile.lastModified(),
                FileService.getOrCreateFile(TEST_DIR, TEST_FILENAME).lastModified());
    }

    @Test
    void generateScadFile() {
        FileService.writeScadStringToFile(new ScadString(""), FileServiceTest.TEST_DIR, FileServiceTest.TEST_FILENAME);
        Assertions.assertTrue(
                new File(FileServiceTest.TEST_DIR + "\\" + FileServiceTest.TEST_FILENAME_WITH_TYPE).isFile());
    }

    @Test
    void getOrCreateFile() {
        File testFile = FileService.getOrCreateFile(TEST_DIR, TEST_FILENAME);
        Assertions.assertNotNull(testFile);
        Assertions.assertTrue(testFile.isFile());
        Assertions.assertTrue(testFile.getPath().startsWith(TEST_DIR));
        Assertions.assertEquals(TEST_FILENAME, testFile.getName());
    }
}