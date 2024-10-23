package sl.chat.dev.common.core.model;

import lombok.Data;

@Data
public class PrivateMessageInfo extends CommonMessageInfo {

  /*
   * 接收者id
   */
  private Long recvId;

}
