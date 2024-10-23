package sl.chat.dev.common.publics.sensitive.core.support.format;

import sl.chat.dev.common.publics.sensitive.common.annotation.ThreadSafe;
import sl.chat.dev.common.publics.sensitive.common.core.ICharFormat;
import sl.chat.dev.common.publics.sensitive.common.utils.CharUtils;

/**
 * 忽略英文的各种格式 muxiaohui
 */
@ThreadSafe
public class IgnoreEnglishStyleFormat implements ICharFormat {

  @Override
  public char format(char original) {
    return CharUtils.getMappingChar(original);
  }

}
