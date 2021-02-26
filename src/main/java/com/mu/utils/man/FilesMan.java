package com.mu.utils.man;

import com.mu.utils.Stats;

import java.io.File;
import java.io.IOException;

public class FilesMan {

    public static void setup() throws IOException {
        new File(Stats.WORKSPACE + "backpacks").mkdirs();
    }
}
