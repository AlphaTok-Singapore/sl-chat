package sl.chat.dev.server.exception;

import sl.chat.dev.common.core.enums.ResultCode;

/**
 * @author muxiaohui
 * @date 2023-06-17 16:58
 */
public class NotJoinGroupException extends BusinessException {

  public NotJoinGroupException(ResultCode resultCode, String message) {
    code = resultCode.getCode();
    message = message;
  }

}
