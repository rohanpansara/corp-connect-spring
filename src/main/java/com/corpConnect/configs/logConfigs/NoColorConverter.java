package com.corpConnect.configs.logConfigs;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.Converter;

public class NoColorConverter extends Converter<ILoggingEvent> {
    @Override
    public String convert(ILoggingEvent event) {
        // Return the message without any color codes
        return event.getFormattedMessage();
    }
}
