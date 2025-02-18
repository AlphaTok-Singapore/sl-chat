package sl.chat.dev.server.controller;

import sl.chat.dev.common.core.model.PageResp;
import sl.chat.dev.common.core.utils.Result;
import sl.chat.dev.common.core.utils.ResultUtils;
import sl.chat.dev.common.log.annotation.ApiLog;
import sl.chat.dev.server.aop.annotation.AnonymousUserCheck;
import sl.chat.dev.server.common.vo.group.GroupMemberQueryReq;
import sl.chat.dev.server.common.vo.user.GroupInviteReq;
import sl.chat.dev.server.common.vo.user.GroupMemberResp;
import sl.chat.dev.server.common.vo.user.GroupVO;
import sl.chat.dev.server.service.IGroupMemberService;
import sl.chat.dev.server.service.IGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Api(tags = "群聊")
@RestController
@RequestMapping("/group")
public class GroupController {

  @Autowired
  private IGroupService groupService;

  @Autowired
  private IGroupMemberService iGroupMemberService;

  @AnonymousUserCheck
  @ApiOperation(value = "创建群聊", notes = "创建群聊")
  @PostMapping("/create")
  public Result<GroupVO> createGroup(
      @NotEmpty(message = "群名不能为空") @RequestParam String groupName) {
    return ResultUtils.success(groupService.createGroup(groupName));
  }

  @AnonymousUserCheck
  @ApiOperation(value = "修改群聊信息", notes = "修改群聊信息")
  @PutMapping("/modify")
  public Result<GroupVO> modifyGroup(@Valid @RequestBody GroupVO vo) {
    return ResultUtils.success(groupService.modifyGroup(vo));
  }

  @AnonymousUserCheck
  @ApiOperation(value = "解散群聊", notes = "解散群聊")
  @DeleteMapping("/delete/{groupId}")
  public Result deleteGroup(@NotNull(message = "群聊id不能为空") @PathVariable Long groupId) {
    groupService.deleteGroup(groupId);
    return ResultUtils.success();
  }

  @ApiOperation(value = "查询群聊", notes = "查询单个群聊信息")
  @GetMapping("/find/{groupId}")
  public Result<GroupVO> findGroup(
      @NotNull(message = "群聊id不能为空") @PathVariable Long groupId) {
    return ResultUtils.success(groupService.findById(groupId));
  }

  @ApiOperation(value = "查询群聊列表", notes = "查询群聊列表")
  @GetMapping("/list")
  public Result<List<GroupVO>> findGroups() {
    return ResultUtils.success(groupService.findGroups());
  }

  @AnonymousUserCheck
  @ApiOperation(value = "邀请进群", notes = "邀请好友进群")
  @PostMapping("/invite")
  public Result invite(@Valid @RequestBody GroupInviteReq vo) {
    groupService.invite(vo);
    return ResultUtils.success();
  }

  @ApiLog
  @ApiOperation(value = "查询群聊成员", notes = "查询群聊成员")
  @GetMapping("/members/{groupId}")
  public Result<List<GroupMemberResp>> findGroupMembers(
      @NotNull(message = "群聊id不能为空") @PathVariable Long groupId) {
    return ResultUtils.success(groupService.findGroupMembers(groupId));
  }

  @ApiLog
  @ApiOperation(value = "查询群聊成员", notes = "查询群聊成员")
  @GetMapping("/membersV2")
  public Result<PageResp<GroupMemberResp>> findGroupMembersV2(@Valid GroupMemberQueryReq req) {
    return ResultUtils.success(iGroupMemberService.findGroupMembersV2(req));
  }

  @AnonymousUserCheck
  @ApiOperation(value = "退出群聊", notes = "退出群聊")
  @DeleteMapping("/quit/{groupId}")
  public Result quitGroup(@NotNull(message = "群聊id不能为空") @PathVariable Long groupId) {
    groupService.quitGroup(groupId);
    return ResultUtils.success();
  }

  @AnonymousUserCheck
  @ApiOperation(value = "踢出群聊", notes = "将用户踢出群聊")
  @DeleteMapping("/kick/{groupId}")
  public Result kickGroup(@NotNull(message = "群聊id不能为空") @PathVariable Long groupId,
      @NotNull(message = "用户id不能为空") @RequestParam Long userId) {
    groupService.kickGroup(groupId, userId);
    return ResultUtils.success();
  }

}
