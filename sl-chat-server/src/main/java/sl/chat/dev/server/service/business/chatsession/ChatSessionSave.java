package sl.chat.dev.server.service.business.chatsession;

import sl.chat.dev.server.common.dto.ChatSessionInfoDto;

import java.util.Set;

/**
 * @author muxiaohui
 * @date 2023-06-15 21:49
 */
public interface ChatSessionSave {

  boolean add(Long userId, ChatSessionInfoDto vo);

  Set<ChatSessionInfoDto> list(Long userId);

  boolean del(Long userId, ChatSessionInfoDto vo);
}
