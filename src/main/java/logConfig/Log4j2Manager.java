package logConfig;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j2Manager {
    private static Log4j2Manager log4jManager;
    private Logger infoLogger;
    private Logger assertionPassLogger;
    private Logger assertionFailLogger;

    private Log4j2Manager(Class<?> clazz) {
        infoLogger = LogManager.getLogger(clazz);
        assertionPassLogger = LogManager.getLogger("assertionsPass." + clazz.getSimpleName());
        assertionFailLogger = LogManager.getLogger("assertionsFail." + clazz.getSimpleName());
    }

    private Log4j2Manager(Object object) {
        infoLogger = LogManager.getLogger(object);
        assertionPassLogger = LogManager.getLogger("assertionsPass." + object.getClass().getSimpleName());
        assertionFailLogger = LogManager.getLogger("assertionsFail." + object.getClass().getSimpleName());
    }

    public Logger getAssertionFailLogger() {
        return assertionFailLogger;
    }

    public Logger getInfoLogger() {
        return infoLogger;
    }

    public Logger getAssertionPassLogger() {
        return assertionPassLogger;
    }


    public static Log4j2Manager getLogger(Class<?> clazz) {
        if (log4jManager == null) log4jManager = new Log4j2Manager(clazz);
        return log4jManager;
    }


    public static Log4j2Manager getLogger(Object object) {
        if (log4jManager == null) log4jManager = new Log4j2Manager(object);
        return log4jManager;
    }

    public void logInfo(String message, Object... params) {
        getInfoLogger().info(message, params);
    }

    public void logAssertionPass(String message, Object... params) {
        getAssertionPassLogger().info(message);
    }

    public void logAssertionFail(String message, Object... params) {
        getAssertionFailLogger().error(message);
    }
}
