package sl.chat.dev.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import sl.chat.dev.common.core.enums.ChatType;
import sl.chat.dev.common.core.enums.ResultCode;
import sl.chat.dev.server.common.contant.RedisKey;
import sl.chat.dev.server.common.entity.Friend;
import sl.chat.dev.server.common.entity.FriendApply;
import sl.chat.dev.server.common.entity.User;
import sl.chat.dev.server.common.vo.user.ChatSessionUpdateReq;
import sl.chat.dev.server.common.vo.user.FriendVO;
import sl.chat.dev.server.exception.GlobalException;
import sl.chat.dev.server.mapper.FriendApplyMapper;
import sl.chat.dev.server.mapper.FriendMapper;
import sl.chat.dev.server.mapper.UserMapper;
import sl.chat.dev.server.service.IChatSessionService;
import sl.chat.dev.server.service.IFriendService;
import sl.chat.dev.server.service.IUserService;
import sl.chat.dev.server.util.SessionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author muxiaohui
 */
@Slf4j
@CacheConfig(cacheNames = RedisKey.IM_CACHE_FRIEND)
@Service
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend> implements IFriendService {

    @Lazy
    @Autowired
    private IUserService userService;

    @Autowired
    private IChatSessionService iChatSessionService;

    @Autowired
    private FriendApplyMapper friendApplyMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询用户的所有好友
     *
     * @param UserId 用户id
     * @return
     */
    @Override
    public List<Friend> findFriendByUserId(Long UserId) {
        QueryWrapper<Friend> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(Friend::getUserId, UserId);
        List<Friend> friends = this.list(queryWrapper);
        return friends;
    }

    /**
     * 添加好友，互相建立好友关系
     *
     * @param friendId 好友的用户id
     * @return
     */
    @Transactional
    @Override
    public void addFriend(Long friendId) {
        long userId = SessionContext.getSession().getId();
        if (userId == friendId) {
            throw new GlobalException(ResultCode.PROGRAM_ERROR, "不允许添加自己为好友");
        }
//        //将用户申请好友信息写入好友申请表
//        FriendApply friendApply = new FriendApply();
//        friendApply.setUserId(userId);
//        friendApply.setFriendId(friendId);
//        friendApplyMapper.insert(friendApply);
        // 互相绑定好友关系
        FriendServiceImpl proxy = (FriendServiceImpl) AopContext.currentProxy();
        proxy.bindFriend(userId, friendId);
        proxy.bindFriend(friendId, userId);
        log.info("添加好友，用户id:{},好友id:{}", userId, friendId);
    }

    /**
     * 删除好友，双方都会解除好友关系
     *
     * @param friendId 好友的用户id
     * @return
     */
    @Transactional
    @Override
    public void delFriend(Long friendId) {
        long userId = SessionContext.getSession().getId();
        // 互相解除好友关系
        FriendServiceImpl proxy = (FriendServiceImpl) AopContext.currentProxy();
        proxy.unbindFriend(userId, friendId);
        proxy.unbindFriend(friendId, userId);
        iChatSessionService.del(
                ChatSessionUpdateReq.builder().chatType(ChatType.PRIVATE).targetId(friendId).build());
        log.info("删除好友，用户id:{},好友id:{}", userId, friendId);
    }

    /**
     * 判断用户2是否用户1的好友
     *
     * @param userId1 用户1的id
     * @param userId2 用户2的id
     * @return
     */
    @Override
    public Boolean isFriend(Long userId1, Long userId2) {
        QueryWrapper<Friend> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Friend::getUserId, userId1).eq(Friend::getFriendId, userId2);
        return this.count(queryWrapper) > 0;
    }

    /**
     * 更新好友信息，主要是头像和昵称
     *
     * @param vo 好友vo
     * @return
     */
    @Override
    public void update(FriendVO vo) {
        long userId = SessionContext.getSession().getId();
        QueryWrapper<Friend> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Friend::getUserId, userId).eq(Friend::getFriendId, vo.getId());

        Friend f = this.getOne(queryWrapper);
        if (f == null) {
            throw new GlobalException(ResultCode.PROGRAM_ERROR, "对方不是您的好友");
        }

        f.setFriendHeadImage(vo.getHeadImage());
        f.setFriendNickName(vo.getNickName());
        this.updateById(f);
    }

    /**
     * 单向绑定好友关系
     *
     * @param userId   用户id
     * @param friendId 好友的用户id
     * @return
     */
    public void bindFriend(Long userId, Long friendId) {
        QueryWrapper<Friend> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Friend::getUserId, userId).eq(Friend::getFriendId, friendId);
        if (this.count(queryWrapper) == 0) {
            Friend friend = new Friend();
            friend.setUserId(userId);
            friend.setFriendId(friendId);
            User friendInfo = userService.getById(friendId);
            friend.setFriendHeadImage(friendInfo.getHeadImage());
            friend.setFriendNickName(friendInfo.getNickName());
            this.save(friend);
        }
    }

    /**
     * 单向解除好友关系
     *
     * @param userId   用户id
     * @param friendId 好友的用户id
     * @return
     */
    public void unbindFriend(Long userId, Long friendId) {
        QueryWrapper<Friend> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Friend::getUserId, userId).eq(Friend::getFriendId, friendId);
        List<Friend> friends = this.list(queryWrapper);
        friends.stream().forEach(friend -> {
            this.removeById(friend.getId());
        });
    }

    /**
     * 查询指定的某个好友信息
     *
     * @param friendId 好友的用户id
     * @return
     */
    @Override
    public FriendVO findFriend(Long friendId) {
        SessionContext.UserSessionInfo session = SessionContext.getSession();
        QueryWrapper<Friend> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Friend::getUserId, session.getId()).eq(Friend::getFriendId, friendId);
        Friend friend = this.getOne(wrapper);
        if (friend == null) {
            throw new GlobalException(ResultCode.PROGRAM_ERROR, "对方不是您的好友");
        }
        FriendVO vo = new FriendVO();
        vo.setId(friend.getFriendId());
        vo.setHeadImage(friend.getFriendHeadImage());
        vo.setNickName(friend.getFriendNickName());
        return vo;
    }

    @Override
    public List<FriendVO> findFriendApply(Long friendId) {
        List<FriendVO> friendVOList = new ArrayList<>();
        LambdaQueryWrapper<FriendApply> friendApplyLambdaQueryWrapper = Wrappers.lambdaQuery();
        friendApplyLambdaQueryWrapper.eq(null != friendId, FriendApply::getFriendId, friendId);
        friendApplyLambdaQueryWrapper.eq(FriendApply::getAddStatus, 0);
        friendApplyLambdaQueryWrapper.eq(FriendApply::getIsDelete, 0);
        List<FriendApply> friendApplies = friendApplyMapper.selectList(friendApplyLambdaQueryWrapper);
        if (CollectionUtils.isNotEmpty(friendApplies)) {
            //提取用户ID
            List<Long> userIdList = friendApplies.stream().map(FriendApply -> FriendApply.getUserId()).distinct().collect(Collectors.toList());
            //查询好友信息
            LambdaQueryWrapper<User> userLambdaQueryWrapper = Wrappers.lambdaQuery();
            userLambdaQueryWrapper.in(CollectionUtils.isNotEmpty(userIdList), User::getId, userIdList);
            List<User> userList = userMapper.selectList(userLambdaQueryWrapper);
            if (CollectionUtils.isNotEmpty(userList)) {
                userList.stream().forEach(user -> {
                    FriendVO friendVO = new FriendVO();
                    friendVO.setId(user.getId());
                    friendVO.setNickName(user.getNickName());
                    friendVO.setHeadImage(user.getHeadImage());
                    friendVOList.add(friendVO);
                });
                return friendVOList;
            }
        }
        return null;
    }

    @Override
    public void verifyFriends(Long friendId, Integer audit) {
        log.info("验证好友，好友id:{}，状态:{}", friendId, audit);
        long userId = SessionContext.getSession().getId();
        if (userId == friendId) {
            throw new GlobalException(ResultCode.PROGRAM_ERROR, "不允许添加自己为好友");
        }
        FriendApply friendApply = new FriendApply();
        //通过
        if (audit == 1) {
            //互相绑定好友关系
            FriendServiceImpl proxy = (FriendServiceImpl) AopContext.currentProxy();
            proxy.bindFriend(userId, friendId);
            proxy.bindFriend(friendId, userId);
            log.info("添加好友，用户id:{},好友id:{}", userId, friendId);
            LambdaUpdateWrapper<FriendApply> updateWrapper = Wrappers.lambdaUpdate();
            updateWrapper.eq(FriendApply::getUserId, userId);
            updateWrapper.eq(FriendApply::getFriendId, friendId);
            updateWrapper.eq(FriendApply::getIsDelete, 0);
            friendApply.setAddStatus(1);
            this.friendApplyMapper.update(friendApply, updateWrapper);
        }
        //拒绝
        if (audit == 2){
            LambdaUpdateWrapper<FriendApply> updateWrapper = Wrappers.lambdaUpdate();
            updateWrapper.eq(FriendApply::getUserId, userId);
            updateWrapper.eq(FriendApply::getFriendId, friendId);
            friendApply.setIsDelete(1);
            this.friendApplyMapper.update(friendApply, updateWrapper);
        }

    }
}
