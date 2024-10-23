package sl.chat.dev.common.publics.sensitive.core.support.allow;

import sl.chat.dev.common.publics.sensitive.common.annotation.ThreadSafe;
import sl.chat.dev.common.publics.sensitive.common.utils.FileUtils;
import sl.chat.dev.common.publics.sensitive.core.api.IWordAllow;

import java.util.List;

/**
 * 系统默认的信息 muxiaohui 3
 */
@ThreadSafe
public class SystemDefaultWordAllow implements IWordAllow {

  @Override
  public List<String> allow() {
    return FileUtils.readAllLinesForZip(
        SystemDefaultWordAllow.class.getResourceAsStream("/allow.zip"));
  }

}
