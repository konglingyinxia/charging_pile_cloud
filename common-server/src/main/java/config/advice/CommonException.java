package config.advice;

import java.io.Serializable;

/**
 * @author zhengliangzhang
 */
public class CommonException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -5158569767859694096L;

    public CommonException() {
        super();
    }

    public CommonException(String message, Throwable cause, boolean enableSuppression,
                           boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonException(String message) {
        super(message);
    }

    public CommonException(Throwable cause) {
        super(cause);
    }


}
