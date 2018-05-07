package top.oahnus.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by oahnus on 2018/2/27
 * 11:46.
 */
@Data
@Entity(name = "category")
public class Category {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long parentId;
    private int heat;
    private Date createTime;
    private Boolean delFlag;
}
