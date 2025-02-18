package sl.chat.dev.connector.remote.netty.tcp.endecode;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import sl.chat.dev.common.core.model.IMSendInfo;

public class MessageProtocolEncoder extends MessageToByteEncoder<IMSendInfo> {

  @Override
  protected void encode(ChannelHandlerContext channelHandlerContext, IMSendInfo sendInfo,
      ByteBuf byteBuf) throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    String content = objectMapper.writeValueAsString(sendInfo);
    byte[] bytes = content.getBytes("UTF-8");
    // 写入长度
    byteBuf.writeLong(bytes.length);
    // 写入命令体
    byteBuf.writeBytes(bytes);
  }

}
