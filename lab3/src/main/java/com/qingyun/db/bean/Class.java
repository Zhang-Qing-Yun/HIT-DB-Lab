package com.qingyun.db.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 張青云
 * @since 2022-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Class implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 班级id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 班主任id
     */
    private Integer tid;

    /**
     * 院系id
     */
    private Integer departmentId;


}
