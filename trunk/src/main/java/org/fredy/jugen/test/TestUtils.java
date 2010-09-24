package org.fredy.jugen.test;

import java.io.File;

/**
 * @author fredy
 */
public class TestUtils {

    private TestUtils() {
    }

    public static boolean deleteDirectory(File file) {
        if (file.exists()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        return file.delete();
    }
}
