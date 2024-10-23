package sl.chat.dev.common.core.utils;

import lombok.Getter;

/**
 * @author muxiaohui
 * @date 2023-07-01 11:36
 */
@Getter
public class TPair<L, R> {

  private L left;

  private R right;

  public TPair(L left, R right) {
    this.left = left;
    this.right = right;
  }

}
