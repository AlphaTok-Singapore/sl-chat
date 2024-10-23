package sl.chat.dev.common.core.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author muxiaohui
 * @date 2023-07-08 11:30
 */
@Data
public class PageReq implements Serializable {

  private Long pageNo;

  private Long pageSize;

}
