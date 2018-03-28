package top.oahnus.controller.pageForm;

import lombok.Data;

import java.util.Date;

/**
 * Created by oahnus on 2018/2/27
 * 11:49.
 */
@Data
public class CategoryForm {
    private String name;
    private Long parentId;
    private Date createTime;
    private PageForm page;
}
