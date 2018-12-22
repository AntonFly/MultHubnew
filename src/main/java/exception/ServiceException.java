package exception;

import java.util.logging.Level;
import java.util.logging.Logger;


public class ServiceException extends Exception {

    private static final Logger logger = Logger.getLogger(ServiceException.class.getName());

    public ServiceException(String message, Exception e) {
        super(message, e);
        logger.log(Level.WARNING, e.getMessage(), e);
    }

    public ServiceException(String message) {
        this(message, null);
    }

    public ServiceException(Exception e) {
        this(e.getMessage(), e);
    }
}