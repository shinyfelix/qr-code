package utils;

public class ContractViolationException extends RuntimeException {
    public ContractViolationException() {
    }

    public ContractViolationException(String message) {
        super(message);
    }

    public ContractViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContractViolationException(Throwable cause) {
        super(cause);
    }

    public ContractViolationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
