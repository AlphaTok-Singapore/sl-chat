package sl.chat.dev.common.publics.sensitive.common.utils;

/**
 * @author muxiaohui
 */
public class ArgUtil {

  private ArgUtil() {
  }

  public static void notNull(Object object, String name) {
    if (null == object) {
      throw new IllegalArgumentException(name + " can not be null!");
    }
  }

  public static void notEmpty(String string, String name) {
    if (StringUtil.isEmpty(string)) {
      throw new IllegalArgumentException(name + " can not be null!");
    }
  }

}
