package top.oahnus.domain.mongodb;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
 * Created by oahnus on 2018/8/8
 * 11:34.
 */
@Data
@Document(collection = "question")
public class Question {
    @Id
    @GeneratedValue
    private String id;
    private String title;
    private String content;
    private List<String> tags;
    private Date creDate;
    private Date uptDate;
}
