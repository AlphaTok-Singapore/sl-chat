package sl.chat.dev.common.core.model;

import sl.chat.dev.common.core.enums.IMSendCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendResult<T> {

  /*
   * 接收者id
   */
  private Long recvId;

  /*
   * 发送状态
   */
  private IMSendCode code;

  /*
   * 消息体(透传)
   */
  private T messageInfo;

}
