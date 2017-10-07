package top.oahnus.common.exception;

/**
 * Created by oahnus on 2017/10/3
 * 14:06.
 */
public class RequestParamInvalidException extends ClientException {
    public RequestParamInvalidException() {
    }

    public RequestParamInvalidException(String message) {
        super(message);
    }
}
