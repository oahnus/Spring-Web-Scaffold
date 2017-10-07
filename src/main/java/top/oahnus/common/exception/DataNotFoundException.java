package top.oahnus.common.exception;

/**
 * Created by oahnus on 2017/10/3
 * 14:05.
 */
public class DataNotFoundException extends ClientException {
    public DataNotFoundException() {
    }

    public DataNotFoundException(String message) {
        super(message);
    }
}
