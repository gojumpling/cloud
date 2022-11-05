package com.code.pojo;

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
 * @since 2022-11-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Itemset implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "itemset_id", type = IdType.AUTO)
    private long itemsetId;

    private String itemsetTitle;

    private long itemsetGrade;

    private String itemsetType;

    private String itemsetNotice;

    @TableField("itemset_startTime")
    private Date itemsetStarttime;

    @TableField("itemset_endTIme")
    private Date itemsetEndtime;

    private String userNo;


}
