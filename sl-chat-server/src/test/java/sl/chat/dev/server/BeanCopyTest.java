package sl.chat.dev.server;

import cn.hutool.core.bean.BeanUtil;
import sl.chat.dev.common.core.model.GroupMessageInfo;
import sl.chat.dev.server.common.entity.GroupMessage;

/**
 * @date 2023-07-02 22:37
 */
public class BeanCopyTest {

  public static void main(String[] args) {
    GroupMessage groupMessage = new GroupMessage();
    groupMessage.setGroupId(1L);
    groupMessage.setContent("aaa");

    GroupMessageInfo groupMessageInfo = BeanUtil.copyProperties(groupMessage,
        GroupMessageInfo.class);
    System.out.println(groupMessageInfo.getGroupId());
    System.out.println(groupMessageInfo.getContent());
  }
}
