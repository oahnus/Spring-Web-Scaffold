package top.oahnus.common.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by oahnus on 2018/2/11
 * 9:15.
 */
public class DateCustomeSerializer extends JsonSerializer<Date> {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static {
        DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        DATETIME_FORMAT.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
    }

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        String dateStr = date == null ? "" : DATETIME_FORMAT.format(date);
        jsonGenerator.writeString(dateStr);
    }
}
