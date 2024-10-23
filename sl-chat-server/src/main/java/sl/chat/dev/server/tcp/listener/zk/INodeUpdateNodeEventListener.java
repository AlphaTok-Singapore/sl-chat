package sl.chat.dev.server.tcp.listener.zk;

import sl.chat.dev.common.core.enums.NetProtocolEnum;
import sl.chat.dev.server.core.NodeContainer;

import java.util.Collection;

/**
 * @author muxiaohui
 * @date 2023-07-03 21:19 长连接节点发送变化事件
 */
public interface INodeUpdateNodeEventListener {

  default void delete(NetProtocolEnum netProtocolEnum, NodeContainer.WNode node) {

  }

  default void add(NetProtocolEnum netProtocolEnum, Collection<NodeContainer.WNode> node) {

  }

  default void list(NetProtocolEnum netProtocolEnum, Collection<NodeContainer.WNode> nodes) {

  }

}
