package top.oahnus.controller.open;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.oahnus.common.config.DateCustomDeserializer;
import top.oahnus.common.config.DateCustomeSerializer;

import java.util.Date;

/**
 * Created by oahnus on 2018/2/11
 * 17:45.
 */
@RestController
@RequestMapping("/date")
@Slf4j
public class DateController {

    @PostMapping
    public Form dateTest(@RequestBody Form form) {
        log.info("[Form] - {}", form);
        return form;
    }
}

class Form {
    @JsonSerialize(using = DateCustomeSerializer.class)
    @JsonDeserialize(using = DateCustomDeserializer.class)
    Date date;
}