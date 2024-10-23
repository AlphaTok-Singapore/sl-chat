package sl.chat.dev.server.controller;

import sl.chat.dev.common.core.utils.Result;
import sl.chat.dev.common.core.utils.ResultUtils;
import sl.chat.dev.common.log.annotation.ApiLog;
import sl.chat.dev.server.aop.annotation.AnonymousUserCheck;
import sl.chat.dev.server.common.vo.user.ChatSessionAddReq;
import sl.chat.dev.server.common.vo.user.ChatSessionInfoResp;
import sl.chat.dev.server.common.vo.user.ChatSessionUpdateReq;
import sl.chat.dev.server.service.IChatSessionService;
import sl.chat.dev.server.util.SessionContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

/**
 * @author muxiaohui
 * @date 2023-06-15 21:43
 */
@Api(tags = "聊天会话")
@RestController
@RequestMapping("/chatSession")
public class ChatSessionController {

  @Autowired
  private IChatSessionService iChatSessionService;

  /**
   * 保存聊天会话
   *
   * @param vo
   * @return
   */
  @ApiLog
  @PostMapping("/save")
  @ApiOperation(value = "保存聊天会话", notes = "保存聊天会话")
  public Result<String> save(@RequestBody @Valid ChatSessionAddReq vo) {
    return iChatSessionService.save(SessionContext.getUserId(), vo) ? ResultUtils.success()
        : ResultUtils.error();
  }

  /**
   * 查询聊天会话
   *
   * @return
   */
  @ApiLog
  @GetMapping("/list")
  @ApiOperation(value = "查询聊天会话", notes = "查询聊天会话")
  public Result<Set<ChatSessionInfoResp>> pages() {
    return iChatSessionService.list();
  }

  /**
   * 删除聊天会话
   *
   * @param vo
   * @return
   */
  @ApiLog
  @AnonymousUserCheck
  @DeleteMapping("/del")
  @ApiOperation(value = "删除聊天会话", notes = "删除聊天会话")
  public Result<String> del(@RequestBody @Valid ChatSessionUpdateReq vo) {
    vo.setUserId(null);
    return iChatSessionService.del(vo) ? ResultUtils.success() : ResultUtils.error();
  }
}
