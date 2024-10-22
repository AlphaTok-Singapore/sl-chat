package io.pisceshub.muchat.common.publics.sensitive.core.support.result;

import io.pisceshub.muchat.common.publics.sensitive.common.annotation.ThreadSafe;
import io.pisceshub.muchat.common.publics.sensitive.core.api.IWordResult;
import io.pisceshub.muchat.common.publics.sensitive.core.api.IWordResultHandler;

/**
 * 只保留单词 muxiaohui
 */
@ThreadSafe
public class WordResultHandlerWord implements IWordResultHandler<String> {

  @Override
  public String handle(IWordResult wordResult) {
    if (wordResult == null) {
      return null;
    }
    return wordResult.word();
  }

}
