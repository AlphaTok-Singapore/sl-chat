package sl.chat.dev.server.tcp.listener.message;

import sl.chat.dev.common.core.enums.IMListenerType;
import sl.chat.dev.common.core.enums.IMSendCode;
import sl.chat.dev.common.core.enums.MessageType;
import sl.chat.dev.common.core.model.GroupMessageInfo;
import sl.chat.dev.common.core.model.SendResult;
import sl.chat.dev.sdk.annotation.IMListener;
import sl.chat.dev.sdk.listener.MessageListener;
import sl.chat.dev.server.common.contant.RedisKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

@Slf4j
@IMListener(type = IMListenerType.GROUP_MESSAGE)
public class GroupMessageListener implements MessageListener {

  @Autowired
  private RedisTemplate<String, Object> redisTemplate;

  @Override
  public void process(SendResult result) {
    GroupMessageInfo messageInfo = (GroupMessageInfo) result.getMessageInfo();
    if (messageInfo.getType().equals(MessageType.TIP.code())) {
      // 提示类数据不记录
      return;
    }

    // 保存该用户已拉取的最大消息id
    if (result.getCode().equals(IMSendCode.SUCCESS)) {
      String key =
          RedisKey.IM_GROUP_READED_POSITION + messageInfo.getGroupId() + ":" + result.getRecvId();
      redisTemplate.opsForValue().set(key, messageInfo.getId());
    }
  }

}
