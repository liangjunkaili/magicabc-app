package com.magicabc.magicabcapp.util;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import com.google.common.util.concurrent.AtomicLongMap;
import org.slf4j.LoggerFactory;

public class RedisAppender extends AppenderBase<ILoggingEvent> {
    public static final AtomicLongMap<String> ATOMIC_LONG_MAP = AtomicLongMap.create();
    static {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger(Logger.ROOT_LOGGER_NAME);
    }
    @Override
    protected void append(ILoggingEvent iLoggingEvent) {

    }
}
