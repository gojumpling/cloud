package com.code.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaoshuai
 * @since 2022-11-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("itemSet")
public class ItemSet implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "itemSet_id", type = IdType.AUTO)
    private Integer itemsetId;

    @TableField("itemSet_title")
    private String itemsetTitle;

    @TableField("itemSet_grade")
    private Integer itemsetGrade;

    @TableField("itemSet_type")
    private String itemsetType;

    @TableField("itemSet_notice")
    private String itemsetNotice;

    @TableField("itemSet_startTime")
    private Date itemsetStarttime;

    @TableField("itemSet_endTIme")
    private Date itemsetEndtime;

    private Integer teacherId;


}
