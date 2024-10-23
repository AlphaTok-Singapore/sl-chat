package sl.chat.dev.server.service.impl;

import cn.hutool.core.bean.BeanUtil;
import sl.chat.dev.common.core.enums.NetProtocolEnum;
import sl.chat.dev.server.common.vo.connector.NodeInfoResp;
import sl.chat.dev.server.core.NodeContainer;
import sl.chat.dev.server.core.algorithm.RouteHandle;
import sl.chat.dev.server.service.ConnectionService;
import sl.chat.dev.server.util.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author muxiaohui
 * @project muchat
 * @date 2021/7/24
 * @description Good Good Study,Day Day Up.
 */
@Service
public class ConnectionServiceImpl implements ConnectionService {

  @Autowired
  private RouteHandle routeHandle;

  @Autowired
  private NodeContainer nodeContainer;

  @Override
  public List<NodeInfoResp> nodeList() {
    return nodeContainer.list(null).stream().map(e -> {
      NodeInfoResp nodeInfoResp = BeanUtil.copyProperties(e, NodeInfoResp.class);
      nodeInfoResp.setProtocol(e.getProtocolEnum().name());
      return nodeInfoResp;
    }).collect(Collectors.toList());
  }

  @Override
  public NodeInfoResp node(NetProtocolEnum netProtocolEnum, Long identify) {
    if (identify == null) {
      identify = Long.valueOf(IpUtil.getIntIp());
    }
    List<NodeContainer.WNode> nodes = nodeContainer.list(netProtocolEnum);
    NodeContainer.WNode wNode = routeHandle.routeServer(netProtocolEnum, nodes,
        String.valueOf(identify));
    if (wNode == null) {
      return null;
    }
    NodeInfoResp resp = new NodeInfoResp();
    resp.setProtocol(wNode.getProtocolEnum().name());
    resp.setIp(wNode.getIp());
    resp.setPort(wNode.getPort());
    return resp;

  }
}
