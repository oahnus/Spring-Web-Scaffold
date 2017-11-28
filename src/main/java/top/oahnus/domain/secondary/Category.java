package top.oahnus.domain.secondary;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by oahnus on 2017/11/28
 * 12:04.
 */
@Entity(name = "category")
@Data
public class Category {
    @Id
    private Long id;

    private String name;
}
