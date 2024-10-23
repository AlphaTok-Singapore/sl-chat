package sl.chat.dev.sdk.listener;

import sl.chat.dev.common.core.model.SendResult;

public interface MessageListener {

  void process(SendResult result);

}
