package top.oahnus.common.config.date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by oahnus on 2018/2/11
 * 9:15.
 */
public class DateCustomDeserializer extends JsonDeserializer<Date> {
    private Logger log = LoggerFactory.getLogger(getClass());

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static {
        DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        DATETIME_FORMAT.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
    }

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String text = jsonParser.getText();
        if (StringUtils.isEmpty(text)) {
            return null;
        }

        try {
            Long time = Long.valueOf(text);
            return new Date(time);
        } catch (NumberFormatException e) {
            log.debug("NumberFormat Error {}", e.getMessage());
        }

        try {
            if (text.contains(":")) {
                return DATETIME_FORMAT.parse(text);
            } else {
                return DATE_FORMAT.parse(text);
            }
        } catch (ParseException e) {
            log.debug("DateFormat Parse Error {}", e.getMessage());
        }
        log.error("Date Json Deserialize Error For Text: {}", text);
        return null;
    }
}
