package com.osama.chatting.exceptions;

/**
 * The type of checked exception that thrown from service-level.
 *
 * @author Osama Alfaqeeh.
 * @see Exception
 */
public class ServiceExceptions extends Exception{
    /**
     * Instantiates a new ServiceException.
     */
    public ServiceExceptions() {
    }

    /**
     * Instantiates a new ServiceException.
     *
     * @param message the message.
     */
    public ServiceExceptions(String message) {
        super(message);
    }

    /**
     * Instantiates a new ServiceException.
     *
     * @param message the message.
     * @param cause   the cause.
     */
    public ServiceExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new ServiceException.
     *
     * @param cause the cause.
     */
    public ServiceExceptions(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new ServiceException.
     *
     * @param message            the message.
     * @param cause              the cause.
     * @param enableSuppression  the enable suppression.
     * @param writableStackTrace the writable stack trace.
     */
    public ServiceExceptions(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
