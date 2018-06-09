package com.epam.courses.loggers;

import com.epam.courses.Event;

public class ConsoleEventLogger implements EventLogger {

    public void logEvent(Event event){
        System.out.println(event);
    }
}
