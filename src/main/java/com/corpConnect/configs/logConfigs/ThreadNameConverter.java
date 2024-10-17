package com.corpConnect.configs.logConfigs;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.pattern.ClassicConverter;

public class ThreadNameConverter extends ClassicConverter {

    private static final int THREAD_NAME_LIMIT = 15; // Set your limit here

    @Override
    public String convert(ILoggingEvent event) {
        String threadName = event.getThreadName();
        if (threadName != null) {
            if (threadName.length() > THREAD_NAME_LIMIT) {
                return threadName.substring(0, THREAD_NAME_LIMIT);
            } else {
                // Create a string with the thread name and fill the rest with spaces
                StringBuilder sb = new StringBuilder(threadName);
                while (sb.length() < THREAD_NAME_LIMIT) {
                    sb.append(" "); // Append space until it reaches THREAD_NAME_LIMIT
                }
                return sb.toString();
            }
        }
        // If threadName is null, return a string of spaces
        return " ".repeat(THREAD_NAME_LIMIT); // Return a string of spaces of THREAD_NAME_LIMIT length
    }
}
