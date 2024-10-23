package sl.chat.dev.server.controller;

import sl.chat.dev.common.core.utils.Result;
import sl.chat.dev.common.core.utils.ResultUtils;
import sl.chat.dev.server.aop.annotation.AnonymousUserCheck;
import sl.chat.dev.server.common.entity.User;
import sl.chat.dev.server.common.vo.user.UserVO;
import sl.chat.dev.server.service.IUserService;
import sl.chat.dev.server.util.BeanUtils;
import sl.chat.dev.server.util.SessionContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Api(tags = "用户")
@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private IUserService userService;

  @GetMapping("/online")
  @ApiOperation(value = "判断用户是否在线", notes = "返回在线的用户id集合")
  public Result checkOnline(@NotEmpty @RequestParam("userIds") String userIds) {
    List<Long> onlineIds = userService.checkOnline(userIds);
    return ResultUtils.success(onlineIds);
  }

  @GetMapping("/self")
  @ApiOperation(value = "获取当前用户信息", notes = "获取当前用户信息")
  public Result findSelfInfo() {
    SessionContext.UserSessionInfo session = SessionContext.getSession();
    User user = userService.getById(session.getId());
    UserVO userVO = BeanUtils.copyProperties(user, UserVO.class);
    return ResultUtils.success(userVO);
  }

  @GetMapping("/find/{id}")
  @ApiOperation(value = "查找用户", notes = "根据id查找用户")
  public Result findByIde(@NotEmpty @PathVariable("id") long id) {

    UserVO userVO = userService.findByIde(id);
    return ResultUtils.success(userVO);
  }

  @PutMapping("/update")
  @ApiOperation(value = "修改用户信息", notes = "修改用户信息，仅允许修改登录用户信息")
  public Result update(@Valid @RequestBody UserVO vo) {
    userService.update(vo);
    return ResultUtils.success();
  }

  @AnonymousUserCheck
  @GetMapping("/findByCode")
  @ApiOperation(value = "查找用户", notes = "根据速聊号查找用户")
  public Result findByNickName(
      @NotEmpty(message = "速聊号不可为空") @RequestParam("userChatCode") String userChatCode) {
    return ResultUtils.success(userService.findUserByCode(userChatCode));
  }
}
