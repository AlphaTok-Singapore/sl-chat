package sl.chat.dev.connector.processor;

import io.netty.channel.ChannelHandlerContext;
import sl.chat.dev.common.core.enums.IMCmdType;
import sl.chat.dev.common.core.model.IMSendInfo;
import sl.chat.dev.connector.utils.SendMessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HeartbeatProcessor implements MessageProcessor {

  @Override
  public void process(ChannelHandlerContext ctx, Object obj) {
    // 响应ws
    SendMessageUtils.send(ctx, IMSendInfo.create(IMCmdType.HEART_BEAT));
  }

}
