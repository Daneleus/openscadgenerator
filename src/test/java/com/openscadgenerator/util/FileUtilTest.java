package com.openscadgenerator.util;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FileUtilTest {

    public static String TEST_DIR = "test";

    public static String TEST_FILENAME = "test.test";

    @AfterAll
    static void cleanupFiles() throws InterruptedException, IOException {
        FileUtil.deleteDirectory(TEST_DIR);
    }

    @Test
    void getOrCreateProductFile_path_filename() {
        File testFile = FileUtil.getOrCreateFile(TEST_DIR, TEST_FILENAME);
        Assertions.assertNotNull(testFile);
        Assertions.assertTrue(testFile.isFile());
        Assertions.assertTrue(testFile.getPath().startsWith(TEST_DIR));
        Assertions.assertEquals(TEST_FILENAME, testFile.getName());
    }
}