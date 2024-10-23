package sl.chat.dev.common.publics.sensitive.core.support.format;

import sl.chat.dev.common.publics.sensitive.common.annotation.ThreadSafe;
import sl.chat.dev.common.publics.sensitive.common.core.ICharFormat;
import sl.chat.dev.common.publics.sensitive.common.utils.NumUtils;

/**
 * 忽略数字的样式 muxiaohui
 */
@ThreadSafe
public class IgnoreNumStyleCharFormat implements ICharFormat {

  @Override
  public char format(char original) {
    return NumUtils.getMappingChar(original);
  }

}
