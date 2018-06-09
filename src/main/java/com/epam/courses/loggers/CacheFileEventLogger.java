package com.epam.courses.loggers;

import com.epam.courses.Event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {
    private int cacheSize;
    List<Event> eventsCache = new ArrayList<Event>();

    public CacheFileEventLogger(String filename, int cacheSize) {
        super(filename);
        this.cacheSize = cacheSize;
    }

    @Override
    public void logEvent(Event event) throws IOException {
        eventsCache.add(event);
        if (eventsCache.size() == cacheSize){
            writeEventsFromCache(eventsCache);
            eventsCache.clear();
        }
    }

    private void writeEventsFromCache(List<Event> eventsCache) throws IOException {
        for (Event curEvent: eventsCache){
            super.logEvent(curEvent);
        }
    }

    public void destroy() throws IOException {
        if (!eventsCache.isEmpty()){
            writeEventsFromCache(eventsCache);
        }
    }

}
