package sl.chat.dev.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sl.chat.dev.common.core.model.PageResp;
import sl.chat.dev.server.common.entity.GroupMember;
import sl.chat.dev.server.common.entity.User;
import sl.chat.dev.server.common.vo.group.GroupMemberQueryReq;
import sl.chat.dev.server.common.vo.user.GroupMemberResp;

import java.util.List;

public interface IGroupMemberService extends IService<GroupMember> {

  GroupMember findByGroupAndUserId(Long groupId, Long userId);

  List<GroupMember> findByUserId(Long userId);

  List<GroupMember> findByGroupId(Long groupId);

  List<Long> findUserIdsByGroupId(Long groupId);

  boolean save(GroupMember member);

  boolean saveOrUpdateBatch(Long groupId, List<GroupMember> members);

  void removeByGroupId(Long groupId);

  void removeByGroupAndUserId(Long groupId, Long userId);

  boolean joinGroup(Long groupId, String remark, User user);

  boolean memberExsit(Long userId, Long targetId);

  PageResp<GroupMemberResp> findGroupMembersV2(GroupMemberQueryReq req);

  Integer findMemberCount(Long groupId);
}
