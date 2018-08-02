package top.oahnus.domain.secondary;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by oahnus on 2017/11/28
 * 12:04.
 */
@Table(name = "category")
@Data
public class Category {
    @Id
    private Long id;

    private String name;
}
