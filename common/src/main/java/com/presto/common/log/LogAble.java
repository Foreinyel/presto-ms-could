package com.presto.common.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shihao on 16/10/29.
 */
public interface LogAble {
    static Map<String, Logger> loggers = new HashMap<String, Logger>();

    public static Logger slog() {
        String clazz = Thread.currentThread().getStackTrace()[2].getClassName();
        return getLogger(clazz);
    }

    static Logger getLogger(String name) {
        if (!loggers.containsKey(name)) {
            loggers.put(name, LoggerFactory.getLogger(name));
        }
        return loggers.get(name);
    }

    default Logger log() {
        return getLogger(this.getClass().getName());
    }

    default void err(Throwable t) {
        log().error(t.getMessage(), t);
    }
}

