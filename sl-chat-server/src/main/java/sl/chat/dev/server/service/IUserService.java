package sl.chat.dev.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sl.chat.dev.server.common.entity.User;
import me.zhyd.oauth.model.AuthUser;
import sl.chat.dev.server.common.vo.user.*;

import java.util.List;

public interface IUserService extends IService<User> {

  LoginResp login(LoginReq dto);

  LoginResp refreshToken(String refreshToken);

  void register(RegisterReq dto);

  User findUserByName(String username);

  void update(UserVO vo);

  List<UserVO> findUserByNickName(String nickname);

  List<UserVO> findUserByCode(String userChatCode);

  List<Long> checkOnline(String userIds);

  UserVO findByUserIdAndFriendId(Long userId, Long friendId);

  LoginResp oauthLogin(String type, AuthUser authUser);

  LoginResp anonymousLogin(AnonymousLoginReq req);

  UserVO findByIde(long id);

  /**
   * 判断用户是否在线
   *
   * @param userId
   * @return
   */
  boolean isOnline(Long userId);
}
