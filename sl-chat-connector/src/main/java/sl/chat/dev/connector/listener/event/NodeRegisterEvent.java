package sl.chat.dev.connector.listener.event;

import sl.chat.dev.common.core.enums.NetProtocolEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: muxiaohui
 * @date: 2023/6/12 16:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NodeRegisterEvent {

  /**
   * 协议类型
   */
  private NetProtocolEnum netProtocolEnum;

  /**
   * 端口
   */
  private Integer port;

  /**
   * 注册时间
   */
  private Long registerTime;

}
