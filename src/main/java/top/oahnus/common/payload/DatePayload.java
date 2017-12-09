package top.oahnus.common.payload;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import top.oahnus.common.config.date.DateJsonDeserializer;
import top.oahnus.common.config.date.DateJsonSerializer;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by oahnus on 2017/12/8
 * 14:01.
 */
@Data
public class DatePayload {
    @NotEmpty(message = "field can not be blank")
    private String otherField;

    @NotNull(message = "date can not be null")
    @JsonSerialize(using = DateJsonSerializer.class)
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private Date createAt;
}
