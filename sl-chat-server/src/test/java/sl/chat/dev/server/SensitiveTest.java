package sl.chat.dev.server;

import sl.chat.dev.common.publics.sensitive.core.config.SensitiveWordConfig;
import sl.chat.dev.common.publics.sensitive.core.main.SWDispatcherDefault;

/**
 * @author xiaochangbai
 * @date 2023-07-09 22:11
 */
public class SensitiveTest {

  public static void main(String[] args) {
    SensitiveWordConfig sensitiveWordConfig = SensitiveWordConfig.defaultConfig();
    SWDispatcherDefault swDispatcherDefault = SWDispatcherDefault.newInstance(sensitiveWordConfig);
    String replaced = swDispatcherDefault.replace("中国南海");
    System.out.println(replaced);
  }
}
