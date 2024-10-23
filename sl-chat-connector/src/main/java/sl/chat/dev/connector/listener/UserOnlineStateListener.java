package sl.chat.dev.connector.listener;

import sl.chat.dev.common.cache.AppCache;
import sl.chat.dev.common.core.contant.RedisKey;
import sl.chat.dev.common.core.enums.IMCmdType;
import sl.chat.dev.common.core.model.IMSendInfo;
import sl.chat.dev.connector.listener.event.UserEvent;
import sl.chat.dev.connector.remote.IMServerGroup;
import sl.chat.dev.connector.utils.SendMessageUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author muxiaohui
 * @project muchat
 * @date 2021/7/23
 * @description Good Good Study,Day Day Up.
 */
@Component
@Slf4j
public class UserOnlineStateListener {

  @Resource
  private AppCache appCache;

  @SneakyThrows
  @EventListener(classes = UserEvent.class)
  public void onApplicationEvent(UserEvent event) {

    if (UserEvent.Event.ONLINE.equals(event.getEvent())) {
      String key = RedisKey.IM_USER_SERVER_ID + event.getUserId();
      appCache.put(key, IMServerGroup.serverId);
      // 响应ws
      SendMessageUtils.send(event.getCtx(), IMSendInfo.create(IMCmdType.LOGIN, "登录成功"));
    } else if (UserEvent.Event.OFFLINE.equals(event.getEvent())) {
      // 用户下线
      String key = RedisKey.IM_USER_SERVER_ID + event.getUserId();
      appCache.remove(key);
    }

  }

}
