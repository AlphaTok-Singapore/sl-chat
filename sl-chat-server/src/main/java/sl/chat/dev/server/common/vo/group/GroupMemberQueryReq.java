package sl.chat.dev.server.common.vo.group;

import sl.chat.dev.common.core.model.PageReq;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author muxiaohui
 * @date 2023-07-08 11:48
 */
@Data
public class GroupMemberQueryReq extends PageReq {

  @NotNull(message = "群聊id不能为空")
  private Long groupId;

  private String search;
}
