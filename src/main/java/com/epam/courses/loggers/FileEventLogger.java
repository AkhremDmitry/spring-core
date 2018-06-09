package com.epam.courses.loggers;

import com.epam.courses.Event;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {
    private String filename;
    private File file;

    public FileEventLogger(String filename) {
        this.filename = filename;
    }

    public void logEvent(Event event) throws IOException {
        FileUtils.writeStringToFile(file, event.toString() + "\n", true);
    }

    private void initNam() {
        this.file = new File(filename);
        file.canWrite();
    }
}
