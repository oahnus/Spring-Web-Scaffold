package top.oahnus.interfaces;

import top.oahnus.common.constants.Constants;

/**
 * Created by oahnus on 2017/11/29
 * 11:00.
 */
public interface SingleLoginMixin extends LoginMixin, LoggerMixin{
    default Long getUserId() {
        try {
            return Long.valueOf(request().getHeader(Constants.USER_ID));
        } catch (NumberFormatException e) {
            logger().error("获取UserID失败");
            return null;
        }
    }
}
