package com.epam.courses;

import com.epam.courses.loggers.EventLogger;
import com.epam.courses.loggers.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Map;

public class App {

    private Client client;
    private Map<EventType, EventLogger> eventLoggers;
    @Autowired
    @Qualifier("consoleEventLogger")
    private EventLogger defaultLogger;
    private static ConfigurableApplicationContext applicationContext;

    public void setDefaultLogger(EventLogger defaultLogger) {
        this.defaultLogger = defaultLogger;
    }

    public void logEvent(String msg, EventType eventType) throws IOException {
        EventLogger logger = eventLoggers.get(eventType);
        if (logger==null){
            logger = defaultLogger;

        }
        String message = msg.replaceAll(client.getId(), client.getFullName());
        Event event = applicationContext.getBean("event", Event.class);
        event.setMsg(message);
        logger.logEvent(event);
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        applicationContext =
                new ClassPathXmlApplicationContext("spring.xml");
        App app = applicationContext.getBean("app", App.class);
        app.logEvent("Event for 1", null);
        Thread.sleep(1000);
        app.logEvent("Event for 2", EventType.ERROR);
        Thread.sleep(1000);
        app.logEvent("Event for 3", EventType.INFO);
        applicationContext.close();
    }

    public App(Client client, Map<EventType, EventLogger> eventLoggers) {
        this.client = client;
        this.eventLoggers = eventLoggers;
    }

    public App() {
    }
}
