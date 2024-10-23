package sl.chat.dev.server.core.algorithm;

import sl.chat.dev.common.core.enums.NetProtocolEnum;
import sl.chat.dev.server.core.NodeContainer;

import java.util.List;

/**
 * Function:
 */
public interface RouteHandle {

  /**
   * 在一批服务器里进行路由
   *
   * @param values
   * @param key
   * @return
   */
  NodeContainer.WNode routeServer(NetProtocolEnum protocolEnum, List<NodeContainer.WNode> values,
                                  String key);
}
