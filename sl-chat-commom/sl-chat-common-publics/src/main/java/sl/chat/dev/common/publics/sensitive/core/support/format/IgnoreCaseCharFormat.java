package sl.chat.dev.common.publics.sensitive.core.support.format;

import sl.chat.dev.common.publics.sensitive.common.annotation.ThreadSafe;
import sl.chat.dev.common.publics.sensitive.common.core.ICharFormat;

/**
 * 忽略大小写 muxiaohui
 */
@ThreadSafe

public class IgnoreCaseCharFormat implements ICharFormat {

  @Override
  public char format(char original) {
    return Character.toLowerCase(original);
  }

}
