package net.sf.anathema;

public class ConfigurationFailedException extends RuntimeException {

    public ConfigurationFailedException(Throwable cause) {
        super(cause);
    }

    public ConfigurationFailedException(String message) {
        super(message);
    }
}