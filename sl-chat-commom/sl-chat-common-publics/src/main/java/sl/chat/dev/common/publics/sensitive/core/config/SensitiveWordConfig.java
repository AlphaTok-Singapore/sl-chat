package sl.chat.dev.common.publics.sensitive.core.config;

import sl.chat.dev.common.publics.sensitive.common.core.WordContext;
import sl.chat.dev.common.publics.sensitive.common.instance.Instances;
import sl.chat.dev.common.publics.sensitive.core.api.IWordAllow;
import sl.chat.dev.common.publics.sensitive.core.api.IWordDeny;
import sl.chat.dev.common.publics.sensitive.core.support.allow.SystemDefaultWordAllow;
import sl.chat.dev.common.publics.sensitive.core.support.check.SensitiveCheckWord;
import sl.chat.dev.common.publics.sensitive.core.support.deny.SystemDefaultWordDeny;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 上下文 muxiaohui
 */
public class SensitiveWordConfig extends WordContext {

  /**
   * 敏感词源
   */
  private List<IWordDeny> wordDenys;

  /**
   * 白名单源
   */
  private List<IWordAllow> wordAllows;

  public SensitiveWordConfig() {
    this.setIgnoreRepeat(false);
    this.setSensitiveCheckNumLen(20);
    this.addSensitiveChecks(Instances.singleton(SensitiveCheckWord.class));
  }

  /**
   * 新建一个对象实例
   *
   * @return 对象实例
   */
  public static SensitiveWordConfig defaultConfig() {
    SensitiveWordConfig sensitiveWordConfig = new SensitiveWordConfig();
    sensitiveWordConfig.addWordAllows(new SystemDefaultWordAllow());
    sensitiveWordConfig.addWordDenys(new SystemDefaultWordDeny());
    return sensitiveWordConfig;
  }

  public void addWordAllows(IWordAllow iWordAllow) {
    if (wordAllows == null) {
      wordAllows = new ArrayList<>(8);
    }
    wordAllows.add(iWordAllow);
  }

  public void addWordDenys(IWordDeny iWordDeny) {
    if (wordDenys == null) {
      wordDenys = new ArrayList<>(8);
    }
    wordDenys.add(iWordDeny);
  }

  public List<IWordDeny> findWordDenys() {
    if (this.wordDenys == null) {
      return Collections.emptyList();
    }
    return this.wordDenys;
  }

  public List<IWordAllow> findWordAllows() {
    if (this.wordAllows == null) {
      return Collections.emptyList();
    }
    return this.wordAllows;
  }

}
