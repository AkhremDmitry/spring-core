package com.epam.courses.loggers;

import com.epam.courses.Event;

import java.io.IOException;
import java.util.Collection;

public class CombinedEventLogger implements EventLogger {

    private Collection<EventLogger> loggers;

    public void logEvent(Event event) throws IOException {

        for (EventLogger curLogger : loggers){
            curLogger.logEvent(event);
        }
    }

    public void setLoggers(Collection<EventLogger> loggers) {
        this.loggers = loggers;
    }
}
