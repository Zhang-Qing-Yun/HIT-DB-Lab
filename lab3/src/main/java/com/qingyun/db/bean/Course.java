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
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课程id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 课程名
     */
    private String cname;

    /**
     * 讲授该课程的教师id
     */
    private Integer tid;


}
