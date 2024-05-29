package org.example.utils.listeners;

import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.LogEventListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Listener implements LogEventListener{

    private static final Logger LOGGER = LogManager.getLogger(Listener.class);

    @Override
    public void afterEvent(LogEvent logEvent) {
        LOGGER.error(logEvent);
    }

    @Override
    public void beforeEvent(LogEvent logEvent) {

    }
}
