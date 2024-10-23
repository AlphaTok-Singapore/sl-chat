package sl.chat.dev.common.publics.sensitive.core.support.replace;

import sl.chat.dev.common.publics.sensitive.common.annotation.ThreadSafe;
import sl.chat.dev.common.publics.sensitive.common.utils.CharUtil;
import sl.chat.dev.common.publics.sensitive.core.api.ISensitiveWordReplace;
import sl.chat.dev.common.publics.sensitive.core.api.ISensitiveWordReplaceContext;

/**
 * 指定字符的替换策略 muxiaohui
 */
@ThreadSafe
public class SensitiveWordReplaceChar implements ISensitiveWordReplace {

  private final char replaceChar;

  public SensitiveWordReplaceChar(char replaceChar) {
    this.replaceChar = replaceChar;
  }

  @Override
  public String replace(ISensitiveWordReplaceContext context) {
    int wordLength = context.wordLength();

    return CharUtil.repeat(replaceChar, wordLength);
  }

}
