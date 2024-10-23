package sl.chat.dev.common.publics.sensitive.core.support.deny;

import sl.chat.dev.common.publics.sensitive.common.annotation.ThreadSafe;
import sl.chat.dev.common.publics.sensitive.common.utils.FileUtils;
import sl.chat.dev.common.publics.sensitive.core.api.IWordDeny;

import java.util.List;

/**
 * 系统默认的信息 muxiaohui 3
 */
@ThreadSafe
public class SystemDefaultWordDeny implements IWordDeny {

  @Override
  public List<String> deny() {
    return FileUtils.readAllLinesForZip(
        SystemDefaultWordDeny.class.getResourceAsStream("/deny.zip"));
  }

}
