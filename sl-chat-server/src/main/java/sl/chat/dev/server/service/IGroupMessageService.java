package sl.chat.dev.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sl.chat.dev.common.core.model.GroupMessageInfo;
import sl.chat.dev.server.common.entity.GroupMessage;
import sl.chat.dev.server.common.vo.message.GroupMessageSendReq;
import sl.chat.dev.server.common.vo.message.MessageSendResp;

import java.util.List;

public interface IGroupMessageService extends IService<GroupMessage> {

  MessageSendResp sendMessage(GroupMessageSendReq vo);

  void recallMessage(Long id);

  void pullUnreadMessage();

  List<GroupMessageInfo> findHistoryMessage(Long groupId, Long lastMessageId);
}
