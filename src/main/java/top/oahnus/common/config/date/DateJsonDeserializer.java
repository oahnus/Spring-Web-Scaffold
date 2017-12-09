package top.oahnus.common.config.date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import top.oahnus.common.exception.RequestParamInvalidException;
import top.oahnus.interfaces.LoggerMixin;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by oahnus on 2017/12/8
 * 13:58.
 */
public class DateJsonDeserializer extends JsonDeserializer<Date> implements LoggerMixin {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        try {
            return FORMAT.parse(jsonParser.getText());
        } catch (ParseException e) {
            logger().error(e.getMessage());
            throw new RequestParamInvalidException(e.getMessage());
        }
    }
}
