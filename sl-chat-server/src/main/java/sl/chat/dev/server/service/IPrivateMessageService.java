package sl.chat.dev.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sl.chat.dev.common.core.model.PrivateMessageInfo;
import sl.chat.dev.server.common.entity.PrivateMessage;
import sl.chat.dev.server.common.vo.message.MessageSendResp;
import sl.chat.dev.server.common.vo.message.PrivateMessageSendReq;

import java.util.List;

public interface IPrivateMessageService extends IService<PrivateMessage> {

  MessageSendResp sendMessage(PrivateMessageSendReq vo);

  void recallMessage(Long id);

  List<PrivateMessageInfo> findHistoryMessage(Long friendId, Long lastMessageId);

  void pullUnreadMessage();

}
