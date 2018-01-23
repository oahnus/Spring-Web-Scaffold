package top.oahnus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.Yaml;
import top.oahnus.common.config.OahnusConfig;
import top.oahnus.controller.mixin.ControllerMixIn;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

/**
 * Created by oahnus on 2018/1/20
 * 17:02.
 */
@RestController
@RequestMapping("/config")
public class RefreshApplicationConfigTestController extends ControllerMixIn{

    @Autowired
    private OahnusConfig oahnusConfig;

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${spring.profiles.active}")
    private String env;

    @GetMapping("/name")
    public String configName() throws FileNotFoundException {
        return oahnusConfig.getName();
    }

    @GetMapping("/refresh")
    public String refresh() throws IOException {
        Yaml yaml = new Yaml();
        Resource resource = new ClassPathResource("application-" + env + ".yaml");
        File file = resource.getFile();
        System.out.println(file.getAbsolutePath());

        Map map = yaml.loadAs(new FileInputStream(file), Map.class);

        System.out.println(map.get("oahnus"));
        System.out.println(((Map)((Map)map.get("oahnus")).get("config")).get("couponIds"));
        String configName = ((Map)((Map)map.get("oahnus")).get("config")).get("name").toString();

        oahnusConfig.setName(configName);
        return "success";
    }
}
