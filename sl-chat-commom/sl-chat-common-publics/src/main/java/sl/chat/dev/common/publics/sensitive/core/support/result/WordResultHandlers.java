package sl.chat.dev.common.publics.sensitive.core.support.result;

import sl.chat.dev.common.publics.sensitive.common.instance.Instances;
import sl.chat.dev.common.publics.sensitive.core.api.IWordResult;
import sl.chat.dev.common.publics.sensitive.core.api.IWordResultHandler;

/**
 * 敏感词的结果处理 muxiaohui
 */
public final class WordResultHandlers {

  private WordResultHandlers() {
  }

  /**
   * 不做任何处理
   *
   * @return 结果
   */
  public static IWordResultHandler<IWordResult> raw() {
    return Instances.singleton(WordResultHandlerRaw.class);
  }

  /**
   * 只保留单词
   *
   * @return 结果
   */
  public static IWordResultHandler<String> word() {
    return Instances.singleton(WordResultHandlerWord.class);
  }

}
