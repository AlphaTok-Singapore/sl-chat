package sl.chat.dev.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sl.chat.dev.server.common.entity.Friend;
import sl.chat.dev.server.common.vo.user.FriendVO;

import java.util.List;

public interface IFriendService extends IService<Friend> {

  Boolean isFriend(Long userId1, Long userId2);

  List<Friend> findFriendByUserId(Long UserId);

  void addFriend(Long friendId);

  void delFriend(Long friendId);

  void update(FriendVO vo);

  FriendVO findFriend(Long friendId);

  List<FriendVO> findFriendApply(Long friendId);

  void verifyFriends(Long friendId, Integer audit);
}
