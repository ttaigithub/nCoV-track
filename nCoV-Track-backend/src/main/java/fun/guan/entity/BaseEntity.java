package fun.guan.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
public class BaseEntity {
    /**
     * 主键id
     */
    @Id
    @Column(name = "record_id")
    @GeneratedValue(generator = "JDBC")
    private Long recordId;

    /**
     * 删除标识
     */
    @Column(name = "is_delete")
    private Boolean isDelete;

    /**
     * 创建时间
     */
    @Column(name = "gmt_create")
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @Column(name = "gmt_modified")
    private Date gmtModified;
}
