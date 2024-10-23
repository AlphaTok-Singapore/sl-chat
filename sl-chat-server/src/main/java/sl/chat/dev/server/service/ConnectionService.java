package sl.chat.dev.server.service;

import sl.chat.dev.common.core.enums.NetProtocolEnum;
import sl.chat.dev.server.common.vo.connector.NodeInfoResp;

import java.util.List;

/**
 * @author muxiaohui
 * @project muchat
 * @date 2021/7/24
 * @description Good Good Study,Day Day Up.
 */
public interface ConnectionService {

  List<NodeInfoResp> nodeList();

  NodeInfoResp node(NetProtocolEnum netProtocolEnum, Long identify);
}
