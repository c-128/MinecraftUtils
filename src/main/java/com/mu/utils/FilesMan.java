package com.mu.utils;

import java.io.File;
import java.io.IOException;

public class FilesMan {

    public static void setup() throws IOException {
        new File(Stats.WORKSPACE + "backpacks").mkdirs();
    }
}
