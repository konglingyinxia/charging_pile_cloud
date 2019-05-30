package com.suda.platform.VO.stockuser;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author kongling
 * @package com.suda.platform.VO.stockuser
 * @date 2019-05-18  11:10
 * @project suda_cloud
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AppEditStockUser {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long stockUserId;

    @ApiModelProperty(value = "用户昵称")
    @TableField("nickname")
    private String nickname;


    @ApiModelProperty(value = "头像")
    @TableField("head_url")
    private String headUrl;
}
