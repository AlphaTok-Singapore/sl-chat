package sl.chat.dev.server.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author muxiaohui
 * @date 2023-06-17 16:34
 */
@Data
@TableName("chat_session")
public class ChatSession {

  private Long id;

  /**
   * 对方id
   */
  private Long targetId;

  /**
   * 聊天类型
   */
  private String chatType;

  /**
   * 归属用户id
   */
  private Long ownId;

  private Long updateTime;

  /**
   * 是否置顶
   */
  private Long topFlag;
}
