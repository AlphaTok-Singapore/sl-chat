package sl.chat.dev.common.publics.sensitive.core.support.result;

import sl.chat.dev.common.publics.sensitive.common.annotation.ThreadSafe;
import sl.chat.dev.common.publics.sensitive.core.api.IWordResult;
import sl.chat.dev.common.publics.sensitive.core.api.IWordResultHandler;

/**
 * 不做任何处理 muxiaohui
 */
@ThreadSafe
public class WordResultHandlerRaw implements IWordResultHandler<IWordResult> {

  @Override
  public IWordResult handle(IWordResult wordResult) {
    return wordResult;
  }

}
