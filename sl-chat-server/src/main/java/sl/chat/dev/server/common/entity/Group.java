package sl.chat.dev.server.common.entity;

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
 * 群
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("im_group")
public class Group extends Model<Group> {

  private static final long serialVersionUID = 1L;

  /**
   * id
   */
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /**
   * 群名字
   */
  @TableField("name")
  private String name;

  /**
   * 群主id
   */
  @TableField("owner_id")
  private Long ownerId;

  /**
   * 头像
   */
  @TableField("head_image")
  private String headImage;

  /**
   * 头像缩略图
   */
  @TableField("head_image_thumb")
  private String headImageThumb;

  /**
   * 群公告
   */
  @TableField("notice")
  private String notice;

  /**
   * 是否已删除:1已删除，0否
   */
  @TableField("deleted")
  private Integer deleted;

  /**
   * 创建时间
   */
  @TableField("created_time")
  private Date createdTime;

  @TableField("group_type")
  private Integer groupType;

  @Override
  protected Serializable pkVal() {
    return this.id;
  }

}
