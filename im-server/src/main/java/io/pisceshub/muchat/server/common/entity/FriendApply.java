package io.pisceshub.muchat.server.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 好友申请
 * @author muxiaohui
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("im_friend_apply")
public class FriendApply extends Model<FriendApply> {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 好友id
     */
    @TableField("friend_id")
    private Long friendId;

    /**
     * 是否通过好友申请  0：否、1：是
     */
    @TableField("add_status")
    private Integer addStatus;

    /**
     * 是否删除  0：否、1：是
     */
    @TableField("is_delete")
    private Integer isDelete;

    /**
     * 创建时间
     */
    @TableField("created_time")
    private Date createdTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
