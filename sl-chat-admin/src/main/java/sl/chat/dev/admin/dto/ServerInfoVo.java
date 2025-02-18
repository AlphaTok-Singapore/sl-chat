package sl.chat.dev.admin.dto;

import lombok.Data;

import java.util.List;

@Data
public class ServerInfoVo {

  private Integer serverCount;

  // 详情
  private List<ServerConnectionInfoItemVo> items;

}
