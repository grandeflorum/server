package com.grandeflorum.common.util;

import com.grandeflorum.common.config.GrandeflorumProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by 13260 on 2019/10/22.
 */

@Component
public class MyApplicationRunner  implements ApplicationRunner {

    @Autowired
    GrandeflorumProperties grandeflorumProperties;

    @Override
    public void run(ApplicationArguments var1) throws Exception{

//        office2PDF.startCommand(rainbowProperties.getOpenoffice());
    }
}
