package com.epam.courses.loggers;

import com.epam.courses.Event;

import java.io.IOException;

public interface EventLogger {
    void logEvent(Event event) throws IOException;
}
