package sl.chat.dev.server.service;

import sl.chat.dev.common.core.utils.Result;
import sl.chat.dev.server.common.vo.user.ChatSessionAddReq;
import sl.chat.dev.server.common.vo.user.ChatSessionInfoResp;
import sl.chat.dev.server.common.vo.user.ChatSessionUpdateReq;

import java.util.Set;

/**
 * @author muxiaohui
 * @date 2023-06-15 21:45
 */
public interface IChatSessionService {

  boolean save(Long userId, ChatSessionAddReq vo);

  Result<Set<ChatSessionInfoResp>> list();

  boolean del(ChatSessionUpdateReq vo);
}
